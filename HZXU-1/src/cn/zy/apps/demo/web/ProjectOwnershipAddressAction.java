package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.service.IProjectOwnershipAddressService ;
import cn.zy.apps.demo.units.search.bean.ProjectOwnershipAddressSearchBean ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectOwnershipAddressAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectOwnershipAddressAction extends ABDemoSystemAction<ProjectOwnershipAddress, ProjectOwnershipAddressSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IProjectOwnershipAddressService.name)
    private IProjectOwnershipAddressService service ;

    // 记得要设置 Get Set 
    private ProjectOwnershipAddress projectownershipaddress ;

    public String save() throws Exception {
        try {

            this.result = service.saveUpdate(optType, projectownershipaddress) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, projectownershipaddress) ;
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
            SelectPage<ProjectOwnershipAddress> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public ProjectOwnershipAddress getProjectownershipaddress() {
        return projectownershipaddress ;
    }

    public void setProjectownershipaddress(ProjectOwnershipAddress projectownershipaddress) {
        this.projectownershipaddress = projectownershipaddress ;
    }

}
