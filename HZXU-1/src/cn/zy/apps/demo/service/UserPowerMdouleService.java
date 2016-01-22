package cn.zy.apps.demo.service ;

import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.MeunsTree ;
import cn.zy.apps.demo.service.units.UserPowerMdouleUnits ;
import cn.zy.apps.tools.units.powers.IUserPowerMdouleService ;
import cn.zy.apps.tools.units.powers.UserOptPower ;
import cn.zy.apps.tools.units.powers.UserPower ;

@Component("IUserPowerMdouleService")
public class UserPowerMdouleService implements IUserPowerMdouleService {

    @Autowired
    @Qualifier("UserPowerMdouleUnits")
    private UserPowerMdouleUnits userPowerMdouleUnits ;

    @Override
    public List<String> listUserModulePowerBySysUserId(String loginUserId) throws Exception {

        Integer loginUserId_ = Integer.parseInt(loginUserId) ;

        return userPowerMdouleUnits.listUserModulePowerBySysUserId(loginUserId_) ;

    }

    @Override
    public List<UserPower<UserOptPower>> searchUserPower(String moduleId, String loginUserId) throws Exception {

        return userPowerMdouleUnits.searchUserPower(moduleId, loginUserId) ;
    }

    @Override
    public String[][] getInitTreeMeuns() {

        return MeunsTree.initTreeData();
    }

    @Override
    public List<UserPower<UserOptPower>> searchUserPowerBySysUserId(String loginUserId) throws Exception {
        
        return userPowerMdouleUnits.searchUserPower( loginUserId) ;
    }

}
