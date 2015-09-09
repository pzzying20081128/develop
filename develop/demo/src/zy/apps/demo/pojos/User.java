package zy.apps.demo.pojos ;

import java.util.List ;

import javax.persistence.CascadeType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType ;
import javax.persistence.Id ;
import javax.persistence.OneToMany ;
import javax.persistence.OneToOne ;
import javax.persistence.Table ;

@Entity
@Table(name = "user")
public class User {
    @Column(name = "uuids")
    @Id
    private Integer id ;

    @Column(name = "name")
    private String name ;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy="user")
    private List<UserOrder> orders ;

    public Integer getId() {
        return id ;
    }

    public void setId(Integer id) {
        this.id = id ;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public List<UserOrder> getOrders() {
        return orders ;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders ;
    }

}
