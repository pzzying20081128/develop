package cn.zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.EnumType ;
import javax.persistence.Enumerated ;
import javax.persistence.MappedSuperclass ;

import cn.zy.apps.demo.HZXUProjectConfig.Status ;
import cn.zy.apps.tools.units.powers.CommBean ;

@MappedSuperclass
public class HZCommBean extends CommBean {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    protected Status status ;

    public Status getStatus() {
        return status ;
    }

    public void setStatus(Status status) {
        this.status = status ;
    }

}
