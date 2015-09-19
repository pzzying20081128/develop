package zy.apps.demo;

import zy.apps.demo.units.cache.CacheFactory ;
import zy.apps.demo.units.cache.ICacheFactory ;

public class PropertiesUnits {
    
    public static String regexPackage = "^zy.apps.demo(\\.\\D+)*(.pojos|.views|.beans)$";
    
    public  static  ICacheFactory cacheFactory = CacheFactory.Instance() ;

}
