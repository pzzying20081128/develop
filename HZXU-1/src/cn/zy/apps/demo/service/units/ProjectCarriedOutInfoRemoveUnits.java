package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectCarriedOutInfoRemoveUnits")
public class ProjectCarriedOutInfoRemoveUnits extends ABCommonsService {

    public ProjectCarriedOutInfo remove(OptType optType, ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException {

        Integer id = optProjectCarriedOutInfo.getId() ;
        ProjectCarriedOutInfo removeProjectCarriedOutInfo = baseService.get(id, ProjectCarriedOutInfo.class) ;
        removeProjectCarriedOutInfo.setStatus(Status.删除) ;
        baseService.update(removeProjectCarriedOutInfo) ;
        return removeProjectCarriedOutInfo ;
    }

}
