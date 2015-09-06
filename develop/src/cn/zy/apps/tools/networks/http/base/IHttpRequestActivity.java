package cn.zy.apps.tools.networks.http.base ;

import cn.zy.apps.tools.networks.http.HttpRequestServiceException ;

/**
 * HTTP 请求服务
 * 
 * @author you
 * 
 */
public interface IHttpRequestActivity {

    public void doActive(IRequest request) throws HttpRequestServiceException ;

}
