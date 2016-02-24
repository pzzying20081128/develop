package cn.zy.apps.demo.service.units ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.pojos.DemoUserPower ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;

@Component("DemoUserSaveUpdateUnits")
public class DemoUserSaveUpdateUnits extends ABCommonsService {

    public DemoUser saveUpdate(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException {

        switch (optType) {
        case save:
            return save(optDemoUser) ;

        case update:
            return update(optDemoUser) ;

        default:
            throw new SystemOptServiceException("[操作类型错误]") ;
        }
    }

    public DemoUser save(DemoUser optDemoUser) throws SystemOptServiceException {
        String sql = "select   count(demoUser.id)     from  DemoUser   as  demoUser   where  demoUser.userXMing ='" + optDemoUser.getUserXMing() + "'  " ;
        Long l = baseService.findSinglenessByHSQL(sql) ;
        if (l > 0) {
            throw new SystemOptServiceException("用户姓名[" + optDemoUser.getUserXMing() + "]已经存在") ;
        }

        String sql1 = "select   count(demoUser.id)     from  DemoUser   as  demoUser   where  demoUser.userName ='" + optDemoUser.getUserName() + "'  " ;
        Long l1 = baseService.findSinglenessByHSQL(sql1) ;
        if (l1 > 0) {
            throw new SystemOptServiceException("登录名[" + optDemoUser.getUserXMing() + "]已经存在") ;
        }

        optDemoUser.setIsAdmin(0) ;
        this.baseService.save(optDemoUser) ;
        for (DemoUserPower demoUserPower : optDemoUser.getSystemUserPowers()) {
            demoUserPower.setDemoUserId(optDemoUser.getId()) ;
            demoUserPower.setDemoUser(optDemoUser) ;
            this.baseService.save(demoUserPower) ;

        }

        return optDemoUser ;
    }

    public DemoUser update(DemoUser optDemoUser) throws SystemOptServiceException {
        String sql = "select   count(demoUser.id)     from  DemoUser   as  demoUser   where  demoUser.userXMing ='" + optDemoUser.getUserXMing() + "'  "

        + "  and   demoUser.id != " + optDemoUser.getId() ;
        
        Long l = baseService.findSinglenessByHSQL(sql) ;
        if (l > 0) {
            throw new SystemOptServiceException("用户姓名[" + optDemoUser.getUserXMing() + "]已经存在") ;
        }

        String sql1 = "select   count(demoUser.id)     from  DemoUser   as  demoUser   where  demoUser.userName ='" + optDemoUser.getUserName() + "'  " +

        "  and   demoUser.id != " + optDemoUser.getId() ;
        
        Long l1 = baseService.findSinglenessByHSQL(sql1) ;
        if (l1 > 0) {
            throw new SystemOptServiceException("登录名[" + optDemoUser.getUserXMing() + "]已经存在") ;
        }

        DemoUser demoUser = baseService.get(DemoUser.class, optDemoUser.getId()) ;
        ToolsUnits.copyBeanProperties(demoUser, optDemoUser, "userName", "userXMing") ;

        if (ToolsUnits.isNOtNulll(optDemoUser.getPassword())) {
            demoUser.setPassword(optDemoUser.getPassword()) ;
        }
        
        
        String delete_demo_user_opt_power ="delete  demo_user_opt_power  from       demo_user_opt_power  as  demo_user_opt_power  inner join   " +
        		
                "  demo_user_power as demo_user_power  " +
                
                "  on  ( demo_user_power.uuid =  demo_user_opt_power.user_power_id )  " +
                
                "  where   demo_user_power.user_info_id = "+demoUser.getId();
        
        
        baseService.executeByNativesSQL(delete_demo_user_opt_power);
        
        String  delete_demo_user_power  = "delete demo_user_power   from  demo_user_power as demo_user_power   where    demo_user_power.user_info_id = "+demoUser.getId();
        
        baseService.executeByNativesSQL(delete_demo_user_power);

        for (DemoUserPower demoUserPower : optDemoUser.getSystemUserPowers()) {
            demoUserPower.setDemoUserId(demoUser.getId()) ;
            demoUserPower.setDemoUser(demoUser) ;
            this.baseService.save(demoUserPower) ;
        }

        return demoUser ;
    }

}
