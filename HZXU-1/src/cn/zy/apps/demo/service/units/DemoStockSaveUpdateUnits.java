package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("DemoStockSaveUpdateUnits")
public class DemoStockSaveUpdateUnits extends ABCommonsService {

    public DemoStock saveUpdate(OptType optType, DemoStock optDemoStock) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optDemoStock) ;

        case update:
            return update(optDemoStock) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public DemoStock save(DemoStock optDemoStock) throws SystemOptServiceException {
        Integer id = optDemoStock.getStockUserId() ;

        DemoUser xx = baseService.load(id, DemoUser.class) ;

        optDemoStock.setStockUser(xx) ;

        this.baseService.save(optDemoStock) ;

        return optDemoStock ;
    }

    public DemoStock update(DemoStock optDemoStock) throws SystemOptServiceException {
        DemoStock demoStock = baseService.get(DemoStock.class, optDemoStock.getId()) ;

        DemoUser xx = baseService.load(optDemoStock.getStockUserId(), DemoUser.class) ;

        demoStock.setStockUser(xx) ;

        demoStock.setStockUserId(optDemoStock.getStockUserId()) ;
        
        demoStock.setStockName(optDemoStock.getStockName());

        this.baseService.update(demoStock) ;

        return demoStock ;
    }

}
