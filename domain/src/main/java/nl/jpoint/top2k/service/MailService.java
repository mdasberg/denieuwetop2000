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

import nl.jpoint.top2k.domain.User;

import org.apache.commons.configuration.Configuration;

/** MailService implementation. */
public class MailService implements IMailService {
	private final String hostname;
	private int smtpPort;
	private String smtpHost;
	private String senderAddress;
	private String senderPassword;

	@Inject
	public MailService(final Configuration configuration) {
		smtpPort = configuration.getInt("smtp.port");
		smtpHost = configuration.getString("smtp.host");
		senderAddress = configuration.getString("sender.address");
		senderPassword = configuration.getString("sender.password");
		hostname = configuration.getString("hostname");
	}

	/** {@inheritDoc}. */
	@Override
	public void sendRegistrationMail(final User user) {
		final Properties props = new Properties();
		final Session session = Session.getDefaultInstance(props, null);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtps.auth", "true");

		try {
			final MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderAddress));
			final Address[] recipientAddresses = InternetAddress.parse(user
					.getEmail());
			msg.setRecipients(Message.RecipientType.TO, recipientAddresses);
			msg.setSubject("Registratie voltooien");
			msg.setContent(getRegisterBodyMessage(user), "text/html");
			final Transport transport = session.getTransport("smtps");
			transport
					.connect(smtpHost, smtpPort, senderAddress, senderPassword);

			transport.sendMessage(msg, recipientAddresses);
			transport.close();
		} catch (final MessagingException e) {
			e.printStackTrace();
		}
	}

	private String getRegisterBodyMessage(final User user) {
		final String finishURI = "<a href=\"" + hostname
				+ "rest/register/finish?email=" + user.getEmail()
				+ "&registrationCode=" + user.getPasswordHash() + "\">hier</a>";
		final String msgBody = "<html><body>Bedankt voor je inschrijving bij de nieuwe Top 2000.\n Klik "
				+ finishURI
				+ " zodat je registratie voltooid wordt.\n"
				+ "<br/><br/> Bedankt het Nieuwe top 2000 team.</body></html>";
		return msgBody;
	}
}
