package cn.zy.apps.tools.jpa ;

import java.util.Map ;

/**
 * 
 * @author zy
 * 
 * @param <T>
 */
public interface IBaseService extends IBasevisit {

    /**
     * 通过QName 来查询数量
     * 
     * @param QName
     * @param values
     * @return
     * @throws ServiceException
     */
    public int selCount(String QName, Map<String, Object> values) throws ServiceException ;

    /**
     * 通过Hsql 来查询数量
     * 
     * @param sql
     * @param values
     * @return
     * @throws ServiceException
     */
    public int selCountHsql(String sql, Map<String, Object> values) throws ServiceException ;

    /**
     * 通过 QName native sql 来查询数量
     * 
     * @param QName
     * @param values
     * @return
     * @throws ServiceException
     */
    public int selCount_native(String QName, Map<String, Object> values) throws ServiceException ;

    /**
     * 
     * @param QName
     * @param values
     * @return
     * @throws ServiceException
     */
    public int selCount_native_Integer(String QName, Map<String, Object> values) throws ServiceException ;

    /**
     * 
     * @param QName
     * @param values
     * @return
     * @throws ServiceException
     */
    public int selCount_native_double(String QName, Map<String, Object> values) throws ServiceException ;

}
