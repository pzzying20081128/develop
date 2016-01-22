package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.service.IProjectProphaseInfoService ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectProphaseInfoAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectProphaseInfoAction extends ABDemoSystemAction<ProjectProphaseInfo, ProjectProphaseInfoSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectProphaseInfoService.name)
    private IProjectProphaseInfoService service ;

    // 记得要设置 Get Set 
    private ProjectProphaseInfo projectprophaseinfo ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectprophaseinfo) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectprophaseinfo) ;
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
            SelectPage<ProjectProphaseInfo> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectProphaseInfo getProjectprophaseinfo() {
        return projectprophaseinfo ;
    }

    public void setProjectprophaseinfo(ProjectProphaseInfo projectprophaseinfo) {
        this.projectprophaseinfo = projectprophaseinfo ;
    }

}
