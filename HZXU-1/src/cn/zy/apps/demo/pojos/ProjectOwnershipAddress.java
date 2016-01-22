package cn.zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.Table ;
import javax.persistence.Transient ;

import cn.zy.apps.tools.jpa.FieldDesc ;

@Entity
@Table(name = "project_ownership_address")
public class ProjectOwnershipAddress extends HZCommBean {

    @Column(name = "address")
    @FieldDesc(name = "项目归属地")
    private String address ;
    
    @Transient
    private String name = address;

    public String getAddress() {
        return address ;
    }

    public void setAddress(String address) {
        this.address = address ;
    }

    public String getName() {
        name = address;
        return name ;
    }

}
