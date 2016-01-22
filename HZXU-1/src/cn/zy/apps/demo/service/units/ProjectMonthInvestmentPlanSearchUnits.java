package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectMonthInvestmentPlanSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectMonthInvestmentPlanSearchUnits")
public class ProjectMonthInvestmentPlanSearchUnits extends ABCommonsService {

    public SelectPage<ProjectMonthInvestmentPlan> search(OptType optType, ProjectMonthInvestmentPlanSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectMonthInvestmentPlan> selectPage = new SelectPage<ProjectMonthInvestmentPlan>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectMonthInvestmentPlan> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }

    public List<ProjectMonthInvestmentPlan> list(OptType optType, ProjectMonthInvestmentPlanSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectMonthInvestmentPlan> selectPage = new SelectPage<ProjectMonthInvestmentPlan>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectMonthInvestmentPlan> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select projectMonthInvestmentPlan   from  ProjectMonthInvestmentPlan as  projectMonthInvestmentPlan " ;

    private List<ProjectMonthInvestmentPlan> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectMonthInvestmentPlan> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select count(projectMonthInvestmentPlan.id)   from  ProjectMonthInvestmentPlan as  projectMonthInvestmentPlan" ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectMonthInvestmentPlanSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "   where  projectMonthInvestmentPlan.projectYearInvestmentPlanId =   " + searchBean.getProjectYearInvestmentPlanId() ;
        return sqlWhere ;
    }

}
