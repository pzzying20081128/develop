/**
 * @(#) IBasevisit.java
 */

package cn.zy.apps.tools.jpa ;

import java.io.Serializable ;

/**
 * 
 * @author pzzying
 * 
 * @param <T>
 */
public interface IBasevisit extends IBaseQuery {

    /**
     * 把entity信息保存到表T
     * 
     * @param entity
     * @throws AccessErrorException
     */
    public <V> void save(V entity) throws AccessErrorException ;

    /**
     * 按entity的修改表T里的信息
     * 
     * @param entity
     * @throws AccessErrorException
     */
    public <V> void update(V entity) throws AccessErrorException ;

    /**
     * 按删除T表里entity信息
     * 
     * @param entity
     * @return
     * @throws AccessErrorException
     */
    public <V> V remove(V entity) throws AccessErrorException ;

    /**
     * 删除序列为key信息
     * 
     * @param key
     * @return
     * @throws AccessErrorException
     */
    public <V> V remove(Serializable key, Class<V> clazz) throws AccessErrorException ;

    /**
     * 获取序列为key、抽象类型为V的classz信息
     * 
     * @param <V>
     * @param key
     * @param clazz
     * @return
     * @throws AccessErrorException
     */
    public <V> V get(Serializable key, Class<V> clazz) throws AccessErrorException ;

    /**
     * 获取序列为key、抽象类型为V的classz信息
     * 
     * @param <V>
     * @param key
     * @param clazz
     * @return
     * @throws AccessErrorException
     */
    public <V> V get(Class<V> clazz, Serializable key) throws AccessErrorException ;

    /**
     * 获取序列为key、抽象类型为V的classz信息
     * 
     * @param <V>
     * @param key
     * @param clazz
     * @return
     * @throws AccessErrorException
     */
    public <V> V load(Serializable key, Class<V> clazz) throws AccessErrorException ;

    /**
     * 
     * @param key
     * @param clazz
     * @return
     * @throws AccessErrorException
     */
    public <V> V getWriteLock(Serializable key, Class<V> clazz) throws AccessErrorException ;

    /**
     * 
     * @param key
     * @param clazz
     * @return
     * @throws AccessErrorException
     */
    public <V> V loadWriteLock(Serializable key, Class<V> clazz) throws AccessErrorException ;

}
