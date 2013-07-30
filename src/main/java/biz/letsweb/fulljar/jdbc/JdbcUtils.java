package biz.letsweb.fulljar.jdbc;

import biz.letsweb.fulljar.CommonsConfig;
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

    private final static Logger LOG = LoggerFactory.getLogger(JdbcUtils.class);
    private static SQLiteConnectionPoolDataSource DATA_SOURCE = null;

    public static synchronized DataSource getConfiguredDataSource() {
        if (DATA_SOURCE == null) {
            final SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding(CommonsConfig.APP_PROPS.getString("encoding")));
            config.setDateClass("INTEGER");
            config.setDateStringFormat(CommonsConfig.APP_PROPS.getString("db.date.format"));
            DATA_SOURCE = new SQLiteConnectionPoolDataSource();
            DATA_SOURCE.setUrl(CommonsConfig.APP_PROPS.getString("db.url"));
            DATA_SOURCE.setConfig(config);
            LOG.trace("Using a new data source.");
            return DATA_SOURCE;
        } else {
            LOG.trace("Using an existing data source.");
            return DATA_SOURCE;
        }
    }

    public static void setupTables() {
        LOG.trace("Setting up tables.");
        try (final Connection connection = getConfiguredDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(loadCreateTablesSql());
        } catch (SQLException e) {
            throw new FulljarRuntimeException("Couldn't setup tables. " + e.getMessage(), e);
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
        try (InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("create.sql");
                StringWriter writer = new StringWriter()) {
            IOUtils.copy(is, writer, CommonsConfig.APP_PROPS.getString("encoding"));
            sql = writer.toString();
        } catch (IOException ex) {
            throw new FulljarRuntimeException("Couldn't load sql script. ", ex);
        }
        return sql;
    }
}
