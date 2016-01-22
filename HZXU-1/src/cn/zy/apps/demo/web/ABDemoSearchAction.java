package cn.zy.apps.demo.web ;

import cn.zy.apps.tools.units.SimpleIdAutoWritePrpertiesObjectService ;
import cn.zy.apps.tools.web.ABSearchAction ;

public abstract class ABDemoSearchAction<SeachBean> extends ABSearchAction {

    /**
     * 
     */
    private static final long serialVersionUID = 863935365111076379L ;

    protected static SimpleIdAutoWritePrpertiesObjectService writeObjectService =PrpertiesAutoWriteObjectService.instance();

    protected Integer uuid ;

    protected SeachBean searchBean ;

    public Integer getUuid() {
        return uuid ;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid ;
    }

    public SeachBean getSearchBean() {
        return searchBean ;
    }

    public void setSearchBean(SeachBean searchBean) {
        this.searchBean = searchBean ;
    }

}
