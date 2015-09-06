package cn.zy.apps.tools.jpa ;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -7794449894704756047L ;

    public ServiceException() {

        super() ;

    }

    public ServiceException(String message, Throwable cause) {

        super(message, cause) ;

    }

    public ServiceException(String message) {

        super(message) ;

    }

    public ServiceException(Throwable cause) {

        super(cause) ;

    }

}
