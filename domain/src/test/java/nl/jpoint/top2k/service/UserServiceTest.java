package nl.jpoint.top2k.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nl.jpoint.top2k.TestUtil;
import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.guice.PersistenceModule;
import nl.jpoint.top2k.util.SecurityUtil;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.persist.PersistService;

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
		Injector injector = Guice.createInjector(new PersistenceModule(),
				new AbstractModule() {
					@Override
					protected void configure() {
						bind(IMailService.class).toInstance(new IMailService() {
							@Override
							public void sendRegistrationMail(User user) {
								// do nothing
							}
						});
						bind(IUserService.class).to(UserService.class);
					}
				});
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
	public void shouldCreateUserWithHashedPassword() throws Exception {

		transaction.begin();
		service.create(user1);
		transaction.commit();

		User retrieved = service.findUserByEmail(user1.getEmail());
		assertEquals(SecurityUtil.createSHAHash("passwordHash"),
				retrieved.getPasswordHash());

	}

	@Test
	public void validateUserPassword() throws Exception {

		transaction.begin();
		service.create(user1);
		transaction.commit();

		User retrieved = service.findUserByEmail(user1.getEmail());
		boolean result = retrieved.validatePassword("passwordHash");

		assertEquals(true, result);

	}

	@Test
	public void failValidateUserPassword() throws Exception {

		transaction.begin();
		service.create(user1);
		transaction.commit();

		User retrieved = service.findUserByEmail(user1.getEmail());
		boolean result = retrieved
				.validatePassword("somethingCompletelyDifferent");

		assertEquals(false, result);

	}

	@Test
	public void shouldGetAllUsers() throws Exception {
		assertEquals(0, service.getAll().size());

		transaction.begin();
		service.create(user1);
		transaction.commit();

		assertEquals(1, service.getAll().size());
	}

	@Test
	public void shouldCreateAShaValue() {
		String toHash = "PasswordToHash";
		String hash = SecurityUtil.createSHAHash(toHash);

		assertNotNull(hash);
		assertEquals(40, hash.length());
	}

}
