package cn.zy.apps.tools.web ;

import java.net.URLEncoder ;

import javax.servlet.http.Cookie ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public abstract class LoginAction<V> extends GeneralAction {

    private static final long serialVersionUID = -8510693613956815141L ;

    protected org.apache.log4j.Logger logger = Loggerfactory.instance(LoginAction.class) ;

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private String userName ;

    private String userPasswd ;

    private int isAutologin = 0 ;

    // action 1: 检测是否在线
    private int at ;

    // private String loginStatus;

    /**
     *     验证失败返回null
     * @param userName
     * @param userPasswd
     * @return
     */
    protected abstract SessionUser<V> isValidate(String userName, String userPasswd) ;
    
    
   
    
    /**
     * 进入主页面初始化
     */
    public  abstract void   intoMain() ;
    
    
    public String exit()throws Exception {
        this.removeSession();
        return SUCCESS;
    }

    
    /**
     *  保存的是用户的数据库的ID
     * @return
     * @throws Exception
     */
    public String login() throws Exception {

        Loggerfactory.info(logger, "user login   in   userName : " + userName + " userPasswd   " + userPasswd + "  isAutologin  :  " + isAutologin + "  at       " + at) ;
 
        SessionUser<V>  sessionUser = isValidate(userName, userPasswd) ;

        if (sessionUser == null) {
//            this.msg = "用户名或密码错误" ;
            this.success = false ;
            return login_error ;
        } else {

            this.userlogins(sessionUser.getUserId(),sessionUser) ;
            
            this.putSessionObject(session_key_userinfo_main, sessionUser);

            String name = URLEncoder.encode(userName, "UTF-8") ;

            if (isAutologin == 1) {// 记住登录

                Cookie cookie = new Cookie("userName", name) ;
                cookie.setMaxAge(60 * 60 * 24 * 365) ;
                this.response.addCookie(cookie) ;

                Cookie isAutologinCookie = new Cookie("isAutologin", Integer.toString(isAutologin)) ;
                isAutologinCookie.setMaxAge(60 * 60 * 24 * 365) ;
                this.response.addCookie(isAutologinCookie) ;

                Cookie userPasswdCookie = new Cookie("userPasswd", userPasswd) ;
                userPasswdCookie.setMaxAge(60 * 60 * 24 * 365) ;
                this.response.addCookie(userPasswdCookie) ;

            } else {// 不
                Cookie cookie = new Cookie("userName", name) ;
                cookie.setMaxAge(-1) ;
                this.response.addCookie(cookie) ;

                Cookie userPasswdCookie = new Cookie("userPasswd", null) ;
                cookie.setMaxAge(-1) ;
                this.response.addCookie(userPasswdCookie) ;

                Cookie myloginCookie = new Cookie("isAutologin", "0") ;
                myloginCookie.setMaxAge(60 * 60 * 24 * 365) ;
                this.response.addCookie(myloginCookie) ;
            }

        }

        return SUCCESS ;
    }

    public String mainPage() {
        String loginUserId = getLoginUserId() ;
        Loggerfactory.info(logger, " main  page load  regGUest  " + loginUserId) ;
        intoMain();
        return SUCCESS ;
    }

    public void setUserName(String userName) {
        this.userName = userName ;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd ;
    }

    public String getUserName() {
        return userName ;
    }

    public int getAt() {
        return at ;
    }

    public void setAt(int at) {
        this.at = at ;
    }

    public int getIsAutologin() {
        return isAutologin ;
    }

    public void setIsAutologin(int isAutologin) {
        this.isAutologin = isAutologin ;
    }

    public String getUserPasswd() {
        return userPasswd ;
    }

}
