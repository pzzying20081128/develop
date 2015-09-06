package cn.zy.apps.tools.networks.http.cores ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;

public interface IHttpProtocolService {

    public enum CharEncoded {
        UTF8, GBK
    }

    public Logger logger = Loggerfactory.instance(HttpProtocolService.class) ;

    public String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" ;

    public String Accept_Language = "zh-cn" ;

    public String User_Agent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:35.0) Gecko/20100101 Firefox/35.0" ;

    public String Connection = "keep-alive" ;

    public String default_CharSet = "utf-8" ;

    /** 连接超时时间，由bean factory设置，缺省为8秒钟 */
    public int defaultConnectionTimeout = 60000 ;

    /** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
    public int defaultSoTimeout = 130000 ;

    /** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
    public int defaultIdleConnTimeout = 160000 ;

    public int defaultMaxConnPerHost = 30 ;

    public int defaultMaxTotalConn = 80 ;

    /** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒 */
    public final long defaultHttpConnectionManagerTimeout = 3 * 1000 ;

}
