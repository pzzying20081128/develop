package cn.zy.apps.tools.weixin ;

import net.sf.json.JSONObject ;
import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.weixin.http.IWeiXinHttpReqService ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpException ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpReqService ;

//https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET

//{"access_token":"NrAiEVtC6IpL4E93rRJaPzQ4R0yMsD0IiTIVHmN86yRtwGIgmparpD_i2XsvKa5ThZLGQpaUnMsd_SAg_O0_QabnTlUCqnYG8Isd8JakpQ0","expires_in":7200}
public class GetAcessTokenHttpReqService extends WeiXinHttpReqService<GetAcessTokenReqInfo> {

    public GetAcessTokenHttpReqService() {
        super(ReqType.Get) ;

    }

    private String params_key_grant_type = "grant_type" ;

    @Override
    protected PostGetResponse switchToReqPostGetResponse(GetAcessTokenReqInfo reqInfo) {
        PostGetResponse postGetResponse = new PostGetResponse() ;
        postGetResponse.getRequestParams().put(params_key_appid, reqInfo.getAppid()) ;
        postGetResponse.getRequestParams().put(params_key_secret, reqInfo.getSecret()) ;
        postGetResponse.getRequestParams().put(params_key_grant_type, "client_credential") ;
        return postGetResponse ;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected GetAcessTokenReqInfo switchResponse(GetAcessTokenReqInfo reqInfo, PostGetResponse postGetResponse) {
        GetAcessTokenReqInfo reqInfo_ = (GetAcessTokenReqInfo) reqInfo ;
        JSONObject dataJson = JSONObject.fromObject(postGetResponse.getResponse().getResponse()) ;
        String access_token = dataJson.getString("access_token") ;
        String expires_in = dataJson.getString("expires_in") ;
        reqInfo_.setAccess_token(access_token) ;
        reqInfo_.setExpiresIn(expires_in) ;
        return reqInfo_ ;
    }

    @Override
    protected String getReqUrl(GetAcessTokenReqInfo getAcessTokenReqInfo) {
        return "https://api.weixin.qq.com/cgi-bin/token" ;
    }

    public static void main(String[] args) throws WeiXinHttpException {
        IWeiXinHttpReqService<GetAcessTokenReqInfo> service = new GetAcessTokenHttpReqService() ;

        GetAcessTokenReqInfo reqInfo = new GetAcessTokenReqInfo() ;
        reqInfo.setAppid("wxf458d773800e6b1e") ;
        reqInfo.setSecret("a2d68fbd8b9504616c0e48fc5d17c23b") ;
        reqInfo = service.req(reqInfo) ;
        System.out.println("==>  " + reqInfo.getAccess_token()) ;

    }

}
