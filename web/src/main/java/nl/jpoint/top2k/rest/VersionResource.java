package nl.jpoint.top2k.rest;

import org.apache.commons.configuration.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/version")
public class VersionResource {
    @Inject
    private Configuration configuration;

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String version() {
        return configuration.getString("version");
    }
}
