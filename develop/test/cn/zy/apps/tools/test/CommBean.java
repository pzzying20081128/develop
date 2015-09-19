package cn.zy.apps.tools.test ;

import javax.persistence.Column ;

import javax.persistence.EnumType ;
import javax.persistence.Enumerated ;
import javax.persistence.MappedSuperclass ;
import javax.persistence.Temporal ;

import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.test.ConfigProperties.Status ;

@MappedSuperclass
public abstract class CommBean implements java.io.Serializable {

    private static final long serialVersionUID = 15993127981203978L ;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @FieldDesc(name="状态",desc="invalid:无效;delete:删除;normal:正常")
    private Status status ;

    public Status getStatus() {
        return status ;
    }

    public void setStatus(Status status) {
        this.status = status ;
    }

}
