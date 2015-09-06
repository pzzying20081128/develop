package cn.zy.apps.tools.weixin ;

import net.sf.json.JSONObject ;
import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.weixin.http.IWeiXinHttpReqService ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpException ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpReqService ;

/**
 * https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN  {"errcode":0,"errmsg":"ok"}
 * 
 * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
 * @author you
 *
 */
public class MeunOptReqService extends WeiXinHttpReqService<MeunOptReqInfo> {

    public enum MeunOpt {
        del, search, create
    }

    private MeunOpt meunOpt ;

    public MeunOptReqService(MeunOpt meunOpt) {
        super(meunOpt.equals(MeunOpt.create) ? ReqType.Post : ReqType.Get) ;
        this.meunOpt = meunOpt ;
    }

    @Override
    protected MeunOptReqInfo switchResponse(MeunOptReqInfo reqInfo, PostGetResponse postGetResponse) {
        MeunOptReqInfo meunOptReqInfo = (MeunOptReqInfo) reqInfo ;
        String result = postGetResponse.getResponse().getResponse() ;
        JSONObject dataJson = JSONObject.fromObject(postGetResponse.getResponse().getResponse()) ;
        String errcode = dataJson.getString("errcode") ;
        String errmsg = dataJson.getString("errmsg") ;
        meunOptReqInfo.setErrcode(errcode) ;
        meunOptReqInfo.setErrmsg(errmsg) ;
        switch (meunOpt) {
        case del: {

            return meunOptReqInfo ;
        }

        case search:

            throw new RuntimeException("this  opt no support ! ") ;
        case create:

            return meunOptReqInfo ;

        default:
            throw new RuntimeException("this  opt no support ! ") ;
        }

    }

    @Override
    protected String getReqUrl(MeunOptReqInfo meunOptReqInfo) {
        switch (meunOpt) {
        case del:
            return "https://api.weixin.qq.com/cgi-bin/menu/delete" ;

        case search:

            throw new RuntimeException("this  opt no support ! ") ;

        case create:
            return "https://api.weixin.qq.com/cgi-bin/menu/create?" + params_key_access_token + "=" + meunOptReqInfo.getReqToken() ;

        default:
            throw new RuntimeException("this  opt no support ! ") ;
        }

    }

    @Override
    protected PostGetResponse switchToReqPostGetResponse(MeunOptReqInfo reqInfo) {
        switch (meunOpt) {
        case del: {
            PostGetResponse b = new PostGetResponse() ;
            b.getRequestParams().put(params_key_access_token, reqInfo.getReqToken()) ;
            return b ;
        }

        case search:

            throw new RuntimeException("this  opt no support ! ") ;
        case create:

        {
            PostGetResponse b = new PostGetResponse() ;
            //            b.getRequestParams().put(params_key_access_token, reqInfo.getReqToken()) ;
            //            b.getRequestParams().put(params_key_post_body, reqInfo.getMeun()) ;
            b.setPostSingleContent(reqInfo.getMeun()) ;
            System.out.println("==>  " + reqInfo.getMeun()) ;
            return b ;
        }

        default:
            throw new RuntimeException("this  opt no support ! ") ;
        }

    }

    public static void main(String[] args) throws WeiXinHttpException {
        IWeiXinHttpReqService<GetAcessTokenReqInfo> service = new GetAcessTokenHttpReqService() ;
        GetAcessTokenReqInfo reqInfo = new GetAcessTokenReqInfo() ;
        reqInfo.setAppid("wxf458d773800e6b1e") ;
        reqInfo.setSecret("a2d68fbd8b9504616c0e48fc5d17c23b") ;
        reqInfo = service.req(reqInfo) ;
        String access_tocken = reqInfo.getAccess_token() ;

        System.out.println("==>  " + access_tocken) ;
        //
        //        MeunOptReqService weiXinHttpReqService = new MeunOptReqService(MeunOpt.del) ;
        //        MeunOptReqInfo meunOptReqInfo = new MeunOptReqInfo() ;
        //        meunOptReqInfo.setReqToken(access_tocken) ;
        //        meunOptReqInfo = weiXinHttpReqService.req(meunOptReqInfo) ;
        String sb = " {" + "     \"button\":[" + "        {  " + "             \"type\":\"view\", " + "             \"name\":\"第一商店\", " + "           \"url\":\"http://wx.kaerp.cn/wx/\" " + "       }" + " ]" + "}" ;

        //        StringBuffer sb = new StringBuffer();  
        //        sb.append("{");  
        //        sb.append(" \"button\":[");  
        //        sb.append("     {");  
        //        sb.append("         \"name\":\"第一个菜单\",");       //第一个菜单  
        //        sb.append("         \"sub_button\":[");  
        //        sb.append("             {");  
        //        sb.append("                 \"type\":\"click\",");  
        //        sb.append("                 \"name\":\"子菜单1\",");  
        //        sb.append("                 \"key\":\"M1\"");  
        //        sb.append("             },");  
        //        sb.append("             {");  
        //        sb.append("                 \"type\":\"click\",");  
        //        sb.append("                 \"name\":\"子菜单2\",");  
        //        sb.append("                 \"key\":\"M2\"");  
        //        sb.append("             },");  
        //        sb.append("             {");  
        //        sb.append("                 \"type\":\"click\",");  
        //        sb.append("                 \"name\":\"子菜单3\",");  
        //        sb.append("                 \"key\":\"M3\"");  
        //        sb.append("             }");  
        //        sb.append("         ]");  
        //        sb.append("     },");  
        //        sb.append("     {");  
        //        sb.append("         \"name\":\"第二个菜单\",");  
        //        sb.append("         \"sub_button\":[");  
        //        sb.append("             {");  
        //        sb.append("                 \"type\":\"click\",");  
        //        sb.append("                 \"name\":\"子菜单\",");  
        //        sb.append("                 \"key\":\"M4\"");  
        //        sb.append("             }");  
        //        sb.append("         ]");  
        //        sb.append("     },");  
        //        sb.append("     {");  
        //        sb.append("         \"name\":\"URL菜单\",");             //URL 连接  
        //        sb.append("         \"sub_button\":[");  
        //        sb.append("             {");  
        //        sb.append("                 \"type\":\"view\",");  
        //        sb.append("                 \"name\":\"无主题\",");  
        //        sb.append("                 \"url\":\"http://www.wuzhuti.cn\",");    //连接地址  
        //        sb.append("             }");  
        //        sb.append("         ]");  
        //        sb.append("     }");  
        //        sb.append(" ]");  
        //        sb.append("}");  

        MeunOptReqService weiXinHttpReqService = new MeunOptReqService(MeunOpt.create) ;
        MeunOptReqInfo meunOptReqInfo = new MeunOptReqInfo() ;
        meunOptReqInfo.setMeun(sb.toString()) ;
        meunOptReqInfo.setReqToken(access_tocken) ;
        meunOptReqInfo = weiXinHttpReqService.req(meunOptReqInfo) ;

    }
}
