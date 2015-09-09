package zy.apps.demo.service ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.tools.jpa.EntityManagerService ;

@Component(IDemoEntityManagerService.name)
public class DemoEntityManagerService extends EntityManagerService implements IDemoEntityManagerService {

}
