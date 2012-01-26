package nl.jpoint.top2k.jetty;

import org.eclipse.jetty.security.IdentityService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.UserIdentity;

/**
 * Created by IntelliJ IDEA.
 * User: misha
 * Date: 26-01-12
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class Top2KLoginService implements LoginService {

    public Top2KLoginService() {
    }

    @Override
    public String getName() {
        return "dnt2000";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserIdentity login(String s, Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
