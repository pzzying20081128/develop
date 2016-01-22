 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectMonthInvestmentPlanService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectMonthInvestmentPlanRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectMonthInvestmentPlanSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectMonthInvestmentPlanSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectMonthInvestmentPlanSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectMonthInvestmentPlanService.name)
public class ProjectMonthInvestmentPlanServiceImple extends  ABCommonsService  implements IProjectMonthInvestmentPlanService {

            //@Resource(name="ProjectMonthInvestmentPlanSearchUnits")
			  @Autowired
            @Qualifier("ProjectMonthInvestmentPlanSearchUnits")        
            private  ProjectMonthInvestmentPlanSearchUnits  iProjectMonthInvestmentPlanSearchUnits;
           
           //@Resource(name=" ProjectMonthInvestmentPlanSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectMonthInvestmentPlanSaveUpdateUnits")      
           private ProjectMonthInvestmentPlanSaveUpdateUnits  iProjectMonthInvestmentPlanSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectMonthInvestmentPlanRemoveUnits")
    private ProjectMonthInvestmentPlanRemoveUnits iProjectMonthInvestmentPlanRemoveUnits ;
		   
			@Override
            public ProjectMonthInvestmentPlan saveUpdate(OptType  optType ,   ProjectMonthInvestmentPlan   optProjectMonthInvestmentPlan )throws SystemOptServiceException{
        	     return 	 iProjectMonthInvestmentPlanSaveUpdateUnits.saveUpdate(optType, optProjectMonthInvestmentPlan);
        		}
            
       	   @Override
            public SelectPage<ProjectMonthInvestmentPlan > search(OptType  optType ,    
				   ProjectMonthInvestmentPlanSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectMonthInvestmentPlanSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectMonthInvestmentPlan > searchList(OptType  optType ,    
				           ProjectMonthInvestmentPlanSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectMonthInvestmentPlanSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectMonthInvestmentPlan   remove(OptType  optType ,   ProjectMonthInvestmentPlan   optProjectMonthInvestmentPlan)throws SystemOptServiceException{
			      return   iProjectMonthInvestmentPlanRemoveUnits.remove(optType, optProjectMonthInvestmentPlan);
			  }
			  
			   @Override
            public ProjectMonthInvestmentPlan get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectMonthInvestmentPlan.class) ;
            }
            
            
}
