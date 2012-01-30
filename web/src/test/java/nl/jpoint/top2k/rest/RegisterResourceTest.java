package nl.jpoint.top2k.rest;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.guice.ConfigurationModule;
import nl.jpoint.top2k.service.IMailService;
import nl.jpoint.top2k.service.IUserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/** Test class for RegisterResource. */
@RunWith(MockitoJUnitRunner.class)
public class RegisterResourceTest {
	@Inject
	private RegisterResource resource; // class under test
	@Mock
	private IMailService mailService;
	@Mock
	private IUserService userService;

	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new ConfigurationModule(),
				new AbstractModule() {
					@Override
					protected void configure() {
						bind(IMailService.class).toInstance(mailService);
						bind(IUserService.class).toInstance(userService);
					}
				});
		injector.injectMembers(this);
	}

	@Test
	public void testRegister() throws Exception {
		resource.register("e@mail.com", "password");
		verify(userService).create(isA(User.class));
		verify(mailService).sendRegistrationMail(isA(User.class));
	}

	@Test
	public void testGetUser() throws Exception {

	}
}
