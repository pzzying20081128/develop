package zy.apps.demo.web;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.zy.apps.tools.logger.Loggerfactory;
import cn.zy.apps.tools.web.ReDynaGeneralAction;

@Component("DemoDynaGeneralAction")
@Scope(DemoDynaGeneralAction.Scope)
public class DemoDynaGeneralAction extends ReDynaGeneralAction {

	private static final long serialVersionUID = -1232176895658561422L;

	public String testDynaProperties() throws Exception {
        Loggerfactory.info(logger, "Test Dyna Properties reDynaBean "+reDynaBean);
        Object xx = reDynaBean.get("xx");
        Map  xx1 =reDynaBean.getMap();
        for( Object key : xx1.keySet()){
        	String[]  xxx = (String[]) xx1.get(key);
        	 Loggerfactory.info(logger, "Test Dyna Properties  key  "+key +"   ->   "+xxx.length);
        }
      
        return  null;
	}

}
