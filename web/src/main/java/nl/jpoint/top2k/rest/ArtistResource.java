package nl.jpoint.top2k.rest;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.json.JSONWithPadding;
import nl.jpoint.top2k.domain.Artist;
import nl.jpoint.top2k.service.ArtistService;


@Singleton
@Path("/artist")
public class ArtistResource {

    @Inject
    private ArtistService artistService;

    @GET
    @Path("/list/{page}")
    @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONWithPadding getUser(@PathParam("page") final int page,
            @QueryParam("jsoncallback") @DefaultValue("fn") final String callback)
    {
        final List<Artist> artists = artistService.getPagedList(page);
        return new JSONWithPadding(new GenericEntity<List<Artist>>(artists) {
        }, callback);
    }

}
