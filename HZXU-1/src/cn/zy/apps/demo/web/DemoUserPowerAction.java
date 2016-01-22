package cn.zy.apps.demo.web ;

import javax.annotation.Resource ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.tools.units.powers.IUserPowerMdouleService ;
import cn.zy.apps.tools.web.SearchUserPowerAction ;
import cn.zy.apps.tools.web.SessionUser ;

@Component("SearchUserPowerAction")
@org.springframework.context.annotation.Scope(SearchUserPowerAction.Scope)
public class DemoUserPowerAction extends SearchUserPowerAction {

    private static final long serialVersionUID = -2713760119222029287L ;

    @Resource(name = "IUserPowerMdouleService")
    private IUserPowerMdouleService userMdoulePowerService ;

    @Override
    protected IUserPowerMdouleService getIUserMdoulePowerService() {

        return userMdoulePowerService ;
    }

    @Override
    protected boolean isAdmin() {
        SessionUser<DemoUser> sessionUser = getSessionUserInfo() ;
        DemoUser systemUserInfo = sessionUser.getUserInfo() ;
        return systemUserInfo.getIsAdmin() == null ? false : (systemUserInfo.getIsAdmin() == 1) ;
    }

}
