package cn.zy.apps.demo.service.units ;

import java.math.BigDecimal ;
import java.util.Calendar ;
import java.util.List ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Complete ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.beans.PCHomeSumBean ;
import cn.zy.apps.tools.units.DateToolsUilts ;

@Component("PCSearchKeyStatisticsSumUnits")
public class PCSearchKeyStatisticsSumUnits extends ABCommonsService {

    //    SELECT 
    //    (SUM( CASE WHEN project_month_complete.`is_complete` = '已完成' THEN 1 ELSE 0 END)) AS 已完成   ,  
    //    (SUM( CASE WHEN project_month_complete.`is_complete` = '末完成' THEN 1 ELSE 0 END)) AS  末完成  ,
    //    (SUM( CASE WHEN project_month_complete.`is_complete` IS  NULL  THEN 1 ELSE 0 END)) AS  没计划
    //    FROM  `project_month_complete` AS project_month_complete

    //searchKeyStatisticsSum
    public PCHomeSumBean searchKeyStatisticsSum() throws SystemOptServiceException {
        Object[] result = baseService.findSinglenessByQName("searchKeyStatisticsSum") ;
        Integer 已完成 = ((BigDecimal) result[0]).intValue() ;
        Integer 末完成 = ((BigDecimal) result[1]).intValue() ;
        Integer 没计划 = ((BigDecimal) result[2]).intValue() ;

        PCHomeSumBean pcHomeSumBean = new PCHomeSumBean() ;
        pcHomeSumBean.setCompleteSum(已完成) ;
        pcHomeSumBean.setNoCompleteSum(末完成) ;
        pcHomeSumBean.setMonthCompleteSum(已完成) ;
        pcHomeSumBean.setMonthNoCompleteSum(末完成) ;
        pcHomeSumBean.setMonthNoPlanSum(没计划) ;
        pcHomeSumBean.setProjectSum(已完成 + 末完成 + 没计划) ;

        return pcHomeSumBean ;
    }

    public List<ProjectCarriedOutInfo> listYearMonthProjectCarriedOutInfo(Complete monthComplete) throws SystemOptServiceException {
        String sql = " select  projectCarriedOutInfo  from   " +

        " ProjectCarriedOutInfo  as  projectCarriedOutInfo   inner join  projectCarriedOutInfo.projectYearInvestmentPlans as  projectYearInvestmentPlans  " +

        "  inner join   projectYearInvestmentPlans.projectMonthInvestmentPlans  as  projectMonthInvestmentPlans   " +

        "  where  projectYearInvestmentPlans.year ='" + Calendar.getInstance().get(Calendar.YEAR) + "'    " +

        "   AND   projectMonthInvestmentPlans.month ='" + DateToolsUilts.getMonth(DateToolsUilts.getnowDate()) + "'  " +

        "  and  projectMonthInvestmentPlans.complete ='" + monthComplete.name() + "'  " ;
        
        System.out.println("--->  "+sql) ;

        return baseService.findByHSQL(sql) ;
    }
}
