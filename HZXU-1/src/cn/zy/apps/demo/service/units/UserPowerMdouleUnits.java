package cn.zy.apps.demo.service.units ;

import java.util.ArrayList ;
import java.util.List ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUserPower ;
import cn.zy.apps.demo.service.ABCommonsService ;

@Component("UserPowerMdouleUnits")
public class UserPowerMdouleUnits extends ABCommonsService {

    public List<String> listUserModulePowerBySysUserId(Integer loginUserId) throws Exception {

        String sql = "select demoUserPower    from   DemoUserPower as  demoUserPower   where  demoUserPower.userInfoId = " + loginUserId ;

        List<String> modules = new ArrayList<String>() ;

        List<DemoUserPower> demoUserPowers = baseService.findByHSQL(sql) ;

        for (DemoUserPower demoUserPower : demoUserPowers) {
            modules.add(demoUserPower.getModuleId()) ;
        }

        return modules ;
    }

    public List searchUserPower(String moduleId, String loginUserId) throws Exception {

        String sql = "select demoUserPower    from   DemoUserPower as  demoUserPower  inner join  fetch  " +

        "  demoUserPower.demoUserOptPowers  " +

        "   where  demoUserPower.userInfoId = " + loginUserId + "  and  demoUserPower.moduleId ='" + moduleId + "' " ;

        List<DemoUserPower> userPowers = baseService.findByHSQL(sql) ;

        for (DemoUserPower userPower : userPowers) {
            userPower.setUserOptPowers(userPower.getDemoUserOptPowers()) ;

        }

        return userPowers ;
    }

    public List searchUserPower(String loginUserId) throws Exception {

        String sql = "select DISTINCT demoUserPower    from   DemoUserPower as  demoUserPower  left  join  fetch  " +

        "  demoUserPower.demoUserOptPowers  " +

        "   where  demoUserPower.userInfoId = " + loginUserId ;

        List<DemoUserPower> userPowers = baseService.findByHSQL(sql) ;

        for (DemoUserPower userPower : userPowers) {
            userPower.setUserOptPowers(userPower.getDemoUserOptPowers()) ;

        }

        return userPowers ;
    }

}
