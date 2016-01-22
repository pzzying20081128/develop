package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.units.search.bean.ProjectProphaseInfoSearchBean ;
import cn.zy.apps.tools.units.CombSearchBean ;

public interface IResponsibilityUnitService {
    
    public String name = "IResponsibilityUnitService";

    public List<CombSearchBean> search(ProjectProphaseInfoSearchBean searchBean, int... startLimt) throws SystemOptServiceException ;

}
