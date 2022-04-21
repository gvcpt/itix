package itix.batch;

import itix.batch.config.AppConfiguration;
import itix.core.config.ItixApplication;
import itix.core.config.ItixConstants;
import itix.core.helper.CsvFileHelper;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RunSpiMatchesBatch {

    private static final String RESOURCES_PATH = "C:\\Users\\Utilisateur\\itix\\itix-batch\\src\\main\\resources";
    private static final String PROPERTIES = "/runtime.properties";
    private static final String SIX_NATIONS_COMMON_FILE_NAME = "/6NationsStatsITIX_";
    private static final String SOCCER_SPI = "/spi_matches";
    private static final String SOCCER_SPI_LATEST = "/spi_matches_latest";

    private static final Logger logger = Logger.getLogger(String.valueOf(RunSpiMatchesBatch.class));


    public static void main(final String[] args) {
        if (args[0] == null) {
            logger.error("No arg specified");
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ItixApplication application = context.getBean(ItixApplication.class);

        switch (args[0]) {
            case "importData":
                List<String> file = CsvFileHelper
                      .readFile(CsvFileHelper.getResource(RESOURCES_PATH + SOCCER_SPI + ".csv"));
                application.storeSpiMatches(file, ItixConstants.SERIE_A_ID);
                break;

            case "createGlobalClassement":
                application.createGlobalxGClassement();
                break;

            case "createClassementByTeam":
                application.createClassementByTeam();
                break;

            case "createClassementByLeagueSeason":
                application.createClassementByLeagueSeason();
                break;

            default:
                logger.error("No treatement for specified argument " + args[0]);
                break;
        }

        System.exit(0);
    }

}
