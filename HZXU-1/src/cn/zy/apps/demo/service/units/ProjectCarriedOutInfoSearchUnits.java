package  cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;


@Component("ProjectCarriedOutInfoSearchUnits")
public class ProjectCarriedOutInfoSearchUnits extends ABCommonsService {

    public SelectPage<ProjectCarriedOutInfo> search(OptType optType,
		                            ProjectCarriedOutInfoSearchBean searchBean,CommSearchBean commSearchBean ,int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectCarriedOutInfo> selectPage = new SelectPage<ProjectCarriedOutInfo>() ;

		Map<String, Object> value=ToolsUnits.createSearchMap();
			
		 String sqlWhere=createWhere(value,searchBean,commSearchBean);
				
        List<ProjectCarriedOutInfo> result = list(sqlWhere,value,startLimit) ;

        Long sum = sum(sqlWhere,value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }
	
	
	    public List<ProjectCarriedOutInfo> list(OptType optType,
		                            ProjectCarriedOutInfoSearchBean searchBean,CommSearchBean commSearchBean ,int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectCarriedOutInfo> selectPage = new SelectPage<ProjectCarriedOutInfo>() ;

		Map<String, Object> value=ToolsUnits.createSearchMap();
			
		 String sqlWhere=createWhere(value,searchBean,commSearchBean);
				
        List<ProjectCarriedOutInfo> result = list(sqlWhere,value,startLimit) ;

     

        return result ;

    }
	

	 private  String sql ="select  projectCarriedOutInfo   from  ProjectCarriedOutInfo  as  projectCarriedOutInfo   ";   
    private   List<ProjectCarriedOutInfo> list(String sqlWhere ,Map<String, Object> value ,int... startLimit) throws SystemOptServiceException {
   String sql_ =sql+ sqlWhere ;
        List<ProjectCarriedOutInfo>  result = baseService.findByHSQL(sql_, value, startLimit);
        return result;
    }
 private  String sqlsum ="select  count(projectCarriedOutInfo.id)   from  ProjectCarriedOutInfo  as  projectCarriedOutInfo  ";   
    private Long sum(String sqlWhere ,Map<String, Object> value) throws SystemOptServiceException {
 String sql_ =sqlsum+ sqlWhere ;
        Long  sum = baseService.findSinglenessByHSQL(sql_, value);
        return sum;
    }
	
	 private String createWhere(Map<String, Object> value,ProjectCarriedOutInfoSearchBean searchBean ,CommSearchBean commSearchBean){
        String sqlWhere="where   1=1 ";
        if(searchBean.getStatus()!=null){
            sqlWhere = sqlWhere+"   and  projectCarriedOutInfo.status =:status  ";
            value.put("status", searchBean.getStatus());
        }
        
        if(searchBean.getName()!=null){
            sqlWhere = sqlWhere+"   and  projectCarriedOutInfo.name like :getName  ";
            value.put("getName", "%"+searchBean.getName()+"%");
        }
        
        if(searchBean.getIsKaiGong()!=null){
            sqlWhere = sqlWhere+"   and  projectCarriedOutInfo.isKaiGong like :isKaiGong  ";
            value.put("isKaiGong", "%"+searchBean.getIsKaiGong()+"%");
        }
        
        if(searchBean.getIsProduction()!=null){
            sqlWhere = sqlWhere+"   and  projectCarriedOutInfo.isProduction like :isProduction  ";
            value.put("isProduction", "%"+searchBean.getIsProduction()+"%");
        }
        
        return sqlWhere;
    }

}
