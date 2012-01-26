package nl.jpoint.top2k.guice;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import nl.jpoint.top2k.rest.VersionResource;

import java.util.HashMap;

/** Web Module */
public class WebModule extends ServletModule {
    protected void configureServlets() {
        bind(VersionResource.class);
        serve("/rest/*").with(GuiceContainer.class, new HashMap<String, String>());
    }
}