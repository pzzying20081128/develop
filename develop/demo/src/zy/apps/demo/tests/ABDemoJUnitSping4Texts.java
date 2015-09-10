package zy.apps.demo.tests ;

import org.junit.runner.RunWith ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

import cn.zy.apps.tools.test.ABJUnitSping4Tests ;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
        "classpath:configs/annotation_config.xml",
        "classpath:configs/transaction_config.xml",
        "classpath:configs/auto_write_config.xml",
        
})
public abstract class ABDemoJUnitSping4Texts extends ABJUnitSping4Tests {

}
