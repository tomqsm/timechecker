package biz.letsweb.fulljar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz
 */
public class Configuration {

    private static Logger LOG = LoggerFactory.getLogger(Configuration.class);
    private String filePath;
    private Properties properties;

    public Configuration() {
    }

    public Configuration(String filePath) {
        this.filePath = filePath;
        loadProperties();
    }

    public void setPropertiesFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a properties from file.
     */
    public final void loadProperties() {
        LOG.trace("Preparing configuration properties {}.", this.filePath);
        try (InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream(this.filePath)) {
            properties = new Properties();
            properties.load(propertiesStream);
        } catch (IOException ex) {
            System.err.println("Missing file ");
        }
    }

    public Properties getProperties() {
        return properties;
    }
}