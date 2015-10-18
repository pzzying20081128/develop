package cn.zy.apps.tools.history ;

import cn.zy.apps.tools.history.RegisterHistoryInfo.HistoryType ;

/**
 * 注册历史
 * 
 * @author zy
 * 
 */
public interface IRegisterHistory {

    /**
     * 注册操作
     * 
     * @param moduleIndex
     *            模块索引
     * @param methodIndex
     *            方法
     * @param tagetObject
     *            要记录的目标对象
     * @param args
     *            传入的参数对象
     */
    public void register(HistoryType historyType, String classification, String module, String operate , String desc, Object tagetObject, String loginUserAttrIndex, Object... args) ;
    
    
    public String  getClassification();

}
