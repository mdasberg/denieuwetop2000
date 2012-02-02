package nl.jpoint.top2k.jetty;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import nl.jpoint.top2k.guice.ConfigurationModule;
import nl.jpoint.top2k.guice.PersistenceModule;
import nl.jpoint.top2k.guice.ServiceModule;
import nl.jpoint.top2k.service.IUserService;
import org.eclipse.jetty.security.DefaultIdentityService;
import org.eclipse.jetty.security.IdentityService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.UserIdentity;

import javax.inject.Inject;
import javax.security.auth.Subject;

/** LoginService. */
public class Top2KLoginService implements LoginService {
    @Inject
    private IUserService service;

    public Top2KLoginService() {
        Injector injector = Guice.createInjector(new ServiceModule(),
                new ConfigurationModule(),
                new PersistenceModule());
        injector.injectMembers(this);
        injector.getInstance(PersistService.class).start();
    }

    @Override
    public String getName() {
        System.out.println(service.getAll().size());
        return "dnt2000";
    }

    @Override
    public UserIdentity login(String s, Object o) {
        final boolean validUserCredentials = service.isValidUserCredentials(s, (String) o);
        final UserIdentity userIdentity;
        if (validUserCredentials) {
            return new DefaultIdentityService().newUserIdentity(new Subject(), null, new String[]{"admin"});
        }
        return null;
    }

    @Override
    public boolean validate(UserIdentity userIdentity) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IdentityService getIdentityService() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setIdentityService(IdentityService identityService) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void logout(UserIdentity userIdentity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
