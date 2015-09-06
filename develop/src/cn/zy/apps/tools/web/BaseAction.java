package cn.zy.apps.tools.web ;

import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import org.apache.log4j.Logger ;
import org.apache.struts2.interceptor.ServletRequestAware ;
import org.apache.struts2.interceptor.ServletResponseAware ;
import org.apache.struts2.interceptor.SessionAware ;

import cn.zy.apps.tools.logger.Loggerfactory ;

import com.opensymphony.xwork2.ActionSupport ;

/**
 * BaseAction类继承ActionSupport包、实现SessionAware类方法 基础Action
 * 
 * pzzy2000 pzzy2000@gmail.com
 * 
 * @author
 * 
 */

//
public abstract class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, IBaseAction {

    protected static final Logger logger = Loggerfactory.instance("request action : ") ;

    protected HttpServletRequest request ;

    protected HttpServletResponse response ;

    protected int ajaxResult = ajax_result_no_success ;

    private String _dc ;

    public void setServletRequest(HttpServletRequest request) {

        this.request = request ;
    }

    public void setServletResponse(HttpServletResponse response) {

        this.response = response ;
    }

    private static final long serialVersionUID = 325925035218044014L ;

    private Map<String, Object> session ;

    /**
     * 设置session
     */
    @SuppressWarnings("unchecked")
    public void setSession(Map arg0) {

        session = arg0 ;

    }

    /**
     * 用户登录
     * 
     * @param userId
     */
    protected <V> void userlogins(String userId, SessionUser<V> sessionUser) {

        Loggerfactory.info(logger, " userlogin  : " + userId) ;

        session.put(IBaseAction.session_key_userinfo, sessionUser) ;

    }

    protected void removeSession() {
        session.clear() ;
    }

    public <V> SessionUser<V> getSessionUserInfo() {
        SessionUser<V> user = (SessionUser<V>) session.get(session_key_userinfo) ;
        return user;
    }

    public String getLoginUserId() {
        SessionUser user = (SessionUser) session.get(session_key_userinfo) ;
        return user.getUserId() ;
    }

    public void userExits(String userId) {

        Loggerfactory.info(logger, " userlogin  : " + userId) ;

        session.remove(session_key_userinfo) ;

    }

    public void putSessionObject(String key, Object element) {
        SessionUser user = (SessionUser) this.session.get(session_key_userinfo) ;
        user.putElement(key, element) ;
    }

    public <T> T gettSessionObject(String key) {
        SessionUser user = (SessionUser) this.session.get(session_key_userinfo) ;
        return (T) user.getElement(key) ;
    }

    public int getAjaxResult() {

        return ajaxResult ;
    }

    public void setAjaxResult(int ajaxResult) {

        this.ajaxResult = ajaxResult ;
    }

    public void set_dc(String _dc) {
        this._dc = _dc ;
    }

}
