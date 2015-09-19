package zy.apps.demo.units.cache ;

import java.util.HashMap ;
import java.util.Map ;

public class CacheFactory implements ICacheFactory {

    Map<String, Object> values = new HashMap<String, Object>() ;

    private static ICacheFactory cacheFactory = new CacheFactory() ;

    
    public static ICacheFactory Instance() {

        return cacheFactory ;
    }

    @Override
    public <V> void put(String key, V object) {

        values.put(key, object) ;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V get(String key) {

        return (V) values.get(key) ;
    }

    @Override
    public String createKey(String key, Class<?> object) {

        return object.getName() + "_" + key ;
    }

}
