package cn.zy.apps.demo.pojos ;

import java.util.List ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.Table ;
import javax.persistence.Transient ;

import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.units.powers.ABUser ;

@Entity
@Table(name = "demo_user")
public class DemoUser extends ABUser {
    @Column(name = "user_name")
    @FieldDesc(name = "用户信息")
    private String userName ;
    
    @Column(name = "password")
    private  String password;
    @Column(name = "user_xm")
    private String userXMing;

    @Transient
    private String name ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DemoDepartment department ;

    @Column(name = "department_id", insertable = false, updatable = false)
    private Integer departmentId ;

    @Transient
    private List<DemoUserPower> systemUserPowers ;

    public String getUserName() {
        return userName ;
    }

    public void setUserName(String userName) {
        this.userName = userName ;
    }

    public DemoDepartment getDepartment() {
        return department ;
    }

    public void setDepartment(DemoDepartment department) {
        this.department = department ;
    }

    public Integer getDepartmentId() {
        return departmentId ;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId ;
    }

    public List<DemoUserPower> getSystemUserPowers() {
        return systemUserPowers ;
    }

    public void setSystemUserPowers(List<DemoUserPower> systemUserPowers) {
        this.systemUserPowers = systemUserPowers ;
    }

    public String getName() {
        String xx =userName+" "+userXMing ;
        name = xx ;
        return name ;
    }

    public String getPassword() {
        return password ;
    }

    public void setPassword(String password) {
        this.password = password ;
    }

    public String getUserXMing() {
        return userXMing ;
    }

    public void setUserXMing(String userXMing) {
        this.userXMing = userXMing ;
    }

    public void setName(String name) {
        this.name = name ;
    }

}
