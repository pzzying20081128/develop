package cn.zy.apps.tools.dev.service ;

import java.io.File ;

import org.apache.velocity.VelocityContext ;

public abstract class IBuildJavaServiceFile {

    protected File velocityFile ;
    
    protected  String outPath;

    protected BuildJavaCommProperties buildJavaCommProperties ;

    public IBuildJavaServiceFile(File velocityFile, BuildJavaCommProperties buildJavaCommProperties) {
        super() ;
        this.velocityFile = velocityFile ;
        this.buildJavaCommProperties = buildJavaCommProperties ;
    }

    public abstract void build(VelocityContext context, Class<?> clazz,String outFile) throws Exception ;

    public void setOutPath(String outPath) {
        this.outPath = outPath ;
    }

}
