package nl.jpoint.top2k.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.persist.PersistService;
import nl.jpoint.top2k.TestUtil;
import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.guice.DomainModule;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;

/** Test class for UserService. */
public class UserServiceTest {
    @Inject
    private UserService service; // class under test
    @Inject
    private Provider<EntityManager> provider;
    private EntityTransaction transaction;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new DomainModule());
        injector.getInstance(PersistService.class).start();
        injector.injectMembers(this);
        transaction = provider.get().getTransaction();

        user1 = new User("username", "email", "passwordHash");
        user2 = new User("username2", "email2", "passwordHash2");
    }

    @Test
    public void shouldCreateUser() throws Exception {
        assertNull(TestUtil.getPrivateFieldValue(user1, "id"));
        assertNull(TestUtil.getPrivateFieldValue(user2, "id"));

        transaction.begin();
        service.create(user1);
        service.create(user1); // should not be added twice
        service.create(user2);
        transaction.commit();

        assertNotNull(TestUtil.getPrivateFieldValue(user1, "id"));
        assertNotNull(TestUtil.getPrivateFieldValue(user2, "id"));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        assertEquals(0, service.getAll().size());

        transaction.begin();
        service.create(user1);
        transaction.commit();

        assertEquals(1, service.getAll().size());
    }


}
