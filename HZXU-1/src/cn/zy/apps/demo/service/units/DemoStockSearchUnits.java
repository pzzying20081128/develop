package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.DemoStockSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("DemoStockSearchUnits")
public class DemoStockSearchUnits extends ABCommonsService {

    public SelectPage<DemoStock> search(OptType optType, DemoStockSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<DemoStock> selectPage = new SelectPage<DemoStock>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<DemoStock> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<DemoStock> list(OptType optType, DemoStockSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<DemoStock> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  demoStock   from  DemoStock as  demoStock   " ;

    private List<DemoStock> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<DemoStock> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select  count(demoStock.id)   from  DemoStock as  demoStock " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, DemoStockSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = " where 1 =1  " ;
        
        if(!ToolsUnits.listIsNULL(searchBean.getStockUserIds())){
            sqlWhere  = sqlWhere+"   and   demoStock.stockUserId  in (:stockUserIds)  ";
            value.put("stockUserIds", searchBean.getStockUserIds());
        }
        
        if(  ToolsUnits.isNOtNulll(searchBean.getStockName()) ){
            sqlWhere  = sqlWhere+"   and   demoStock.stockName  in (:stockNames)  ";
            value.put("stockNames", "%"+searchBean.getStockName()+"%");
        }
        
        
        
        return sqlWhere ;
    }

}
