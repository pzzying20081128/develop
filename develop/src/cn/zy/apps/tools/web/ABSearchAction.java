package cn.zy.apps.tools.web ;

import java.util.List ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public abstract class ABSearchAction extends GeneralAction {

    protected org.apache.log4j.Logger logger = Loggerfactory.instance(ABSearchAction.class) ;

    private static final long serialVersionUID = -7327913212569390663L ;

    protected String name ;

    protected String load ;

    private List<Object> results ;

    public String search() throws Exception {
        try {
            results = searchResult() ;
            success = true ;
        } catch (Exception e) {
            success = false ;
            this.msg = handError(e) ;
        }
        return SUCCESS ;
    }

    protected abstract <V> List<V> searchResult() throws Exception ;

    public List<Object> getResults() {
        return results ;
    }

    public String getLoad() {
        return load ;
    }

    public void setLoad(String load) {
        this.load = load ;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name ;
    }

}
