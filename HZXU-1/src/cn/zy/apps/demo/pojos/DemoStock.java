package cn.zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.JoinColumn ;
import javax.persistence.OneToOne ;
import javax.persistence.Table ;

import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.units.powers.CommBean ;

@Entity
@Table(name = "demo_dtock")
public class DemoStock extends CommBean {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demo_userid")
    @FieldDesc(name = "采购人", mapping = "stockUser.userName")
    private DemoUser stockUser ;

    @Column(name = "demo_userid", insertable = false, updatable = false)
    @FieldDesc(name = "采购人", isShow = false)
    private Integer stockUserId ;

    @Column(name = "stock_name")
    @FieldDesc(name = "采购单")
    private String stockName ;

    public DemoUser getStockUser() {
        return stockUser ;
    }

    public void setStockUser(DemoUser stockUser) {
        this.stockUser = stockUser ;
    }

    public Integer getStockUserId() {
        return stockUserId ;
    }

    public void setStockUserId(Integer stockUserId) {
        this.stockUserId = stockUserId ;
    }

    public String getStockName() {
        return stockName ;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName ;
    }

}
