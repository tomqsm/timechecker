package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.FulljarRuntimeException;
import biz.letsweb.fulljar.domain.Work;
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
public class WorkDao implements Crudable<Work> {

    private Logger LOG = LoggerFactory.getLogger(WorkDao.class);
    private String tableName = "works";

    @Override
    public void create(final Work work) {
        final String sql = String.format("INSERT INTO %s VALUES (null, \"%s\", %d, %d);",
                tableName,
                work.getChangeTime(),
                work.getActivity().getId(),
                work.getProject().getId());
        LOG.trace("Executing: {}", sql);
        try (final Connection con = JdbcUtils.getConfiguredDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed: %s.", sql, ex.getMessage()), ex);
        }
    }

    @Override
    public Work findLast() {
        Work work = null;
        final String sql = "SELECT w.id, w.change_time, w.change_type, p.name, a.name FROM works w JOIN projects p ON w.id_project=p.id JOIN activities a ON w.id_activity=a.id WHERE w.id=(SELECT MAX(id) FROM works);";
        LOG.trace("Executing: {}", sql);
        try (final Connection con = JdbcUtils.getConfiguredDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            work = new Work();
            while(rs.next()){
                work.setId(rs.getInt(0));
                work.setChangeTime(rs.getString(1));
                System.out.println(rs.getString(3));
            }
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed: %s.", sql, ex.getMessage()), ex);
        }
        return work;
    }

    @Override
    public Work find(final int id) {
        return null;
    }

    @Override
    public Iterator<Work> findAll() {
        return null;
    }

    @Override
    public void update(Work t) {
    }

    @Override
    public void delete(Work t) {
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
