package cn.zy.apps.tools.dev.javascript ;

import java.io.File ;
import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Properties ;

import org.apache.tools.ant.Project ;
import org.apache.tools.ant.types.FileSet ;
import org.apache.tools.ant.types.Resource ;
import org.apache.tools.ant.types.resources.FileResource ;
import org.apache.velocity.VelocityContext ;
import org.apache.velocity.app.Velocity ;

public class BuildJSModule implements IBuildJSModule {

    private String outPath ;

    private String jsSrcPath ;

    public BuildJSModule(String jsSrcPath, String outPath) {
        super() ;
        this.outPath = outPath ;
        this.jsSrcPath = jsSrcPath ;
    }

    private void mkdirOutPath() throws Exception {
        File outFile = new File(outPath) ;

        if (!outFile.exists()) outFile.mkdirs() ;
    }

    private void buildGrid(VelocityContext context, File vmFile, JavaScriptCommBean javaScriptCommBean, Class<?> clazz) throws Exception {

        IBuildJavaScriptFile buildConfigs = new BuildConfigs(vmFile, javaScriptCommBean) ;
        buildConfigs.build(context, clazz) ;
    }

    private void buildDefaults(VelocityContext context, File vmFile, JavaScriptCommBean javaScriptCommBean, Class<?> clazz) throws Exception {

        IBuildJavaScriptFile buildConfigs = new DefaultBuildJavaScriptFile(vmFile, javaScriptCommBean) ;
        buildConfigs.build(context, clazz) ;
    }

    @Override
    public void build(String moduleName, Class<?> clazz) throws Exception {

        mkdirOutPath() ;

        JavaScriptCommBean javaScriptCommBean = new JavaScriptCommBean() ;
        javaScriptCommBean.setModule(moduleName) ;
        javaScriptCommBean.setOutPath(outPath) ;

        Properties p = new Properties() ;
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8") ;
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8") ;
        Velocity.init(p) ;

        // 创建一个上下文环境，此实例是非线程安全的 
        VelocityContext context = new VelocityContext() ;
        context.put("javaScript", javaScriptCommBean);
        
        Project prj = new Project() ;
        //        List<FileSet> filesets = new ArrayList<FileSet>() ;
        FileSet srcs = new FileSet() ;
        srcs.setProject(prj) ;
        srcs.setIncludes("**/**/*.js") ;
        srcs.setDir(new File(jsSrcPath)) ;
        //        filesets.add(xx1) ;
        Iterator<Resource> src = srcs.iterator() ;
        while (src.hasNext()) {
            FileResource resource = (FileResource) src.next() ;
            File vmFile = resource.getFile() ;
            switch (vmFile.getName()) {
            case "action_properties.js":
                buildGrid(context, vmFile, javaScriptCommBean, clazz) ;
                break ;

            default:
                buildDefaults(context, vmFile, javaScriptCommBean, clazz) ;
                break ;
            }

        }

    }

    public void setOutPath(String outPath) {
        this.outPath = outPath ;
    }

    public void setJsSrcPath(String jsSrcPath) {
        this.jsSrcPath = jsSrcPath ;
    }

}
