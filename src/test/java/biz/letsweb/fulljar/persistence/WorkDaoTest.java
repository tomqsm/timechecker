package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.domain.Activity;
import biz.letsweb.fulljar.domain.ActivityEnum;
import biz.letsweb.fulljar.domain.Project;
import biz.letsweb.fulljar.domain.Work;
import biz.letsweb.fulljar.jdbc.JdbcUtils;
import biz.letsweb.fulljar.time.TimeController;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tomasz
 */
public class WorkDaoTest {

    public WorkDaoTest() {
    }

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
     * // * Test of create method, of class WorkDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create work");

        // insert activity
        Activity activity = new Activity();
        activity.setActivityType(ActivityEnum.ANALYSIS.name());
        activity.setDesc(ActivityEnum.ANALYSIS.getDesc());
        Crudable<Activity> activityDao = new ActivityDao();
        activityDao.create(activity);

        //find last
        Activity retrievedActivity = null;
        retrievedActivity = activityDao.findLast();
        
        // create a project
        Project project = new Project("letsweb", "letsweb description");
        Crudable<Project> projectDao = new ProjectDao();
        projectDao.create(project);

        Project retrievedProject = projectDao.findLast();


        Work work = new Work();
        work.setActivity(retrievedActivity);
        work.setProject(retrievedProject);
        work.setChangeTime(TimeController.dateToFormattedString(new Date()));
        Crudable<Work> crudableWork = new WorkDao();
        crudableWork.create(work);
    }
}