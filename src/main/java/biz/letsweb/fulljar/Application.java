package biz.letsweb.fulljar;

import java.io.IOException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Application {

    static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Validate.notEmpty(args, "Thre should be a parameter passed for start of this program. I.e. 'start | stop'");
        
//        JdbcUtils.setupTables();
    }
}
