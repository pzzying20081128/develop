package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("DemoStockRemoveUnits")
public class DemoStockRemoveUnits extends ABCommonsService {

    public DemoStock  remove(OptType optType, DemoStock  optDemoStock ) throws SystemOptServiceException {
        
         Integer id =optDemoStock.getId() ;
        DemoStock   removeDemoStock =baseService.get(id, DemoStock.class);
        baseService.remove(removeDemoStock);
        return  removeDemoStock ;
    }

}
