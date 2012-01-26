package nl.jpoint.top2k.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import nl.jpoint.top2k.jetty.Top2KLoginService;
import nl.jpoint.top2k.rest.*;
import org.eclipse.jetty.security.LoginService;

import java.util.HashMap;

/** Web Module */
public class WebModule extends ServletModule {
    protected void configureServlets() {
        filter("/rest/*").through(PersistFilter.class);

        bind(VersionResource.class);
        bind(RegisterResource.class);
        bind(ArtistResource.class);
        bind(TrackResource.class);
        bind(LoginService.class).to(Top2KLoginService.class);
        bind(ContestResource.class);
        serve("/rest/*").with(GuiceContainer.class, new HashMap<String, String>());
    }
}