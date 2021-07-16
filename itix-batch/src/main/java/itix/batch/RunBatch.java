package itix.batch;

import itix.batch.config.AppConfiguration;
import itix.core.helper.CsvFileHelper;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RunBatch {

    private static final String RESOURCES_PATH = "C:\\Users\\Utilisateur\\itix\\itix-batch\\src\\main\\resources";
    private static final String PROPERTIES = "/runtime.properties";
    private static final String SIX_NATIONS_COMMON_FILE_NAME = "/6NationsStatsITIX_";
    private static final String SOCCER_SPI = "/spi_matches";
    private static final String SOCCER_SPI_LATEST = "/spi_matches_latest";
    private static final String SALERNITANA = "Salernitana";
    private static final String SERIE_B_ID = "1856";

    private static final Logger logger = Logger.getLogger(String.valueOf(RunBatch.class));


    public static void main(final String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ItixApplication application = context.getBean(ItixApplication.class);

        List<String> file = CsvFileHelper
              .readFile(CsvFileHelper.getResource(RESOURCES_PATH + SOCCER_SPI_LATEST + ".csv"));
        application.footballWriteDb(file, SERIE_B_ID);

        application.createxGClassement();

//        final Properties prop = PropertyLoader.load(CsvFileHelper.getResourcePath(RESOURCES_PATH + "" + PROPERTIES));
//        final String teamToLoad = prop.getProperty("teamToLoad");
//        List<String> file2 = CsvFileHelper.readFile(CsvFileHelper.getResource(RESOURCES_PATH + "" + SIX_NATIONS_COMMON_FILE_NAME + teamToLoad + ".csv"));
//        application.rugbyWriteToDb(file2);

        System.exit(0);
    }

}
