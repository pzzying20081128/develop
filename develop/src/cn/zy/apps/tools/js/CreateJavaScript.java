package cn.zy.apps.tools.js ;

import java.io.File ;
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.Reader ;
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

public class CreateJavaScript {

    // js 输出路径
    private  String outPath ;

    private  String module ;

    private  String jsPath ;
    
    

    private void wirteWebfile(VelocityContext context, String module, FileSet srcs) throws Exception {

        Iterator<Resource> src = srcs.iterator() ;
        while (src.hasNext()) {
            FileResource resource = (FileResource) src.next() ;
            File vmFile = resource.getFile() ;

            // 通过一个FileReader读取模板文件 
            Reader reader = new FileReader(vmFile) ;
            // 创建一个字符串输出流，模板输出的目标 
            String xx = vmFile.getName() ;
            if (!xx.equals("index.js")) xx = module + "_" + vmFile.getName() ;
            File outFile = new File(outPath, xx) ;

            FileWriter writer = new FileWriter(outFile) ;
            //          // 根据模板上下文对模板求值，logMsgName字符串为发生异常时候记录模板异常提供方便 
            Velocity.evaluate(context, writer, "erp web file", reader) ;
            // 将求值的结果从控制台输出 
            //          //System.out.println(writer.toString());
            writer.flush() ;
            writer.close() ;
        }

    }

    public void build() throws Exception {
        
        File outFile = new File(outPath) ;
        
        if(!outFile.exists())outFile.mkdirs();
        
        Properties p = new Properties() ;
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8") ;
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8") ;
        Velocity.init(p) ;

         // 创建一个上下文环境，此实例是非线程安全的 
        VelocityContext context = new VelocityContext() ;

        //////////////////////////////////////////////////////////
        JavaScript xx = new JavaScript() ;
        xx.setPackages(module) ;
        context.put("javaScript", xx) ;

        Project prj = new Project() ;
        List<FileSet> filesets = new ArrayList<FileSet>() ;
        FileSet xx1 = new FileSet() ;
        xx1.setProject(prj) ;
        xx1.setIncludes("**/**/*.js") ;
        xx1.setDir(new File(jsPath)) ;

        filesets.add(xx1) ;

        for (FileSet srcs : filesets) {
            wirteWebfile(context, module, srcs) ;
        }

    }

    public String getOutPath() {
        return outPath ;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath ;
    }

    public String getModule() {
        return module ;
    }

    public void setModule(String module) {
        this.module = module ;
    }

    public String getJsPath() {
        return jsPath ;
    }

    public void setJsPath(String jsPath) {
        this.jsPath = jsPath ;
    }

  

}
