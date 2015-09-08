package zy.apps.demo.units;

import cn.zy.apps.tools.units.PrpertiesSetValueService;

public class DemoPrpertiesSetValueService extends PrpertiesSetValueService {

	public DemoPrpertiesSetValueService(String regexPackage) {
		super(regexPackage);
	}

	@Override
	protected void handField(String fieldName, Object result) {
		if (fieldName.endsWith("MoneyShow")) {
			handMoney(fieldName, result);
		}
	}

	private void handMoney(String fieldName, Object result) {
		String money_field = fieldName.substring(0, fieldName.length() - 9);
		Long money = readFieldValue(money_field, result);
		String show = "我要显示 : " + money;
		writeFieldValue(fieldName, result, show);
	}
	
	
	public static void main(String[] args) {
		
		String regexPackage= "^zy.apps.demo(\\.\\D+)*(.pojos|.views|.beans)$";
		
//		PrpertiesSetValueService  prpertiesSetValueService  =new DemoPrpertiesSetValueService(regexPackage);
//		Order  order=new Order();
//		order.setMoney(1200L);
//		List<Order>   orders=new ArrayList<Order>();
//		order.setOrders(orders);
//		{
//			Order  order1=new Order();
//			order1.setMoney(200L);
//			orders.add(order1);
//		}
//		
//		{
//			Order  order2=new Order();
//			order2.setMoney(100L);
//			orders.add(order2);
//		}
//		
//		try {
//			prpertiesSetValueService.execactionaftereturn(order);
//			String xx =order.getMoneyMoneyShow();
//			System.out.println("==> "+xx);
//			for(Order  order_:order.getOrders() ){
//				String xx1 =order_.getMoneyMoneyShow();
//				System.out.println("==> "+xx1);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
