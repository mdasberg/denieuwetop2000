package nl.jpoint.top2k.rest;

import com.google.inject.Singleton;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * JAXBContextResolver fixes the single element array result.
 * Since Jersey doesn't return a list but a single object when the list contains only one element, this class
 * fixes the return type.
 */
@Singleton
@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {
    private JAXBContext context;
    @SuppressWarnings("rawtypes")
	private Class[] types = {};

    /**
     * Default constructor.
     * @throws Exception The exception.
     */
    public JAXBContextResolver() throws Exception {
        this.context = new JSONJAXBContext(JSONConfiguration.mapped().arrays().build(), types);
    }

    /**
     * Gets the context.
     * @param objectType The object Type.
     * @return context The context.
     */
    public JAXBContext getContext(Class<?> objectType) {
        return context;
    }
}