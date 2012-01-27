package nl.jpoint.top2k.service;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.configuration.Configuration;

/** MailService implementation. */
public class MailService implements IMailService {
	private final int smtpPort;
	private final String smtpHost;
	private final String senderAddress;
	private final String senderPassword;

	@Inject
	public MailService(final Configuration configuration) {
		smtpPort = configuration.getInt("smtp.port");
		smtpHost = configuration.getString("smtp.host");
		senderAddress = configuration.getString("sender.address");
		senderPassword = configuration.getString("sender.password");
	}

	/** {@inheritDoc}. */
	@Override
	public void sendMail(final String email, final String subject,
			final String body) {
		final Properties props = new Properties();
		final Session session = Session.getDefaultInstance(props, null);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtps.auth", "true");

		try {
			final MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderAddress));
			final Address[] recipientAddresses = InternetAddress.parse(email);
			msg.setRecipients(Message.RecipientType.TO, recipientAddresses);
			msg.setSubject(subject);
			msg.setContent(body, "text/html");
			final Transport transport = session.getTransport("smtps");
			transport
					.connect(smtpHost, smtpPort, senderAddress, senderPassword);

			transport.sendMessage(msg, recipientAddresses);
			transport.close();
		} catch (final MessagingException e) {
			e.printStackTrace();
		}
	}
}
