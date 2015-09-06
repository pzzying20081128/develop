package cn.zy.apps.tools.networks.http ;

import java.util.HashSet ;
import java.util.Set ;

import org.apache.http.Header ;
import org.apache.http.cookie.Cookie ;

/**
 * http 请求结果
 * 
 * @author you
 * 
 */
public class HttpRequestResult {

    private Set<Cookie> cookie = new HashSet<Cookie>() ;

    private Header[] headers ;

    private String response ;

    private byte[] responseBtye ;

    public String getResponse() {
        return response ;
    }

    public void setResponse(String response) {
        this.response = response ;
    }

    public byte[] getResponseBtye() {
        return responseBtye ;
    }

    public void setResponseBtye(byte[] responseBtye) {
        this.responseBtye = responseBtye ;
    }

    public Header[] getHeaders() {
        return headers ;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers ;
    }

    public Set<Cookie> getCookie() {
        return cookie ;
    }

    public void setCookie(Set<Cookie> cookie) {
        this.cookie = cookie ;
    }

}
