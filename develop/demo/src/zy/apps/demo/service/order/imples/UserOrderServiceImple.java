package zy.apps.demo.service.order.imples ;

import java.util.List ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.tools.jpa.ServiceException ;
import cn.zy.apps.tools.units.SQLUilts ;
import zy.apps.demo.pojos.User ;
import zy.apps.demo.pojos.UserOrder ;
import zy.apps.demo.service.DemoEntityManagerService ;
import zy.apps.demo.service.order.IUserOrderService ;

@Component(IUserOrderService.name)
public class UserOrderServiceImple extends DemoEntityManagerService implements IUserOrderService {

    @Override
    public List<UserOrder> list() throws ServiceException {

        String sql = "select  userOrder from  UserOrder as  userOrder  " ;

        return findByHSQL(sql) ;
    }

    @Override
    public void addUserOrder(Integer userId, UserOrder userOrder) throws ServiceException {

        User user = load(userId, User.class) ;

        userOrder.setId(SQLUilts.getUniqueId()) ;

        userOrder.setUser(user) ;

        save(userOrder) ;

    }

}
