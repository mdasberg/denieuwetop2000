package nl.jpoint.top2k.service;

/** MailService interface. */
public interface IMailService {
    /**
     * Sends an email to the given email adres.
     * @param email   The email address to send to.
     * @param subject The subject.
     * @param body    The body.
     */
    void sendMail(final String email, final String subject, final String body);
}
