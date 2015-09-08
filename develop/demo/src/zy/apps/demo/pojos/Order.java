package zy.apps.demo.pojos;

import java.util.List;

public class Order {
	
	private Long money;
	
	private String moneyMoneyShow;
	
	private String name;
	
	private String sax;
	
	private String nameSaxNameSaxShow;
	
	private List<Order>  orders;

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getMoneyMoneyShow() {
		return moneyMoneyShow;
	}

	public void setMoneyMoneyShow(String moneyMoneyShow) {
		this.moneyMoneyShow = moneyMoneyShow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSax() {
		return sax;
	}

	public void setSax(String sax) {
		this.sax = sax;
	}

	public String getNameSaxNameSaxShow() {
		return nameSaxNameSaxShow;
	}

	public void setNameSaxNameSaxShow(String nameSaxNameSaxShow) {
		this.nameSaxNameSaxShow = nameSaxNameSaxShow;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
