package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectProgressTypeService {

    public String name = "IProjectProgressTypeService" ;

    /**
    *  增加或更新
    */
    public ProjectProgressType saveUpdate(OptType optType, ProjectProgressType optProjectProgressType) throws SystemOptServiceException ;

    public SelectPage<ProjectProgressType> search(OptType optType, ProjectProgressTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public List<ProjectProgressType> searchList(OptType optType, ProjectProgressTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public ProjectProgressType remove(OptType optType, ProjectProgressType optProjectProgressType) throws SystemOptServiceException ;

    public ProjectProgressType get(Integer id) throws SystemOptServiceException ;

}
