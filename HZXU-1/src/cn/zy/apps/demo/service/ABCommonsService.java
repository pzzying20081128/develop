package cn.zy.apps.demo.service ;

import org.apache.log4j.Logger ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public abstract class ABCommonsService {

    protected Logger logger = Loggerfactory.instance("service") ;



    @Autowired
    @Qualifier(IABService.name)
    protected IABService baseService ;

}
