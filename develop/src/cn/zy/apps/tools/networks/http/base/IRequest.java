package cn.zy.apps.tools.networks.http.base ;

import java.util.HashMap ;
import java.util.Map ;

import org.apache.http.cookie.Cookie ;

import cn.zy.apps.tools.networks.http.HttpRequestResult ;
import cn.zy.apps.tools.networks.http.cores.IHttpProtocolService.CharEncoded ;

/**
 * 请求
 * 
 * @author you
 * 
 */
public abstract class IRequest {

    // 返回结果
    private HttpRequestResult response ;

    private CharEncoded charEncoded ;

    /**
     * 请求的Cookie
     */
    private Map<String, Cookie> reqCookie = new HashMap<String, Cookie>() ;

    public Map<String, String> header = new HashMap<String, String>() ;

    private Map<String, String> requestParams = new HashMap<String, String>() ;

    /**
     * post 单一内容
     */
    private String postSingleContent ;

    public void addRequestParams(String key, String value) {
        requestParams.put(key, value) ;
    }

    public IRequest(CharEncoded charEncoded) {
        this.charEncoded = charEncoded ;

    }

    public HttpRequestResult getResponse() {
        return response ;
    }

    public void setResponse(HttpRequestResult response) {
        this.response = response ;
    }

    public CharEncoded getCharEncoded() {
        return charEncoded ;
    }

    public void setCharEncoded(CharEncoded charEncoded) {
        this.charEncoded = charEncoded ;
    }

    public Map<String, Cookie> getReqCookie() {
        return reqCookie ;
    }

    public void setReqCookie(Map<String, Cookie> reqCookie) {
        this.reqCookie = reqCookie ;
    }

    public Map<String, String> getHeader() {
        return header ;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header ;
    }

    public Map<String, String> getRequestParams() {
        return requestParams ;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams ;
    }

    public String getPostSingleContent() {
        return postSingleContent ;
    }

    public void setPostSingleContent(String postSingleContent) {
        this.postSingleContent = postSingleContent ;
    }

}
