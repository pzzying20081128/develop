package cn.zy.apps.tools.jpa;

import javax.persistence.EntityManager ;
import javax.persistence.PersistenceContext ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.tools.jpa.Basevisit ;

//@Component(IERPBaseService.name)
public abstract  class ERPBaseService extends Basevisit implements IERPBaseService {

	@PersistenceContext(name = "db")
	private EntityManager entityManager;
	
	

    @Override
    public EntityManager getEntityManager() {
      
        return entityManager ;
    }

	

}
