package zy.apps.demo.service.user;

import cn.zy.apps.tools.jpa.ServiceException ;
import zy.apps.demo.pojos.User ;

public interface IUserService {
    
              public String name="IUserService";
              
              public User addUser(User   user )throws ServiceException;

}
