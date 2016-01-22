package cn.zy.apps.demo.service.imples ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IDemoStockService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.DemoStockRemoveUnits ;
import cn.zy.apps.demo.service.units.DemoStockSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.DemoStockSearchUnits ;
import cn.zy.apps.demo.units.search.bean.DemoStockSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component(IDemoStockService.name)
public class DemoStockServiceImple extends ABCommonsService implements IDemoStockService {

    //@Resource(name="DemoStockSearchUnits")
    @Autowired
    @Qualifier("DemoStockSearchUnits")
    private DemoStockSearchUnits iDemoStockSearchUnits ;

    //@Resource(name=" DemoStockSaveUpdateUnits")
    @Autowired
    @Qualifier("DemoStockSaveUpdateUnits")
    private DemoStockSaveUpdateUnits iDemoStockSaveUpdateUnits ;

    @Autowired
    @Qualifier("DemoStockRemoveUnits")
    private DemoStockRemoveUnits iDemoStockRemoveUnits ;

    @Override
    public DemoStock saveUpdate(OptType optType, DemoStock optDemoStock) throws SystemOptServiceException {
        return iDemoStockSaveUpdateUnits.saveUpdate(optType, optDemoStock) ;
    }

    @Override
    public SelectPage<DemoStock> search(OptType optType, DemoStockSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        return iDemoStockSearchUnits.search(optType, searchBean, commSearchBean, startLimit) ;
    }

    @Override
    public List<DemoStock> searchList(OptType optType, DemoStockSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {

        return iDemoStockSearchUnits.list(optType, searchBean, commSearchBean, startLimit) ;

    }

    @Override
    public DemoStock remove(OptType optType, DemoStock optDemoStock) throws SystemOptServiceException {
        return iDemoStockRemoveUnits.remove(optType, optDemoStock) ;
    }

    @Override
    public DemoStock get(Integer id) throws SystemOptServiceException {

        return baseService.get(id, DemoStock.class) ;
    }

}
