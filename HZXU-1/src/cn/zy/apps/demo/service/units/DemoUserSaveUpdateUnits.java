package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.pojos.DemoUserPower ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("DemoUserSaveUpdateUnits")
public class DemoUserSaveUpdateUnits extends ABCommonsService {

    public DemoUser saveUpdate(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optDemoUser) ;

        case update:
            return update(optDemoUser) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public DemoUser save(DemoUser optDemoUser) throws SystemOptServiceException {
        optDemoUser.setIsAdmin(0) ;
        this.baseService.save(optDemoUser) ;
        for (DemoUserPower demoUserPower : optDemoUser.getSystemUserPowers()) {
            demoUserPower.setDemoUserId(optDemoUser.getId()) ;
            demoUserPower.setDemoUser(optDemoUser);
            this.baseService.save(demoUserPower) ;

        }

        return optDemoUser ;
    }

    public DemoUser update(DemoUser optDemoUser) throws SystemOptServiceException {
        return optDemoUser ;
    }

}
