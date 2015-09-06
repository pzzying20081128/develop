package cn.zy.apps.tools.web;

import java.util.List ;

public class SelectPage<V> {
    
    private Long count;
    
    private  List<V>  result;

    public Long getCount() {
        return count ;
    }

    public void setCount(Long count) {
        this.count = count ;
    }

    public List<V> getResult() {
        return result ;
    }

    public void setResult(List<V> result) {
        this.result = result ;
    }

   
    
    

}
