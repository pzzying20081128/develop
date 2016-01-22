package cn.zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.Table ;

import cn.zy.apps.tools.jpa.FieldDesc ;

/**
 * 项目重点分类
 * @author you
 *
 */
@Entity
@Table(name = "project_major_type")
public class ProjectMajorType extends HZCommBean {

    @Column(name = "address")
    @FieldDesc(name = "项目重点分类")
    private String name ;

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

}
