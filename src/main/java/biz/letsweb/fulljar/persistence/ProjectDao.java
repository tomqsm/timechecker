package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.FulljarRuntimeException;
import biz.letsweb.fulljar.domain.Activity;
import biz.letsweb.fulljar.domain.Project;
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
public class ProjectDao implements Crudable<Project> {

    private static Logger LOG = LoggerFactory.getLogger(ProjectDao.class);
    private String tableName = "projects";

    @Override
    public void create(final Project project) {
        final String sql = String.format("INSERT INTO %s VALUES (null, '%s', '%s')", tableName, project.getName(), project.getDesc());
        LOG.trace("Executing: {}", sql);
        try (final Connection con = JdbcUtils.getConfiguredDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed.", sql), ex);
        }
    }

    @Override
    public Project find(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id=%d", tableName, id);
        return null;
    }

    @Override
    public Iterator<Project> findAll() {
        String sql = String.format("SELECT * FROM %s", tableName);
        return null;
    }

    @Override
    public void update(Project project) {
        String sql = String.format("UPDATE %s SET name=%s;", tableName, project.getName());

    }

    @Override
    public void delete(int id) {
        String sql = String.format("DELETE FROM %s WHERE id=%d;", tableName, id);
    }

    @Override
    public Project findLast() {
        Project p = null;
        final String sql = "SELECT * FROM projects WHERE id=(SELECT MAX(id) FROM projects);";
        LOG.trace("Executing: {}", sql);
        try (final Connection con = JdbcUtils.getConfiguredDataSource().getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            p = new Project();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDesc(rs.getString(3));
            }
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed: %s.", sql, ex.getMessage()), ex);
        }
        return p;
    }

    @Override
    public void delete(Project t) {
        String sql = String.format("DELETE FROM %s WHERE id=%d;", tableName, t.getId());
    }
}
