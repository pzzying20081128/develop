package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectProgressTypeRemoveUnits")
public class ProjectProgressTypeRemoveUnits extends ABCommonsService {

    public ProjectProgressType remove(OptType optType, ProjectProgressType optProjectProgressType) throws SystemOptServiceException {

        Integer id = optProjectProgressType.getId() ;
        ProjectProgressType removeProjectProgressType = baseService.get(id, ProjectProgressType.class) ;
        removeProjectProgressType.setStatus(Status.删除) ;
        baseService.update(removeProjectProgressType) ;
        return removeProjectProgressType ;
    }

}
