package cn.zy.apps.tools.weixin ;

import java.io.ByteArrayInputStream ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.Map ;

import org.dom4j.Document ;
import org.dom4j.Element ;
import org.dom4j.io.SAXReader ;

import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.networks.http.PostGetResponse ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.weixin.http.WeiXinHttpReqService ;
import cn.zy.apps.tools.weixin.units.WeiXinPayMD5Units ;

public class UnifiedPlaceAnOrderHttpReqService extends WeiXinHttpReqService<UnifiedPlaceAnOrderBean> {

    private String signKey ;

    public UnifiedPlaceAnOrderHttpReqService(String signKey) {
        super(ReqType.Post) ;

        this.signKey = signKey ;
    }

    @Override
    protected UnifiedPlaceAnOrderBean switchResponse(UnifiedPlaceAnOrderBean reqInfo, PostGetResponse postGetResponse) {
        Loggerfactory.info(logger, postGetResponse.getResponse().getResponse()) ;
        paserxml(postGetResponse.getResponse().getResponse(), reqInfo) ;
        return reqInfo ;

    }

    @Override
    protected String getReqUrl(UnifiedPlaceAnOrderBean reqInfo) {

        return "https://api.mch.weixin.qq.com/pay/unifiedorder" ;
    }

    @Override
    protected PostGetResponse switchToReqPostGetResponse(UnifiedPlaceAnOrderBean reqInfo) {

        Map<String, String> prams = new HashMap<String, String>() ;
        prams.put(UnifiedPlaceAnOrderBean.key_appid, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_appid)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_attach, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_attach)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_body, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_body)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_mch_id, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_mch_id)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_nonce_str, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_nonce_str)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_notify_url, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_notify_url)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_openid, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_openid)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_out_trade_no, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_out_trade_no)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_spbill_create_ip, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_spbill_create_ip)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_total_fee, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_total_fee)) ;
        prams.put(UnifiedPlaceAnOrderBean.key_trade_type, ToolsUnits.getValue(reqInfo, UnifiedPlaceAnOrderBean.key_trade_type)) ;

        String sign = WeiXinPayMD5Units.buildParamsMd5(prams, signKey).toUpperCase() ;

        reqInfo.setSign(sign) ;

        PostGetResponse postGetResponse = new PostGetResponse() ;

        postGetResponse.setPostSingleContent(reqInfo.toXml()) ;

        return postGetResponse ;
    }

    /**
     * <xml><return_code><![CDATA[SUCCESS]]></return_code>
    <return_msg><![CDATA[OK]]></return_msg>
    <appid><![CDATA[wxf458d773800e6b1e]]></appid>
    <mch_id><![CDATA[1238154202]]></mch_id>
    <nonce_str><![CDATA[1XG7Dsju0DgN65KP]]></nonce_str>
    <sign><![CDATA[33FF346D110507985E739A92C73E43B1]]></sign>
    <result_code><![CDATA[SUCCESS]]></result_code>
    <prepay_id><![CDATA[wx20150610135846862c347ce90023177537]]></prepay_id>
    <trade_type><![CDATA[JSAPI]]></trade_type>
    </xml>
     * 
     * @param xml
     */
    private void paserxml(String xml, UnifiedPlaceAnOrderBean reqInfo) {
        try {

            SAXReader reader = new SAXReader() ;
            Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8"))) ;

            Loggerfactory.info(logger, xml) ;

            Element root = document.getRootElement() ;

            for (Iterator it = root.elementIterator(); it.hasNext();) {
                Element element = (Element) it.next() ;
                System.out.println("==>  " + element.getName() + "   >   " + element.getTextTrim()) ;

                if (element.getName().equals("trade_type")) continue ;

                ToolsUnits.wirteValue(reqInfo, element.getName(), element.getTextTrim()) ;
            }

        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    public static void main(String[] args) {

        UnifiedPlaceAnOrderBean xx = new UnifiedPlaceAnOrderBean() ;

        xx.setAppid("ssss") ;

        String string = ToolsUnits.getValue(xx, UnifiedPlaceAnOrderBean.key_appid) ;

    }

}
