package cn.zy.apps.demo.web ;

import java.util.List ;

import org.springframework.web.context.WebApplicationContext ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.IDemoUserService ;
import cn.zy.apps.demo.service.IProjectMajorTypeService ;
import cn.zy.apps.demo.service.IProjectOwnershipAddressService ;
import cn.zy.apps.demo.service.IProjectProgressTypeService ;
import cn.zy.apps.demo.units.search.bean.DemoUserSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectOwnershipAddressSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.ABConfigInitListener ;

public class DemoConfigInitListener extends ABConfigInitListener {

    protected PrpertiesAutoWriteObjectService writeObjectService = PrpertiesAutoWriteObjectService.instance() ;

    @Override
    protected void load(WebApplicationContext springContext) {
        IDemoUserService iDemoUserService = (IDemoUserService) springContext.getBean(IDemoUserService.name) ;
        DemoUserSearchBean searchBean = new DemoUserSearchBean() ;
        List<DemoUser> users = iDemoUserService.searchList(OptType.search, searchBean, null) ;

        for (DemoUser user : users) {
            writeObjectService.searchAutoWriteObject().cacheObject(user.getId().toString(), user) ;
        }
        loadProjectProgressType(springContext) ;
        loadProjectMajorType(springContext) ;
        loadProjectOwnershipAddress(springContext) ;
    }

    private void loadProjectProgressType(WebApplicationContext springContext) {
        IProjectProgressTypeService service = (IProjectProgressTypeService) springContext.getBean(IProjectProgressTypeService.name) ;

        ProjectProgressTypeSearchBean searchBean = new ProjectProgressTypeSearchBean() ;

        List<ProjectProgressType> results = service.searchList(OptType.search, searchBean, null) ;
        System.out.println("==============  loadProjectProgressType  >   "+results.size()) ;
        for (ProjectProgressType result : results) {
            writeObjectService.searchAutoWriteObject().cacheObject(result.getId().toString(), result) ;
        }

    }

    private void loadProjectMajorType(WebApplicationContext springContext) {
        IProjectMajorTypeService service = (IProjectMajorTypeService) springContext.getBean(IProjectMajorTypeService.name) ;

        ProjectMajorTypeSearchBean searchBean = new ProjectMajorTypeSearchBean() ;

        List<ProjectMajorType> results = service.searchList(OptType.search, searchBean, null) ;
        System.out.println("==============  loadProjectMajorType  >   "+results.size()) ;
        for (ProjectMajorType result : results) {
            writeObjectService.searchAutoWriteObject().cacheObject(result.getId().toString(), result) ;
        }

    }

    private void loadProjectOwnershipAddress(WebApplicationContext springContext) {
        IProjectOwnershipAddressService service = (IProjectOwnershipAddressService) springContext.getBean(IProjectOwnershipAddressService.name) ;

        ProjectOwnershipAddressSearchBean searchBean = new ProjectOwnershipAddressSearchBean() ;

        List<ProjectOwnershipAddress> results = service.searchList(OptType.search, searchBean, null) ;
        System.out.println("==============  loadProjectOwnershipAddress  >   "+results.size()) ;
        for (ProjectOwnershipAddress result : results) {
            writeObjectService.searchAutoWriteObject().cacheObject(result.getId().toString(), result) ;
        }

    }

}
