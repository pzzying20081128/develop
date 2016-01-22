package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectProphaseInfoSearchUnits")
public class ProjectProphaseInfoSearchUnits extends ABCommonsService {

    public SelectPage<ProjectProphaseInfo> search(OptType optType, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectProphaseInfo> selectPage = new SelectPage<ProjectProphaseInfo>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectProphaseInfo> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectProphaseInfo> list(OptType optType, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectProphaseInfo> selectPage = new SelectPage<ProjectProphaseInfo>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectProphaseInfo> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select   projectProphaseInfo   from  ProjectProphaseInfo as  projectProphaseInfo   " ;

    private List<ProjectProphaseInfo> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectProphaseInfo> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select count(projectProphaseInfo.id)   from  ProjectProphaseInfo as  projectProphaseInfo  " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "where    1=1 " ;
        if(ToolsUnits.isNOtNulll(searchBean.getName())){
            sqlWhere   =  sqlWhere+"    and  projectProphaseInfo.name   like :name   ";
            value.put("name", "%"+searchBean.getName()+"%");
        }
        if(ToolsUnits.isNOtNulll(searchBean.getConstructionScale())){
            sqlWhere   =  sqlWhere+"    and  projectProphaseInfo.constructionScale   like :constructionScale   ";
            value.put("constructionScale", "%"+searchBean.getConstructionScale()+"%");
        }
        if(ToolsUnits.isNOtNulll(searchBean.getConstructionScale())){
            sqlWhere   =  sqlWhere+"    and  projectProphaseInfo.constructionScale   like :constructionScale   ";
            value.put("constructionScale", "%"+searchBean.getConstructionScale()+"%");
        }
        
        if(ToolsUnits.isNOtNulll(searchBean.getResponsibilityUnit())){
            sqlWhere   =  sqlWhere+"    and  projectProphaseInfo.responsibilityUnit   like :getResponsibilityUnit   ";
            value.put("getResponsibilityUnit", "%"+searchBean.getResponsibilityUnit()+"%");
        }
        
       
        return sqlWhere ;
    }

}
