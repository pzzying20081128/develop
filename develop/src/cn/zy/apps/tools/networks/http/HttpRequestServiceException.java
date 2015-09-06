package cn.zy.apps.tools.networks.http ;

import cn.zy.apps.tools.networks.NetworkServiceException ;

public class HttpRequestServiceException extends NetworkServiceException {

    private static final long serialVersionUID = -5750116128452390118L ;

    public HttpRequestServiceException() {

    }

    public HttpRequestServiceException(String message) {
        super(message) ;

    }

    public HttpRequestServiceException(Throwable cause) {
        super(cause) ;

    }

    public HttpRequestServiceException(String message, Throwable cause) {
        super(message, cause) ;

    }

}
