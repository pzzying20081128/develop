package cn.zy.apps.tools.weixin ;

import net.sf.json.JSONObject ;
import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpReqService ;

//https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8888888888888888&secret=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa&code=00b788e3b42043c8459a57a8d8ab5d9f&grant_type=authorization_code

public class AccessTokenReqService extends WeiXinHttpReqService<AccessTokenReqBean> {

    private String appid ;

    private String secret ;

    public AccessTokenReqService(String appid, String secret) {
        super(ReqType.Get) ;
        this.appid = appid ;
        this.secret = secret ;

    }

    /**
     * {"access_token":"OezXcEiiBSKSxW0eoylIeM7SZEbnR1W24R1xR6Xen6xhKy1uJmZgx8VZ_Xue6LwfQg44csBm1BRMaGgwg_QNK6UzJbBGa3s75GqoDhjMGopQUY5aCE9c1e-sG6eoPKBkUA1NpMQMsHWMui_s3hzD7g",
     * "expires_in":7200,
     * "refresh_token":"OezXcEiiBSKSxW0eoylIeM7SZEbnR1W24R1xR6Xen6xhKy1uJmZgx8VZ_Xue6Lwf2j96XLs0po2qCz8RiK76Ya9TcqVuG0pAK0MJPt5s0WI4v0weKN_wTCsN-lsLmHkAi_D1w61SULFFkxqt4gZhPw",
     * "openid":"oTlkfs0BKS_HjC0OBkeDL9wt67pk",
     * "scope":"snsapi_base"
     * 
     */
    @Override
    protected AccessTokenReqBean switchResponse(AccessTokenReqBean reqInfo, PostGetResponse postGetResponse) {
        JSONObject dataJson = JSONObject.fromObject(postGetResponse.getResponse().getResponse()) ;
        String openId = dataJson.getString("openid") ;
        reqInfo.setOpenid(openId) ;
        String access_token = dataJson.getString("access_token") ;
        reqInfo.setAccess_token(access_token) ;
        return reqInfo ;
    }

    @Override
    protected String getReqUrl(AccessTokenReqBean reqInfo) {

        return "https://api.weixin.qq.com/sns/oauth2/access_token" ;
    }

    @Override
    protected PostGetResponse switchToReqPostGetResponse(AccessTokenReqBean reqInfo) {

        PostGetResponse postGetResponse = new PostGetResponse() ;
        postGetResponse.getRequestParams().put("appid", appid) ;
        postGetResponse.getRequestParams().put("secret", secret) ;
        postGetResponse.getRequestParams().put("grant_type", "authorization_code") ;
        postGetResponse.getRequestParams().put("code", reqInfo.getCode()) ;
        return postGetResponse ;
    }

}
