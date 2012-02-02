package nl.jpoint.top2k.rest;

import java.net.URI;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.service.IMailService;
import nl.jpoint.top2k.service.IUserService;

import org.apache.commons.configuration.Configuration;

import com.google.inject.persist.Transactional;
import com.sun.jersey.api.json.JSONWithPadding;

@Singleton
@Path("/register")
public class RegisterResource {
	private final String hostname;
	@Inject
	private IUserService userService;
	@Inject
	private IMailService mailService;

	@Inject
	public RegisterResource(final Configuration configuration) {
		hostname = configuration.getString("hostname");
	}

	@POST
	@Path("/new")
	@Transactional
	public String register(@FormParam("email") final String email,
			@FormParam("password") final String password) {

		final User user = new User(email, email, password);
		userService.create(user);
		mailService.sendRegistrationMail(user);
		return "Gelukt. Check email";
	}

	@GET
	@Path("/finish")
	@Transactional
	public Response finish(@QueryParam("email") final String email,
			@QueryParam("registrationCode") final String passwordHashed) {
		final User user = userService.findUserByEmail(email);
		if (user.getPasswordHash().equals(passwordHashed)) {
			user.setFinishedRegistration(true);
			userService.create(user);
			return sendToHomePage();
		}
		throw new RuntimeException("send to some error page.....");
	}

	@GET
	@Path("/user")
	@Produces({ "application/x-javascript", MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML })
	public JSONWithPadding getUser(
			@QueryParam("jsoncallback") @DefaultValue("fn") final String callback) {
		final List<User> users = userService.getAll();
		return new JSONWithPadding(new GenericEntity<List<User>>(users) {
		}, callback);
	}

	private Response sendToHomePage() {
		final URI uri = UriBuilder.fromUri(hostname + "index.html").build(
				"index");
		return Response.seeOther(uri).build();
	}
}