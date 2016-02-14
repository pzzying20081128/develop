package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig ;
import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectMonthInvestmentPlanSaveUpdateUnits")
public class ProjectMonthInvestmentPlanSaveUpdateUnits extends ABCommonsService {

    public ProjectMonthInvestmentPlan saveUpdate(OptType optType, ProjectMonthInvestmentPlan optProjectMonthInvestmentPlan) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectMonthInvestmentPlan) ;

        case update:
            return update(optProjectMonthInvestmentPlan) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectMonthInvestmentPlan save(ProjectMonthInvestmentPlan optProjectMonthInvestmentPlan) throws SystemOptServiceException {

        ProjectYearInvestmentPlan projectYearInvestmentPlan = baseService.get(ProjectYearInvestmentPlan.class, optProjectMonthInvestmentPlan.getProjectYearInvestmentPlanId()) ;

        optProjectMonthInvestmentPlan.setProjectYearInvestmentPlan(projectYearInvestmentPlan) ;
        optProjectMonthInvestmentPlan.setComplete(HZXUProjectConfig.Complete.末完成);
        baseService.save(optProjectMonthInvestmentPlan) ;
        return optProjectMonthInvestmentPlan ;
    }

    public ProjectMonthInvestmentPlan update(ProjectMonthInvestmentPlan optProjectMonthInvestmentPlan) throws SystemOptServiceException {
        ProjectMonthInvestmentPlan projectMonthInvestmentPlan = baseService.get(ProjectMonthInvestmentPlan.class, optProjectMonthInvestmentPlan.getId()) ;

        ToolsUnits.copyBeanProperties(projectMonthInvestmentPlan, optProjectMonthInvestmentPlan, "month", "investmentPlan", 
                    "constructionContent", "existingProblems",
                         "imageProgress","truthInvestment","truthCompletionStatus"  ) ;
        baseService.update(projectMonthInvestmentPlan) ;
        return projectMonthInvestmentPlan ;
    }

}
