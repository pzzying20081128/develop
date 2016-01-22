package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectMajorTypeRemoveUnits")
public class ProjectMajorTypeRemoveUnits extends ABCommonsService {

    public ProjectMajorType  remove(OptType optType, ProjectMajorType  optProjectMajorType ) throws SystemOptServiceException {
        
         Integer id =optProjectMajorType.getId() ;
        ProjectMajorType   removeProjectMajorType =baseService.get(id, ProjectMajorType.class);
        removeProjectMajorType.setStatus(Status.删除);
        baseService.update(removeProjectMajorType);
        return  removeProjectMajorType ;
    }

}
