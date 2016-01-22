package cn.zy.apps.demo.pojos ;

import java.util.Date ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.Table ;

import cn.zy.apps.tools.jpa.FieldDesc ;

/**
 * 前期 工作内容
 * @author you
 *
 */
@Entity
@Table(name = "project_prophase_work_content")
public class ProjectProphaseWorkContent extends HZCommBean {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_prophase_info_id")
    private ProjectProphaseInfo projectProphaseInfo ;

    @Column(insertable = false, updatable = false, name = "project_prophase_info_id")
    private Integer projectProphaseInfoId ;

    @Column(name = "work_date")
    @FieldDesc(name = "日期")
    private Date workDate ;
    @Column(name = "content")
    private String content ;

    public ProjectProphaseInfo getProjectProphaseInfo() {
        return projectProphaseInfo ;
    }

    public void setProjectProphaseInfo(ProjectProphaseInfo projectProphaseInfo) {
        this.projectProphaseInfo = projectProphaseInfo ;
    }

    public Integer getProjectProphaseInfoId() {
        return projectProphaseInfoId ;
    }

    public void setProjectProphaseInfoId(Integer projectProphaseInfoId) {
        this.projectProphaseInfoId = projectProphaseInfoId ;
    }

    public Date getWorkDate() {
        return workDate ;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate ;
    }

    public String getContent() {
        return content ;
    }

    public void setContent(String content) {
        this.content = content ;
    }

}
