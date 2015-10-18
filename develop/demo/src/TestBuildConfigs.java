import cn.zy.apps.tools.dev.javascript.BuildConfigs ;
import cn.zy.apps.tools.dev.javascript.BuildJSModule ;
import cn.zy.apps.tools.dev.javascript.IBuildJSModule ;
import cn.zy.apps.tools.dev.javascript.IBuildJavaScriptFile ;
import cn.zy.apps.tools.test.StaffInfo1 ;
import cn.zy.apps.tools.test.SystemUser1 ;


public class TestBuildConfigs {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
//        IBuildConfigs buildConfigs = new BuildConfigs("/media/you/MY_WORKSHOPS/tools/git/project/develop/develop/release/build") ;
//        buildConfigs.build("test", StaffInfo.class) ;
        
//        String outPath, String jsSrcPath
        String jsSrcPath  =  "/media/you/MY_WORKSHOPS/tools/git/project/develop/develop/src/cn/zy/apps/tools/dev/builds/js";
        String out ="/media/you/MY_WORKSHOPS/tools/git/project/develop/develop/release/build";
        IBuildJSModule buildJSModule=new BuildJSModule(jsSrcPath ,out );
        buildJSModule.build("SystemUser",SystemUser1.class);

    }

}
