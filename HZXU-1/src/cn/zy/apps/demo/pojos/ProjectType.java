package cn.zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.Table ;
import javax.persistence.Transient ;

import cn.zy.apps.tools.jpa.FieldDesc ;

@Entity
@Table(name = "project_type")
public class ProjectType extends HZCommBean {

    @Column(name = "type_name")
    @FieldDesc(name = "项目类型")
    private String typeName ;
    
    @Transient
    private String name;

    public String getTypeName() {
        return typeName ;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName ;
    }

    public String getName() {
        name = typeName;
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

}
