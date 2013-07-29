package biz.letsweb.fulljar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomasz
 */
public class ConfigurationTest {
    /**
     * Test of setPropertiesFile method, of class Configuration.
     */
    @Test
    public void testPrepareProperties() {
        System.out.println("setPropertiesFile");
        Configuration config = new Configuration();
        config.setPropertiesFile(Constants.APPLICATION_PROPERTIES_LOCATION);
        config.loadProperties();
        assertEquals("Tomasz Kuśmierczyk", config.getProperties().getProperty("developer"));
    }
}