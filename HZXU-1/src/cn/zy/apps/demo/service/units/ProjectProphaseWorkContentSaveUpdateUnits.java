package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.pojos.ProjectProphaseWorkContent ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectProphaseWorkContentSaveUpdateUnits")
public class ProjectProphaseWorkContentSaveUpdateUnits extends ABCommonsService {

    public ProjectProphaseWorkContent saveUpdate(OptType optType, ProjectProphaseWorkContent optProjectProphaseWorkContent) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectProphaseWorkContent) ;

        case update:
            return update(optProjectProphaseWorkContent) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectProphaseWorkContent save(ProjectProphaseWorkContent optProjectProphaseWorkContent) throws SystemOptServiceException {

        ProjectProphaseInfo projectProphaseInfo = baseService.get(ProjectProphaseInfo.class, optProjectProphaseWorkContent.getProjectProphaseInfoId()) ;

        optProjectProphaseWorkContent.setProjectProphaseInfo(projectProphaseInfo) ;

        baseService.save(optProjectProphaseWorkContent) ;

        return optProjectProphaseWorkContent ;
    }

    public ProjectProphaseWorkContent update(ProjectProphaseWorkContent optProjectProphaseWorkContent) throws SystemOptServiceException {

        ProjectProphaseWorkContent projectProphaseWorkContent = baseService.get(ProjectProphaseWorkContent.class, optProjectProphaseWorkContent.getId()) ;
        projectProphaseWorkContent.setContent(optProjectProphaseWorkContent.getContent()) ;
        projectProphaseWorkContent.setWorkDate(optProjectProphaseWorkContent.getWorkDate()) ;

        baseService.update(projectProphaseWorkContent) ;

        return projectProphaseWorkContent ;
    }
}
