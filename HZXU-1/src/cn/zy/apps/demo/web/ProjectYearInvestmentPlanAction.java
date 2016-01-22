package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.IProjectYearInvestmentPlanService ;
import cn.zy.apps.demo.units.search.bean.ProjectYearInvestmentPlanSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectYearInvestmentPlanAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectYearInvestmentPlanAction extends ABDemoSystemAction<ProjectYearInvestmentPlan, ProjectYearInvestmentPlanSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectYearInvestmentPlanService.name)
    private IProjectYearInvestmentPlanService service ;
	
	 // 记得要设置 Get Set 
	 private ProjectYearInvestmentPlan  projectyearinvestmentplan ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectyearinvestmentplan) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectyearinvestmentplan) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String get() throws Exception {
        try {
            this.result = service.get(uuid) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;

        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String list() throws Exception {
        try {
            SelectPage<ProjectYearInvestmentPlan> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectYearInvestmentPlan getProjectyearinvestmentplan() {
        return projectyearinvestmentplan ;
    }

    public void setProjectyearinvestmentplan(ProjectYearInvestmentPlan projectyearinvestmentplan) {
        this.projectyearinvestmentplan = projectyearinvestmentplan ;
    }

}
