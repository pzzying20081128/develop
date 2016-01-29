package cn.zy.apps.demo.web.search;

import java.util.List ;
import java.util.Map ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.IProjectTypeService ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectTypeSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectTypeSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectTypeSearchAction extends ABDemoSearchAction<ProjectTypeSearchBean> {

    @Autowired
    @Qualifier(IProjectTypeService.name)
   private IProjectTypeService  service;

    @Override
    protected  List<ProjectType> searchResult() throws Exception {
       
        return service.searchList(OptType.search, searchBean, null) ;
    }

}
