package cn.zy.apps.demo.pojos ;

import java.util.List ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.OneToMany ;
import javax.persistence.Table ;

import cn.zy.apps.tools.units.powers.CommBean ;

@Entity
@Table(name = "demo_department")
public class DemoDepartment extends CommBean {

    @Column(name="name")
    private String name ;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<DemoUser> demoUsers ;

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public List<DemoUser> getDemoUsers() {
        return demoUsers ;
    }

    public void setDemoUsers(List<DemoUser> demoUsers) {
        this.demoUsers = demoUsers ;
    }

}
