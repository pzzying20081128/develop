package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectTypeSaveUpdateUnits")
public class ProjectTypeSaveUpdateUnits extends ABCommonsService {

    public ProjectType saveUpdate(OptType optType, ProjectType optProjectType) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectType) ;

        case update:
            return update(optProjectType) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectType save(ProjectType optProjectType) throws SystemOptServiceException {
        optProjectType.setStatus(Status.有效);
        baseService.save(optProjectType) ;
        return optProjectType ;
    }

    public ProjectType update(ProjectType optProjectType) throws SystemOptServiceException {

        ProjectType projectType = baseService.get(ProjectType.class, optProjectType.getId()) ;
        projectType.setTypeName(optProjectType.getTypeName()) ;
        baseService.update(projectType) ;
        return projectType ;
    }

}
