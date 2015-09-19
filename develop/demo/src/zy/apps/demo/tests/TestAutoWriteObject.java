package zy.apps.demo.tests ;

import java.util.ArrayList ;
import java.util.List ;

import org.junit.Test ;

import zy.apps.demo.PropertiesUnits ;
import zy.apps.demo.pojos.User ;
import zy.apps.demo.pojos.UserOrder ;
import zy.apps.demo.units.DemoAutoWritePrpertiesObjectService ;
import zy.apps.demo.units.cache.ICacheFactory ;

public class TestAutoWriteObject {

    private static ICacheFactory cacheFactory = PropertiesUnits.cacheFactory ;
   

//    @Test
    public void autoWriteObject() throws Exception {
        
       
            User user = new User() ;
            user.setId(110) ;
            user.setName("sssss") ;
            cacheFactory.put(cacheFactory.createKey(user.getId().toString(), User.class), user);

  

        DemoAutoWritePrpertiesObjectService service =new DemoAutoWritePrpertiesObjectService();
        
        UserOrder  userOrder=new UserOrder();
        
        userOrder.setUserId(110);
        
        service.setPrpertiesUnits(userOrder);
        
        User user_ =  userOrder.getUser();
        
        
        
        System.out.println("============== >  "+user_) ;
        
        
    }
    
    @Test
    public void autoParentWriteObject() throws Exception {
        
        User user = new User() ;
        
        user.setId(110) ;
        
        user.setName("sssss") ;
        
        cacheFactory.put(cacheFactory.createKey(user.getId().toString(), User.class), user);

      
        
        UserOrder  userOrder=new UserOrder();
        
        userOrder.setId("1120");
        
        userOrder.setUserId(110);
        
        userOrder.setUser(user);
        
        List<UserOrder > userOrders =new ArrayList<UserOrder > ();
        
        userOrders.add(userOrder);
        
        user.setOrders(userOrders);
        
        DemoAutoWritePrpertiesObjectService service =new DemoAutoWritePrpertiesObjectService();
        
        service.setPrpertiesUnits(user);
        
        System.out.println("============== >  "+userOrder.getUser()) ;
        
        
    }
    
    

}
