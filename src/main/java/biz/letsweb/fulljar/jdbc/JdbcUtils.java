package biz.letsweb.fulljar.jdbc;

import biz.letsweb.fulljar.FulljarRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz
 */
public class JdbcUtils {

    static Logger log = LoggerFactory.getLogger(JdbcUtils.class);

    public static void loadSqliteDriver() {
        try {
            log.trace("Loading org.sqlite.JDBC");
            Class.forName("org.sqlite.JDBC");
            log.trace("Loaded ok.");
        } catch (ClassNotFoundException ex) {
            throw new FulljarRuntimeException("Sorry, failed to load org.sqlite.JDBC.", ex);
        }
    }
}
