package nl.jpoint.top2k.rest;

import com.google.inject.persist.Transactional;
import com.sun.jersey.api.json.JSONWithPadding;
import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Singleton
@Path("/register")
public class RegisterResource {

    @Inject
    private UserService userService;


    @POST
    @Path("/new")
    @Transactional
    public void register(@FormParam("email") final String email, @FormParam("password") final String password) {
        userService.create(new User(email, email, password));
    }

    @GET
    @Path("/user")
    @Produces({"application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public JSONWithPadding getUser(@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
        final List<User> users = userService.getAll();
        return new JSONWithPadding(new GenericEntity<List<User>>(users) {
        }, callback);
    }

}
