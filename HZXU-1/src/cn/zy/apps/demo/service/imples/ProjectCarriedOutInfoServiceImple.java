package cn.zy.apps.demo.service.imples ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectCarriedOutInfoService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectCarriedOutInfoRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectCarriedOutInfoSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectCarriedOutInfoSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component(IProjectCarriedOutInfoService.name)
public class ProjectCarriedOutInfoServiceImple extends ABCommonsService implements IProjectCarriedOutInfoService {

    //@Resource(name="ProjectCarriedOutInfoSearchUnits")
    @Autowired
    @Qualifier("ProjectCarriedOutInfoSearchUnits")
    private ProjectCarriedOutInfoSearchUnits iProjectCarriedOutInfoSearchUnits ;

    //@Resource(name=" ProjectCarriedOutInfoSaveUpdateUnits")
    @Autowired
    @Qualifier("ProjectCarriedOutInfoSaveUpdateUnits")
    private ProjectCarriedOutInfoSaveUpdateUnits iProjectCarriedOutInfoSaveUpdateUnits ;

    @Autowired
    @Qualifier("ProjectCarriedOutInfoRemoveUnits")
    private ProjectCarriedOutInfoRemoveUnits iProjectCarriedOutInfoRemoveUnits ;

    @Override
    public ProjectCarriedOutInfo saveUpdate(OptType optType, ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException {
        return iProjectCarriedOutInfoSaveUpdateUnits.saveUpdate(optType, optProjectCarriedOutInfo) ;
    }

    @Override
    public SelectPage<ProjectCarriedOutInfo> search(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        return iProjectCarriedOutInfoSearchUnits.search(optType, searchBean, commSearchBean, startLimit) ;
    }

    @Override
    public List<ProjectCarriedOutInfo> searchList(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {

        return iProjectCarriedOutInfoSearchUnits.list(optType, searchBean, commSearchBean, startLimit) ;

    }

    @Override
    public ProjectCarriedOutInfo remove(OptType optType, ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException {
        return iProjectCarriedOutInfoRemoveUnits.remove(optType, optProjectCarriedOutInfo) ;
    }

    @Override
    public ProjectCarriedOutInfo get(Integer id) throws SystemOptServiceException {

        return baseService.get(id, ProjectCarriedOutInfo.class) ;
    }

}
