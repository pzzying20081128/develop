package cn.zy.apps.tools.dev.builds ;

import java.io.File ;
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.Reader ;
import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;
import java.util.Properties ;

import org.apache.tools.ant.Project ;
import org.apache.tools.ant.types.FileSet ;
import org.apache.tools.ant.types.Resource ;
import org.apache.tools.ant.types.resources.FileResource ;
import org.apache.velocity.VelocityContext ;
import org.apache.velocity.app.Velocity ;

public class BuildJavaScript {

    private Map<String, Object> velocityBuildValue ;

    public BuildJavaScript(Map<String, Object> velocityBuildValue) {
        super() ;
        this.velocityBuildValue = velocityBuildValue ;
    }

    private void wirteWebfile(VelocityContext context, JavaScriptCommBean javaScriptCommBean, File velocityFile) throws Exception {

        File vmFile = velocityFile ;
        // 通过一个FileReader读取模板文件 
        Reader reader = new FileReader(vmFile) ;
        // 创建一个字符串输出流，模板输出的目标 
        String xx = javaScriptCommBean.getModule() + "_" + vmFile.getName() ;
        File outFile = new File(javaScriptCommBean.getOutPath(), xx) ;
        FileWriter writer = new FileWriter(outFile) ;
        //          // 根据模板上下文对模板求值，logMsgName字符串为发生异常时候记录模板异常提供方便 
        Velocity.evaluate(context, writer, "build " + xx, reader) ;

        writer.flush() ;

        writer.close() ;

    }

    public void build(VelocityContext context, File velocityFile, JavaScriptCommBean javaScriptCommBean) throws Exception {

        //        Properties p = new Properties() ;
        //        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8") ;
        //        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8") ;
        //        Velocity.init(p) ;

        //////////////////////////////////////////////////////////

        context.put("javaScript", javaScriptCommBean) ;

        for (Map.Entry<String, Object> entry : velocityBuildValue.entrySet()) {
            context.put(entry.getKey(), entry.getValue()) ;
        }

        wirteWebfile(context, javaScriptCommBean, velocityFile) ;

    }

}
