package nl.jpoint.top2k.guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

import java.io.IOException;
import java.util.Properties;

/** Domain module. */
public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        JpaPersistModule persistenceModule = new JpaPersistModule("dnt2000");
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/persistence.properties"));
            persistenceModule.properties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        install(persistenceModule);
    }
}
