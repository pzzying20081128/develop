package zy.apps.demo.units.cache ;

public interface ICacheFactory {
    


    public <V> void put(String key, V object) ;

    public <V> V get(String key) ;
    
    public String  createKey( String key  ,  Class<?>  object);

}
