package  cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;


@Component("ProjectMajorTypeSearchUnits")
public class ProjectMajorTypeSearchUnits extends ABCommonsService {

    public SelectPage<ProjectMajorType> search(OptType optType,
		                            ProjectMajorTypeSearchBean searchBean,CommSearchBean commSearchBean ,int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectMajorType> selectPage = new SelectPage<ProjectMajorType>() ;

		Map<String, Object> value=ToolsUnits.createSearchMap();
			
		 String sqlWhere=createWhere(value,searchBean,commSearchBean);
				
        List<ProjectMajorType> result = list(sqlWhere,value,startLimit) ;

        Long sum = sum(sqlWhere,value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }
	
	
	    public List<ProjectMajorType> list(OptType optType,
		                            ProjectMajorTypeSearchBean searchBean,CommSearchBean commSearchBean ,int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectMajorType> selectPage = new SelectPage<ProjectMajorType>() ;

		Map<String, Object> value=ToolsUnits.createSearchMap();
			
		 String sqlWhere=createWhere(value,searchBean,commSearchBean);
				
        List<ProjectMajorType> result = list(sqlWhere,value,startLimit) ;

     

        return result ;

    }
	

	 private  String sql =" select projectMajorType   from   ProjectMajorType as  projectMajorType ";   
    private   List<ProjectMajorType> list(String sqlWhere ,Map<String, Object> value ,int... startLimit) throws SystemOptServiceException {
   String sql_ =sql+ sqlWhere ;
        List<ProjectMajorType>  result = baseService.findByHSQL(sql_, value, startLimit);
        return result;
    }
 private  String sqlsum =" select count(projectMajorType.id)   from   ProjectMajorType as  projectMajorType ";   
    private Long sum(String sqlWhere ,Map<String, Object> value) throws SystemOptServiceException {
 String sql_ =sqlsum+ sqlWhere ;
        Long  sum = baseService.findSinglenessByHSQL(sql_, value);
        return sum;
    }
	
	 private String createWhere(Map<String, Object> value,ProjectMajorTypeSearchBean searchBean ,CommSearchBean commSearchBean){
        String sqlWhere=" where   1=1 ";
        if(searchBean.getStatus()!=null){
            sqlWhere = sqlWhere+"   and  projectMajorType.status =:status  ";
            value.put("status", searchBean.getStatus());
        }
        return sqlWhere;
    }

}
