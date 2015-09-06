package cn.zy.apps.tools.logger ;

import org.apache.log4j.Logger ;

public class Loggerfactory {

    private static final boolean print = false ;

    public static <T> Logger instance(Class<T> clazz) {
        return Logger.getLogger(clazz) ;
    }

    public static <T> Logger instance(String xx) {
        return Logger.getLogger(xx) ;
    }

    public static void info(Logger logger, String message) {
        print(message) ;
        if (logger.isInfoEnabled()) logger.info("<=======>" + message) ;
    }

    public static void info(Logger logger, Class<?> clazz, String message) {
        if (logger.isInfoEnabled()) logger.info("clazz : " + clazz.getSimpleName() + " message : " + message) ;
    }

    public static void error(Logger logger, String message, Throwable exception) {

        {
            // if (logger.isInfoEnabled())
            {
                if (exception == null)

                logger.error(" - > " + message) ;
                else {
                    exception.printStackTrace() ;
                    // logger.error("<=======>" + message, exception);
                }
            }
        }

    }

    public static void error(Logger logger, Throwable exception) {

        {
            if (logger.isInfoEnabled()) {
                exception.printStackTrace() ;
            }
        }

    }

    public static void error(Logger logger, String message) {

        error(logger, message, null) ;

    }

    public static void warn(Logger logger, String message) {

        logger.warn(message) ;

    }

    public static void print(Object message) {
        if (print) System.out.println("bug-------------> <--- ------:" + message) ;
    }

}
