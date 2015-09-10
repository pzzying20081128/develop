package zy.apps.demo.units ;

import zy.apps.demo.PropertiesUnits ;
import zy.apps.demo.units.cache.ICacheFactory ;
import cn.zy.apps.tools.units.AutoWriteObject ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnitsException ;

public class DemoAutoWriteObject extends AutoWriteObject {

    protected ICacheFactory cacheFactory = PropertiesUnits.cacheFactory ;

    @Override
    protected String getFieldOfFieldId(String fieldName) {

        return fieldName + "Id" ;
    }

    @Override
    protected boolean filterSetProperties(Class<?> classes) {

        boolean result = ToolsUnits.regex(PropertiesUnits.regexPackage, classes.getPackage().getName()) ;

        return result ;
    }

    @Override
    protected <V> V searchCacheObject(Object id, Class<V> cacheObject) throws ToolsUnitsException {

        String key = null ;
    
        if (id instanceof String) {
            key = cacheFactory.createKey((String) id, cacheObject) ;
        } else if (id instanceof Integer) {
            key = cacheFactory.createKey(((Integer) id).toString(), cacheObject) ;
        }
        if (key == null) throw new ToolsUnitsException("search   cache key error : " + key) ;
        return cacheFactory.get(key) ;

    }

 

}
