package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectOwnershipAddressSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectOwnershipAddressSearchUnits")
public class ProjectOwnershipAddressSearchUnits extends ABCommonsService {

    public SelectPage<ProjectOwnershipAddress> search(OptType optType, ProjectOwnershipAddressSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectOwnershipAddress> selectPage = new SelectPage<ProjectOwnershipAddress>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectOwnershipAddress> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectOwnershipAddress> list(OptType optType, ProjectOwnershipAddressSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectOwnershipAddress> selectPage = new SelectPage<ProjectOwnershipAddress>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectOwnershipAddress> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  projectOwnershipAddress   from  ProjectOwnershipAddress as  projectOwnershipAddress    " ;

    private List<ProjectOwnershipAddress> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectOwnershipAddress> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select  count(projectOwnershipAddress.id)   from  ProjectOwnershipAddress as  projectOwnershipAddress  " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectOwnershipAddressSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "where   1= 1 " ;
        
        if(searchBean.getStatus()!=null){
            sqlWhere = sqlWhere+"   and  projectOwnershipAddress.status =:status  ";
            value.put("status", searchBean.getStatus());
        }
        
        return sqlWhere ;
    }

}
