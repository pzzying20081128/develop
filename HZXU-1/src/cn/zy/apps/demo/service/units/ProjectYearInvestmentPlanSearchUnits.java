package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectYearInvestmentPlanSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectYearInvestmentPlanSearchUnits")
public class ProjectYearInvestmentPlanSearchUnits extends ABCommonsService {

    public SelectPage<ProjectYearInvestmentPlan> search(OptType optType, ProjectYearInvestmentPlanSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectYearInvestmentPlan> selectPage = new SelectPage<ProjectYearInvestmentPlan>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectYearInvestmentPlan> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectYearInvestmentPlan> list(OptType optType, ProjectYearInvestmentPlanSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectYearInvestmentPlan> selectPage = new SelectPage<ProjectYearInvestmentPlan>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectYearInvestmentPlan> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  projectYearInvestmentPlan  from  ProjectYearInvestmentPlan  as  projectYearInvestmentPlan  " ;

    private List<ProjectYearInvestmentPlan> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectYearInvestmentPlan> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select  count(projectYearInvestmentPlan.id)  from  ProjectYearInvestmentPlan  as  projectYearInvestmentPlan  " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectYearInvestmentPlanSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "where projectYearInvestmentPlan.projectCarriedOutInfoId =  " + searchBean.getProjectCarriedOutInfoId() ;
        return sqlWhere ;
    }

}
