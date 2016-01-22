package cn.zy.apps.demo.service.imples ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectProphaseInfoService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectProphaseInfoRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectProphaseInfoSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectProphaseInfoSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component(IProjectProphaseInfoService.name)
public class ProjectProphaseInfoServiceImple extends ABCommonsService implements IProjectProphaseInfoService {

    //@Resource(name="ProjectProphaseInfoSearchUnits")
    @Autowired
    @Qualifier("ProjectProphaseInfoSearchUnits")
    private ProjectProphaseInfoSearchUnits iProjectProphaseInfoSearchUnits ;

    //@Resource(name=" ProjectProphaseInfoSaveUpdateUnits")
    @Autowired
    @Qualifier("ProjectProphaseInfoSaveUpdateUnits")
    private ProjectProphaseInfoSaveUpdateUnits iProjectProphaseInfoSaveUpdateUnits ;

    @Autowired
    @Qualifier("ProjectProphaseInfoRemoveUnits")
    private ProjectProphaseInfoRemoveUnits iProjectProphaseInfoRemoveUnits ;

    @Override
    public ProjectProphaseInfo saveUpdate(OptType optType, ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException {
        return iProjectProphaseInfoSaveUpdateUnits.saveUpdate(optType, optProjectProphaseInfo) ;
    }

    @Override
    public SelectPage<ProjectProphaseInfo> search(OptType optType, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        return iProjectProphaseInfoSearchUnits.search(optType, searchBean, commSearchBean, startLimit) ;
    }

    @Override
    public List<ProjectProphaseInfo> searchList(OptType optType, ProjectProphaseInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {

        return iProjectProphaseInfoSearchUnits.list(optType, searchBean, commSearchBean, startLimit) ;

    }

    @Override
    public ProjectProphaseInfo remove(OptType optType, ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException {
        return iProjectProphaseInfoRemoveUnits.remove(optType, optProjectProphaseInfo) ;
    }

    @Override
    public ProjectProphaseInfo get(Integer id) throws SystemOptServiceException {

        return baseService.get(id, ProjectProphaseInfo.class) ;
    }

}
