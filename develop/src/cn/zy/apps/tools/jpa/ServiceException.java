package cn.zy.apps.tools.jpa ;

public class ServiceException extends AccessErrorException {

    private static final long serialVersionUID = -7794449894704756047L ;

    private String msg ;

    public ServiceException() {

        super() ;

    }

    public ServiceException(String message, Throwable cause) {

        super(message, cause) ;
        this.msg = message ;

    }

    public ServiceException(String message) {

        super(message) ;
        this.msg = message ;

    }

    public ServiceException(Throwable cause) {

        super(cause) ;

    }

    public String getMsg() {
        return msg ;
    }

    public void setMsg(String msg) {
        this.msg = msg ;
    }

}
