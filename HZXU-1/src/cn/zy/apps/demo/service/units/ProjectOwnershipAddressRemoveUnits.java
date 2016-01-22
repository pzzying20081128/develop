package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectOwnershipAddressRemoveUnits")
public class ProjectOwnershipAddressRemoveUnits extends ABCommonsService {

    public ProjectOwnershipAddress remove(OptType optType, ProjectOwnershipAddress optProjectOwnershipAddress) throws SystemOptServiceException {

        Integer id = optProjectOwnershipAddress.getId() ;
        ProjectOwnershipAddress removeProjectOwnershipAddress = baseService.get(id, ProjectOwnershipAddress.class) ;
        removeProjectOwnershipAddress.setStatus(Status.删除) ;
        baseService.update(removeProjectOwnershipAddress) ;
        return removeProjectOwnershipAddress ;
    }

}
