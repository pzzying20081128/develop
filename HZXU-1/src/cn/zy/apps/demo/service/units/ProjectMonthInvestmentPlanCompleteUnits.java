package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig ;
import cn.zy.apps.demo.HZXUProjectConfig.Complete ;
import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectMonthInvestmentPlanCompleteUnits")
public class ProjectMonthInvestmentPlanCompleteUnits extends ABCommonsService {

    public ProjectMonthInvestmentPlan complete(Integer id) throws SystemOptServiceException {
        ProjectMonthInvestmentPlan projectMonthInvestmentPlan = baseService.get(ProjectMonthInvestmentPlan.class, id) ;
        Complete complete = projectMonthInvestmentPlan.getComplete() ;
        if (complete.equals(Complete.已完成)) {
            projectMonthInvestmentPlan.setComplete(Complete.末完成) ;
        } else {
            projectMonthInvestmentPlan.setComplete(Complete.已完成) ;
        }
        baseService.update(projectMonthInvestmentPlan) ;
        return projectMonthInvestmentPlan ;
    }

}
