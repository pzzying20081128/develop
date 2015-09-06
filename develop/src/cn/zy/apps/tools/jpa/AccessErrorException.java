package cn.zy.apps.tools.jpa ;

/**
 * 
 * @author pzzy2000-2
 * 
 */
public class AccessErrorException extends RuntimeException {

    private static final long serialVersionUID = 6145175405948978835L ;

    public AccessErrorException() {
        super() ;

    }

    public AccessErrorException(String message, Throwable cause) {
        super(message, cause) ;

    }

    public AccessErrorException(String message) {
        super(message) ;

    }

    public AccessErrorException(Throwable cause) {
        super(cause) ;

    }

}
