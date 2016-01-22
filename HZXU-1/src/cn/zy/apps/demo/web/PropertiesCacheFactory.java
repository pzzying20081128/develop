package cn.zy.apps.demo.web ;

import java.util.HashMap ;
import java.util.Map ;

public class PropertiesCacheFactory implements IPropertiesCacheFactory {

    private Map<String, Object> aloneObjectCache = new HashMap<String, Object>() ;

    private static IPropertiesCacheFactory propertiesCacheFactory = new PropertiesCacheFactory() ;

    private PropertiesCacheFactory() {

    }

    public static IPropertiesCacheFactory instance() {
        return propertiesCacheFactory ;
    }

    @Override
    public void cacheObject(String key, Object object) {

        String key_ = createAloneObjectKey(key, object.getClass()) ;

        aloneObjectCache.put(key_, object) ;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V searchCacheObject(String key, Class<V> clazz) {

        return (V) searchAloneObject(key, clazz) ;
    }

    private Object searchAloneObject(String key, Class<?> clazz) {
        String keys = createAloneObjectKey(key, clazz) ;
        //        System.out.println("================= searchAloneObject  > keys  " + keys + "         clazz    " + clazz.getClass().getSimpleName()) ;
        return aloneObjectCache.get(keys) ;
    }

    private String createAloneObjectKey(String key, Class<?> clazz) {
        return clazz.getSimpleName() + "_id_" + key ;
    }

}
