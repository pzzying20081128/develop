package cn.zy.apps.tools.web ;

import java.util.HashMap ;
import java.util.Map ;

/**
 */
public class SessionUser<V> {

    /**
     * 用户Id
     */
    private String userId ;

    private String loginUserName ;
    
    private V  userInfo;

    private Map<String, Object> annex = new HashMap<String, Object>(0) ;

    public String getUserId() {
        return userId ;
    }

    public void setUserId(String userId) {
        this.userId = userId ;
    }

    /**
     * 在此映射中关联指定值与指定键
     * 
     * @param <T>
     * @param key
     * @param element
     */
    public <T> void putElement(String key, T element) {
        annex.put(key, element) ;
    }

    /**
     * 返回指定键在此标识哈希映射中所映射的值
     * 
     * @param <T>
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getElement(String key) {
        return (T) annex.get(key) ;
    }

    public void clear() {
        annex.clear() ;
    }

    public String getLoginUserName() {
        return loginUserName ;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName ;
    }

    public Map<String, Object> getAnnex() {
        return annex ;
    }

    public V getUserInfo() {
        return userInfo ;
    }

    public void setUserInfo(V userInfo) {
        this.userInfo = userInfo ;
    }

}
