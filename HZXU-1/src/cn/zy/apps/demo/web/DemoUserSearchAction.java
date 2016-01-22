package cn.zy.apps.demo.web ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.service.IDemoUserService ;
import cn.zy.apps.demo.units.search.bean.DemoUserSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("DemoUserSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class DemoUserSearchAction extends ABDemoSearchAction<DemoUserSearchBean> {

    @Autowired
    @Qualifier(IDemoUserService.name)
    private IDemoUserService service ;

    @Override
    protected List<DemoUser> searchResult() throws Exception {
        List<DemoUser> demoUsers = service.searchList(OptType.search, searchBean, null, 0, 12) ;

        DemoUserSearchBean searchBean1 = new DemoUserSearchBean() ;

        List<DemoUser> demoUsers1 = service.searchList(OptType.search, searchBean1, null, 0, 12) ;

        demoUsers.addAll(demoUsers1) ;
        
        return demoUsers ;

    }

}
