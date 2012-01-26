package nl.jpoint.top2k.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import nl.jpoint.top2k.guice.ConfigurationModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/** Test class for VersionResource. */
@RunWith(MockitoJUnitRunner.class)
public class VersionResourceTest {
    @Inject
    private VersionResource resource; // class under test

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new ConfigurationModule());
        injector.injectMembers(this);
    }

    @Test
    public void shouldGetVersion() throws Exception {
        assertEquals("1.0-test", resource.version());
    }

}
