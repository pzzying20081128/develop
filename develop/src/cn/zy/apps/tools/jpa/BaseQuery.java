package cn.zy.apps.tools.jpa ;

import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;
import java.util.Map.Entry ;

import javax.persistence.EntityManager ;
import javax.persistence.NoResultException ;
import javax.persistence.Query ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;

/**
 * 
 * 
 * 
 * @author
 * 
 */
public abstract class BaseQuery extends BEntrtyManager implements IBaseQuery {

    private Logger logger = Loggerfactory.instance(BaseQuery.class) ;

    /**
     * 按参数为values、范围rowsAndSize进行查询，并返回结果
     * 
     * @param <T>
     * @param query
     * @param values
     * @param rowsAndSize
     * @return
     * @throws AccessErrorException
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> excQuery(Query query, Map<String, Object> values, int... rowsAndSize) throws AccessErrorException {
        if (values != null) {
            for (Iterator<Entry<String, Object>> entrys = values.entrySet().iterator(); entrys.hasNext();) {
                Entry<String, Object> entry = entrys.next() ;
                Object value = entry.getValue() ;
                query.setParameter(entry.getKey(), value) ;
            }
        }
        if (rowsAndSize != null) {
            int leght = rowsAndSize.length ;
            if (leght > 0) {
                if (leght == 1) {
                    int first = rowsAndSize[0] ;
                    if (first < 0) throw new AccessErrorException(" find sql First Result  < 0 ") ;
                    query.setFirstResult(rowsAndSize[0]) ;
                } else if (leght == 2) {
                    int first = rowsAndSize[0] ;
                    int max = rowsAndSize[1] ;
                    if (first < 0 || max < 0) throw new AccessErrorException(" find sql First Result  < 0  or max <0") ;
                    query.setFirstResult(first) ;
                    query.setMaxResults(max) ;
                } else if (leght > 2) throw new AccessErrorException(" find sql First  leght ==2  ;first is  start ; ") ;
            }
        }
        return query.getResultList() ;
    }

    /**
     * 对参数values 进行更新
     * 
     * @param query
     * @param values
     * @return
     */
    private int exceteNoReturnQuery(Query query, Map<String, Object> values) {
        if (values != null) {
            for (Iterator<Entry<String, Object>> entrys = values.entrySet().iterator(); entrys.hasNext();) {
                Entry<String, Object> entry = entrys.next() ;
                Object value = entry.getValue() ;
                query.setParameter(entry.getKey(), value) ;
            }
        }

        return query.executeUpdate() ;
    }

    /**
     * 按values查询并返回一个结果
     * 
     * @param query
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T exceteSinglenessQuery(Query query, Map<String, Object> values) {
        try {
            if (values != null) {
                for (Iterator<Entry<String, Object>> entrys = values.entrySet().iterator(); entrys.hasNext();) {
                    Entry<String, Object> entry = entrys.next() ;
                    Object value = entry.getValue() ;
                    query.setParameter(entry.getKey(), value) ;
                }
            }

            return (T) query.getSingleResult() ;
        } catch (NoResultException e) {
            Loggerfactory.error(logger, e.getMessage()) ;
            return null ;
        }
    }

    /**
     * 执行本地SQL
     * 
     */
    public void executeByNativesSQL(String sql) throws AccessErrorException {
        EntityManager entityManager = this.getEntityManager() ;

        Query query = entityManager.createNativeQuery(sql) ;

        exceteNoReturnQuery(query, null) ;

    }

    @Override
    public void executeByNativesSQL(String sql, Map<String, Object> values) throws AccessErrorException {
        EntityManager entityManager = this.getEntityManager() ;

        Query query = entityManager.createNativeQuery(sql) ;

        exceteNoReturnQuery(query, values) ;

    }

    /**
     * 创建一个查询，执行HSQL语句
     * 
     * @param HSQL
     * @return
     */
    private Query hsqlQuery(String HSQL) {
        EntityManager entityManager = getEntityManager() ;
        return entityManager.createQuery(HSQL) ;
    }

    /**
     * 创建一个查询，执行QueryName语句
     * 
     * @param QueryName
     * @return
     */
    private Query qNameQuery(String QueryName) {
        EntityManager entityManager = getEntityManager() ;
        return entityManager.createNamedQuery(QueryName) ;
    }

    /**
     * 创建一个本地查询执行sql语句和类型为T的clazz
     * 
     * @param <T>
     * @param sql
     * @param clazz
     * @return
     */
    private <T> Query nativesQueryOfClass(final String sql, final Class<T> clazz) {
        EntityManager entityManager = getEntityManager() ;
        return entityManager.createNativeQuery(sql, clazz) ;
    }

    /**
     * 创建一个本地查询，执行对类型名ReultTypeName的sql语句
     * 
     * @param sql
     * @param ReultTypeName
     * @return
     */
    private Query nativesQueryOfReultTypeName(String sql, String ReultTypeName) {
        EntityManager entityManager = getEntityManager() ;
        return entityManager.createNativeQuery(sql, ReultTypeName) ;
    }

    /**
     * 执行HSQL后更新
     * 
     */
    public int executeByHSQL(String HSQL) throws AccessErrorException {

        return executeByHSQL(HSQL, null) ;
    }

    /**
     * 执行HSQL后，更新values
     * 
     */
    public int executeByHSQL(String HSQL, Map<String, Object> values) throws AccessErrorException {
        Query query = hsqlQuery(HSQL) ;
        return exceteNoReturnQuery(query, values) ;
    }

    /**
     * 执行QName后更新
     */
    public int executeByQName(String QName) throws AccessErrorException {

        return executeByQName(QName, null) ;
    }

