package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectProphaseWorkContent ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseWorkContentSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectProphaseWorkContentService {

    public String name = "IProjectProphaseWorkContentService" ;

    /**
    *  增加或更新
    */
    public ProjectProphaseWorkContent saveUpdate(OptType optType, ProjectProphaseWorkContent optProjectProphaseWorkContent) throws SystemOptServiceException ;

    public SelectPage<ProjectProphaseWorkContent> search(OptType optType, ProjectProphaseWorkContentSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public List<ProjectProphaseWorkContent> searchList(OptType optType, ProjectProphaseWorkContentSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public ProjectProphaseWorkContent remove(OptType optType, ProjectProphaseWorkContent optProjectProphaseWorkContent) throws SystemOptServiceException ;

    public ProjectProphaseWorkContent get(Integer id) throws SystemOptServiceException ;

}
