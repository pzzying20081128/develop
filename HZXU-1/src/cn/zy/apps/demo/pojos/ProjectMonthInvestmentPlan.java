package cn.zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.EnumType ;
import javax.persistence.Enumerated ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.Table ;

import org.springframework.mock.staticmock.MockStaticEntityMethods ;

import cn.zy.apps.demo.HZXUProjectConfig.Complete ;
import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.units.powers.CommBean ;

@Entity
@Table(name = "project_month_investment_plan")
public class ProjectMonthInvestmentPlan extends CommBean {

    @Column(name = "month")
    @FieldDesc(name = "月")
    private String month ;

    @Column(name = "investment_plan")
    @FieldDesc(name = "月投资计划")
    private String investmentPlan ;

    @Column(name = "construction_content")
    @FieldDesc(name = "主要建设内容")
    private String constructionContent ;

    @Column(name = "existing_problems")
    @FieldDesc(name = "存在问题")
    private String existingProblems ;

    @Column(name = "image_progress")
    @FieldDesc(name = "形象进度")
    private String imageProgress ;
    @Column(name = "is_complete")
    @FieldDesc(name = "是否完成")
    @Enumerated(EnumType.STRING)
    private Complete complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_year_investment_plan_id",nullable=false)
    private ProjectYearInvestmentPlan projectYearInvestmentPlan ;

    @Column(name = "project_year_investment_plan_id", insertable = false, updatable = false)
    private Integer projectYearInvestmentPlanId ;

    public String getMonth() {
        return month ;
    }

    public void setMonth(String month) {
        this.month = month ;
    }

    public String getInvestmentPlan() {
        return investmentPlan ;
    }

    public void setInvestmentPlan(String investmentPlan) {
        this.investmentPlan = investmentPlan ;
    }

    public String getConstructionContent() {
        return constructionContent ;
    }

    public void setConstructionContent(String constructionContent) {
        this.constructionContent = constructionContent ;
    }

    public String getExistingProblems() {
        return existingProblems ;
    }

    public void setExistingProblems(String existingProblems) {
        this.existingProblems = existingProblems ;
    }

    public String getImageProgress() {
        return imageProgress ;
    }

    public void setImageProgress(String imageProgress) {
        this.imageProgress = imageProgress ;
    }

    public ProjectYearInvestmentPlan getProjectYearInvestmentPlan() {
        return projectYearInvestmentPlan ;
    }

    public void setProjectYearInvestmentPlan(ProjectYearInvestmentPlan projectYearInvestmentPlan) {
        this.projectYearInvestmentPlan = projectYearInvestmentPlan ;
    }

    public Integer getProjectYearInvestmentPlanId() {
        return projectYearInvestmentPlanId ;
    }

    public void setProjectYearInvestmentPlanId(Integer projectYearInvestmentPlanId) {
        this.projectYearInvestmentPlanId = projectYearInvestmentPlanId ;
    }

    public Complete getComplete() {
        return complete ;
    }

    public void setComplete(Complete complete) {
        this.complete = complete ;
    }

    

}
