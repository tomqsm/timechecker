package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.domain.Project;
import biz.letsweb.fulljar.jdbc.JdbcUtils;
import java.util.List;
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
    
    @Test
    public void testFindAll(){
        Project project1 = new Project("letsweb", "letsweb description");
        Project project2 = new Project("lukasfloor", "lukasfloor description");
        Crudable<Project> projectDao = new ProjectDao();
        projectDao.create(project1);
        projectDao.create(project2);
        final List<Project> findAll = projectDao.findAll();
        assertNotNull(findAll);
        assertEquals(2, findAll.size());
    }
}