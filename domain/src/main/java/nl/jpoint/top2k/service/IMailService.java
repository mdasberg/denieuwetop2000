package nl.jpoint.top2k.service;

import nl.jpoint.top2k.domain.User;

/** MailService interface. */
public interface IMailService {
	/**
	 * Sends a registration email to the given user.
	 * 
	 * @param user
	 *            The user to send a registration email.
	 */
	void sendRegistrationMail(User user);
}
