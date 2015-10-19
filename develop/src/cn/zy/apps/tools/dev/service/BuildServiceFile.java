package cn.zy.apps.tools.dev.service ;

import java.io.File ;
import java.util.Properties ;

import org.apache.velocity.VelocityContext ;
import org.apache.velocity.app.Velocity ;

public class BuildServiceFile implements IBuildServiceFile {

    private String basePath ;

    private String templatePath ;

    public BuildServiceFile(String templatePath, String basePath) {
        super() ;
        this.basePath = basePath ;
        this.templatePath = templatePath ;
    }

    private void mkdirOutPath() throws Exception {
        File outFile = new File(basePath) ;

        if (!outFile.exists()) outFile.mkdirs() ;
    }

    private void buildServiceFile(VelocityContext context, File vmFile,

    BuildJavaCommProperties buildJavaCommProperties, Class<?> clazz, String outPath , String outFile) throws Exception {
        
        File outPathFile = new File(outPath) ;

        if (!outPathFile.exists()) outPathFile.mkdirs() ;

        IBuildJavaServiceFile buildConfigs = new DefaultBuildJavaServiceFile(vmFile, buildJavaCommProperties) ;
        buildConfigs.setOutPath(outPath) ;
        buildConfigs.build(context, clazz, outFile) ;
    }

    @Override
    public void build(String  servicePackage, Class<?> clazz) throws Exception {

        mkdirOutPath() ;

        BuildJavaCommProperties buildJavaCommProperties = new BuildJavaCommProperties() ;
        buildJavaCommProperties.setClassName(clazz.getSimpleName()) ;
        buildJavaCommProperties.setClssPackage(clazz.getPackage().getName()) ;
        buildJavaCommProperties.setServicePackage(servicePackage);

        Properties p = new Properties() ;
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8") ;
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8") ;
        Velocity.init(p) ;

        // 创建一个上下文环境，此实例是非线程安全的 
        VelocityContext context = new VelocityContext() ;
        context.put("buildJavaCommProperties", buildJavaCommProperties) ;
        {
        File vmFile_I = new File(templatePath + "/IService.java.vm") ;

        // build 接口文件

        String outFile = "I" + clazz.getSimpleName() + "Service.java" ;

        buildServiceFile(context, vmFile_I, buildJavaCommProperties, clazz, basePath, outFile) ;
        }
        // build 实现类
        {
        File vmFile_Imple = new File(templatePath + "/IServiceImple.java.vm") ;

        String outFile_Imple =  clazz.getSimpleName() + "ServiceImple.java" ;

        buildServiceFile(context, vmFile_Imple, buildJavaCommProperties, clazz, basePath+"/imples" ,outFile_Imple) ;
        }
        // build查询
        {
        File vmFile_search = new File(templatePath + "/SearchBean.java.vm") ;

        String outFile_search =  clazz.getSimpleName() + "SearchBean.java" ;

        buildServiceFile(context, vmFile_search, buildJavaCommProperties, clazz, basePath+"/units/search/bean",outFile_search) ;
        }
        //
     // build   SaveUpdateUnits.java.vm
        {
        File vmFile_SaveUpdateUnits = new File(templatePath + "/SaveUpdateUnits.java.vm") ;

        String outFile_SaveUpdateUnits =  clazz.getSimpleName() + "SaveUpdateUnits.java" ;

        buildServiceFile(context, vmFile_SaveUpdateUnits, buildJavaCommProperties, clazz, basePath+"/units",outFile_SaveUpdateUnits) ;
        }
        {
            //StaffInfoSearchUnits.java.vm
            File vmFile= new File(templatePath + "/SearchUnits.java.vm") ;

            String outFile =  clazz.getSimpleName() + "SearchUnits.java" ;

            buildServiceFile(context, vmFile, buildJavaCommProperties, clazz, basePath+"/units",outFile) ;
            
        }
        
        {
            //RemoveUnits.java.vm
            File vmFile= new File(templatePath + "/RemoveUnits.java.vm") ;

            String outFile =  clazz.getSimpleName() + "RemoveUnits.java" ;

            buildServiceFile(context, vmFile, buildJavaCommProperties, clazz, basePath+"/units",outFile) ;
            
        }
        
        {
            //RemoveUnits.java.vm
            File vmFile= new File(templatePath + "/IAopService.java.vm") ;

            String outFile ="IAop"+clazz.getSimpleName() + "Service.java" ;

            buildServiceFile(context, vmFile, buildJavaCommProperties, clazz, basePath+"/web/aop",outFile) ;
            
        }
        
        {
            //RemoveUnits.java.vm
            File vmFile= new File(templatePath + "/AopService.java.vm") ;

            String outFile ="Aop"+clazz.getSimpleName() + "Service.java" ;

            buildServiceFile(context, vmFile, buildJavaCommProperties, clazz, basePath+"/web/aop",outFile) ;
            
        }
        
        
        {
            //RemoveUnits.java.vm
            File vmFile= new File(templatePath + "/InfaceService.java.vm") ;

            String outFile ="Inface"+clazz.getSimpleName() + "Service.java" ;

            buildServiceFile(context, vmFile, buildJavaCommProperties, clazz, basePath+"/inface",outFile) ;
            
        }
        
        


    }

}
