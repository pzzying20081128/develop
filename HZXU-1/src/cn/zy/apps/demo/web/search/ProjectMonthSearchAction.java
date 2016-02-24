package cn.zy.apps.demo.web.search ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.Map ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.service.IProjectCarriedOutInfoService ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.demo.web.ABDemoSearchAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.units.CombSearchBean ;


@Component("ProjectMonthSearchAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class ProjectMonthSearchAction extends ABDemoSearchAction<CombSearchBean> {
    
    private static String[] monthsList = new String[] { "一月", "二月", "三月", "四月", "五月", "六月", "七月","八月", "九月", "十月", "十一月", "十二月" } ;
    
    
    @Autowired
    @Qualifier(IProjectCarriedOutInfoService.name)
    private  IProjectCarriedOutInfoService  projectCarriedOutInfoService;

    private Integer projectId ;
    
 

    @Override
    protected  List<CombSearchBean> searchResult() throws Exception {
        
        List<CombSearchBean>  sss=new ArrayList<CombSearchBean>();
        
        List<String >  month_ = projectCarriedOutInfoService.haveProjectMonth(projectId);
        
        for(String month:  monthsList ){
            if(!month_.contains(month)){
                CombSearchBean combSearchBean =new CombSearchBean();
                combSearchBean.setId(month);
                combSearchBean.setName(month);
                sss.add(combSearchBean);
            }
        }
        
          return sss ;
    }

    public Integer getProjectId() {
        return projectId ;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId ;
    }

}
