package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectTypeSearchUnits")
public class ProjectTypeSearchUnits extends ABCommonsService {

    public SelectPage<ProjectType> search(OptType optType, ProjectTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectType> selectPage = new SelectPage<ProjectType>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectType> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectType> list(OptType optType, ProjectTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectType> selectPage = new SelectPage<ProjectType>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectType> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  projectType   from   ProjectType as  projectType " ;

    private List<ProjectType> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectType> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select  count(projectType.id)   from   ProjectType as  projectType" ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectTypeSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = " where  1=1 " ;
        if(searchBean.getStatus()!=null){
            sqlWhere = sqlWhere+"   and  projectType.status =:status  ";
            value.put("status", searchBean.getStatus());
        }
        return sqlWhere ;
    }

}
