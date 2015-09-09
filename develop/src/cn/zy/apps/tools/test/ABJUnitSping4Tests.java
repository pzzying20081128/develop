package cn.zy.apps.tools.test;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public class ABJUnitSping4Tests extends AbstractJUnit4SpringContextTests {
    
    protected org.apache.log4j.Logger logger = Loggerfactory.instance("ABJUnitSping4Tests") ;

}
