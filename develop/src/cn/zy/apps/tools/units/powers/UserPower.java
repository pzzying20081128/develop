package cn.zy.apps.tools.units.powers ;

import java.util.List ;

import javax.persistence.CascadeType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.MappedSuperclass ;
import javax.persistence.OneToMany ;
import javax.persistence.Table ;

// Generated 2013-6-20 10:53:13 by Hibernate Tools 3.4.0.CR1

/**
 * 用户权限
 */

@MappedSuperclass
public   class UserPower<OPT extends UserOptPower> implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3236790824914583703L ;

    // treeid
    @Column(name = "module_id")
    private  String moduleId ;

    @Column(name = "module_name")
    private String moduleName ;
    
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="user_power_id")
    private  List<OPT> userOptPowers;

    public String getModuleId() {
        return moduleId ;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId ;
    }

    public String getModuleName() {
        return moduleName ;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName ;
    }

    public List<OPT> getUserOptPowers() {
        return userOptPowers ;
    }

    public void setUserOptPowers(List<OPT> userOptPowers) {
        this.userOptPowers = userOptPowers ;
    }

}
