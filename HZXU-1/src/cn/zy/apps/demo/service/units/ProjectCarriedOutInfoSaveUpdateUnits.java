package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.ProjectStauts ;
import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectCarriedOutInfoSaveUpdateUnits")
public class ProjectCarriedOutInfoSaveUpdateUnits extends ABCommonsService {

    public ProjectCarriedOutInfo saveUpdate(OptType optType, ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optProjectCarriedOutInfo) ;

        case update:
            return update(optProjectCarriedOutInfo) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectCarriedOutInfo save(ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException {

        setProperties(optProjectCarriedOutInfo) ;
        optProjectCarriedOutInfo.setStatus(Status.有效);
        optProjectCarriedOutInfo.setProjectStauts(ProjectStauts.建设中) ;

        baseService.save(optProjectCarriedOutInfo) ;

        return optProjectCarriedOutInfo ;
    }

    public ProjectCarriedOutInfo update(ProjectCarriedOutInfo optProjectCarriedOutInfo) throws SystemOptServiceException {
        
        setProperties(optProjectCarriedOutInfo) ;
        
        
        ProjectCarriedOutInfo  projectCarriedOutInfo  =baseService.get(ProjectCarriedOutInfo.class, optProjectCarriedOutInfo.getId());
        
        ToolsUnits.copyBeanProperties(projectCarriedOutInfo, optProjectCarriedOutInfo, 
                
                "totalInvestment","constructionContent","isKaiGong","isProduction","projectAddress","projectOwnershipAddress",
                
                "projectOwnershipAddressId","projectProgressType","projectProgressTypeId","projectMajorType",
                
                "projectMajorTypeId","kaiGongDate","buildStartDate","buildEndDate","implementationUnit","implementationUnitPerson","implementationUnitPhoto","name",
                
                "responsibilityUnit","responsibilityUnitPerson","responsibilityUnitPhoto","fenGuanMiShuZhang","fenGuanMiShuZhangPhoto","fenGuanHuShiZhang","fenGuanHuShiZhangPhoto"
                
                );
        
        return projectCarriedOutInfo ;
    }

    private void setProperties(ProjectCarriedOutInfo optProjectCarriedOutInfo) {
        Integer getProjectOwnershipAddressId = optProjectCarriedOutInfo.getProjectOwnershipAddressId() ;

        optProjectCarriedOutInfo.setProjectOwnershipAddress(baseService.load(getProjectOwnershipAddressId, ProjectOwnershipAddress.class)) ;

        Integer getProjectProgressTypeId = optProjectCarriedOutInfo.getProjectProgressTypeId() ;

        optProjectCarriedOutInfo.setProjectProgressType(baseService.load(getProjectProgressTypeId, ProjectProgressType.class)) ;

        Integer getProjectMajorTypeId = optProjectCarriedOutInfo.getProjectMajorTypeId() ;

        optProjectCarriedOutInfo.setProjectMajorType(baseService.load(getProjectMajorTypeId, ProjectMajorType.class)) ;
        
        Integer getProjectTypeId = optProjectCarriedOutInfo.getProjectTypeId();

        optProjectCarriedOutInfo.setProjectType(baseService.load(getProjectTypeId, ProjectType.class)) ;

    }

}
