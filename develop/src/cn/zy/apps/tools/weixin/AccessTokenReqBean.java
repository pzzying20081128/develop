package cn.zy.apps.tools.weixin ;

public class AccessTokenReqBean extends IReqInfo {

    private String code ;

    private String openid ;

    private String access_token ;

    public String getCode() {
        return code ;
    }

    public void setCode(String code) {
        this.code = code ;
    }

    public String getOpenid() {
        return openid ;
    }

    public void setOpenid(String openid) {
        this.openid = openid ;
    }

    public String getAccess_token() {
        return access_token ;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token ;
    }

}
