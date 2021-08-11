package itix.rest.spark;

import static spark.Spark.get;

import com.google.gson.Gson;
import itix.core.config.ItixApplication;
import itix.core.service.MatchService;
import itix.rest.spark.StandardResponse.StatusResponse;
import itix.rest.spark.conf.RestAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestService {

    @Autowired
    private static MatchService matchService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestAppConfiguration.class);
        ItixApplication application = context.getBean(ItixApplication.class);
        matchService = application.getMatchService();

        // url : http://localhost:4567/getMatches
        get("/getMatches", (req, res) -> {
            res.type("application/json");
//            return new Gson().toJson(matchService.getAllMatches());
            return new StandardResponse(StatusResponse.SUCCESS,
                  new Gson().toJsonTree(matchService.getAllMatches()));
        });
    }


}
