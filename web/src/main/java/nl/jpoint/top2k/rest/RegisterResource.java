package nl.jpoint.top2k.rest;

import com.google.inject.persist.Transactional;
import com.sun.jersey.api.json.JSONWithPadding;
import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.service.IMailService;
import nl.jpoint.top2k.service.IUserService;

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
    private IUserService userService;
    @Inject
    private IMailService mailService;


    @POST
    @Path("/new")
    @Transactional
    public void register(@FormParam("email") final String email, @FormParam("password") final String password) {
        userService.create(new User(email, email, password));
        final String msgBody = "Bedankt voor je inschrijving bij de nieuwe Top 2000.\n Klik de bevestigings link hieronder zodat je registratie voltooid wordt.\n"
                + "<LINK>\n Bedankt het Nieuwe top 2000 team.";

        mailService.sendMail(email, "Registratie voltooien", msgBody);
    }

    @GET
    @Path("/user")
    @Produces({"application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public JSONWithPadding getUser(@QueryParam("jsoncallback") @DefaultValue("fn") final String callback) {
        final List<User> users = userService.getAll();
        return new JSONWithPadding(new GenericEntity<List<User>>(users) {
        }, callback);
    }

}
