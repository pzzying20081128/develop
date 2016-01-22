package cn.zy.apps.demo.web.search ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.IProjectProgressTypeService ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectProgressTypeSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectProgressTypeSearchAction extends ABDemoSearchAction<ProjectProgressTypeSearchBean> {

    @Autowired
    @Qualifier(IProjectProgressTypeService.name)
    private IProjectProgressTypeService service ;

    @Override
    protected List<ProjectProgressType> searchResult() throws Exception {

        return service.searchList(OptType.search, searchBean, null) ;
    }

}
