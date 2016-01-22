package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectMonthInvestmentPlanRemoveUnits")
public class ProjectMonthInvestmentPlanRemoveUnits extends ABCommonsService {

    public ProjectMonthInvestmentPlan  remove(OptType optType, ProjectMonthInvestmentPlan  optProjectMonthInvestmentPlan ) throws SystemOptServiceException {
        
         Integer id =optProjectMonthInvestmentPlan.getId() ;
        ProjectMonthInvestmentPlan   removeProjectMonthInvestmentPlan =baseService.get(id, ProjectMonthInvestmentPlan.class);
        baseService.remove(removeProjectMonthInvestmentPlan);
        return  removeProjectMonthInvestmentPlan ;
    }

}
