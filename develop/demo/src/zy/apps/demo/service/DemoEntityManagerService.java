package zy.apps.demo.service ;

import org.springframework.stereotype.Component ;

import zy.apps.demo.PropertiesUnits ;
import zy.apps.demo.units.cache.CacheFactory ;
import zy.apps.demo.units.cache.ICacheFactory ;

import cn.zy.apps.tools.jpa.EntityManagerService ;

@Component(IDemoEntityManagerService.name)
public class DemoEntityManagerService extends EntityManagerService implements IDemoEntityManagerService {

    protected  ICacheFactory cacheFactory = PropertiesUnits.cacheFactory;
}
