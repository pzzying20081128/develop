package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectProphaseInfoRemoveUnits")
public class ProjectProphaseInfoRemoveUnits extends ABCommonsService {

    public ProjectProphaseInfo remove(OptType optType, ProjectProphaseInfo optProjectProphaseInfo) throws SystemOptServiceException {

        Integer id = optProjectProphaseInfo.getId() ;
        ProjectProphaseInfo removeProjectProphaseInfo = baseService.get(id, ProjectProphaseInfo.class) ;
        baseService.remove(removeProjectProphaseInfo) ;
        return removeProjectProphaseInfo ;
    }

}
