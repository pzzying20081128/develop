package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectProgressTypeSaveUpdateUnits")
public class ProjectProgressTypeSaveUpdateUnits extends ABCommonsService {

    public ProjectProgressType saveUpdate(OptType optType, ProjectProgressType optProjectProgressType) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectProgressType) ;

        case update:
            return update(optProjectProgressType) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectProgressType save(ProjectProgressType optProjectProgressType) throws SystemOptServiceException {
        optProjectProgressType.setStatus(Status.有效) ;
     
        baseService.save(optProjectProgressType) ;
        return optProjectProgressType ;
    }

    public ProjectProgressType update(ProjectProgressType optProjectProgressType) throws SystemOptServiceException {

        ProjectProgressType projectProgressType = baseService.get(ProjectProgressType.class, optProjectProgressType.getId()) ;

        projectProgressType.setTypeName(optProjectProgressType.getTypeName()) ;

        baseService.update(projectProgressType) ;

        return projectProgressType ;
    }

}
