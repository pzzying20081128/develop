package cn.zy.apps.tools.units.powers;

import java.util.List ;



public class MyPowerBean {
    
    private List<TreeData> children ;
    
    private boolean success;
    
    private String msg;

    public List<TreeData> getChildren() {
        return children ;
    }

    public void setChildren(List<TreeData> children) {
        this.children = children ;
    }

    public boolean isSuccess() {
        return success ;
    }

    public void setSuccess(boolean success) {
        this.success = success ;
    }

    public String getMsg() {
        return msg ;
    }

    public void setMsg(String msg) {
        this.msg = msg ;
    }

}
