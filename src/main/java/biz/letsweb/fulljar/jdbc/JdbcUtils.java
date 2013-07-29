package biz.letsweb.fulljar.jdbc;

import biz.letsweb.fulljar.Constants;
import biz.letsweb.fulljar.FulljarRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

/**
 *
 * @author Tomasz
 */
public class JdbcUtils {

    private static Logger LOG = LoggerFactory.getLogger(JdbcUtils.class);
    private static DataSource DATA_SOURCE = null;

    public static synchronized DataSource getConfiguredDataSource() {
        if (DATA_SOURCE == null) {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding(Constants.APPLICATION_PROPERTIES.getProperty("default.encoding")));
            config.setDateClass("INTEGER");
            config.setDateStringFormat(Constants.APPLICATION_PROPERTIES.getProperty("db.date.format"));
            SQLiteConnectionPoolDataSource dataSource = new SQLiteConnectionPoolDataSource();
            dataSource.setUrl(Constants.APPLICATION_PROPERTIES.getProperty("db.url"));
            dataSource.setConfig(config);
            return dataSource;
        } else {
            return DATA_SOURCE;
        }
    }

    public static void setupTables() {
        LOG.trace("Setting up tables.");
        try (Connection connection = getConfiguredDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(loadCreateTablesSql());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads sql script for setting up a data base.
     *
     * @return
     */
    private static String loadCreateTablesSql() {
        LOG.trace("Loading create table sql script.");
        String sql = null;
        try (InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("create.sql")) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, Constants.APPLICATION_PROPERTIES.getProperty("default.encoding"));
            sql = writer.toString();
        } catch (IOException ex) {
            throw new FulljarRuntimeException("Couldn't load sql script. ", ex);
        }
        return sql;
    }
}
