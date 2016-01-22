package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("DemoUserRemoveUnits")
public class DemoUserRemoveUnits extends ABCommonsService {

    public DemoUser remove(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException {

        Integer id = optDemoUser.getId() ;
        DemoUser removeDemoUser = baseService.get(id, DemoUser.class) ;
        baseService.remove(removeDemoUser);
        return removeDemoUser ;
    }

}
