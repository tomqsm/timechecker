package biz.letsweb.fulljar.persistence;

import biz.letsweb.fulljar.domain.Activity;
import biz.letsweb.fulljar.domain.ActivityEnum;
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
public class ActivityDaoTest {
    
    public ActivityDaoTest() {
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
     * Test of create method, of class ActivityDao.
     */
    @Test
    public void testCreateAndFindLast() {
        System.out.println("create");
        Activity activity = new Activity();
        activity.setActivityType(ActivityEnum.ANALYSIS.name());
        activity.setDesc(ActivityEnum.ANALYSIS.getDesc());
        Crudable<Activity> activityDao = new ActivityDao();
        activityDao.create(activity);
        //find last
        Activity retrievedActivity = null;
        retrievedActivity = activityDao.findLast();
        assertNotNull(retrievedActivity);
        System.out.println(retrievedActivity.getActivityType());
        assertTrue(activity.getActivityType().equals(retrievedActivity.getActivityType()));
    }
}