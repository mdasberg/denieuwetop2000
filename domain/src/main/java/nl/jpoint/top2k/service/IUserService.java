package nl.jpoint.top2k.service;

import java.util.List;

import nl.jpoint.top2k.domain.User;

/** UserService interface.  */
public interface IUserService {

	/**
	 * Creates the given user.
	 * 
	 * @param user
	 *            The user.
	 */
	void create(final User user);

	/**
	 * Gets all the users.
	 * 
	 * @return users The users.
	 */
	List<User> getAll();

	/**
	 * Indicates if the given credentials are valid.
	 * 
	 * @param email
	 *            The email address.
	 * @param password
	 *            The password.
	 * @return <code>true</code> if valide, else <code>false</code>.
	 */
	boolean isValidUserCredentials(final String email, final String password);

	/**
	 * Find an user by email
	 * 
	 * @param email
	 * @return user The found user
	 */
	User findUserByEmail(String email);
}
