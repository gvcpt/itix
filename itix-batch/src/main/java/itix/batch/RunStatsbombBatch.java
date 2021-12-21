package itix.batch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import itix.batch.config.AppConfiguration;
import itix.core.config.ItixApplication;
import itix.core.model.Competition;
import itix.core.model.MatchSb;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunStatsbombBatch {

    private static final String STATSBOMB_JSON_PATH_ROOT = "C:\\Users\\Utilisateur\\itix\\itix-batch\\src\\main\\resources\\data";
    private static final String STATSBOMB_SUFFX_COMPETITIONS = "\\competitions.json";
    private static final String STATSBOMB_SUFFX_MATCHES = "\\matches";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_2 = "\\2";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_11 = "\\11";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_16 = "\\16";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_37 = "\\37";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_43 = "\\43";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_49 = "\\49";
    private static final String STATSBOMB_SFFX_FOLDER_MATCH_72 = "\\72";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_1 = "\\1.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_2 = "\\2.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_3 = "\\3.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_4 = "\\4.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_21 = "\\21.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_22 = "\\22.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_23 = "\\23.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_24 = "\\24.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_25 = "\\25.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_26 = "\\26.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_27 = "\\27.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_30 = "\\30.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_37 = "\\37.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_38 = "\\38.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_39 = "\\39.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_40 = "\\40.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_41 = "\\41.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_42 = "\\42.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_44 = "\\44.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_76 = "\\76.json";
    private static final String STATSBOMB_SFFX_FOLDER_SSFFX_90 = "\\76.json";


    private static final Logger logger = Logger.getLogger(String.valueOf(RunStatsbombBatch.class));


    public static void main(final String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ItixApplication application = context.getBean(ItixApplication.class);

        /**
         * read competitions.json
         * */
//        BufferedReader br = null;
//        br = bufferReadearFile(br, STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_COMPETITIONS);
//
//        Gson g = new Gson();
//        Type cType = new TypeToken<Collection<Competition>>() {
//        }.getType();
//        Collection<Competition> competitions = g.fromJson(br, cType);
//        logger.debug(competitions.size());
//        application.storeStatsbombData(competitions);

        /**
         * read matches folder
         * */
        BufferedReader br = null;
        List<String> pathList = populatePathList();
        for (String path : pathList) {
            br = bufferReadearFile(br, path);

            Gson g = new Gson();
            Type cType = new TypeToken<Collection<MatchSb>>() {
            }.getType();
            Collection<MatchSb> matches = g.fromJson(br, cType);
            logger.debug(matches.size());
            application.storeStatsbombDataMatches(matches);
        }

        System.exit(0);
    }

    private static List<String> populatePathList() {
        List<String> pathList = new ArrayList<>();
        // folder 2
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_2 + STATSBOMB_SFFX_FOLDER_SSFFX_44);
        // folder 11
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_1);
        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_2);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_4);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_21);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_22);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_23);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_24);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_25);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_26);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_27);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_37);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_38);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_39);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_40);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_41);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_11 + STATSBOMB_SFFX_FOLDER_SSFFX_42);
//        // folder 16
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_1);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_2);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_4);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_21);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_22);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_23);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_24);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_25);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_26);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_27);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_37);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_39);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_41);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_42);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_44);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_16 + STATSBOMB_SFFX_FOLDER_SSFFX_76);
//        // folder 37
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_37 + STATSBOMB_SFFX_FOLDER_SSFFX_4);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_37 + STATSBOMB_SFFX_FOLDER_SSFFX_42);
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_37 + STATSBOMB_SFFX_FOLDER_SSFFX_90);
//        // folder 43 -- world cup 2018
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_43 + STATSBOMB_SFFX_FOLDER_SSFFX_3);
//        // folder 49 -- female USA
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_49 + STATSBOMB_SFFX_FOLDER_SSFFX_3);
//        // folder 72 -- women world cup
//        pathList.add(STATSBOMB_JSON_PATH_ROOT + STATSBOMB_SUFFX_MATCHES + STATSBOMB_SFFX_FOLDER_MATCH_72 + STATSBOMB_SFFX_FOLDER_SSFFX_30);

        return pathList;
    }

    private static BufferedReader bufferReadearFile(BufferedReader br, String filePath) {
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }


}
