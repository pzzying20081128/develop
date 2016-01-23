package cn.zy.apps.demo.web.mobile ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.IProjectCarriedOutInfoService ;
import cn.zy.apps.demo.service.IProjectMonthInvestmentPlanService ;
import cn.zy.apps.demo.service.IProjectYearInvestmentPlanService ;
import cn.zy.apps.demo.units.search.bean.DemoStockSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectMonthInvestmentPlanSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectYearInvestmentPlanSearchBean ;
import cn.zy.apps.demo.web.ABDemoSystemAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("MobileProjectAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class MobileProjectAction extends ABDemoSystemAction<ProjectCarriedOutInfo, ProjectCarriedOutInfoSearchBean> {

    @Autowired
    @Qualifier(IProjectCarriedOutInfoService.name)
    IProjectCarriedOutInfoService service ;

    @Autowired
    @Qualifier(IProjectYearInvestmentPlanService.name)
    IProjectYearInvestmentPlanService iProjectYearInvestmentPlanService ;

    @Autowired
    @Qualifier(IProjectMonthInvestmentPlanService.name)
    IProjectMonthInvestmentPlanService iProjectMonthInvestmentPlanService ;

    private List<ProjectCarriedOutInfo> projectCarriedOutInfos ;

    private ProjectCarriedOutInfo projectCarriedOutInfo ;

    private List<ProjectYearInvestmentPlan> projectYearInvestmentPlans ;

    private List<ProjectMonthInvestmentPlan> projectMonthInvestmentPlans ;

    public String mobile_list_key_project() throws Exception {
        try {
            projectCarriedOutInfos = service.searchList(optType, searchBean, null, 0, 10) ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

        return SUCCESS ;

    }

    public String mobile_get_key_project() throws Exception {
        try {
            projectCarriedOutInfo = service.get(uuid) ;
            writeObjectService.intToPrpertiesUnits(projectCarriedOutInfo) ;
            ProjectYearInvestmentPlanSearchBean projectYearInvestmentPlanSearchBean = new ProjectYearInvestmentPlanSearchBean() ;
            projectYearInvestmentPlanSearchBean.setProjectCarriedOutInfoId(uuid) ;
            projectYearInvestmentPlans = iProjectYearInvestmentPlanService.searchList(OptType.search, projectYearInvestmentPlanSearchBean, null) ;

        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

        return SUCCESS ;
    }

    public String mobile_get_month_investmentPlans() throws Exception {
        try {
            ProjectMonthInvestmentPlanSearchBean searchBean = new ProjectMonthInvestmentPlanSearchBean() ;
            searchBean.setProjectYearInvestmentPlanId(uuid) ;
            projectMonthInvestmentPlans = this.iProjectMonthInvestmentPlanService.searchList(OptType.search, searchBean, null) ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

        return SUCCESS ;
    }

    public List<ProjectCarriedOutInfo> getProjectCarriedOutInfos() {
        return projectCarriedOutInfos ;
    }

    public void setProjectCarriedOutInfos(List<ProjectCarriedOutInfo> projectCarriedOutInfos) {
        this.projectCarriedOutInfos = projectCarriedOutInfos ;
    }

    public ProjectCarriedOutInfo getProjectCarriedOutInfo() {
        return projectCarriedOutInfo ;
    }

    public void setProjectCarriedOutInfo(ProjectCarriedOutInfo projectCarriedOutInfo) {
        this.projectCarriedOutInfo = projectCarriedOutInfo ;
    }

    public List<ProjectYearInvestmentPlan> getProjectYearInvestmentPlans() {
        return projectYearInvestmentPlans ;
    }

    public void setProjectYearInvestmentPlans(List<ProjectYearInvestmentPlan> projectYearInvestmentPlans) {
        this.projectYearInvestmentPlans = projectYearInvestmentPlans ;
    }

    public List<ProjectMonthInvestmentPlan> getProjectMonthInvestmentPlans() {
        return projectMonthInvestmentPlans ;
    }

    public void setProjectMonthInvestmentPlans(List<ProjectMonthInvestmentPlan> projectMonthInvestmentPlans) {
        this.projectMonthInvestmentPlans = projectMonthInvestmentPlans ;
    }

}
