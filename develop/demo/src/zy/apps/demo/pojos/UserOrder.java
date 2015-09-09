package zy.apps.demo.pojos ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.Id ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;
import javax.persistence.Table ;
import javax.persistence.Transient ;

@Entity
@Table(name = "user_order")
public class UserOrder {
    @Column(name = "uuids")
    @Id
    private String id ;

    @Column(name = "money")
    private Long money ;

    @Transient
    private String moneyMoneyShow ;

    @Column(name = "name")
    private String name ;

    @Column(name = "sax")
    private String sax ;

    @Transient
    private String nameSaxNameSaxShow ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user ;

    @Column(insertable = false, updatable = false, name = "user_id")
    private Integer userId ;

    public Long getMoney() {
        return money ;
    }

    public void setMoney(Long money) {
        this.money = money ;
    }

    public String getMoneyMoneyShow() {
        return moneyMoneyShow ;
    }

    public void setMoneyMoneyShow(String moneyMoneyShow) {
        this.moneyMoneyShow = moneyMoneyShow ;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public String getSax() {
        return sax ;
    }

    public void setSax(String sax) {
        this.sax = sax ;
    }

    public String getNameSaxNameSaxShow() {
        return nameSaxNameSaxShow ;
    }

    public void setNameSaxNameSaxShow(String nameSaxNameSaxShow) {
        this.nameSaxNameSaxShow = nameSaxNameSaxShow ;
    }

    public User getUser() {
        return user ;
    }

    public void setUser(User user) {
        this.user = user ;
    }

    public Integer getUserId() {
        return userId ;
    }

    public void setUserId(Integer userId) {
        this.userId = userId ;
    }

    public String getId() {
        return id ;
    }

    public void setId(String id) {
        this.id = id ;
    }

}
