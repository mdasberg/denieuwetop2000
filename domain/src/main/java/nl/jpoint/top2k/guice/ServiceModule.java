package nl.jpoint.top2k.guice;

import com.google.inject.AbstractModule;
import nl.jpoint.top2k.service.*;

/** Service Module. */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IUserService.class).to(UserService.class);
        bind(IMailService.class).to(MailService.class);
        bind(IArtistService.class).to(ArtistService.class);
        bind(ITrackService.class).to(TrackService.class);
    }
}
