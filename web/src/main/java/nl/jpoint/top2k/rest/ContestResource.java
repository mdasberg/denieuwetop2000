package nl.jpoint.top2k.rest;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.json.JSONWithPadding;
import nl.jpoint.top2k.domain.Track;
import nl.jpoint.top2k.service.IContestService;


@Singleton
@Path("/contest")
public class ContestResource {

    @Inject
    private IContestService contestService;


    @GET
    @Path("/new")
    @Produces({"application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public JSONWithPadding newContest(@QueryParam("jsoncallback") @DefaultValue("fn") final String callback) {
        final List<Track> tracks = contestService.getTracksForContest();
        return new JSONWithPadding(new GenericEntity<List<Track>>(tracks) { }, callback);
    }

}
