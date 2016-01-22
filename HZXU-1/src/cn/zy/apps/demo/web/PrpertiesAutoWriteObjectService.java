package cn.zy.apps.demo.web ;

import cn.zy.apps.tools.units.PrpertiesSetValueService ;
import cn.zy.apps.tools.units.SimpleIdAutoWritePrpertiesObjectService ;

public class PrpertiesAutoWriteObjectService extends SimpleIdAutoWritePrpertiesObjectService {

    private static PrpertiesAutoWriteObjectService service ;

    public static String regexPackage = "^cn.zy.apps.demo(\\.\\D+)*(.pojos|.bean)$" ;

    private static String idfield = "id" ;

    private DemoAutoWriteObject autoWriteObject ;

    private DemoPrpertiesSetValueService demoPrpertiesSetValueService ;

    protected PrpertiesAutoWriteObjectService() {

        super(regexPackage, idfield) ;

        DemoAutoWriteObject autoWriteObject = new DemoAutoWriteObject(regexPackage) ;

        this.autoWriteObject = autoWriteObject ;

        DemoPrpertiesSetValueService demoPrpertiesSetValueService = new DemoPrpertiesSetValueService(regexPackage) ;

        this.demoPrpertiesSetValueService = demoPrpertiesSetValueService ;

    }

    public static PrpertiesAutoWriteObjectService instance() {
        if (service == null) {
            service = new PrpertiesAutoWriteObjectService() ;
        }
        return service ;
    }

    @Override
    protected PrpertiesSetValueService autoPrpertiesSetValueService() {

        return demoPrpertiesSetValueService ;
    }

    @Override
    protected DemoAutoWriteObject searchAutoWriteObject() {

        return autoWriteObject ;
    }

}
