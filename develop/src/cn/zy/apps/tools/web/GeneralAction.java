package cn.zy.apps.tools.web ;

import cn.zy.apps.tools.jpa.ServiceException ;
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

    protected Integer start ;

    protected Integer limit ;

    protected String handError(Exception ex) {
        Loggerfactory.error(logger, ex.getMessage(), ex) ;
        if (ex instanceof ServiceException) {
            ServiceException xx = (ServiceException) ex ;
            this.msg = xx.getMsg();
            System.out.println("======== > ") ;
        } else {
            this.msg = ex.getMessage() ;
        }

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

    public Integer getStart() {
        return start ;
    }

    public void setStart(Integer start) {
        this.start = start ;
    }

    public Integer getLimit() {
        return limit ;
    }

    public void setLimit(Integer limit) {
        this.limit = limit ;
    }

}
