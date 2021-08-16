package itix.rest.itixrestservice;

import itix.core.model.Match;
import itix.core.service.MatchService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;

@Path("/controller")
public class RestRessourceController extends RestControllerApplication {

    @Autowired
    MatchService matchService;

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

    @GET
    @Path("getReal")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Match>> getReal() {
        List<Match> allMatches = matchService.getAllMatches();
        return ResponseEntity.ok(allMatches);
    }


}