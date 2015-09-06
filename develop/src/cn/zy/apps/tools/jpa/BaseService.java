package cn.zy.apps.tools.jpa ;

import java.math.BigInteger ;
import java.util.Map ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public abstract class BaseService extends Basevisit implements IBaseService {

    private static Logger logger = Loggerfactory.instance(BaseService.class) ;

    public int selCount(String QName, Map<String, Object> values) throws ServiceException {

        Long result = findSinglenessByQName(QName, values) ;

        return result.intValue() ;
    }

    public int selCountHsql(String sql, Map<String, Object> values) throws ServiceException {

        Long result = findSinglenessByHSQL(sql, values) ;

        return result.intValue() ;

    }

    public int selCount_native(String QName, Map<String, Object> values) throws ServiceException {

        try {

            BigInteger result = findSinglenessByQName(QName, values) ;

            return result.intValue() ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
            throw new ServiceException(e) ;
        }

    }

    public int selCount_native_Integer(String QName, Map<String, Object> values) throws ServiceException {

        try {

            Integer result = findSinglenessByQName(QName, values) ;

            return result.intValue() ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
            throw new ServiceException(e) ;
        }

    }

    public int selCount_native_double(String QName, Map<String, Object> values) throws ServiceException {

        try {

            Double result = findSinglenessByQName(QName, values) ;

            return result.intValue() ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
            throw new ServiceException(e) ;
        }

    }

}
