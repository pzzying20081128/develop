/**
 * @(#) Basevisit.java
 */

package cn.zy.apps.tools.jpa ;

import java.io.Serializable ;

import javax.persistence.EntityManager ;
import javax.persistence.LockModeType ;

public abstract class Basevisit extends BaseQuery implements IBasevisit {

    /**
     * 获取序列为key、抽象类型为V的classz信息方法
     */

    public <V> V get(Serializable key, Class<V> clazz) throws AccessErrorException {
        EntityManager entityManager = this.getEntityManager() ;
        return entityManager.find(clazz, key) ;

    }

    public <V> V get(Class<V> clazz, Serializable key) throws AccessErrorException {
        EntityManager entityManager = this.getEntityManager() ;
        return entityManager.find(clazz, key) ;

    }

    public <V> V load(Serializable key, Class<V> clazz) throws AccessErrorException {

        EntityManager entityManager = this.getEntityManager() ;
        return entityManager.getReference(clazz, key) ;
    }

    /**
     * 删除 entity方法
     */

    public <V> V remove(V entity) throws AccessErrorException {
        EntityManager entityManager = this.getEntityManager() ;

        entityManager.remove(entity) ;
        return entity ;
    }

    /**
     * 删除序列为key的方法
     */
    public <V> V remove(Serializable key, Class<V> clazz) throws AccessErrorException {
        EntityManager entityManager = this.getEntityManager() ;
        V entity = load(key, clazz) ;
        if (entity == null) throw new AccessErrorException(" remove entity no  success ! not find key : " + key + " entity !  ") ;
        entityManager.remove(entity) ;
        return entity ;
    }

    /**
     * 保存entity方法
     */
    public <V> void save(V entity) throws AccessErrorException {
        if (entity == null) throw new AccessErrorException(" save  entity no  success ! save's entity is  null !  ") ;
        EntityManager entityManager = this.getEntityManager() ;
        entityManager.persist(entity) ;
    }

    /**
     * 保存一个V类型的clazz和entity参数方法
     */
    public <V> void save(Class<V> clazz, V entity) throws AccessErrorException {

        if (entity == null) throw new AccessErrorException(" save  entity no  success ! save's entity is  null !  ") ;
        EntityManager entityManager = this.getEntityManager() ;
        entityManager.persist(entity) ;
    }

    /**
     * 修改表T里entity信息方法
     */
    public <V> void update(V entity) throws AccessErrorException {
        if (entity == null) throw new AccessErrorException(" update  entity no  success ! update's entity is  null !  ") ;
        EntityManager entityManager = this.getEntityManager() ;
        entityManager.merge(entity) ;
    }

    public <V> V loadWriteLock(Serializable key, Class<V> clazz) throws AccessErrorException {
        try {
            EntityManager entityManager = this.getEntityManager() ;
            V v = entityManager.getReference(clazz, key) ;
            entityManager.lock(v, LockModeType.PESSIMISTIC_WRITE) ;

            return v ;
        } catch (javax.persistence.PersistenceException | org.hibernate.exception.LockAcquisitionException e) {
            throw new AccessLockException(e) ;
        }
    }

    public <V> V getWriteLock(Serializable key, Class<V> clazz) throws AccessErrorException {
        try {
            EntityManager entityManager = this.getEntityManager() ;
            V v = entityManager.getReference(clazz, key) ;
            entityManager.lock(v, LockModeType.PESSIMISTIC_WRITE) ;

            return v ;
        } catch (javax.persistence.PersistenceException | org.hibernate.exception.LockAcquisitionException e) {
            throw new AccessLockException(e) ;
        }
    }

}
