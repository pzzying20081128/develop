package  cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseWorkContent ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;


@Component("ProjectProphaseWorkContentRemoveUnits")
public class ProjectProphaseWorkContentRemoveUnits extends ABCommonsService {

    public ProjectProphaseWorkContent  remove(OptType optType, ProjectProphaseWorkContent  optProjectProphaseWorkContent ) throws SystemOptServiceException {
        
         Integer id =optProjectProphaseWorkContent.getId() ;
        ProjectProphaseWorkContent   removeProjectProphaseWorkContent =baseService.get(id, ProjectProphaseWorkContent.class);
        baseService.remove(removeProjectProphaseWorkContent);
        return  removeProjectProphaseWorkContent ;
    }

}
