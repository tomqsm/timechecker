package biz.letsweb.fulljar;

import java.io.File;
import java.util.Properties;

/**
 *
 * @author Tomasz
 */
public interface Constants {
    String APPLICATION_PROPERTIES_LOCATION = "properties/application.properties";
    Properties APPLICATION_PROPERTIES = new Configuration(Constants.APPLICATION_PROPERTIES_LOCATION).getProperties();
    File SQL_DOMAIN = new File("");
}
