package cn.zy.apps.demo.pojos ;

import java.util.List ;

import javax.persistence.CascadeType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.OneToMany ;
import javax.persistence.Table ;

import cn.zy.apps.tools.units.powers.UserPower ;

@Entity
@Table(name = "demo_user_power")
public class DemoUserPower extends UserPower<DemoUserOptPower> {

    //  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //  @JoinColumn(name = "user_power_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_power_id")
    private List<DemoUserOptPower> demoUserOptPowers ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private DemoUser demoUser ;
    
    @Column(insertable=false,updatable=false,name="user_info_id")
    private Integer  userInfoId;

    @Column(name = "user_info_id", insertable = false, updatable = false)
    private Integer demoUserId ;

    public List<DemoUserOptPower> getDemoUserOptPowers() {
        return demoUserOptPowers ;
    }

    public void setDemoUserOptPowers(List<DemoUserOptPower> demoUserOptPowers) {
        this.demoUserOptPowers = demoUserOptPowers ;
    }

    public DemoUser getDemoUser() {
        return demoUser ;
    }

    public void setDemoUser(DemoUser demoUser) {
        this.demoUser = demoUser ;
    }

    public Integer getDemoUserId() {
        return demoUserId ;
    }

    public void setDemoUserId(Integer demoUserId) {
        this.demoUserId = demoUserId ;
    }

    public Integer getUserInfoId() {
        return userInfoId ;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId ;
    }

}
