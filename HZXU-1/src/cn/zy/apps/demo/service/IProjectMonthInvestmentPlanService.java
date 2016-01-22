 package  cn.zy.apps.demo.service;

 
 
 import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.units.search.bean.ProjectMonthInvestmentPlanSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectMonthInvestmentPlanService   { 
    
            public String name="IProjectMonthInvestmentPlanService";
			
			
			 /**
             *  增加或更新
             */
            public ProjectMonthInvestmentPlan   saveUpdate(OptType  optType ,   ProjectMonthInvestmentPlan   optProjectMonthInvestmentPlan )throws SystemOptServiceException;
            
       	  
            public SelectPage<ProjectMonthInvestmentPlan > search(OptType  optType ,    
				           ProjectMonthInvestmentPlanSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
			public List<ProjectMonthInvestmentPlan > searchList(OptType  optType ,    
				           ProjectMonthInvestmentPlanSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
            public  ProjectMonthInvestmentPlan    remove(OptType  optType ,  ProjectMonthInvestmentPlan   optProjectMonthInvestmentPlan)throws SystemOptServiceException;
            
            
           public  ProjectMonthInvestmentPlan get(Integer id)throws SystemOptServiceException;
            
                
        
            
            
            

}
