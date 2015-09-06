package cn.zy.apps.tools.weixin ;

import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpReqService ;

/**
 * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
 * @author you
 *
 */
public class NoticePayResultsService extends WeiXinHttpReqService<NoticePayResultsBean> {

    private String access_token ;

    public NoticePayResultsService(String access_token) {
        super(ReqType.Post) ;
        this.access_token = access_token ;

    }

    @Override
    protected NoticePayResultsBean switchResponse(NoticePayResultsBean reqInfo, PostGetResponse postGetResponse) {
        System.out.println("++++++++++ >   " + postGetResponse.getResponse().getResponse()) ;
        return reqInfo ;
    }

    @Override
    protected String getReqUrl(NoticePayResultsBean reqInfo) {
        // TODO Auto-generated method stub
        return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token ;
    }

    @Override
    protected PostGetResponse switchToReqPostGetResponse(NoticePayResultsBean reqInfo) {
        PostGetResponse postGetResponse = new PostGetResponse() ;
        postGetResponse.setPostSingleContent(reqInfo.toJson()) ;
        return postGetResponse ;
    }

}
