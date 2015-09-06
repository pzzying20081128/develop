package cn.zy.apps.tools.weixin ;

/**
 * 统一下单
 * @author you
 *
 */

/**
 * <xml>
   <appid>微信分配的公众账号ID</appid>
   <attach>附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据</attach>
   <body>商品或支付单简要描述</body>
   <mch_id>微信支付分配的商户号</mch_id>
   <nonce_str>随机字符串，不长于32位。</nonce_str>
   <notify_url>接收微信支付异步通知回调地址</notify_url>
   <openid>用户标识  /   trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。</openid>
   <out_trade_no>商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号</out_trade_no>
   <spbill_create_ip>APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。</spbill_create_ip>
   <total_fee>   总金额 / 订单总金额，只能为整数，详见支付金额</total_fee>
   <trade_type>JSAPI</trade_type>
   <sign>签名</sign>
</xml>
 * 
 * @author you
 *
 */
public class UnifiedPlaceAnOrderBean extends IReqInfo {

    public static String key_appid = "appid" ;

    public static String key_attach = "attach" ;

    public static String key_body = "body" ;

    public static String key_mch_id = "mch_id" ;

    public static String key_nonce_str = "nonce_str" ;

    public static String key_notify_url = "notify_url" ;

    public static String key_openid = "openid" ;

    public static String key_out_trade_no = "out_trade_no" ;

    public static String key_spbill_create_ip = "spbill_create_ip" ;

    public static String key_total_fee = "total_fee" ;

    public static String key_trade_type = "trade_type" ;

    private String appid ;

    private String attach ;

    private String body ;

    private String mch_id ;

    private String nonce_str ;

    private String notify_url ;

    private String openid ;

    private String out_trade_no ;

    private String spbill_create_ip ;

    private String total_fee ;

    private String trade_type = "JSAPI" ;

    private String sign ;

    private String prepay_id ;

    public String getAppid() {
        return appid ;
    }

    public void setAppid(String appid) {
        this.appid = appid ;
    }

    public String getAttach() {
        return attach ;
    }

    public void setAttach(String attach) {
        this.attach = attach ;
    }

    public String getBody() {
        return body ;
    }

    public void setBody(String body) {
        this.body = body ;
    }

    public String getMch_id() {
        return mch_id ;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id ;
    }

    public String getNonce_str() {
        return nonce_str ;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str ;
    }

    public String getNotify_url() {
        return notify_url ;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url ;
    }

    public String getOpenid() {
        return openid ;
    }

    public void setOpenid(String openid) {
        this.openid = openid ;
    }

    public String getOut_trade_no() {
        return out_trade_no ;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no ;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip ;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip ;
    }

    public String getTotal_fee() {
        return total_fee ;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee ;
    }

    public String getTrade_type() {
        return trade_type ;
    }

    public String getSign() {
        return sign ;
    }

    public void setSign(String sign) {
        this.sign = sign ;
    }

    public String toXml() {
        String key = "<xml>"

        + "<appid>" + this.appid + "</appid>"

        + "<attach>" + this.attach + "</attach>"

        + "<body>" + this.body + "</body>"

        + "<mch_id>" + this.mch_id + "</mch_id>"

        + "<nonce_str>" + this.nonce_str + "</nonce_str>"

        + "<notify_url>" + this.notify_url + "</notify_url>"

        + "<openid>" + this.openid + "</openid>"

        + "<out_trade_no>" + this.out_trade_no + "</out_trade_no>"

        + "<spbill_create_ip>" + this.spbill_create_ip + "</spbill_create_ip>"

        + "<total_fee>" + this.total_fee + "</total_fee>"

        + "<trade_type>JSAPI</trade_type>"

        + "<sign>" + this.sign + "</sign> "

        + "</xml>" ;

        return key ;
    }

    public String getPrepay_id() {
        return prepay_id ;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id ;
    }
}
