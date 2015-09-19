package zy.apps.demo.service.user.imples ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.tools.jpa.ServiceException ;

import zy.apps.demo.pojos.User ;
import zy.apps.demo.service.DemoEntityManagerService ;
import zy.apps.demo.service.user.IUserService ;

@Component(IUserService.name)
public class UserServiceImples extends DemoEntityManagerService implements IUserService {

    @Override
    public User addUser(User user) throws ServiceException {
        save(user) ;
        return user ;
    }

}
