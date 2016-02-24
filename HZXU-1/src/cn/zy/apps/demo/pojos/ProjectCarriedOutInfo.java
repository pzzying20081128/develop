package cn.zy.apps.demo.pojos ;

import java.util.Date ;
import java.util.List ;

import javax.persistence.CascadeType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.OneToMany ;
import javax.persistence.Table ;
import javax.persistence.Transient ;

import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.units.DateToolsUilts ;
import cn.zy.apps.tools.units.DateToolsUilts.DateType ;

/**
 * 进行中的项目
 * @author you
 *
 */
@Entity
@Table(name = "project_carried_out_info")
public class ProjectCarriedOutInfo extends ProjectInfo {

    public ProjectCarriedOutInfo(String responsibilityUnit, String responsibilityUnitPerson, String responsibilityUnitPhoto,
            String fenGuanMiShuZhang, String fenGuanMiShuZhangPhoto, String fenGuanHuShiZhang, String fenGuanHuShiZhangPhoto) {
        super() ;
        this.responsibilityUnit = responsibilityUnit ;
        this.responsibilityUnitPerson = responsibilityUnitPerson ;
        this.responsibilityUnitPhoto = responsibilityUnitPhoto ;
        this.fenGuanMiShuZhang = fenGuanMiShuZhang ;
        this.fenGuanMiShuZhangPhoto = fenGuanMiShuZhangPhoto ;
        this.fenGuanHuShiZhang = fenGuanHuShiZhang ;
        this.fenGuanHuShiZhangPhoto = fenGuanHuShiZhangPhoto ;
    }

    public ProjectCarriedOutInfo() {

    }

    //总投资
    @Column(name = "total_investment")
    @FieldDesc(name = "总投资")
    private Double totalInvestment ;

    @Column(name = "construction_content")
    @FieldDesc(name = "项目建设内容")
    private String constructionContent ;

    @Column(name = "is_kai_gong")
    @FieldDesc(name = "是否开工")
    private String isKaiGong ;

    @Column(name = "is_production")
    @FieldDesc(name = "是否投产")
    private String isProduction ;

    @Column(name = "project_address")
    @FieldDesc(name = "项目地址")
    private String projectAddress ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_ownership_address_id")
    @FieldDesc(name = "项目归属地", mapping = "projectOwnershipAddress.address")
    private ProjectOwnershipAddress projectOwnershipAddress ;

    @Column(name = "project_ownership_address_id", insertable = false, updatable = false)
    @FieldDesc(name = "项目归属地")
    private Integer projectOwnershipAddressId ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_progress_id")
    @FieldDesc(name = "项目进展类型", mapping = "projectProgressType.typeName")
    private ProjectProgressType projectProgressType ;

    @Column(name = "project_progress_id", insertable = false, updatable = false)
    @FieldDesc(name = "项目进展类型")
    private Integer projectProgressTypeId ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_major_id")
    @FieldDesc(name = "项目重点分类", mapping = "projectMajorType.name")
    private ProjectMajorType projectMajorType ;

    @Column(name = "project_major_id", insertable = false, updatable = false)
    @FieldDesc(name = "项目重点分类")
    private Integer projectMajorTypeId ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type_id")
    @FieldDesc(name = "项目类型", mapping = "projectType.name")
    private ProjectType projectType ;

    @Column(name = "project_type_id", insertable = false, updatable = false)
    @FieldDesc(name = "项目类型")
    private Integer projectTypeId ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectCarriedOutInfo")
    private List<ProjectYearInvestmentPlan> projectYearInvestmentPlans ;

    @Column(name = "kai_gong_date")
    @FieldDesc(name = "开工时间")
    private Date kaiGongDate ;

    @Transient
    private String kaiGongDateString ;

    @Column(name = "build_start_date")
    @FieldDesc(name = "建设开始时间")
    private Date buildStartDate ;

    @Column(name = "build_end_date")
    @FieldDesc(name = "建设结束时间")
    private Date buildEndDate ;

    @Transient
    private String buildStartEndDate ;

    @Column(name = "implementation_unit")
    @FieldDesc(name = "项目实施单位")
    private String implementationUnit ;

