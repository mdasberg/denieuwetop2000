package nl.jpoint.top2k.service;

import com.google.inject.Provider;
import nl.jpoint.top2k.domain.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/** User service. */
public class UserService {

    @Inject
    private Provider<EntityManager> provider;

    public void create(final User user) {
        provider.get().persist(user);
    }

    public List<User> getAll() {
        return provider.get().createNamedQuery("User.findAll").getResultList();
    }
}
