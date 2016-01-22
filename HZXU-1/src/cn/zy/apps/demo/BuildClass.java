package cn.zy.apps.demo;

import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.tools.dev.service.BuildServiceFile ;
import cn.zy.apps.tools.dev.service.IBuildServiceFile ;


public class BuildClass {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        
        String module= "project";
        
        Class<?> clazz = ProjectMonthInvestmentPlan.class;
        
        String className=clazz.getSimpleName();

        String out ="./release/build/service/"+className.toLowerCase();
        
        String templatePath ="/home/you/workspace/DevModule/src/cn/zy/apps/tools/dev/service/template";
        
        String servicePackage="cn.zy.apps.demo.service";
        
        IBuildServiceFile  buildServiceFile  =new BuildServiceFile(  templatePath ,out  );
        
        buildServiceFile.build(servicePackage +"."+module,clazz);

    }

}
