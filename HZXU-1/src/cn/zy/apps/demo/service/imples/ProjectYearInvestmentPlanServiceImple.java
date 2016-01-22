 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectYearInvestmentPlanService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectYearInvestmentPlanRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectYearInvestmentPlanSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectYearInvestmentPlanSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectYearInvestmentPlanSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectYearInvestmentPlanService.name)
public class ProjectYearInvestmentPlanServiceImple extends  ABCommonsService  implements IProjectYearInvestmentPlanService {

            //@Resource(name="ProjectYearInvestmentPlanSearchUnits")
			  @Autowired
            @Qualifier("ProjectYearInvestmentPlanSearchUnits")        
            private  ProjectYearInvestmentPlanSearchUnits  iProjectYearInvestmentPlanSearchUnits;
           
           //@Resource(name=" ProjectYearInvestmentPlanSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectYearInvestmentPlanSaveUpdateUnits")      
           private ProjectYearInvestmentPlanSaveUpdateUnits  iProjectYearInvestmentPlanSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectYearInvestmentPlanRemoveUnits")
    private ProjectYearInvestmentPlanRemoveUnits iProjectYearInvestmentPlanRemoveUnits ;
		   
			@Override
            public ProjectYearInvestmentPlan saveUpdate(OptType  optType ,   ProjectYearInvestmentPlan   optProjectYearInvestmentPlan )throws SystemOptServiceException{
        	     return 	 iProjectYearInvestmentPlanSaveUpdateUnits.saveUpdate(optType, optProjectYearInvestmentPlan);
        		}
            
       	   @Override
            public SelectPage<ProjectYearInvestmentPlan > search(OptType  optType ,    
				   ProjectYearInvestmentPlanSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectYearInvestmentPlanSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectYearInvestmentPlan > searchList(OptType  optType ,    
				           ProjectYearInvestmentPlanSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectYearInvestmentPlanSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectYearInvestmentPlan   remove(OptType  optType ,   ProjectYearInvestmentPlan   optProjectYearInvestmentPlan)throws SystemOptServiceException{
			      return   iProjectYearInvestmentPlanRemoveUnits.remove(optType, optProjectYearInvestmentPlan);
			  }
			  
			   @Override
            public ProjectYearInvestmentPlan get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectYearInvestmentPlan.class) ;
            }
            
            
}
