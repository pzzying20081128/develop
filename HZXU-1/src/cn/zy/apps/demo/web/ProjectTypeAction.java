package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.IProjectTypeService ;
import cn.zy.apps.demo.units.search.bean.ProjectTypeSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectTypeAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectTypeAction extends ABDemoSystemAction<ProjectType, ProjectTypeSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectTypeService.name)
    private IProjectTypeService service ;

    // 记得要设置 Get Set 
    private ProjectType projecttype ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projecttype) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            this.result =  service.remove(optType, projecttype) ;
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
            SelectPage<ProjectType> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectType getProjecttype() {
        return projecttype ;
    }

    public void setProjecttype(ProjectType projecttype) {
        this.projecttype = projecttype ;
    }

}
