package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.service.IProjectMajorTypeService ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectMajorTypeAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectMajorTypeAction extends ABDemoSystemAction<ProjectMajorType, ProjectMajorTypeSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectMajorTypeService.name)
    private IProjectMajorTypeService service ;
	
	 // 记得要设置 Get Set 
	 private ProjectMajorType  projectmajortype ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectmajortype) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectmajortype) ;
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
            SelectPage<ProjectMajorType> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectMajorType getProjectmajortype() {
        return projectmajortype ;
    }

    public void setProjectmajortype(ProjectMajorType projectmajortype) {
        this.projectmajortype = projectmajortype ;
    }

}
