package cn.zy.apps.tools.web ;

import java.util.Map ;
import java.util.Random ;

import javax.servlet.http.HttpServletResponse ;

import net.sf.json.JSONException ;
import net.sf.json.JSONObject ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;

import com.opensymphony.xwork2.ActionContext ;
import com.opensymphony.xwork2.ActionInvocation ;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor ;

/**
 * SessionInterceptor 判断用户是否登陆
 * 
 * @author zy
 * 
 */
@SuppressWarnings("serial")
public abstract class JSONSessionInterceptor extends MethodFilterInterceptor  implements IBaseAction{

    protected static Logger logger = Loggerfactory.instance(JSONSessionInterceptor.class) ;

    private static JSONObject jsonObject = new JSONObject() ;

    static {
        try {
            jsonObject.put("success", false) ;
            jsonObject.put("msg", user_no_login_or_timeout) ;
        } catch (JSONException e) {
            Loggerfactory.error(logger, e.getLocalizedMessage(), e) ;
        }

    }

    protected Integer ceateYN() {
        Random ran = new Random() ;
        int num = ran.nextInt(100) ;
        return num ;

    }

//    @SuppressWarnings("unchecked")
    protected String doIntercept(ActionInvocation arg0) throws Exception {
        //定义个session获取arg0的内容并且得到它的Session
        Map<String, Object> session = arg0.getInvocationContext().getSession() ;
        return unLogin(session) ;

        // 定义一个object并得到BaseAction里面的key
        //        Object object = session.get(Constants.USER_KEY) ;
        // System.err.println("============== >  " + "doIntercept           " +
        // object);
        // 判断 如果为空就报错跳到"sessionTimeOut"设置的页面 如果没错 继续执行下去
        // Loggerfactory.error(logger, " : " + arg0.getProxy().getMethod() +
        // "   " + arg0.getProxy().getActionName()+"  object  "+object);

        //        if (object == null) {
        //            // if(arg0.getProxy().getMethod().equals("mainPage")){
        //            // return arg0.invoke();
        //            // }
        //
        //            // action获得关于arg0的Action
        //            // ActionSupport action = (ActionSupport) arg0.getAction();
        //            // action.addActionError("用户超时");
        //
        //            // User user = new User( ceateYN() , "TEST_"+ ceateYN() );
        //            // SystemUser systemUser = new SystemUser();
        //            // systemUser.setId(user.getId());
        //            // systemUser.setAccount(user.getUserName());
        //            // session.put(Constants.USER_KEY, user);
        //            // session.put(Constants.USER_SYSTEM_USER,systemUser);
        //            // return arg0.invoke();
        //            // BaseAction baseAction = (BaseAction) arg0.getAction();
        //
        //            if (!arg0.getProxy().getActionName().equals("main")) {
        //                HttpServletResponse httpServletResponse = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE) ;
        //                httpServletResponse.setCharacterEncoding("UTF-8") ;// 这儿是为了返回中文格式的response。如果都是英文就不用了。
        //                httpServletResponse.getWriter().print(jsonObject) ; // {"name":"fly","type":"虫子"}
        //                return null ;
        //            } else {
        //                return session_time_out ;
        //            }
        //
        //        } else
        //            return arg0.invoke() ;

    }

    protected HttpServletResponse getHttpServletResponse() {
        HttpServletResponse httpServletResponse = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE) ;
        return httpServletResponse ;
    }

    protected String getNologinJson() {
        return jsonObject.toString() ;
    }

    protected abstract String unLogin(Map<String, Object> session) throws Exception ;

}
