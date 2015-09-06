package cn.zy.apps.tools.weixin.http ;

import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.networks.http.HttpGetRequestActivity ;
import cn.zy.apps.tools.networks.http.HttpPostRequestActivity ;
import cn.zy.apps.tools.networks.http.HttpRequestServiceException ;
import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.networks.http.base.IHttpRequestActivity ;
import cn.zy.apps.tools.networks.http.cores.HttpProtocolService ;

public abstract class WeiXinHttpReqService<V> implements IWeiXinHttpReqService<V> {

    private HttpProtocolService httpProtocolService = new HttpProtocolService() ;

    private ReqType reqType ;

    public WeiXinHttpReqService(cn.zy.apps.tools.weixin.http.IWeiXinHttpReqService.ReqType reqType) {
        super() ;
        this.reqType = reqType ;
    }

    @Override
    public V req(V reqInfo) throws WeiXinHttpException {

        switch (reqType) {
        case Get: {
            IHttpRequestActivity httpRequestActivity = new HttpGetRequestActivity(httpProtocolService, getReqUrl(reqInfo)) ;
            PostGetResponse postGetResponse = switchToReqPostGetResponse(reqInfo) ;
            try {
                httpRequestActivity.doActive(postGetResponse) ;
                Loggerfactory.info(logger, "req  result : " + postGetResponse.getResponse().getResponse()) ;
                V reqInfo_ = switchResponse(reqInfo, postGetResponse) ;
                return reqInfo_ ;
            } catch (HttpRequestServiceException e) {
                throw new WeiXinHttpException(e) ;
            }
        }

        case Post: {
            IHttpRequestActivity httpRequestActivity = new HttpPostRequestActivity(httpProtocolService, getReqUrl(reqInfo)) ;
            PostGetResponse postGetResponse = switchToReqPostGetResponse(reqInfo) ;
            try {
                httpRequestActivity.doActive(postGetResponse) ;
                Loggerfactory.info(logger, "req  result : " + postGetResponse.getResponse().getResponse()) ;
                V reqInfo_ = switchResponse(reqInfo, postGetResponse) ;
                return reqInfo_ ;
            } catch (HttpRequestServiceException e) {
                throw new WeiXinHttpException(e) ;
            }
        }
        case PostString: {
            IHttpRequestActivity httpRequestActivity = new HttpPostRequestActivity(httpProtocolService, getReqUrl(reqInfo)) ;
            PostGetResponse postGetResponse = switchToReqPostGetResponse(reqInfo) ;
            try {
                httpRequestActivity.doActive(postGetResponse) ;
                Loggerfactory.info(logger, "req  result : " + postGetResponse.getResponse().getResponse()) ;
                V reqInfo_ = switchResponse(reqInfo, postGetResponse) ;
                return reqInfo_ ;
            } catch (HttpRequestServiceException e) {
                throw new WeiXinHttpException(e) ;
            }
        }
        default:
            throw new RuntimeException("this  opt no support ! ") ;
        }

    }

    protected abstract V switchResponse(V reqInfo, PostGetResponse postGetResponse) ;

    protected abstract String getReqUrl(V reqInfo) ;

    protected abstract PostGetResponse switchToReqPostGetResponse(V reqInfo) ;

}
