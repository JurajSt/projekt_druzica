package druzica;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by Juraj on 14.12.2016.
 */
@Path("/")
@Produces("application/json")
@Singleton
public class NavigSpravaRESTResource {

    private final NavigSpravaRepository navigSpravaRepository;

    @Autowired
    public NavigSpravaRESTResource(NavigSpravaRepository druzicaRepository) {
        this.navigSpravaRepository = druzicaRepository;
    }

    // TODO implement REST druzica manipulace

    @GET
    @Path("/echo/{text}")
    public String echo(@PathParam("text") String text) {
        return text;
    }
}
