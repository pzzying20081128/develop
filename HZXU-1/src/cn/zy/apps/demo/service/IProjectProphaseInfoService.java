package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectProphaseInfoService {

    public String name = "IProjectProphaseInfoService" ;

    /**
    *  增加或更新
    */
    public ProjectProphaseInfo saveUpdate(OptType optType, ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException ;

    public SelectPage<ProjectProphaseInfo> search(OptType optType, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public List<ProjectProphaseInfo> searchList(OptType optType, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public ProjectProphaseInfo remove(OptType optType, ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException ;

    public ProjectProphaseInfo get(Integer id) throws SystemOptServiceException ;

}
