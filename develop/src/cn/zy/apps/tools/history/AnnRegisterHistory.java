package cn.zy.apps.tools.history ;

/**
 * 注册历史
 * 
 * @author zy
 * 
 */
@java.lang.annotation.Target(value = { java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD })
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface AnnRegisterHistory {

    /**
     * 模块
     * 
     * @return
     */
    public abstract String classification() ;

    /**
     * 栏目
     * 
     * @return
     */
    public abstract String module() ;

    /**
     * 操作
     * 
     * @return
     */
    public abstract String operate() ;

    public abstract String desc() ;

    /**
     * 要记录日志的的参数索引； 已 ";" 来区分.入 1；2；4 起点是1
     * 
     * @return
     */
    public abstract java.lang.String AttrIndex() ;

    public abstract String loginUserAttrIndex() ;

}
