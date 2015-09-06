package cn.zy.apps.tools.web ;

import cn.zy.apps.tools.logger.Loggerfactory ;

/**
 * 普通Action
 * 
 * @author pzzy2000
 * 
 */
public abstract class GeneralAction extends BaseAction implements IGeneralAction {

    private static final long serialVersionUID = -2091592711398695194L ;

    protected String msg ;

    protected boolean success = true ;
    
    
    protected String handError(Exception ex) {
        Loggerfactory.error(logger, ex.getMessage(), ex) ;
        this.msg = ex.getMessage() ;
        return msg ;
    }

    public String getMsg() {
        return msg ;
    }

    public void setMsg(String msg) {
        this.msg = msg ;
    }

    public boolean isSuccess() {
        return success ;
    }

    public void setSuccess(boolean success) {
        this.success = success ;
    }

   

}
