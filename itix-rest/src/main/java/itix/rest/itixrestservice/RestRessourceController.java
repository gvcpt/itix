package itix.rest.itixrestservice;

import itix.core.model.Match;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;

@Path("/controller")
public class RestRessourceController {


    @GET
    @Path("getDummy")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Match> greeting() {
        Match m = new Match();
        m.setHomeTeam("Salernitana");
        m.setAwayTeam("Venezia");
        m.setHScore("2");
        m.setAScore("1");
        return ResponseEntity.ok(m);
    }

}