package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.units.search.bean.ProjectTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectTypeService {

    public String name = "IProjectTypeService" ;

    /**
    *  增加或更新
    */
    public ProjectType saveUpdate(OptType optType, ProjectType optProjectType) throws SystemOptServiceException ;

    public SelectPage<ProjectType> search(OptType optType, ProjectTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public List<ProjectType> searchList(OptType optType, ProjectTypeSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public ProjectType remove(OptType optType, ProjectType optProjectType) throws SystemOptServiceException ;

    public ProjectType get(Integer id) throws SystemOptServiceException ;

}
