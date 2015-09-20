package cn.zy.apps.tools.dev.service ;

import java.io.File ;
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.Reader ;

import org.apache.velocity.VelocityContext ;
import org.apache.velocity.app.Velocity ;

public class DefaultBuildJavaServiceFile extends IBuildJavaServiceFile {
    
    

    public DefaultBuildJavaServiceFile(File velocityFile, BuildJavaCommProperties buildJavaCommProperties) {
        super(velocityFile, buildJavaCommProperties) ;
    }

    @Override
    public void build(VelocityContext context, Class<?> clazz,String outFile) throws Exception {

        File vmFile = velocityFile ;

        // 通过一个FileReader读取模板文件 
        Reader reader = new FileReader(vmFile) ;
        // 创建一个字符串输出流，模板输出的目标   I${buildJavaCommProperties.className}Service
//        String xx = "I" + clazz.getName() + "Service.java" ;
        File outFile_ = new File(outPath, outFile) ;

        FileWriter writer = new FileWriter(outFile_) ;
        //          // 根据模板上下文对模板求值，logMsgName字符串为发生异常时候记录模板异常提供方便 

        Velocity.evaluate(context, writer, "build " + outFile, reader) ;
        // 将求值的结果从控制台输出 
        //          //System.out.println(writer.toString());
        writer.flush() ;
        writer.close() ;

    }

}
