package druzica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by Juraj on 30.11.2016.
 */

@Path("/")
@Produces("application/json")
@Singleton
public class StanicaRESTResource {

    private final StanicaRepository stanicaRepository;

    @Autowired
    public StanicaRESTResource(StanicaRepository stanicaRepository) {
        this.stanicaRepository = stanicaRepository;
    }

    // TODO implement REST stanica manipulace

    @GET
    @Path("/echo/{text}")
    public String echo(@PathParam("text") String text) {
        return text;
    }
}
