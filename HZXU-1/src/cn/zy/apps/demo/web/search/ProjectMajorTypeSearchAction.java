package cn.zy.apps.demo.web.search ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.service.IProjectMajorTypeService ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectMajorTypeSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectMajorTypeSearchAction extends ABDemoSearchAction<ProjectMajorTypeSearchBean> {

    @Autowired
    @Qualifier(IProjectMajorTypeService.name)
    private IProjectMajorTypeService   service;
    @Override
    protected List<ProjectMajorType> searchResult() throws Exception {
        
        return service.searchList(OptType.search, searchBean, null) ;
    }

}
