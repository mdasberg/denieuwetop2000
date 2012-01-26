package nl.jpoint.top2k.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.service.MailService;
import nl.jpoint.top2k.service.UserService;

import com.google.inject.persist.Transactional;
import com.sun.jersey.api.json.JSONWithPadding;


@Singleton
@Path("/register")
public class RegisterResource {

    @Inject
    private UserService userService;
    @Inject
    private MailService mailService;


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
