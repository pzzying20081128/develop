package cn.zy.apps.tools.history ;

import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import org.aspectj.lang.JoinPoint ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public abstract class RegisterHistoryInfo {

    private org.apache.log4j.Logger logger = Loggerfactory.instance(RegisterHistoryInfo.class) ;

    public static enum HistoryType {
        after, before, exception
    }

    protected List<IRegisterHistory> regHistoryModule ; //IRegisterHistory

    private Map<String, IRegisterHistory> mapHistory = new HashMap<String, IRegisterHistory>() ;

    /**
     * 
     * @param jp
     * @param element
     */
    public abstract void afterHistory(JoinPoint jp, Object element) ;

    /**
     * 
     * @param jp
     * @param element
     */
    public abstract void beforeHistory(JoinPoint jp, Object element) ;

    public RegisterHistoryInfo() {

    }

    /**
     * 注册历史
     * 
     * @param moduleIndex
     * @param methodIndex
     * @param tagetObject
     * @param args
     */
    //    note(historyType, classification, module, operate , desc, targetObject, loginUserAttrIndex, agrs) ;
    public void note(HistoryType historyType, String classification, String module, String operate, String desc, Object tagetObject, String loginUserAttrIndex, Object... args) {

        IRegisterHistory registerHistirys = search(classification) ;
        if (registerHistirys == null) {
            Loggerfactory.error(logger, "reg  history   module  : " + classification + "  not find ! ") ;
        } else {
            registerHistirys.register(historyType, classification, module, operate, desc, tagetObject, loginUserAttrIndex, args) ;
        }

    }

    private IRegisterHistory search(String classification) {
        IRegisterHistory registerHistory = mapHistory.get(classification) ;
        if (registerHistory == null) {
            synchronized (this) {
                for (IRegisterHistory registerHistory_ : regHistoryModule) {
                    if (registerHistory_.getClassification().equals(classification)) {
                        mapHistory.put(registerHistory_.getClassification(), registerHistory_) ;
                        registerHistory = registerHistory_;
                        break;
                    }
                }
            }
            return registerHistory ;
        } else {
            return registerHistory ;
        }
      
    }

    public void setRegHistoryModule(List<IRegisterHistory> regHistoryModule) {
        this.regHistoryModule = regHistoryModule ;
    }

}
