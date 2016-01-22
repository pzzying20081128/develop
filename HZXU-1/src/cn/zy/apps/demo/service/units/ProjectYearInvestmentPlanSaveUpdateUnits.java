package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectYearInvestmentPlanSaveUpdateUnits")
public class ProjectYearInvestmentPlanSaveUpdateUnits extends ABCommonsService {

    public ProjectYearInvestmentPlan saveUpdate(OptType optType, ProjectYearInvestmentPlan optProjectYearInvestmentPlan) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectYearInvestmentPlan) ;

        case update:
            return update(optProjectYearInvestmentPlan) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectYearInvestmentPlan save(ProjectYearInvestmentPlan optProjectYearInvestmentPlan) throws SystemOptServiceException {
        Integer    getProjectCarriedOutInfoId = optProjectYearInvestmentPlan.getProjectCarriedOutInfoId();
        ProjectCarriedOutInfo  projectCarriedOutInfo =baseService.get(ProjectCarriedOutInfo.class, getProjectCarriedOutInfoId); 
        optProjectYearInvestmentPlan.setProjectCarriedOutInfo(projectCarriedOutInfo);
        baseService.save(optProjectYearInvestmentPlan) ;
        return optProjectYearInvestmentPlan ;
    }

    public ProjectYearInvestmentPlan update(ProjectYearInvestmentPlan optProjectYearInvestmentPlan) throws SystemOptServiceException {

        ProjectYearInvestmentPlan projectYearInvestmentPlan = baseService.get(ProjectYearInvestmentPlan.class, optProjectYearInvestmentPlan.getId()) ;
        projectYearInvestmentPlan.setInvestmentPlan(optProjectYearInvestmentPlan.getInvestmentPlan()) ;
        projectYearInvestmentPlan.setYear(optProjectYearInvestmentPlan.getYear());
        baseService.update(projectYearInvestmentPlan) ;
        return projectYearInvestmentPlan ;
    }

}
