package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectOwnershipAddressSaveUpdateUnits")
public class ProjectOwnershipAddressSaveUpdateUnits extends ABCommonsService {

    public ProjectOwnershipAddress saveUpdate(OptType optType, ProjectOwnershipAddress optProjectOwnershipAddress) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return   save(optProjectOwnershipAddress) ;
           
        case update:
            return update(optProjectOwnershipAddress) ;
            

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public ProjectOwnershipAddress  save(ProjectOwnershipAddress optProjectOwnershipAddress) throws SystemOptServiceException {
        optProjectOwnershipAddress.setStatus(Status.有效);
        baseService.save(optProjectOwnershipAddress);
        return   optProjectOwnershipAddress;
    }

    public ProjectOwnershipAddress  update(ProjectOwnershipAddress optProjectOwnershipAddress) throws SystemOptServiceException {
        
        ProjectOwnershipAddress  projectOwnershipAddress =baseService.get(ProjectOwnershipAddress.class, optProjectOwnershipAddress.getId());
        
        projectOwnershipAddress.setAddress(optProjectOwnershipAddress.getAddress());

        baseService.update(projectOwnershipAddress);
        
        return   projectOwnershipAddress;
    }

}
