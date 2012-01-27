package nl.jpoint.top2k.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import nl.jpoint.top2k.domain.User;

import com.google.inject.Provider;

/** User service. */
public class UserService implements IUserService {

	@Inject
	private Provider<EntityManager> provider;

	/** {@inheritDoc}. */
	@Override
	public void create(final User user) {
		provider.get().persist(user);
	}

	/** {@inheritDoc}. */
	@Override
	public List<User> getAll() {
		return provider.get().createNamedQuery("User.findAll").getResultList();
	}

	/** {@inheritDoc}. */
	@Override
	public boolean isValidUserCredentials(final String email,
			final String password) {
		final User user = findUserByEmail(email);
		final boolean valid;
		if (user == null) {
			valid = false;
		} else {
			valid = user.validatePassword(password);
		}
		return valid;
	}

	@Override
	public User findUserByEmail(final String email) {
		return (User) provider.get().createNamedQuery("User.byEmail")
				.setParameter("email", email).getSingleResult();
	}
}
