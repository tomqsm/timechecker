package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.FulljarRuntimeException;
import biz.letsweb.fulljar.domain.Activity;
import biz.letsweb.fulljar.jdbc.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz
 */
public class ActivityDao implements Crudable<Activity> {
    
    Logger LOG = LoggerFactory.getLogger(ActivityDao.class);
    
    @Override
    public void create(final Activity t) {
        final String sql = String.format("INSERT INTO activities VALUES (null, '%s', '%s');", t.getActivityType(), t.getDesc());
        LOG.trace("Executing: {}", sql);
        try (final Connection con = JdbcUtils.getConfiguredDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed: %s.", sql, ex.getMessage()), ex);
        }
    }
    
    @Override
    public Activity find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Iterator<Activity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Activity findLast() {
        Activity a = null;
        final String sql = "SELECT * FROM activities WHERE id=(SELECT MAX(id) FROM activities);";
        LOG.trace("Executing: {}", sql);
        try (final Connection con = JdbcUtils.getConfiguredDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            a = new Activity();
            while (rs.next()) {
                a.setId(rs.getInt(1));
                a.setActivityType(rs.getString(2));
                a.setDesc(rs.getString(3));
            }
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed: %s.", sql, ex.getMessage()), ex);
        }
        return a;
    }
    
    @Override
    public void update(Activity t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Activity t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
