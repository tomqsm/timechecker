package biz.letsweb.fulljar;

import biz.letsweb.fulljar.domain.ProjectSetup;
import java.io.IOException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Validate.notEmpty(args, "Thre should be a parameter passed for start of this program. I.e. 'start | stop'");
        Validate.isTrue(args.length == 2, "There should be just 2 parameters on start.");
        LOG.trace("starting application.");
        String project = args[0];
        String action = args[1].replace(".bat", "");
        LOG.trace("Project: {}, action: {}", project, action);
        ProjectSetup setup = new ProjectSetup(project, action);
        setup.scanDirs();
        CommonsConfig commonsConfig = new CommonsConfig(setup.getPropertiesFile());
//        JdbcUtils.setupTables();
    }
}