    @Column(name = "implementation_unit_person")
    @FieldDesc(name = "项目实施负责人")
    private String implementationUnitPerson ;

    @Column(name = "implementation_unit_photo")
    @FieldDesc(name = "项目实施负责人手机号")
    private String implementationUnitPhoto ;

    /////////////////////////Responsibility unit
    @Column(name = "responsibility_unit")
    @FieldDesc(name = "项目责任单位")
    private String responsibilityUnit ;

    @Column(name = "responsibility_unit_person")
    @FieldDesc(name = "项目责任单位负责人")
    private String responsibilityUnitPerson ;

    @Column(name = "responsibility_unit_photo")
    @FieldDesc(name = "项目责任单位负责人手机号")
    private String responsibilityUnitPhoto ;

    @Column(name = "fen_guan_mi_shu_zhang")
    @FieldDesc(name = "分管副秘书长")
    private String fenGuanMiShuZhang ;

    @Column(name = "fen_guan_mi_shu_zhang_photo")
    @FieldDesc(name = "分管副秘书长手机号")
    private String fenGuanMiShuZhangPhoto ;

    @Column(name = "fen_guan_hu_shi_zhang")
    @FieldDesc(name = "分管副市长")
    private String fenGuanHuShiZhang ;

    @Column(name = "fen_guan_hu_shi_zhang_photo")
    @FieldDesc(name = "分管副市长手机号")
    private String fenGuanHuShiZhangPhoto ;

    public Double getTotalInvestment() {
        return totalInvestment ;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment ;
    }

    public String getConstructionContent() {
        return constructionContent ;
    }

    public void setConstructionContent(String constructionContent) {
        this.constructionContent = constructionContent ;
    }

    public String getIsKaiGong() {
        return isKaiGong ;
    }

    public void setIsKaiGong(String isKaiGong) {
        this.isKaiGong = isKaiGong ;
    }

    public String getIsProduction() {
        return isProduction ;
    }

    public void setIsProduction(String isProduction) {
        this.isProduction = isProduction ;
    }

    public String getProjectAddress() {
        return projectAddress ;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress ;
    }

    public ProjectOwnershipAddress getProjectOwnershipAddress() {
        return projectOwnershipAddress ;
    }

    public void setProjectOwnershipAddress(ProjectOwnershipAddress projectOwnershipAddress) {
        this.projectOwnershipAddress = projectOwnershipAddress ;
    }

    public Integer getProjectOwnershipAddressId() {
        return projectOwnershipAddressId ;
    }

    public void setProjectOwnershipAddressId(Integer projectOwnershipAddressId) {
        this.projectOwnershipAddressId = projectOwnershipAddressId ;
    }

    public ProjectProgressType getProjectProgressType() {
        return projectProgressType ;
    }

    public void setProjectProgressType(ProjectProgressType projectProgressType) {
        this.projectProgressType = projectProgressType ;
    }

    public Integer getProjectProgressTypeId() {
        return projectProgressTypeId ;
    }

    public void setProjectProgressTypeId(Integer projectProgressTypeId) {
        this.projectProgressTypeId = projectProgressTypeId ;
    }

    public ProjectMajorType getProjectMajorType() {
        return projectMajorType ;
    }

    public void setProjectMajorType(ProjectMajorType projectMajorType) {
        this.projectMajorType = projectMajorType ;
    }

    public Integer getProjectMajorTypeId() {
        return projectMajorTypeId ;
    }

    public void setProjectMajorTypeId(Integer projectMajorTypeId) {
        this.projectMajorTypeId = projectMajorTypeId ;
    }

    public Date getKaiGongDate() {
        return kaiGongDate ;
    }

    public void setKaiGongDate(Date kaiGongDate) {
        this.kaiGongDate = kaiGongDate ;
    }

    public Date getBuildStartDate() {
        return buildStartDate ;
    }

    public void setBuildStartDate(Date buildStartDate) {
        this.buildStartDate = buildStartDate ;
    }

    public Date getBuildEndDate() {
        return buildEndDate ;
    }

