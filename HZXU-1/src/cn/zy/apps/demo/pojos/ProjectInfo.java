package cn.zy.apps.demo.pojos ;

import java.util.Date ;

import javax.persistence.Column ;
import javax.persistence.MappedSuperclass ;

import cn.zy.apps.demo.HZXUProjectConfig.ProjectStauts ;
import cn.zy.apps.tools.jpa.FieldDesc ;

//@Entity
//@Table(name = "project_info")
@MappedSuperclass
public abstract class ProjectInfo extends HZCommBean {

    @Column(name = "name")
    @FieldDesc(name = "项目名称")
    private String name ;

    @Column(name = "project_status")
    @FieldDesc(name = "项目状态")
    private ProjectStauts projectStauts ;

    @Column(name = "text")
    @FieldDesc(name = "备注")
    private String text ;
    
    @Column(name = "project_date")
    @FieldDesc(name = "项目时间")
    private Date  projectDate;

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }


    public String getText() {
        return text ;
    }

    public void setText(String text) {
        this.text = text ;
    }

    public Date getProjectDate() {
        return projectDate ;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate ;
    }

    public ProjectStauts getProjectStauts() {
        return projectStauts ;
    }

    public void setProjectStauts(ProjectStauts projectStauts) {
        this.projectStauts = projectStauts ;
    }

}
