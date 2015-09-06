package cn.zy.apps.tools.networks.http.base ;

import java.util.Map ;
import java.util.Map.Entry ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public class HttpRequestActivity {

    protected Logger logger = Loggerfactory.instance("HttpRequestActivity") ;

    protected String url ;

    public HttpRequestActivity(String url) {
        super() ;
        this.url = url ;
    }

    public String getUrl() {
        return url ;
    }

    protected String buildParams(Map<String, String> params) {
        if (params == null || params.size() == 0) return null ;
        StringBuffer buffer = new StringBuffer() ;
        boolean isf = false ;
        for (Entry<String, String> entry : params.entrySet()) {
            if (!isf) {
                buffer.append(entry.getKey() + "=" + entry.getValue()) ;
                isf = true ;
            } else {
                buffer.append("&" + entry.getKey() + "=" + entry.getValue()) ;
            }
        }
        return buffer.toString() ;
    }

}