    public void setBuildEndDate(Date buildEndDate) {
        this.buildEndDate = buildEndDate ;
    }

    public String getImplementationUnit() {
        return implementationUnit ;
    }

    public void setImplementationUnit(String implementationUnit) {
        this.implementationUnit = implementationUnit ;
    }

    public String getImplementationUnitPerson() {
        return implementationUnitPerson ;
    }

    public void setImplementationUnitPerson(String implementationUnitPerson) {
        this.implementationUnitPerson = implementationUnitPerson ;
    }

    public String getImplementationUnitPhoto() {
        return implementationUnitPhoto ;
    }

    public void setImplementationUnitPhoto(String implementationUnitPhoto) {
        this.implementationUnitPhoto = implementationUnitPhoto ;
    }

    public String getResponsibilityUnit() {
        return responsibilityUnit ;
    }

    public void setResponsibilityUnit(String responsibilityUnit) {
        this.responsibilityUnit = responsibilityUnit ;
    }

    public String getResponsibilityUnitPerson() {
        return responsibilityUnitPerson ;
    }

    public void setResponsibilityUnitPerson(String responsibilityUnitPerson) {
        this.responsibilityUnitPerson = responsibilityUnitPerson ;
    }

    public String getResponsibilityUnitPhoto() {
        return responsibilityUnitPhoto ;
    }

    public void setResponsibilityUnitPhoto(String responsibilityUnitPhoto) {
        this.responsibilityUnitPhoto = responsibilityUnitPhoto ;
    }

    public String getFenGuanMiShuZhang() {
        return fenGuanMiShuZhang ;
    }

    public void setFenGuanMiShuZhang(String fenGuanMiShuZhang) {
        this.fenGuanMiShuZhang = fenGuanMiShuZhang ;
    }

    public String getFenGuanMiShuZhangPhoto() {
        return fenGuanMiShuZhangPhoto ;
    }

    public void setFenGuanMiShuZhangPhoto(String fenGuanMiShuZhangPhoto) {
        this.fenGuanMiShuZhangPhoto = fenGuanMiShuZhangPhoto ;
    }

    public String getFenGuanHuShiZhang() {
        return fenGuanHuShiZhang ;
    }

    public void setFenGuanHuShiZhang(String fenGuanHuShiZhang) {
        this.fenGuanHuShiZhang = fenGuanHuShiZhang ;
    }

    public String getFenGuanHuShiZhangPhoto() {
        return fenGuanHuShiZhangPhoto ;
    }

    public void setFenGuanHuShiZhangPhoto(String fenGuanHuShiZhangPhoto) {
        this.fenGuanHuShiZhangPhoto = fenGuanHuShiZhangPhoto ;
    }

    public String getKaiGongDateString() {
        if (kaiGongDate != null) kaiGongDateString = DateToolsUilts.dateToString(kaiGongDate, DateType.yyyy.toString()) ;
        return kaiGongDateString ;
    }

    public String getBuildStartEndDate() {
        if (buildStartDate != null) buildStartEndDate = DateToolsUilts.dateToString(this.buildStartDate, DateType.yyyy.toString()) ;
        if (buildEndDate != null) buildStartEndDate = buildStartEndDate + "-" + DateToolsUilts.dateToString(this.buildEndDate, DateType.yyyy.toString()) ;

        return buildStartEndDate ;
    }

    public ProjectType getProjectType() {
        return projectType ;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType ;
    }

    public Integer getProjectTypeId() {
        return projectTypeId ;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId ;
    }

    public List<ProjectYearInvestmentPlan> getProjectYearInvestmentPlans() {
        return projectYearInvestmentPlans ;
    }

    public void setProjectYearInvestmentPlans(List<ProjectYearInvestmentPlan> projectYearInvestmentPlans) {
        this.projectYearInvestmentPlans = projectYearInvestmentPlans ;
    }

    public void setKaiGongDateString(String kaiGongDateString) {
        this.kaiGongDateString = kaiGongDateString ;
    }

    public void setBuildStartEndDate(String buildStartEndDate) {
        this.buildStartEndDate = buildStartEndDate ;
    }

}
