package cn.zy.apps.demo.service ;

import javax.persistence.EntityManager ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.tools.jpa.ERPBaseService ;

@Component(IABService.name)
public class ABService extends ERPBaseService implements IABService {

    @Override
    public EntityManager getEm() {

        return getEntityManager() ;
    }

}
