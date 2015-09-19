package zy.apps.demo.web.units ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import zy.apps.demo.pojos.UserOrder ;
import zy.apps.demo.service.order.IUserOrderService ;
import cn.zy.apps.tools.jpa.ServiceException ;

@Component(IUserOrderService.top_name)
public class TopUserOrderService implements IUserOrderService {
    @Autowired
    @Qualifier(IUserOrderService.name)
    private IUserOrderService userOrderService ;

    @Override
    public List<UserOrder> list() throws ServiceException {
           return userOrderService.list() ;
    }

    @Override
    public void addUserOrder(Integer userId, UserOrder userOrder) throws ServiceException {
        userOrderService.addUserOrder(userId, userOrder);
    }

}
