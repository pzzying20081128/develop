package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectProgressTypeSearchUnits")
public class ProjectProgressTypeSearchUnits extends ABCommonsService {

    public SelectPage<ProjectProgressType> search(OptType optType, ProjectProgressTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectProgressType> selectPage = new SelectPage<ProjectProgressType>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectProgressType> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectProgressType> list(OptType optType, ProjectProgressTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectProgressType> selectPage = new SelectPage<ProjectProgressType>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectProgressType> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = " select   projectProgressType  from  ProjectProgressType as  projectProgressType   " ;

    private List<ProjectProgressType> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectProgressType> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = " select   count(projectProgressType.id)  from  ProjectProgressType as  projectProgressType  " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectProgressTypeSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "where    1 =1 " ;
        if(searchBean.getStatus()!=null){
            sqlWhere = sqlWhere+"   and  projectProgressType.status =:status  ";
            value.put("status", searchBean.getStatus());
        }
        return sqlWhere ;
    }

}
