package cn.zy.apps.tools.dev.javascript;

import java.io.File ;
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.Reader ;

import org.apache.tools.ant.types.resources.FileResource ;
import org.apache.velocity.VelocityContext ;
import org.apache.velocity.app.Velocity ;

public class DefaultBuildJavaScriptFile extends  IBuildJavaScriptFile {
    
 
    public DefaultBuildJavaScriptFile(File velocityFile, JavaScriptCommBean javaScriptCommBean) {
        super(velocityFile, javaScriptCommBean  ) ;
     
    }

    @Override
    public void build(VelocityContext context, Class<?> clazz) throws Exception {
     
      
        File vmFile = velocityFile;

        // 通过一个FileReader读取模板文件 
        Reader reader = new FileReader(vmFile) ;
        // 创建一个字符串输出流，模板输出的目标 
        String xx = javaScriptCommBean.getModule() + "_" + vmFile.getName() ;
        File outFile = new File(javaScriptCommBean.getOutPath(), xx) ;

        FileWriter writer = new FileWriter(outFile) ;
        //          // 根据模板上下文对模板求值，logMsgName字符串为发生异常时候记录模板异常提供方便 
        Velocity.evaluate(context, writer, "build "+xx, reader) ;
        // 将求值的结果从控制台输出 
        //          //System.out.println(writer.toString());
        writer.flush() ;
        writer.close() ;

    }

}
