package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.DemoUserSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("DemoUserSearchUnits")
public class DemoUserSearchUnits extends ABCommonsService {

    public DemoUser searchByName(String userName) throws SystemOptServiceException {
        String sql = "select  demoUser  from DemoUser as  demoUser   where   demoUser.userName ='" + userName + "' " ;
        return baseService.findSinglenessByHSQL(sql) ;
    }

    public SelectPage<DemoUser> search(OptType optType, DemoUserSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<DemoUser> selectPage = new SelectPage<DemoUser>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<DemoUser> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<DemoUser> list(OptType optType, DemoUserSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<DemoUser> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  demoUser  from   DemoUser as demoUser  " ;

    private List<DemoUser> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<DemoUser> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select   count(demoUser.id) from   DemoUser as demoUser " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, DemoUserSearchBean searchBean, CommSearchBean commSearchBean) {
        
        String sqlWhere = " where   demoUser.userName !='admin' " ;

        if (searchBean.getId() != null) {
            sqlWhere = sqlWhere + "   and  demoUser.id = " + searchBean.getId() ;
        }

        return sqlWhere ;
    }

}
