package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectMajorTypeSaveUpdateUnits")
public class ProjectMajorTypeSaveUpdateUnits extends ABCommonsService {

    public ProjectMajorType saveUpdate(OptType optType, ProjectMajorType optProjectMajorType) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectMajorType) ;

        case update:
            return update(optProjectMajorType) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectMajorType save(ProjectMajorType optProjectMajorType) throws SystemOptServiceException {
        optProjectMajorType.setStatus(Status.有效) ;
        baseService.save(optProjectMajorType) ;

        return optProjectMajorType ;
    }

    public ProjectMajorType update(ProjectMajorType optProjectMajorType) throws SystemOptServiceException {

        ProjectMajorType projectMajorType = baseService.get(ProjectMajorType.class, optProjectMajorType.getId()) ;

        projectMajorType.setName(optProjectMajorType.getName()) ;

        baseService.update(projectMajorType) ;

        return projectMajorType ;
    }

}
