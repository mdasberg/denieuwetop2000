package nl.jpoint.top2k.rest;

import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Singleton
public class RegisterResource {

	
    @POST
    @Path("/register")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String register(@FormParam("email") final String email,@FormParam("password") final String password) {
        return "Email: " + email + ", password: " + password;
    }

}
