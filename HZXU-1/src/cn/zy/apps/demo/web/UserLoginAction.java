package cn.zy.apps.demo.web ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.service.IDemoUserService ;
import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.web.LoginAction ;
import cn.zy.apps.tools.web.SessionUser ;

@Component("UserLoginAction")
@org.springframework.context.annotation.Scope(LoginAction.Scope)
public class UserLoginAction extends LoginAction<DemoUser> {

    private static final long serialVersionUID = 8564146055362445474L ;

    @Autowired
    @Qualifier(IDemoUserService.name)
    private IDemoUserService demoUserService ;

    private DemoUser demoUser ;

    @Override
    protected SessionUser<DemoUser> isValidate(String userName, String userPasswd) {

        {
            DemoUser demoUser = demoUserService.searchByName(userName) ;
            Loggerfactory.info(logger, " user login  userName :  " + userName + "   DemoUser :  " + demoUser) ;
            if (demoUser == null) {
                this.msg = "用户名或密码错误" ;
                return null ;
            } else {
//                demoUser.setIsAdmin(1);
                SessionUser<DemoUser> sessionUser = new SessionUser<DemoUser>() ;
                sessionUser.setLoginUserName(userName) ;
                sessionUser.setUserId(demoUser.getId().toString()) ;
                sessionUser.setUserInfo(demoUser) ;
                return sessionUser ;
            }

        }
    }

    @Override
    public void intoMain() {
        String loginUserId = getLoginUserId() ;
        Integer loginUserId_ = Integer.parseInt(loginUserId) ;
        demoUser = demoUserService.get(loginUserId_) ;

    }

    public DemoUser getDemoUser() {
        return demoUser ;
    }

    public void setDemoUser(DemoUser demoUser) {
        this.demoUser = demoUser ;
    }

}
