package nl.jpoint.top2k.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/** Guice servlet config. */
public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ConfigurationModule(), new PersistenceModule(), new ServiceModule(), new WebModule());
    }
}