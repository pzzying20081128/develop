package cn.zy.apps.tools.networks.http ;

import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.networks.http.base.HttpRequestResponseActivity ;
import cn.zy.apps.tools.networks.http.cores.HttpProtocolService ;

/**
 * 请求Post应答
 * 
 * @author you
 * 
 */
public class HttpPostRequestActivity extends HttpRequestResponseActivity {

    public HttpPostRequestActivity(HttpProtocolService httpProtocolService, String url) {
        super(httpProtocolService, url) ;

    }

    @Override
    protected void doActive(PostGetResponse request) throws HttpRequestServiceException {
        Loggerfactory.info(logger, "do active url " + getUrl()) ;
        HttpProtocolService httpProtocolService = getHttpProtocolService() ;
        HttpRequestResult response = httpProtocolService.post(getUrl(), request) ;
        request.setResponse(response) ;

    }

}
