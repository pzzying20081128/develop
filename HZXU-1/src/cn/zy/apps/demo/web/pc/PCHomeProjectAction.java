package cn.zy.apps.demo.web.pc ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.HZXUProjectConfig.Complete ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.IPCHomeService ;
import cn.zy.apps.demo.units.beans.PCHomeSumBean ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.demo.web.ABDemoSystemAction ;
import cn.zy.apps.demo.web.DemoUserAction ;

@Component("PCHomeProjectAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class PCHomeProjectAction extends ABDemoSystemAction<ProjectCarriedOutInfo, ProjectCarriedOutInfoSearchBean> {

    @Autowired
    @Qualifier(IPCHomeService.name)
    private IPCHomeService pcHomeService ;

    private PCHomeSumBean keyPCHomeSumBean ;

    private List<ProjectCarriedOutInfo> projectCarriedOutInfos ;

    private Complete monthComplete ;
    
    private Integer  monthCompleteInt;

    public String searchKeyStatisticsSum() throws Exception {
        try {
            keyPCHomeSumBean = pcHomeService.searchKeyStatisticsSum() ;
            this.success = true ;
        } catch (Exception e) {
            this.msg = handError(e) ;
            this.success = false ;
        }

        return SUCCESS ;
    }

    public String listYearMonthProjectCarriedOutInfo() throws Exception {
        try {
            monthComplete = monthCompleteInt==1?Complete.已完成:Complete.末完成;
            projectCarriedOutInfos = pcHomeService.listYearMonthProjectCarriedOutInfo(monthComplete) ;
            this.success = true ;
        } catch (Exception e) {
            this.msg = handError(e) ;
            this.success = false ;
        }

        return SUCCESS ;
    }

    public PCHomeSumBean getKeyPCHomeSumBean() {
        return keyPCHomeSumBean ;
    }

    public void setKeyPCHomeSumBean(PCHomeSumBean keyPCHomeSumBean) {
        this.keyPCHomeSumBean = keyPCHomeSumBean ;
    }

    public List<ProjectCarriedOutInfo> getProjectCarriedOutInfos() {
        return projectCarriedOutInfos ;
    }

    public void setProjectCarriedOutInfos(List<ProjectCarriedOutInfo> projectCarriedOutInfos) {
        this.projectCarriedOutInfos = projectCarriedOutInfos ;
    }

    public Complete getMonthComplete() {
        return monthComplete ;
    }

    public void setMonthComplete(Complete monthComplete) {
        this.monthComplete = monthComplete ;
    }

    public Integer getMonthCompleteInt() {
        return monthCompleteInt ;
    }

    public void setMonthCompleteInt(Integer monthCompleteInt) {
        this.monthCompleteInt = monthCompleteInt ;
    }

}
