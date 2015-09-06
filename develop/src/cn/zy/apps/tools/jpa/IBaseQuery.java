/**
 * @(#) IBaseQuery.java
 */

package cn.zy.apps.tools.jpa ;

import java.util.List ;
import java.util.Map ;

public interface IBaseQuery

{

    /**
     * 按原生SQL执行一条SQL语句
     * 
     * @param sql
     * @throws AccessErrorException
     */
    public void executeByNativesSQL(String sql) throws AccessErrorException ;

    public void executeByNativesSQL(String sql, Map<String, Object> values) throws AccessErrorException ;

    /**
     * 按HSQL执行一条HSQL语句
     * 
     * @param HSQL
     * @return
     * @throws AccessErrorException
     */
    public int executeByHSQL(String HSQL) throws AccessErrorException ;

    /**
     * 按HSQL提供的HSQL语句
     * 
     * @param HSQL
     * @param values
     *            所带参数
     * @return
     * @throws AccessErrorException
     */
    public int executeByHSQL(String HSQL, Map<String, Object> values) throws AccessErrorException ;

    /**
     * 按QName语句更新
     * 
     * @param QName
     *            ROM.XML 的name
     * @return
     * @throws AccessErrorException
     */
    public int executeByQName(String QName) throws AccessErrorException ;

    /**
     * 执行按QName、values更新语句
     * 
     * @param QName
     * @param values
     * @return
     * @throws AccessErrorException
     */
    public int executeByQName(String QName, Map<String, Object> values) throws AccessErrorException ;

    /**
     * 按HSQL提供的HSQL语句、
     * 
     * @param <T>
     * @param HSQL
     * @param stateRowsAndSize
     *            stateRows:开始行数 。Size 查询几行
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByHSQL(String HSQL, int... stateRowsAndSize) throws AccessErrorException ;

    /**
     * 按HSQL语句、values、rowsAndSize查找
     * 
     * @param <T>
     * @param HSQL
     * @param values
     *            所带参数
     * @param stateRowsAndSize
     *            stateRows:开始行数 。Size 查询几行
     * @return
     * @throws AccessErrorException
     */

    public <T> List<T> findByHSQL(String HSQL, Map<String, Object> values, int... stateRowsAndSize) throws AccessErrorException ;

    /**
     * 按提供的原生SQL语句、resultType、rowsAndSize查找
     * 
     * @param <T>
     * @param sql
     * @param resultType
     * @param stateRowsAndSize
     *            stateRows:开始行数 。Size 查询几行
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByNativesSQL(String sql, Class<T> resultType, int... stateRowsAndSize) throws AccessErrorException ;

    /**
     * 
     * 
     * @param <T>
     * @param sql
     * @param values
     * @param resultType
     * @param stateRowsAndSize
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByNativesSQL(String sql, Map<String, Object> values, Class<T> resultType, int... rowsAndSize) throws AccessErrorException ;

    /**
     * 按提供的本地SQL语句、resultTypeName、rowsAndSize查找返回ResultType
     * 
     * @param <T>
     * @param sql
     * @param resultTypeName
     * @param rowsAndSize
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByNativesSQL(String sql, String resultTypeName, int... stateRowsAndSize) throws AccessErrorException ;

    /**
     * 按提供的本地SQL语句、values、resultTypeName、rowsAndSize查找返回ResultType
     * 
     * @param <T>
     * @param sql
     * @param values
     * @param resultTypeName
     *            rom.xml 文件中定义的
     * @param rowsAndSize
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByNativesSQL(String sql, Map<String, Object> values, String resultTypeName, int... stateRowsAndSize) throws AccessErrorException ;

    /**
     * 
     * 
     * @param <T>
     * @param QName
     *            rom.xml 文件中定义的
     * @param stateRowsAndSize
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByQName(String QName, int... stateRowsAndSize) throws AccessErrorException ;

    /**
     * 按QName提供的QName语句、values、rowsAndSize查找
     * 
     * @param <T>
     * @param QName
     * @param values
     * @param rowsAndSize
     * @return
     * @throws AccessErrorException
     */
    public <T> List<T> findByQName(String QName, Map<String, Object> values, int... rowsAndSize) throws AccessErrorException ;

    /**
     * 返回一条只有一条独立的SQL
     * 
     * @param <T>
     * @param HSQL
     * @param values
     * @return
     * @throws AccessErrorException
     */

    public <T> T findSinglenessByHSQL(String HSQL) throws AccessErrorException ;

    public <T> T findSinglenessByHSQL(String HSQL, Map<String, Object> values) throws AccessErrorException ;
    
    
    public <T> T findSinglenessByHSQL(String HSQL, Map<String, Object> values,String resultTypeName) throws AccessErrorException ;

    /**
     * 按本地的SQL语句、resultType查找
     * 
     * @param <T>
     * @param sql
     * @param resultType
     * @return
     * @throws AccessErrorException
     */
    public <T> T findSinglenessByNativesSQL(String sql, Class<T> resultType) throws AccessErrorException ;

    /**
     * 按本地SQL语句、values、resultType查找
     * 
     * @param <T>
     * @param sql
     * @param values
     * @param resultType
     * @return
     * @throws AccessErrorException
     */
    public <T> T findSinglenessByNativesSQL(String sql, Map<String, Object> values, Class<T> resultType) throws AccessErrorException ;

    /**
     * 按SQL提供的SQL语句、resultTypeName查找返回一个ResultType
     * 
     * @param <T>
     * @param sql
     * @param resultTypeName
     * @return
     * @throws AccessErrorException
     */
    public <T> T findSinglenessByNativesSQL(String sql, String resultTypeName) throws AccessErrorException ;

    /**
     * 按QName语句、values查找
     * 
     * @param <T>
     * @param QName
     * @param values
     * @return
     * @throws AccessErrorException
     */
    public <T> T findSinglenessByQName(String QName, Map<String, Object> values) throws AccessErrorException ;

    public <T> T findSinglenessByQName(String QName) throws AccessErrorException ;

    /**
     * 按SQL语句、values、resultTypeName查找返回一个ResultType
     * 
     * @param <T>
     * @param sql
     * @param values
     * @param resultTypeName
     * @return
     * @throws AccessErrorException
     */
    public <T> T findSinglenessByNativesSQL(String sql, Map<String, Object> values, String resultTypeName) throws AccessErrorException ;

    public Long selectSumByHSQL(String HQL, Map<String, Object> values) ;

    public Long selectSumByHSQL(String HQL) ;
}
