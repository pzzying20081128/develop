package cn.zy.apps.tools.web;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.zy.apps.tools.logger.Loggerfactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * SessionInterceptor 判断用户是否登陆
 * 
 * @author zy
 * 
 */
@SuppressWarnings("serial")
public class ActionSessionInterceptor extends MethodFilterInterceptor implements IBaseAction {

	public boolean isSession = false;

	protected static Logger logger = Loggerfactory.instance(ActionSessionInterceptor.class);

	// @SuppressWarnings("unchecked")
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		if (!isSession)
			return arg0.invoke();
		System.out.println("--> isSession " + isSession);
		// 定义个session获取arg0的内容并且得到它的Session
		Map<String, Object> session = arg0.getInvocationContext().getSession();

		Object object = session.get(IBaseAction.session_key_userinfo);

		Loggerfactory.error(logger,
				" : " + arg0.getProxy().getMethod() + "   " + arg0.getProxy().getActionName() + "  object  " + object);

		if (object == null) {

			return user_no_login_or_timeout;
		} else
			return arg0.invoke();

	}

	protected HttpServletResponse getHttpServletResponse() {
		HttpServletResponse httpServletResponse = (HttpServletResponse) ActionContext.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		return httpServletResponse;
	}

	public boolean isSession() {
		return isSession;
	}

	@Inject(value = "struts.apps.session")
	public void setSession(boolean isSession) {
		this.isSession = isSession;
	}

}
