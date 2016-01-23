package cn.zy.apps.demo.pojos ;

import java.util.List ;

import javax.persistence.CascadeType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.OneToMany ;
import javax.persistence.Table ;

import cn.zy.apps.tools.jpa.FieldDesc ;

/**
 * 前期预备计划
 * @author you
 *
 */

@Entity
@Table(name = "project_prophase_info")
public class ProjectProphaseInfo extends ProjectInfo {


    //责任单位
    @Column(name = "responsibility_unit")
    @FieldDesc(name = "责任单位")
    private String responsibilityUnit ;

    //实施单位
    @Column(name = "implementation_unit")
    @FieldDesc(name = "实施单位")
    private String implementationUnit ;

    //建设规模
    @Column(name = "construction_scale")
    @FieldDesc(name = "建设规模")
    private String constructionScale ;

    //估算总投资
    @Column(name = "estimated_total_investment")
    @FieldDesc(name = "总投资")
    private Double estimatedTotalInvestment ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectProphaseInfo",cascade=CascadeType.ALL)
    private List<ProjectProphaseWorkContent> projectProphaseWorkContents ;

  

    public String getResponsibilityUnit() {
        return responsibilityUnit ;
    }

    public void setResponsibilityUnit(String responsibilityUnit) {
        this.responsibilityUnit = responsibilityUnit ;
    }

    public String getImplementationUnit() {
        return implementationUnit ;
    }

    public void setImplementationUnit(String implementationUnit) {
        this.implementationUnit = implementationUnit ;
    }

    public String getConstructionScale() {
        return constructionScale ;
    }

    public void setConstructionScale(String constructionScale) {
        this.constructionScale = constructionScale ;
    }

    public Double getEstimatedTotalInvestment() {
        return estimatedTotalInvestment ;
    }

    public void setEstimatedTotalInvestment(Double estimatedTotalInvestment) {
        this.estimatedTotalInvestment = estimatedTotalInvestment ;
    }

    public List<ProjectProphaseWorkContent> getProjectProphaseWorkContents() {
        return projectProphaseWorkContents ;
    }

    public void setProjectProphaseWorkContents(List<ProjectProphaseWorkContent> projectProphaseWorkContents) {
        this.projectProphaseWorkContents = projectProphaseWorkContents ;
    }

}
