package nl.jpoint.top2k.service;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	
	private static final int SMTP_PORT = 465;
	private static final String SMTP_HOST = "pop.gmail.com";
	private static final String SENDER_ADDRESS = "newTop2k@gmail.com";
	private static final String SENDER_PASSWORD = "n3wT0p2k";

	public void sendMail(final String email, final String subject, final String body) {
        final Properties props = new Properties();
        final Session session = Session.getDefaultInstance(props, null);
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtps.auth", "true");

        try {
            final Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SENDER_ADDRESS));
            final Address[] recipientAddresses = InternetAddress.parse(email);
			msg.setRecipients(Message.RecipientType.TO,
					recipientAddresses);
            msg.setSubject(subject);
            msg.setText(body);
            final Transport transport = session.getTransport("smtps");
            transport.connect(SMTP_HOST, SMTP_PORT , SENDER_ADDRESS, SENDER_PASSWORD);

            transport.sendMessage(msg, recipientAddresses);
            transport.close();
		} catch (final MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
}
