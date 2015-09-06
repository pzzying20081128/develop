package cn.zy.apps.tools.weixin.http ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public interface IWeiXinHttpReqService<V> {

    public static Logger logger = Loggerfactory.instance(IWeiXinHttpReqService.class) ;

    public String params_key_appid = "appid" ;

    public String params_key_secret = "secret" ;

    public String params_key_access_token = "access_token" ;

    public String params_key_post_body = "body" ;

    public enum ReqType {
        Get, Post, PostString
    }

    /**
     * 
     * @param type 0:  get  1 : post
     * @param reqInfo
     * @return
     */
    public V req(V reqInfo) throws WeiXinHttpException ;

}
