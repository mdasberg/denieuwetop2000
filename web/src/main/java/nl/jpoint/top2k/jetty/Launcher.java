package nl.jpoint.top2k.jetty;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Simple jetty launcher, which launches the webapplication from the local
 * resources and reuses the projects classpath.
 * 
 * @author jos
 */
public class Launcher {

	/** run under root context */
	private static String contextPath = "/";
	/** location where resources should be provided not related to the webapp */
	private static String resourceBase = "src/main/webapp";
	/** port to listen on */
	private static int httpPort = 8081;
	
	/**
	 * Start the server, and keep waiting.
	 */
	public static void main(String[] args) throws Exception {
		
		// setup a server with the specified port
		Server server = new Server(httpPort);
		WebAppContext webapp = new WebAppContext();
		
		// configure the webapp
		webapp.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		webapp.setContextPath(contextPath);
		webapp.setResourceBase(resourceBase);
		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		
		// set the loginghandler for the webapp
        ConstraintSecurityHandler secHandler = new ConstraintSecurityHandler();
        secHandler.setLoginService(new Top2KLoginService());
        webapp.setSecurityHandler(secHandler);
		
        // attach the webapp to the server
		server.setHandler(webapp);
		
		// start the server and wait
		server.start();
		server.join();
	}

}