package biz.letsweb.fulljar;

import biz.letsweb.fulljar.jdbc.JdbcUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    
    static Logger log = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final InputStream propertiesStream = App.class.getClassLoader().getResourceAsStream("properties/application.properties");
        Properties props = new Properties();
        props.load(propertiesStream);
        log.info(props.getProperty("developer"));
        JdbcUtils.loadSqliteDriver();
    }
}
