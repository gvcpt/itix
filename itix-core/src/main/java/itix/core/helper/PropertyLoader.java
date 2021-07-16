package itix.core.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyLoader {

    private static final Logger logger = Logger.getLogger(String.valueOf(PropertyLoader.class));

    public static Properties load(String filename) throws IOException {

        Properties properties = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream(filename);
            properties.load(input);
        } catch (FileNotFoundException fnf) {
            logger.error(":( fichier " + filename + " non trouvé :(");
        } catch (IOException ioe) {
            logger.error(":( erreur I/O avec le fichire " + filename + " :(");
        } finally {
            input.close();
        }
        return properties;
    }
}
