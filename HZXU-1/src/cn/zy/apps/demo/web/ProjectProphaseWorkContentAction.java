package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseWorkContent ;
import cn.zy.apps.demo.service.IProjectProphaseWorkContentService ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseWorkContentSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectProphaseWorkContentAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectProphaseWorkContentAction extends ABDemoSystemAction<ProjectProphaseWorkContent, ProjectProphaseWorkContentSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectProphaseWorkContentService.name)
    private IProjectProphaseWorkContentService service ;
	
	 // 记得要设置 Get Set 
	 private ProjectProphaseWorkContent  projectprophaseworkcontent ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectprophaseworkcontent) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectprophaseworkcontent) ;
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
            SelectPage<ProjectProphaseWorkContent> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectProphaseWorkContent getProjectprophaseworkcontent() {
        return projectprophaseworkcontent ;
    }

    public void setProjectprophaseworkcontent(ProjectProphaseWorkContent projectprophaseworkcontent) {
        this.projectprophaseworkcontent = projectprophaseworkcontent ;
    }

}
