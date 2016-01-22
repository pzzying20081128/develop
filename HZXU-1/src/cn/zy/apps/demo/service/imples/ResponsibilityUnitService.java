package cn.zy.apps.demo.service.imples ;

import java.util.ArrayList ;
import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseInfo ;
import cn.zy.apps.demo.service.IProjectProphaseInfoService ;
import cn.zy.apps.demo.service.IResponsibilityUnitService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.tools.units.CombSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

/**
 * 责任单位
 * @author you
 *
 */
@Component(IResponsibilityUnitService.name)
public class ResponsibilityUnitService implements IResponsibilityUnitService {

    @Autowired
    @Qualifier(IProjectProphaseInfoService.name)
    private IProjectProphaseInfoService projectProphaseInfoService ;

    @Override
    public List<CombSearchBean> search(ProjectProphaseInfoSearchBean searchBean, int... startLimt) throws SystemOptServiceException {
       
        List<ProjectProphaseInfo> projectProphaseInfos = projectProphaseInfoService.searchList(OptType.save, searchBean, null, startLimt) ;
        List<CombSearchBean> commSearchBeans = new ArrayList<CombSearchBean>() ;
        for (ProjectProphaseInfo projectProphaseInfo : projectProphaseInfos) {
            CombSearchBean commSearchBean = new CombSearchBean() ;
            commSearchBeans.add(commSearchBean) ;
            commSearchBean.setId(projectProphaseInfo.getResponsibilityUnit()) ;
            commSearchBean.setName(projectProphaseInfo.getResponsibilityUnit()) ;
        }
        return commSearchBeans ;
    }

}
