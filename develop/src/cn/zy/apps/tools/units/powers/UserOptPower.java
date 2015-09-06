package cn.zy.apps.tools.units.powers ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.MappedSuperclass ;
import javax.persistence.Table ;
import javax.persistence.Transient ;

@MappedSuperclass
public    class  UserOptPower {

//    
    
    @Column(name = "power_name")
    protected String powerName ;
    @Column(name = "power_code")
    protected String powerCode ;

    // 0 no 1 use
    @Column(name = "is_use")
    protected Integer  isUse ;

    public String getPowerName() {
        return powerName ;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName ;
    }

    public String getPowerCode() {
        return powerCode ;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode ;
    }

    public Integer getIsUse() {
        return isUse ;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse ;
    }

    
}
