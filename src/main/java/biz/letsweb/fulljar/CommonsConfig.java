package biz.letsweb.fulljar;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz
 */
public class CommonsConfig {

    private static Logger LOG = LoggerFactory.getLogger(CommonsConfig.class);
    public static PropertiesConfiguration APP_PROPS;

    static {
        APP_PROPS = new PropertiesConfiguration();
        APP_PROPS.setBasePath("src/main/resources/properties/");
        APP_PROPS.setFileName("application.properties");
        try {
            APP_PROPS.load();
        } catch (ConfigurationException ex) {
            throw new FulljarRuntimeException("Couldn't construct commons properties. ", ex);
        }
    }

}
