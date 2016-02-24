package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectCarriedOutInfoService {

    public String name = "IProjectCarriedOutInfoService" ;

    /**
    *  增加或更新
    */
    public ProjectCarriedOutInfo saveUpdate(OptType optType, ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException ;

    public SelectPage<ProjectCarriedOutInfo> search(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public List<ProjectCarriedOutInfo> searchList(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;
    
    
    public List<ProjectCarriedOutInfo> searchCombo(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;
    
    

    public ProjectCarriedOutInfo remove(OptType optType, ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException ;

    public ProjectCarriedOutInfo get(Integer id) throws SystemOptServiceException ;
    
    public List<String>   haveProjectMonth(Integer   projectId)throws SystemOptServiceException ;

}
