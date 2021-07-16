package itix.batch;

import itix.batch.config.AppConfiguration;
import itix.core.service.MatchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBatchConf {

    private static final Logger logger = Logger.getLogger(String.valueOf(RunBatch.class));

    @Autowired
    MatchService matchService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;
        try {
            context = new AnnotationConfigApplicationContext(AppConfiguration.class);
            ItixApplication application = context.getBean(ItixApplication.class);

            application.testSimpleWrite();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.close();
        }

    }
}
