package cn.zy.apps.demo.web.search ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.service.IResponsibilityUnitService ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.CombSearchBean ;

@Component("ResponsibilityUnitSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ResponsibilityUnitSearchAction extends ABDemoSearchAction<ProjectProphaseInfoSearchBean> {

    private static final long serialVersionUID = 356691572225687845L ;

    @Autowired
    @Qualifier(IResponsibilityUnitService.name)
    private IResponsibilityUnitService service ;

    @Override
    protected List<CombSearchBean> searchResult() throws Exception {
        
        return service.search(searchBean, 0, 10) ;
    }

}
