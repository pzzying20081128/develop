package zy.apps.demo.service.order;

import java.util.List ;

import cn.zy.apps.tools.jpa.ServiceException ;

import zy.apps.demo.pojos.UserOrder ;

public interface IUserOrderService {
    
    public String name="IUserOrderService";
    
    public String top_name="TopIUserOrderService";
    
    public List<UserOrder> list() throws ServiceException;
    
    public void addUserOrder(Integer userId , UserOrder  userOrder ) throws ServiceException;

}
