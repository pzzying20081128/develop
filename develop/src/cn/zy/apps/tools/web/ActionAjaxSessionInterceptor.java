package cn.zy.apps.tools.web ;

import java.util.Map ;

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
public  class ActionAjaxSessionInterceptor extends MethodFilterInterceptor implements IBaseAction{

    protected static Logger logger = Loggerfactory.instance(ActionAjaxSessionInterceptor.class) ;
    
    private static JSONObject jsonObject = new JSONObject();

    static {
        try {
            jsonObject.put("success", false);
            jsonObject.put("msg", user_no_login_or_timeout);
        } catch (JSONException e) {
            Loggerfactory.error(logger, e.getLocalizedMessage(), e);
        }

    }

    //    @SuppressWarnings("unchecked")
    protected String doIntercept(ActionInvocation arg0) throws Exception {
        //定义个session获取arg0的内容并且得到它的Session
        Map<String, Object> session = arg0.getInvocationContext().getSession() ;

        Object object = session.get(IBaseAction.session_key_userinfo) ;
    
        Loggerfactory.error(logger, " : " + arg0.getProxy().getMethod() + "   " + arg0.getProxy().getActionName() + "  object  " + object) ;

        if (object == null) {
           
            HttpServletResponse httpServletResponse = (HttpServletResponse) ActionContext.getContext().get(
                    org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
            httpServletResponse.setCharacterEncoding("UTF-8");// 这儿是为了返回中文格式的response。如果都是英文就不用了。
            httpServletResponse.getWriter().print(jsonObject); // {"name":"fly","type":"虫子"}
            return null;
        } else
            return arg0.invoke() ;

    }

    protected HttpServletResponse getHttpServletResponse() {
        HttpServletResponse httpServletResponse = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE) ;
        return httpServletResponse ;
    }

}
