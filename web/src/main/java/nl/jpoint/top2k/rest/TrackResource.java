package nl.jpoint.top2k.rest;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.json.JSONWithPadding;
import nl.jpoint.top2k.domain.Track;
import nl.jpoint.top2k.service.ITrackService;


@Singleton
@Path("/track")
public class TrackResource {

    @Inject
    private ITrackService trackService;

    @GET
    @Path("/list/{page}")
    @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONWithPadding getTracks(@PathParam("page") final int page,
            @QueryParam("jsoncallback") @DefaultValue("fn") final String callback)
    {
        final List<Track> tracks = trackService.getPagedList(page);
        return new JSONWithPadding(new GenericEntity<List<Track>>(tracks) { }, callback);
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONWithPadding getTrack(@PathParam("id") final long id,
            @QueryParam("jsoncallback") @DefaultValue("fn") final String callback)
    {
        final Track track = trackService.getById(id);
        return new JSONWithPadding(new GenericEntity<Track>(track) { }, callback);
    }

}
