package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectTypeRemoveUnits")
public class ProjectTypeRemoveUnits extends ABCommonsService {

    public ProjectType  remove(OptType optType, ProjectType  optProjectType ) throws SystemOptServiceException {
        
         Integer id =optProjectType.getId() ;
        ProjectType   removeProjectType =baseService.get(id, ProjectType.class);
        removeProjectType.setStatus(Status.删除);
        baseService.update(removeProjectType);
        return  removeProjectType ;
    }

}
