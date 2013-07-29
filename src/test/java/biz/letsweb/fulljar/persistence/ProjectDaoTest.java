package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.domain.Project;
import biz.letsweb.fulljar.jdbc.JdbcUtils;
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
public class ProjectDaoTest {
    
    @BeforeClass
    public static void setUpClass() {
        JdbcUtils.setupTables();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDesc method, of class Project.
     */
    @Test
    public void testCreateProject() {
        System.out.println("create project");
        Project project = new Project("letsweb", "letsweb description");
        Crudable<Project> projectDao = new ProjectDao();
        projectDao.create(project);
        // find last created
        Project retrievedProject = projectDao.findLast();
        assertNotNull(retrievedProject);
        assertEquals(project.getName(), retrievedProject.getName());
    }
}