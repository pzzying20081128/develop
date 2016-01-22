package cn.zy.apps.demo.pojos ;

import java.util.Date ;

import javax.persistence.CascadeType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.Table ;

import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.units.powers.CommBean ;

/**
 * 年度投资计划
 * @author you
 *
 */

@Entity
@Table(name = "project_year_investment_plan")
public class ProjectYearInvestmentPlan extends CommBean {

    @Column(name = "year")
    @FieldDesc(name = "年")
    private Date year ;

    @Column(name = "investment_plan")
    @FieldDesc(name = "投资计划")
    private String investmentPlan ;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_carried_out_info_id")
    private ProjectCarriedOutInfo projectCarriedOutInfo;
    @Column(name = "project_carried_out_info_id" ,insertable=false,updatable=false)
    private Integer  projectCarriedOutInfoId;

    public Date getYear() {
        return year ;
    }

    public void setYear(Date year) {
        this.year = year ;
    }

    public String getInvestmentPlan() {
        return investmentPlan ;
    }

    public void setInvestmentPlan(String investmentPlan) {
        this.investmentPlan = investmentPlan ;
    }

    public ProjectCarriedOutInfo getProjectCarriedOutInfo() {
        return projectCarriedOutInfo ;
    }

    public void setProjectCarriedOutInfo(ProjectCarriedOutInfo projectCarriedOutInfo) {
        this.projectCarriedOutInfo = projectCarriedOutInfo ;
    }

    public Integer getProjectCarriedOutInfoId() {
        return projectCarriedOutInfoId ;
    }

    public void setProjectCarriedOutInfoId(Integer projectCarriedOutInfoId) {
        this.projectCarriedOutInfoId = projectCarriedOutInfoId ;
    }

}
