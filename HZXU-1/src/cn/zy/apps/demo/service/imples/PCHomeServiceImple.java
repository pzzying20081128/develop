package cn.zy.apps.demo.service.imples ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Complete ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.IPCHomeService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.PCSearchKeyStatisticsSumUnits ;
import cn.zy.apps.demo.units.beans.PCHomeSumBean ;

@Component(IPCHomeService.name)
public class PCHomeServiceImple implements IPCHomeService {

    @Autowired
    @Qualifier("PCSearchKeyStatisticsSumUnits")
    private PCSearchKeyStatisticsSumUnits pcSearchKeyStatisticsSumUnits ;

    @Override
    public PCHomeSumBean searchKeyStatisticsSum() throws SystemOptServiceException {
        return pcSearchKeyStatisticsSumUnits.searchKeyStatisticsSum() ;
    }

    @Override
    public List<ProjectCarriedOutInfo> listYearMonthProjectCarriedOutInfo(Complete monthComplete) throws SystemOptServiceException{
         
        return pcSearchKeyStatisticsSumUnits.listYearMonthProjectCarriedOutInfo(monthComplete) ;
    }

}
