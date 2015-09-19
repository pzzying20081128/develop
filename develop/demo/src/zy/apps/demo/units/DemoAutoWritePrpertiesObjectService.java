package zy.apps.demo.units;

import zy.apps.demo.PropertiesUnits ;
import cn.zy.apps.tools.units.SimpleAutoWritePrpertiesObjectService ;

public class DemoAutoWritePrpertiesObjectService extends SimpleAutoWritePrpertiesObjectService {
    
    private  static String idfield = "id";
    
    private static  String  regexPackage =PropertiesUnits.regexPackage;

    public DemoAutoWritePrpertiesObjectService() {
        super(regexPackage, idfield) ;
        DemoAutoWriteObject  demoAutoWriteObject =new DemoAutoWriteObject();
        this.setAutoWriteObject(demoAutoWriteObject);
    }

}
