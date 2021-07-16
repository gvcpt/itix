package itix.core.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class CsvFileHelper {

    private static final Logger logger = Logger.getLogger(String.valueOf(CsvFileHelper.class));

    public static String getResourcePath(String fileName) {
        final File f = new File("");
        final String dossierPath = fileName;
        return dossierPath;
    }

    public static File getResource(String fileName) {
        return new File(getResourcePath(fileName));
    }

    public static List<String> readFile(File file) {
        final List<String> result = new ArrayList<String>();
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            logger.error(":( Erreur dans la récupération du fichier: " + file.getAbsolutePath() + " :(");
        }
        BufferedReader br = new BufferedReader(fr);
        int totLines = 0;

        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                result.add(line);
//				addLineToBdd(connexion, line);
                totLines++;
            }
        } catch (IOException e) {
            logger.error(":( Erreur dans la lecture d'une ligne du fichier " + file.getAbsolutePath() + " :(");
        }
        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            logger.error(":( Erreur dans cloture du fichier " + file.getAbsolutePath() + " :(");
        }

        logger.debug("== " + totLines + " lignes du fichier  " + file.getAbsolutePath() + " ont été lues sans erreurs");
        return result;
    }


    private static Connection getConnection() {
			/* test connection bd: *
		TODO à déplacer après lecture données et utiliser un fichier properties
		*/
        Connection connexion = null;
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String utilisateur = "itix";
        String motDePasse = "itix";

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            logger.debug("= connecté à la bdd :)");

        } catch (SQLException e) {
            logger.error(" :( erreur à l'acces de la bdd" + e.getMessage());
        }
        return connexion;
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
                logger.warn(" :( erreur à la fermeture de la bdd");
            }
        }
    }

}
