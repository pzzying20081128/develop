package cn.zy.apps.tools.networks.http.base ;

import cn.zy.apps.tools.networks.http.HttpRequestServiceException ;
import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.networks.http.cores.HttpProtocolService ;

public abstract class HttpRequestResponseActivity extends HttpRequestActivity implements IHttpRequestActivity {

    protected HttpProtocolService httpProtocolService ;

    public HttpRequestResponseActivity(HttpProtocolService httpProtocolService, String url) {
        super(url) ;
        this.httpProtocolService = httpProtocolService ;
    }

    @Override
    public void doActive(IRequest request) throws HttpRequestServiceException {
        if (request instanceof PostGetResponse) {
            PostGetResponse requestResponse = (PostGetResponse) request ;
            doActive(requestResponse) ;
        } else {
            throw new HttpRequestServiceException(" " + request.getClass() + " error ! ") ;
        }

    }

    protected abstract void doActive(PostGetResponse request) throws HttpRequestServiceException ;

    protected HttpProtocolService getHttpProtocolService() {
        return httpProtocolService ;
    }

}
