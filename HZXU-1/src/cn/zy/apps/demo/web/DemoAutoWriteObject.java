package cn.zy.apps.demo.web ;

import cn.zy.apps.tools.units.AutoWriteObject ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnitsException ;

public class DemoAutoWriteObject extends AutoWriteObject {

    protected IPropertiesCacheFactory cacheFactory = PropertiesCacheFactory.instance() ;

    public void cacheObject(String key, Object object) {

        cacheFactory.cacheObject(key, object) ;
    }

    private String regexPackage ;

    public DemoAutoWriteObject(String regexPackage) {
        super(false) ;
        this.regexPackage = regexPackage ;
       
    }

    @Override
    protected String getFieldOfFieldId(String fieldName) {

        return fieldName + "Id" ;
    }

    @Override
    protected boolean filterSetProperties(Class<?> classes) {
       
        boolean result = ToolsUnits.regex(regexPackage, classes.getPackage().getName()) ;

        if (result == false) {
            result = filterClass(classes) ;
        }
        return result ;
    }

    private boolean filterClass(Class<?> classes) {

        return true ;

    }

    @Override
    protected <V> V searchCacheObject(Object id, Class<V> cacheObject) throws ToolsUnitsException {

        String key = null ;

        if (id instanceof String) {
            key = (String) id ;
        } else if (id instanceof Integer) {
            key = ((Integer) id).toString() ;
        }
        if (key == null) throw new ToolsUnitsException("search   cache key error : " + key) ;
        
        V  v = cacheFactory.searchCacheObject(id.toString(), cacheObject) ;
        
        
        System.out.println( id+" ==========>filterSetProperties     "+cacheObject.toString()+"   regexPackage  "+regexPackage+"  V  "+v) ;
        
        return   v;

    }

}
