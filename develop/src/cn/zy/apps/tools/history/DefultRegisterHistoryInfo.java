package cn.zy.apps.tools.history ;

import java.lang.reflect.Method ;

import org.aspectj.lang.JoinPoint ;

/**
 * 历史记录
 * 
 * @author pzzy2000
 * 
 */
public class DefultRegisterHistoryInfo extends RegisterHistoryInfo {

    /**
     * 所要匹配的的方法的的名字
     */
    private String[] matchName ;

    public void setMatchName(String matchName) {
        this.matchName = matchName.split(",") ;
    }

    /**
     * 记录历史
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterHistory(JoinPoint jp, Object element) {
        history(HistoryType.after, jp) ;
    }

    @Override
    public void beforeHistory(JoinPoint jp, Object element) {
        history(HistoryType.before, jp) ;
    }

    private boolean matchName(String inModule) {
        for (String matchName_ : matchName) {
            if (inModule.endsWith(matchName_)) return true ;
        }
        return false ;
    }

    private void history(HistoryType historyType, JoinPoint jp) {
        String typeModule = jp.getSignature().getDeclaringTypeName() ;
        if (matchName(typeModule)) {
            String method = jp.getSignature().getName() ;
            Object targetObject = jp.getTarget() ;
            Class<?>[] infaces = targetObject.getClass().getInterfaces() ;
            if (infaces == null || infaces.length == 0) return ;
            for (Class<?> clazz_ : infaces) {
                Method[] methods = clazz_.getMethods() ;
                Object[] attr = jp.getArgs() ;
                for (Method method_ : methods) {
                    if (method_.getName().equals(method) && method_.getParameterTypes().length == attr.length) {
                        AnnRegisterHistory prjHistory = method_.getAnnotation(AnnRegisterHistory.class) ;
                        if (prjHistory == null) return ;
                        String classification = prjHistory.classification() ;
                        String module = prjHistory.module() ;
                        String operate = prjHistory.operate() ;
//                        String hisMethod = prjHistory.method() ;
                        String desc = prjHistory.desc() ;
                        String loginUserAttrIndex = prjHistory.loginUserAttrIndex() ;
                        String[] attrIndex = prjHistory.AttrIndex().split(";") ;
                        Object[] agrs = new Object[attrIndex.length] ;
                        for (int i = 0; i < attrIndex.length; i++) {
                            agrs[i] = attr[Integer.parseInt(attrIndex[i])] ;
                        }
                        note(historyType, classification, module, operate , desc, targetObject, loginUserAttrIndex, agrs) ;

                    }
                }
            }

        }
    }

}
