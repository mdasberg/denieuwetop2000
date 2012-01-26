package nl.jpoint.top2k.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import nl.jpoint.top2k.rest.VersionResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/** Web Module */
public class WebModule extends ServletModule {
    protected void configureServlets() {
        JpaPersistModule persistenceModule = new JpaPersistModule("dnt2000");
        try {
            Properties properties = new Properties();
            properties.load(WebModule.class.getResourceAsStream("/persistence.properties"));
            persistenceModule.properties(properties);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        install(persistenceModule);
        filter("/rest/*").through(PersistFilter.class);

        bind(VersionResource.class);
        serve("/rest/*").with(GuiceContainer.class, new HashMap<String, String>());
    }
}