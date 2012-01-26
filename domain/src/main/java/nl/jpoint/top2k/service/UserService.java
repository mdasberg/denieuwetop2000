package nl.jpoint.top2k.service;

import com.google.inject.Provider;
import nl.jpoint.top2k.domain.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/** User service. */
public class UserService implements IUserService {

    @Inject
    private Provider<EntityManager> provider;

    /** {@inheritDoc}. */
    public void create(final User user) {
        provider.get().persist(user);
    }

    /** {@inheritDoc}. */
    public List<User> getAll() {
        return provider.get().createNamedQuery("User.findAll").getResultList();
    }

    /** {@inheritDoc}. */
    public boolean isValidUserCredentials(final String email, final String password) {
        User user = (User) provider.get().createNamedQuery("User.byEmail").setParameter("email", email).getSingleResult();
        final boolean valid;
        if (user == null) {
            valid = false;
        } else {
            valid = user.validatePassword(password);
        }
        return valid;
    }
}
