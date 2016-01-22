package cn.zy.apps.demo.web.search ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.service.IProjectOwnershipAddressService ;
import cn.zy.apps.demo.units.search.bean.ProjectOwnershipAddressSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectOwnershipAddressSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectOwnershipAddressSearchAction extends ABDemoSearchAction<ProjectOwnershipAddressSearchBean> {

    @Autowired
    @Qualifier(IProjectOwnershipAddressService.name)
    private IProjectOwnershipAddressService service ;

    @Override
    protected List<ProjectOwnershipAddress> searchResult() throws Exception {
        return service.searchList(OptType.search, searchBean, null) ;

    }

}
