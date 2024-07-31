package azure.deploy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "<h1>Hello, World!</h1>";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json")
    public String json() {
        return "{\"hello\": \"world\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json2")
    public String json2() {
        return "{\"hello\": \"world2\"}";
    }
}
