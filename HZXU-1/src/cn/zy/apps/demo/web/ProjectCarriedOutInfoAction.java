package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.IProjectCarriedOutInfoService ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectCarriedOutInfoAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectCarriedOutInfoAction extends ABDemoSystemAction<ProjectCarriedOutInfo, ProjectCarriedOutInfoSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectCarriedOutInfoService.name)
    private IProjectCarriedOutInfoService service ;
	
	 // 记得要设置 Get Set 
	 private ProjectCarriedOutInfo  projectcarriedoutinfo ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectcarriedoutinfo) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectcarriedoutinfo) ;
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
            SelectPage<ProjectCarriedOutInfo> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectCarriedOutInfo getProjectcarriedoutinfo() {
        return projectcarriedoutinfo ;
    }

    public void setProjectcarriedoutinfo(ProjectCarriedOutInfo projectcarriedoutinfo) {
        this.projectcarriedoutinfo = projectcarriedoutinfo ;
    }

}
