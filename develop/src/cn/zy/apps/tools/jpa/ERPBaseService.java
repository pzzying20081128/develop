package cn.zy.apps.tools.jpa;

import javax.persistence.EntityManager ;
import javax.persistence.PersistenceContext ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.tools.jpa.Basevisit ;

@Component(IERPBaseService.name)
public class ERPBaseService extends Basevisit implements IERPBaseService {

	@PersistenceContext(name = "erp")
	private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
      
        return entityManager ;
    }

	

}
