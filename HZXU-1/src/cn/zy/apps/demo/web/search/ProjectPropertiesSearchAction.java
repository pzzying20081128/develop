package cn.zy.apps.demo.web.search ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.Map ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.IProjectCarriedOutInfoService ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.CombSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("ProjectPropertiesSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectPropertiesSearchAction extends ABDemoSearchAction<CombSearchBean> {

    private String selecttype ;

    private String zrdw ;

    @Autowired
    @Qualifier(IProjectCarriedOutInfoService.name)
    private IProjectCarriedOutInfoService service ;

    @Override
    protected List<CombSearchBean> searchResult() throws Exception {

        ProjectCarriedOutInfoSearchBean searchBean = new ProjectCarriedOutInfoSearchBean() ;

        if (ToolsUnits.isNOtNulll(zrdw)) searchBean.setResponsibilityUnit(zrdw) ;

        {
            if (selecttype.equals("zrdw")) {
                searchBean.setResponsibilityUnit(name) ;

            }
            if (selecttype.equals("zrdwglr")) {
                searchBean.setResponsibilityUnitPerson(name) ;

            }
            if (selecttype.equals("msz")) {
                searchBean.setFenGuanMiShuZhang(name) ;

            }
            if (selecttype.equals("fsz")) {
                searchBean.setFenGuanHuShiZhang(name) ;

            }

        }
        List<CombSearchBean> combSearchBeans = new ArrayList<CombSearchBean>() ;
        List<ProjectCarriedOutInfo> results = service.searchCombo(OptType.search, searchBean, null) ;

        for (ProjectCarriedOutInfo projectCarriedOutInfo : results) {
            CombSearchBean combSearchBean = new CombSearchBean() ;
            combSearchBeans.add(combSearchBean) ;
            combSearchBean.setResult(projectCarriedOutInfo) ;
            switch (selecttype) {
            case "zrdw":
                combSearchBean.setName(projectCarriedOutInfo.getResponsibilityUnit()) ;
                combSearchBean.setId(combSearchBean.getName()) ;
                break ;
            case "zrdwglr":

                combSearchBean.setName(projectCarriedOutInfo.getResponsibilityUnitPerson()) ;
                combSearchBean.setId(combSearchBean.getName()) ;
                break ;
            case "msz":
                combSearchBean.setName(projectCarriedOutInfo.getFenGuanMiShuZhang()) ;
                combSearchBean.setId(combSearchBean.getName()) ;
                break ;
            case "fsz":
                combSearchBean.setName(projectCarriedOutInfo.getFenGuanHuShiZhang()) ;
                combSearchBean.setId(combSearchBean.getName()) ;
                break ;
            }

        }
        return combSearchBeans ;

    }

    public String getSelecttype() {
        return selecttype ;
    }

    public void setSelecttype(String selecttype) {
        this.selecttype = selecttype ;
    }

    public String getZrdw() {
        return zrdw ;
    }

    public void setZrdw(String zrdw) {
        this.zrdw = zrdw ;
    }

}
