package cn.zy.apps.tools.dev.builds ;

import java.io.File ;

import org.apache.velocity.VelocityContext ;

public abstract class IBuildJavaScriptFile {

    protected File velocityFile ;

    protected JavaScriptCommBean javaScriptCommBean ;

    public IBuildJavaScriptFile(File velocityFile, JavaScriptCommBean javaScriptCommBean) {
        super() ;
        this.velocityFile = velocityFile ;
        this.javaScriptCommBean = javaScriptCommBean ;
    }

    public abstract void build(VelocityContext context, Class<?> clazz) throws Exception ;

}