    /**
     * 执行QName语句，更新values
     */
    public int executeByQName(String QName, Map<String, Object> values) throws AccessErrorException {
        Query query = qNameQuery(QName) ;
        return exceteNoReturnQuery(query, values) ;
    }

    /**
     * 
     * 执行HSQL语句 对范围为rowsAndSize进行查询
     */
    public <T> List<T> findByHSQL(String HSQL, int... rowsAndSize) throws AccessErrorException {

        Query query = hsqlQuery(HSQL) ;
        return excQuery(query, null, rowsAndSize) ;
    }

    /**
     * 
     * 执行HSQL语句对参数为values范围为rowsAndSize进行查询
     */
    public <T> List<T> findByHSQL(String HSQL, Map<String, Object> values, int... rowsAndSize) throws AccessErrorException {
        Query query = hsqlQuery(HSQL) ;
        return excQuery(query, values, rowsAndSize) ;
    }

    /**
     * 执行本地sql语句，对类型为resultType、范围为rowsAndSize查询
     */
    public <T> List<T> findByNativesSQL(String sql, Class<T> resultType, int... rowsAndSize) throws AccessErrorException {

        return findByNativesSQL(sql, null, resultType, rowsAndSize) ;
    }

    /**
     * 执行本地sql语句，对参数为values、类型为resultType、范围为rowsAndSize查询
     */
    public <T> List<T> findByNativesSQL(String sql, Map<String, Object> values, Class<T> resultType, int... rowsAndSize) throws AccessErrorException {
        Query query = nativesQueryOfClass(sql, resultType) ;
        return excQuery(query, values, rowsAndSize) ;
    }

    /**
     * 按resultTypeName执行sql语句，对resultTypeName、rowsAndSize进行查询
     * 
     */
    public <T> List<T> findByNativesSQL(String sql, String resultTypeName, int... rowsAndSize) throws AccessErrorException {

        return findByNativesSQL(sql, null, resultTypeName, rowsAndSize) ;
    }

    /**
     * 按resultTypeName执行sql语句，对values、resultTypeName、rowsAndSize进行查询
     * 
     */
    public <T> List<T> findByNativesSQL(String sql, Map<String, Object> values, String resultTypeName, int... rowsAndSize) throws AccessErrorException {
        Query query = nativesQueryOfReultTypeName(sql, resultTypeName) ;
        return excQuery(query, values, rowsAndSize) ;
    }

    /**
     * 执行QName语句，对范围为rowsAndSize查询
     */
    public <T> List<T> findByQName(String QName, int... rowsAndSize) throws AccessErrorException {

        Query query = qNameQuery(QName) ;
        return excQuery(query, null, rowsAndSize) ;
    }

    /**
     * 执行QName语句，对参数为values、范围为rowsAndSize查询
     */
    public <T> List<T> findByQName(String QName, Map<String, Object> values, int... rowsAndSize) throws AccessErrorException {
        Query query = qNameQuery(QName) ;
        return excQuery(query, values, rowsAndSize) ;
    }

    public <T> T findSinglenessByHSQL(String HSQL) throws AccessErrorException {

        Query query = hsqlQuery(HSQL) ;
        return exceteSinglenessQuery(query, null) ;
    }

    /**
     * 执行HSQL语句，查询values并返回一个结果
     */
    public <T> T findSinglenessByHSQL(String HSQL, Map<String, Object> values) throws AccessErrorException {

        Query query = hsqlQuery(HSQL) ;
        return exceteSinglenessQuery(query, values) ;
    }

    public <T> T findSinglenessByHSQL(String HSQL, Map<String, Object> values, String resultTypeName) throws AccessErrorException {
        Query query = nativesQueryOfReultTypeName(HSQL, resultTypeName) ;
        return exceteSinglenessQuery(query, values) ;
    }

    /**
     * 执行类型为resultType的本地SQL语句
     */
    public <T> T findSinglenessByNativesSQL(String sql, Class<T> resultType) throws AccessErrorException {
        return findSinglenessByNativesSQL(sql, null, resultType) ;
    }

    /**
     * 执行类型为resultType的本地SQL语句，对values进行查询并返回一个结果
     * 
     */
    public <T> T findSinglenessByNativesSQL(String sql, Map<String, Object> values, Class<T> resultType) throws AccessErrorException {

        Query query = nativesQueryOfClass(sql, resultType) ;
        return exceteSinglenessQuery(query, values) ;
    }

    /**
     * 执行resultTypeName的本地SQL语句，并返回ResultType
     */
    public <T> T findSinglenessByNativesSQL(String sql, String resultTypeName) throws AccessErrorException {
        Query query = nativesQueryOfReultTypeName(sql, resultTypeName) ;
        return exceteSinglenessQuery(query, null) ;
    }

    /**
     * 执行resultTypeName的本地SQL语句，对values进行查询并返回ResultType
     */
    public <T> T findSinglenessByNativesSQL(String sql, Map<String, Object> values, String resultTypeName) throws AccessErrorException {
        Query query = nativesQueryOfReultTypeName(sql, resultTypeName) ;
        return exceteSinglenessQuery(query, values) ;
    }

    /**
     * 执行QName语句对values进行查询
     */
    public <T> T findSinglenessByQName(String QName, Map<String, Object> values) throws AccessErrorException {
        Query query = qNameQuery(QName) ;
        return exceteSinglenessQuery(query, values) ;

    }

    public <T> T findSinglenessByQName(String QName) throws AccessErrorException {
        Query query = qNameQuery(QName) ;
        return exceteSinglenessQuery(query, null) ;
    }

    @Override
    public Long selectSumByHSQL(String HQL, Map<String, Object> values) {
        return findSinglenessByHSQL(HQL, values) ;
    }

    @Override
    public Long selectSumByHSQL(String HQL) {
        return findSinglenessByHSQL(HQL, null) ;
    }

}
