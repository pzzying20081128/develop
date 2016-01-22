package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectYearInvestmentPlanRemoveUnits")
public class ProjectYearInvestmentPlanRemoveUnits extends ABCommonsService {

    public ProjectYearInvestmentPlan  remove(OptType optType, ProjectYearInvestmentPlan  optProjectYearInvestmentPlan ) throws SystemOptServiceException {
        
         Integer id =optProjectYearInvestmentPlan.getId() ;
        ProjectYearInvestmentPlan   removeProjectYearInvestmentPlan =baseService.get(id, ProjectYearInvestmentPlan.class);
        baseService.remove(removeProjectYearInvestmentPlan);
        return  removeProjectYearInvestmentPlan ;
    }

}
