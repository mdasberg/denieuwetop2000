package nl.jpoint.top2k.rest;

import com.google.inject.persist.Transactional;
import com.sun.jersey.api.json.JSONWithPadding;
import nl.jpoint.top2k.domain.User;
import nl.jpoint.top2k.service.IMailService;
import nl.jpoint.top2k.service.IUserService;
import org.apache.commons.configuration.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

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
		this.hostname = configuration.getString("hostname");
	}

	@POST
	@Path("/new")
	@Transactional
	public Response register(@FormParam("email") final String email,
			@FormParam("password") final String password) {

			final User user = new User(email, email, password);
			userService.create(user);
        final String msgBody;
        try {
            msgBody = getRegisterBodyMessage(email, user);
            mailService.sendMail(email, "Registratie voltooien", msgBody);
        } catch (UnsupportedEncodingException e) {
            // won't happen
        }

		return sendToHomePage();
	}

	@GET
	@Path("/finish")
	@Transactional
	public Response finish(@QueryParam("email") final String email,
			@QueryParam("registrationCode") final String password) {
		final User user = userService.findUserByEmail(email);
		if (user.validatePassword(password)) {
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

	private String getRegisterBodyMessage(final String email, final User user)
			throws UnsupportedEncodingException {
		final String finishURI = "<a href=\"" + hostname
				+ "rest/register/finish?email="
				+ URLEncoder.encode(email, "UTF-8") + "&registrationCode="
				+ user.getPassword() + "\">hier</a>";
		final String msgBody = "<html><body>Bedankt voor je inschrijving bij de nieuwe Top 2000.\n Klik "
				+ finishURI
				+ " zodat je registratie voltooid wordt.\n"
				+ "<br/><br/> Bedankt het Nieuwe top 2000 team.</body></html>";
		return msgBody;
	}

	private Response sendToHomePage() {
		final URI uri = UriBuilder.fromUri(hostname + "index.html").build(
				"index");
		return Response.seeOther(uri).build();
	}
}