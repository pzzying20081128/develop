package cn.zy.apps.tools.weixin ;

public class GetAcessTokenReqInfo extends IReqInfo {

    private String access_token ;

    private String expiresIn ;

    public String getExpiresIn() {
        return expiresIn ;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn ;
    }

    public String getAccess_token() {
        return access_token ;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token ;
    }

}
