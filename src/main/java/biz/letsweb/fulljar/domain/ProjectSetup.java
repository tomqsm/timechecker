package biz.letsweb.fulljar.domain;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz
 */
public class ProjectSetup {

    private static Logger LOG = LoggerFactory.getLogger(ProjectSetup.class);

    /**
     * Scans directories and allocates a project to each.
     */
    public void scanDirs() {
        File currentDir = new File("./projects/.");
        Validate.isTrue(currentDir.exists(), "Default projects directory doesn't exist.");
        LOG.trace("checking for projects in: {}", currentDir.getAbsolutePath());
        File[] listed = currentDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        LOG.trace("listed {} projects", listed.length);
        
        //check in project dir there is a project property file
        for(File file : listed){
            String projectProperty = file.getName() + ".properties";
            LOG.trace("Looking up property {} for project", projectProperty, file.getName());
            File propertyFile = new File(file.getAbsolutePath() + "/" + projectProperty);
            if(propertyFile.exists()){
                
            } else {
                LOG.info("Project {} doesn't have {}", file.getName(), projectProperty);
            }
        }
    }
}
