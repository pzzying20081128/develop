 package  cn.zy.apps.demo.service;

 
 
 import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.units.search.bean.ProjectYearInvestmentPlanSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectYearInvestmentPlanService   { 
    
            public String name="IProjectYearInvestmentPlanService";
			
			
			 /**
             *  增加或更新
             */
            public ProjectYearInvestmentPlan   saveUpdate(OptType  optType ,   ProjectYearInvestmentPlan   optProjectYearInvestmentPlan )throws SystemOptServiceException;
            
       	  
            public SelectPage<ProjectYearInvestmentPlan > search(OptType  optType ,    
				           ProjectYearInvestmentPlanSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
			public List<ProjectYearInvestmentPlan > searchList(OptType  optType ,    
				           ProjectYearInvestmentPlanSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
            public  ProjectYearInvestmentPlan    remove(OptType  optType ,  ProjectYearInvestmentPlan   optProjectYearInvestmentPlan)throws SystemOptServiceException;
            
            
           public  ProjectYearInvestmentPlan get(Integer id)throws SystemOptServiceException;
            
                
        
            
            
            

}
