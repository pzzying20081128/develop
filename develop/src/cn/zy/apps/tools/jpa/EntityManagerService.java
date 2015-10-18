package cn.zy.apps.tools.jpa;

import javax.persistence.EntityManager ;
import javax.persistence.PersistenceContext ;

public abstract class EntityManagerService extends Basevisit implements IEntityManagerService {

	@PersistenceContext(name = "db")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
      
        return entityManager ;
    }

	

}
