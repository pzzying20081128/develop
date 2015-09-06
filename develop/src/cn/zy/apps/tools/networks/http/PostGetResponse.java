package cn.zy.apps.tools.networks.http ;

import cn.zy.apps.tools.networks.http.base.IRequest ;
import cn.zy.apps.tools.networks.http.cores.IHttpProtocolService.CharEncoded ;

/**
 * 请求应答
 * 
 * @author you
 * 
 */
public class PostGetResponse extends IRequest {

    public PostGetResponse(CharEncoded charEncoded) {
        super(charEncoded) ;

    }

    public PostGetResponse() {
        super(CharEncoded.UTF8) ;

    }

}
