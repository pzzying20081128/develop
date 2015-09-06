package cn.zy.apps.tools.jpa ;

public class AccessLockException extends AccessErrorException {

    /**
     * 
     */
    private static final long serialVersionUID = -8720232646823542150L ;

    public AccessLockException() {
        super() ;

    }

    public AccessLockException(String message, Throwable cause) {
        super(message, cause) ;

    }

    public AccessLockException(String message) {
        super(message) ;

    }

    public AccessLockException(Throwable cause) {
        super(cause) ;

    }

}
