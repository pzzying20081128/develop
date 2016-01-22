package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.IProjectProgressTypeService ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectProgressTypeAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectProgressTypeAction extends ABDemoSystemAction<ProjectProgressType, ProjectProgressTypeSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectProgressTypeService.name)
    private IProjectProgressTypeService service ;
	
	 // 记得要设置 Get Set 
	 private ProjectProgressType  projectprogresstype ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectprogresstype) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectprogresstype) ;
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
            SelectPage<ProjectProgressType> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectProgressType getProjectprogresstype() {
        return projectprogresstype ;
    }

    public void setProjectprogresstype(ProjectProgressType projectprogresstype) {
        this.projectprogresstype = projectprogresstype ;
    }

}
