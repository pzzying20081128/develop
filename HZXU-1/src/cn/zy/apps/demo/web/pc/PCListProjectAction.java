package cn.zy.apps.demo.web.pc ;

import java.awt.image.BufferedImage ;
import java.io.ByteArrayOutputStream ;
import java.io.File ;
import java.io.IOException ;
import java.util.Hashtable ;
import java.util.List ;

import javax.imageio.ImageIO ;
import javax.servlet.ServletOutputStream ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import com.google.zxing.BarcodeFormat ;
import com.google.zxing.EncodeHintType ;
import com.google.zxing.MultiFormatWriter ;
import com.google.zxing.common.BitMatrix ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.pojos.ProjectMonthInvestmentPlan ;
import cn.zy.apps.demo.pojos.ProjectYearInvestmentPlan ;
import cn.zy.apps.demo.service.IProjectCarriedOutInfoService ;
import cn.zy.apps.demo.service.IProjectMonthInvestmentPlanService ;
import cn.zy.apps.demo.service.IProjectYearInvestmentPlanService ;
import cn.zy.apps.demo.units.search.bean.DemoStockSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectMonthInvestmentPlanSearchBean ;
import cn.zy.apps.demo.units.search.bean.ProjectYearInvestmentPlanSearchBean ;
import cn.zy.apps.demo.web.ABDemoSystemAction ;
import cn.zy.apps.demo.web.DemoUserAction ;
import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.units.MatrixToImageWriter ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("PCListProjectAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class PCListProjectAction extends ABDemoSystemAction<ProjectCarriedOutInfo, ProjectCarriedOutInfoSearchBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 6070665942541338560L ;

    @Autowired
    @Qualifier(IProjectCarriedOutInfoService.name)
    IProjectCarriedOutInfoService service ;

    @Autowired
    @Qualifier(IProjectYearInvestmentPlanService.name)
    IProjectYearInvestmentPlanService iProjectYearInvestmentPlanService ;

    @Autowired
    @Qualifier(IProjectMonthInvestmentPlanService.name)
    IProjectMonthInvestmentPlanService iProjectMonthInvestmentPlanService ;

    private List<ProjectCarriedOutInfo> projectCarriedOutInfos ;

    private ProjectCarriedOutInfo projectCarriedOutInfo ;

    private List<ProjectYearInvestmentPlan> projectYearInvestmentPlans ;

    private List<ProjectMonthInvestmentPlan> projectMonthInvestmentPlans ;

    public String list_key_project() throws Exception {
        try {
            projectCarriedOutInfos = service.searchList(optType, searchBean, null, 0,100) ;
            System.err.println("==>   "+projectCarriedOutInfos) ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

        return SUCCESS ;

    }
    
    public String   get_key_project() throws Exception {
        try {
            projectCarriedOutInfo = service.get(uuid) ;
            writeObjectService.intToPrpertiesUnits(projectCarriedOutInfo) ;
            ProjectYearInvestmentPlanSearchBean projectYearInvestmentPlanSearchBean = new ProjectYearInvestmentPlanSearchBean() ;
            projectYearInvestmentPlanSearchBean.setProjectCarriedOutInfoId(uuid) ;
            projectYearInvestmentPlans = iProjectYearInvestmentPlanService.searchListJoinMonth(OptType.search, projectYearInvestmentPlanSearchBean, null) ;

        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

        return SUCCESS ;
    }
    
    public String   search_count_key_project() throws Exception {
        try {
            
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

        return SUCCESS ;
    }

    public List<ProjectCarriedOutInfo> getProjectCarriedOutInfos() {
        return projectCarriedOutInfos ;
    }

    public void setProjectCarriedOutInfos(List<ProjectCarriedOutInfo> projectCarriedOutInfos) {
        this.projectCarriedOutInfos = projectCarriedOutInfos ;
    }

    public ProjectCarriedOutInfo getProjectCarriedOutInfo() {
        return projectCarriedOutInfo ;
    }

    public void setProjectCarriedOutInfo(ProjectCarriedOutInfo projectCarriedOutInfo) {
        this.projectCarriedOutInfo = projectCarriedOutInfo ;
    }

    public List<ProjectYearInvestmentPlan> getProjectYearInvestmentPlans() {
        return projectYearInvestmentPlans ;
    }

    public void setProjectYearInvestmentPlans(List<ProjectYearInvestmentPlan> projectYearInvestmentPlans) {
        this.projectYearInvestmentPlans = projectYearInvestmentPlans ;
    }

    public List<ProjectMonthInvestmentPlan> getProjectMonthInvestmentPlans() {
        return projectMonthInvestmentPlans ;
    }

    public void setProjectMonthInvestmentPlans(List<ProjectMonthInvestmentPlan> projectMonthInvestmentPlans) {
        this.projectMonthInvestmentPlans = projectMonthInvestmentPlans ;
    }

}
