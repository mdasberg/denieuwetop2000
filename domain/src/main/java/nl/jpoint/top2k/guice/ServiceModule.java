package nl.jpoint.top2k.guice;

import com.google.inject.AbstractModule;
import nl.jpoint.top2k.service.IMailService;
import nl.jpoint.top2k.service.IUserService;
import nl.jpoint.top2k.service.MailService;
import nl.jpoint.top2k.service.UserService;

/** Service Module. */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IUserService.class).to(UserService.class);
        bind(IMailService.class).to(MailService.class);
    }
}
