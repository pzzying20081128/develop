package cn.zy.apps.demo.web ;

import cn.zy.apps.tools.units.SimpleIdAutoWritePrpertiesObjectService ;
import cn.zy.apps.tools.web.ABSystemABAction ;

public abstract class ABDemoSystemAction<Bean, SeachBean> extends ABSystemABAction<Bean,SeachBean> {

    private static final long serialVersionUID = -8469898425371487062L ;

    protected static SimpleIdAutoWritePrpertiesObjectService writeObjectService =PrpertiesAutoWriteObjectService.instance();



    public SimpleIdAutoWritePrpertiesObjectService getSimpleIdAutoWritePrpertiesObjectService() {
        return writeObjectService ;
    }


}
