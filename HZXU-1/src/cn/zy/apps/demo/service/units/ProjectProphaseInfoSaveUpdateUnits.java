package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.ProjectStauts ;
import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectProphaseInfoSaveUpdateUnits")
public class ProjectProphaseInfoSaveUpdateUnits extends ABCommonsService {

    public ProjectProphaseInfo saveUpdate(OptType optType, ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectProphaseInfo) ;

        case update:
            return update(optProjectProphaseInfo) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectProphaseInfo save(ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException {
        optProjectProphaseInfo.setProjectStauts(ProjectStauts.建设前期预备) ;
        baseService.save(optProjectProphaseInfo) ;
        return optProjectProphaseInfo ;
    }

    public ProjectProphaseInfo update(ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException {

        ProjectProphaseInfo projectProphaseInfo = baseService.get(optProjectProphaseInfo.getId(), ProjectProphaseInfo.class) ;

        ToolsUnits.copyBeanProperties(projectProphaseInfo, optProjectProphaseInfo,

        "responsibilityUnit", "implementationUnit", "constructionScale", "estimatedTotalInvestment", "name", "text", "projectDate"

        ) ;

        baseService.update(projectProphaseInfo) ;

        return projectProphaseInfo ;
    }

}
