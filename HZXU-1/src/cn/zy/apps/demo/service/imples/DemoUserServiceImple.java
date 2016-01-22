package cn.zy.apps.demo.service.imples ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IDemoUserService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.DemoUserRemoveUnits ;
import cn.zy.apps.demo.service.units.DemoUserSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.DemoUserSearchUnits ;
import cn.zy.apps.demo.units.search.bean.DemoUserSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component(IDemoUserService.name)
public class DemoUserServiceImple extends ABCommonsService implements IDemoUserService {

    //@Resource(name="DemoUserSearchUnits")
    @Autowired
    @Qualifier("DemoUserSearchUnits")
    private DemoUserSearchUnits iDemoUserSearchUnits ;

    //@Resource(name=" DemoUserSaveUpdateUnits")
    @Autowired
    @Qualifier("DemoUserSaveUpdateUnits")
    private DemoUserSaveUpdateUnits iDemoUserSaveUpdateUnits ;

    @Autowired
    @Qualifier("DemoUserRemoveUnits")
    private DemoUserRemoveUnits iDemoUserRemoveUnits ;

    @Override
    public DemoUser saveUpdate(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException {
        return iDemoUserSaveUpdateUnits.saveUpdate(optType, optDemoUser) ;
    }

    @Override
    public SelectPage<DemoUser> search(OptType optType, DemoUserSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        return iDemoUserSearchUnits.search(optType, searchBean, commSearchBean, startLimit) ;
    }

    @Override
    public List<DemoUser> searchList(OptType optType, DemoUserSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {

        return iDemoUserSearchUnits.list(optType, searchBean, commSearchBean, startLimit) ;

    }

    @Override
    public DemoUser remove(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException {
        return iDemoUserRemoveUnits.remove(optType, optDemoUser) ;
    }

    @Override
    public DemoUser get(Integer id) throws SystemOptServiceException {

        return baseService.get(id, DemoUser.class) ;
    }

    @Override
    public DemoUser searchByName(String userName) throws SystemOptServiceException {

        return iDemoUserSearchUnits.searchByName(userName);
    }

}
