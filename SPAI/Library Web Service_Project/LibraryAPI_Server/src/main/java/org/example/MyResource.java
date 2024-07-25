package org.example;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @Path("Check")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        return "Got it!";
    }

    @Path("reflect")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String createConfiguration(String reflected) {
        return "Hello " + reflected;
    }

}
