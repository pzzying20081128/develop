package cn.zy.apps.tools.test ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ;

import cn.zy.apps.tools.jpa.FieldDesc ;

// Generated 2013-6-20 10:53:13 by Hibernate Tools 3.4.0.CR1

/**
 * 企业员工
 * @author you
 *
 */
@Entity
@Table(name = "staff_info")
public class StaffInfo extends CommBean {

    private static final long serialVersionUID = 466546235646688855L ;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id ;

    @Column(name = "name", length = 10)
    @FieldDesc(name = "姓名")
    private String name ;

    @Column(name = "sex", length = 1)
    @FieldDesc(name = "性别", desc = "0:女;1:男")
    private Integer sex ;

    @Column(name = "phone", length = 20)
    @FieldDesc(name = "手机")
    private String phone ;

    @Column(name = "cell", length = 20)
    @FieldDesc(name = "联系电话")
    private String cell ;

    @Column(name = "address", length = 200)
    @FieldDesc(name = "地址")
    private String address ;

    @Column(name = "is_stock", length = 1)
    @FieldDesc(name = "是否采购员", desc = "0:否;1:是")
    private Integer isStock ;

    @Column(name = "is_biz", length = 1)
    @FieldDesc(name = "是否业务员", desc = "0:否;1:是")
    private Integer isBiz ;

    @Column(name = "is_transport_man", length = 1)
    @FieldDesc(name = "是否运输员", desc = "0:否;1:是")
    private Integer isTransportContactMan ;

    @Column(name = "is_goods_man", length = 1)
    @FieldDesc(name = "是否理货员", desc = "0:否;1:是")
    private Integer isGoodsMan ;

    @Column(name = "is_distribution_man", length = 1)
    @FieldDesc(name = "是否配货员", desc = "0:否;1:是")
    private Integer isDistributionMan ;

    @Column(name = "text", length = 200)
    private String text ;

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

    public Integer getSex() {
        return sex ;
    }

    public void setSex(Integer sex) {
        this.sex = sex ;
    }

    public String getPhone() {
        return phone ;
    }

    public void setPhone(String phone) {
        this.phone = phone ;
    }

    public String getCell() {
        return cell ;
    }

    public void setCell(String cell) {
        this.cell = cell ;
    }

    public String getAddress() {
        return address ;
    }

    public void setAddress(String address) {
        this.address = address ;
    }

    public Integer getIsStock() {
        return isStock ;
    }

    public void setIsStock(Integer isStock) {
        this.isStock = isStock ;
    }

    public Integer getIsBiz() {
        return isBiz ;
    }

    public void setIsBiz(Integer isBiz) {
        this.isBiz = isBiz ;
    }

    public Integer getIsTransportContactMan() {
        return isTransportContactMan ;
    }

    public void setIsTransportContactMan(Integer isTransportContactMan) {
        this.isTransportContactMan = isTransportContactMan ;
    }

    public Integer getIsGoodsMan() {
        return isGoodsMan ;
    }

    public void setIsGoodsMan(Integer isGoodsMan) {
        this.isGoodsMan = isGoodsMan ;
    }

    public Integer getIsDistributionMan() {
        return isDistributionMan ;
    }

    public void setIsDistributionMan(Integer isDistributionMan) {
        this.isDistributionMan = isDistributionMan ;
    }

    public String getText() {
        return text ;
    }

    public void setText(String text) {
        this.text = text ;
    }

}
