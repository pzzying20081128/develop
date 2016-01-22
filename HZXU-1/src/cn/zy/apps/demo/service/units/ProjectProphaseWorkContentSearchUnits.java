package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseWorkContent ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseWorkContentSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectProphaseWorkContentSearchUnits")
public class ProjectProphaseWorkContentSearchUnits extends ABCommonsService {

    public SelectPage<ProjectProphaseWorkContent> search(OptType optType, ProjectProphaseWorkContentSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectProphaseWorkContent> selectPage = new SelectPage<ProjectProphaseWorkContent>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectProphaseWorkContent> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectProphaseWorkContent> list(OptType optType, ProjectProphaseWorkContentSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectProphaseWorkContent> selectPage = new SelectPage<ProjectProphaseWorkContent>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectProphaseWorkContent> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  projectProphaseWorkContent   from   ProjectProphaseWorkContent  as  projectProphaseWorkContent " ;

    private List<ProjectProphaseWorkContent> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectProphaseWorkContent> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select  count(projectProphaseWorkContent.id)   from   ProjectProphaseWorkContent  as  projectProphaseWorkContent " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectProphaseWorkContentSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "where  projectProphaseWorkContent.projectProphaseInfoId = "+searchBean.getProjectProphaseInfoId() ;
        return sqlWhere ;
    }

}
