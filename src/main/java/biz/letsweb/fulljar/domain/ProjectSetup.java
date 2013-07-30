package biz.letsweb.fulljar.domain;

import biz.letsweb.fulljar.CommonsConfig;
import java.io.File;
import java.io.FileFilter;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz
 */
public class ProjectSetup {
    
    private static Logger LOG = LoggerFactory.getLogger(ProjectSetup.class);
    private String projectName;
    private File properties;
    private int countProjects;
    
    public ProjectSetup(String projectName, String action) {
        this.projectName = projectName;
    }

    /**
     * Scans directories and allocates a project to each.
     */
    public void scanDirs() {
        LOG.trace("Scanning dirs.");
        final File projectsDir = new File(CommonsConfig.APP_PROPS.getString("projects.location"));
        LOG.trace("Project dir:  {}", projectsDir);
//        final LookupResult lookupResult = findAllProjectDirs(projectsDir);
//        LOG.trace("Found {} projects.", countProjects);
        //check in project dir there is a project property file
//        File ranProjectDir = lookupResult.getRunningDir();
        // ory doesn't exist, no {}.c:\Users\Tomasz\Documents\NetBeansProjects\fulljar\projects\default\.\projects
        String runProjectProperties = this.projectName + ".properties";
        
        File propertyFile = new File(projectsDir.getAbsolutePath() + "/" + runProjectProperties);
        LOG.trace("Looking up property {} for project {}", propertyFile.getAbsolutePath(), projectsDir.getName());
        if (propertyFile.exists()) {
            LOG.trace("Found property file: " + propertyFile.getName());
            this.properties = propertyFile;
        } else {
            LOG.info("Project {} doesn't have {}", projectsDir.getName(), runProjectProperties);
        }
    }
    public void scanDirsTest() {
        LOG.trace("Scanning dirs.");
        final File projectsDir = new File(CommonsConfig.APP_PROPS.getString("projects.location"));
        LOG.trace("Project dir:  {}", projectsDir);
        final LookupResult lookupResult = findAllProjectDirs(projectsDir);
        LOG.trace("Found {} projects.", countProjects);
        File ranProjectDir = lookupResult.getRunningDir();
        String runProjectProperties = this.projectName + ".properties";
        
        File propertyFile = new File(projectsDir.getAbsolutePath() + "/" + runProjectProperties);
        LOG.trace("Looking up property {} for project {}", propertyFile.getAbsolutePath(), projectsDir.getName());
        if (propertyFile.exists()) {
            LOG.trace("Found property file: " + propertyFile.getName());
            this.properties = propertyFile;
        } else {
            LOG.info("Project {} doesn't have {}", projectsDir.getName(), runProjectProperties);
        }
    }
    
    public LookupResult findAllProjectDirs(File projectsDir) {
        final LookupResult lookupResult = new LookupResult();
        Validate.isTrue(projectsDir.exists(), "Projects directory doesn't exist, no " + projectsDir.getAbsolutePath());
        LOG.trace("checking for projects in: {}", projectsDir.getAbsolutePath());
        File[] listed = projectsDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File name) {
                boolean isDirectory = false;
                if (name.getName().equals(projectName)) {
                    lookupResult.setFound(name);
                }
                if (name.isDirectory()) {
                    isDirectory = true;
                    countProjects++;
                }
                return isDirectory;
            }
        });
        if(listed == null){
            listed = new File [] {};
        }
        lookupResult.setAllFiles(listed);
        return lookupResult;
    }
    
    public int getCountProjects() {
        return countProjects;
    }

    public String getProjectName() {
        return projectName;
    }

    public File getPropertiesFile() {
        return properties;
    }
    
    class LookupResult {
        
        File found;
        File[] allFiles;
        
        public File getRunningDir() {
            return found;
        }
        
        public void setFound(File found) {
            this.found = found;
        }
        
        public File[] getAllFiles() {
            return allFiles;
        }
        
        public void setAllFiles(File[] allFiles) {
            this.allFiles = allFiles;
        }
    }
}
