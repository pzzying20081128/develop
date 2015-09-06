package cn.zy.apps.tools.networks.http ;

import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.networks.http.base.HttpRequestResponseActivity ;
import cn.zy.apps.tools.networks.http.cores.HttpProtocolService ;
import cn.zy.apps.tools.units.ToolsUnits ;

/**
 * 
 * @author you
 * 
 */
public class HttpGetRequestActivity extends HttpRequestResponseActivity {

    public HttpGetRequestActivity(HttpProtocolService httpProtocolService, String url) {
        super(httpProtocolService, url) ;
    }

    @Override
    protected void doActive(PostGetResponse request) throws HttpRequestServiceException {
        Loggerfactory.info(logger, "do active url " + getUrl() + "  request " + request.getCharEncoded()) ;
        HttpProtocolService httpProtocolService = getHttpProtocolService() ;

        String url = getUrl() ;

        String prams = buildParams(request.getRequestParams()) ;
        boolean isc = url.contains("?") ;
        if (isc) {
            if (ToolsUnits.isNOtNulll(prams)) url = url + "&" + prams ;
        } else {
            if (ToolsUnits.isNOtNulll(prams)) url = url + "?" + prams ;
        }
        HttpRequestResult response = httpProtocolService.get(url, request) ;
        request.setResponse(response) ;

    }

}
