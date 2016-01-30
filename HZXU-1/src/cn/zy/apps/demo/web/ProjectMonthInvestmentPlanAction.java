package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.service.IProjectMonthInvestmentPlanService ;
import cn.zy.apps.demo.units.search.bean.ProjectMonthInvestmentPlanSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectMonthInvestmentPlanAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectMonthInvestmentPlanAction extends ABDemoSystemAction<ProjectMonthInvestmentPlan, ProjectMonthInvestmentPlanSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectMonthInvestmentPlanService.name)
    private IProjectMonthInvestmentPlanService service ;

    // 记得要设置 Get Set 
    private ProjectMonthInvestmentPlan projectmonthinvestmentplan ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectmonthinvestmentplan) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectmonthinvestmentplan) ;
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
    
    public String complete() throws Exception {
        try {
            this.result = service.complete(uuid) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;

        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    
    
    
    public String list() throws Exception {
        try {
            SelectPage<ProjectMonthInvestmentPlan> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectMonthInvestmentPlan getProjectmonthinvestmentplan() {
        return projectmonthinvestmentplan ;
    }

    public void setProjectmonthinvestmentplan(ProjectMonthInvestmentPlan projectmonthinvestmentplan) {
        this.projectmonthinvestmentplan = projectmonthinvestmentplan ;
    }

}
