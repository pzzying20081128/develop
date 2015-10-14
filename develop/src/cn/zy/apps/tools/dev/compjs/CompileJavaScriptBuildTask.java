package cn.zy.apps.tools.dev.compjs ;

import java.io.File ;
import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.List ;

import org.apache.tools.ant.BuildException ;
import org.apache.tools.ant.DirectoryScanner ;
import org.apache.tools.ant.Task ;
import org.apache.tools.ant.types.FileSet ;

public class CompileJavaScriptBuildTask extends Task {

//    private String Type {
//        dir, merge
//    }

    private File buildBasePath ;

    private List<FileSet> filesets = new ArrayList<FileSet>() ;

    private File buildOutFile ;
    
    private String buildOutFileName;

    private String time ;

    private String buildType ;

    @Override
    public void execute() throws BuildException {

        List<String> excludedFiles = initCompile() ;

        compileJS(excludedFiles) ;
    }

    private List<String> initCompile() {
        return initExcludedFiles() ;
    }

    private List<String> initExcludedFiles() {
        List<String> excludedFiles = new ArrayList<String>() ;
        for (Iterator<FileSet> iterator = filesets.iterator(); iterator.hasNext();) {
            FileSet fs = (FileSet) iterator.next() ;
            DirectoryScanner ds = fs.getDirectoryScanner(getProject()) ;
            String[] excludedFiles_ = ds.getExcludedFiles() ;
            for (String file : excludedFiles_) {
                excludedFiles.add(buildBasePath + "/" + file) ;
            }
        }
        return excludedFiles ;
    }

    private void compileJS(List<String> excludedFiles) throws BuildException {

        switch (buildType) {
        case "merge": {
            try {
                 {
                   String buildOutFileName_=
                             (buildOutFileName==null || buildOutFileName.equals(""))?buildBasePath.getName():buildOutFileName;
                    IBuildJSModule module = new BuildJSModule(buildOutFile.getPath(), null, excludedFiles) ;
                   String xx  =  buildOutFileName_.substring(0, buildOutFileName_.indexOf("."));
                    module.build(buildBasePath, xx  + "."+ time+".js") ;
                }
            } catch (Exception e) {
                throw new BuildException(e) ;
            }
        }

            break ;
        case "dir": {
            try {
                File[] childFiles = buildBasePath.listFiles() ;
                for (File childFile : childFiles) {
                    if (!childFile.isDirectory()) continue ;
                    IBuildJSModule module = new BuildJSModule(buildOutFile.getPath(), null, excludedFiles) ;
                    module.build(childFile.getPath(), childFile.getName() + "." + time + ".js") ;
                }
            } catch (Exception e) {
                throw new BuildException(e) ;
            }
        }
            break ;

        default:
            break ;
        }

    }

    public void addFileset(FileSet fileset) {

        filesets.add(fileset) ;
    }

    public File getBuildOutFile() {
        return buildOutFile ;
    }

    public void setBuildOutFile(File buildOutFile) {
        this.buildOutFile = buildOutFile ;
    }

    public File getBuildBasePath() {
        return buildBasePath ;
    }

    public void setBuildBasePath(File buildBasePath) {
        this.buildBasePath = buildBasePath ;
    }

    public String getTime() {
        return time ;
    }

    public void setTime(String time) {
        this.time = time ;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType ;
    }

    public void setBuildOutFileName(String buildOutFileName) {
        this.buildOutFileName = buildOutFileName ;
    }

}
