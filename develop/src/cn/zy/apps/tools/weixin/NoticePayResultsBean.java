package cn.zy.apps.tools.weixin ;

import java.util.Date ;

import cn.zy.apps.tools.units.SQLUilts ;

/**
 * 
 *   {
           \"touser\":\"OPENID\",
           \"template_id\":\"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY\",
           \"url\":\"http://weixin.qq.com/download\",
           \"topcolor\":\"#FF0000\",
           \"data\":{
                   \"first\": {
                       \"value\":\"恭喜你购买成功！\",
                       \"color\":\"#173177\"
                   },
                   \"keynote1\":{
                       \"value\":\"巧克力\",
                       \"color\":\"#173177\"
                   },
                   \"keynote2\": {
                       \"value\":\"39.8元\",
                       \"color\":\"#173177\"
                   },
                   \"keynote3\": {
                       \"value\":\"2014年9月16日\",
                       \"color\":\"#173177\"
                   },
                   \"remark\":{
                       \"value\":\"欢迎再次购买！\",
                       \"color\":\"#173177\"
                   }
           }
       }
 * 
 * 
 * @author you
 *
 */

public class NoticePayResultsBean extends IReqInfo {

    private String touser ;

    private String template_id = "-9K3ExkrTNnMDbTuVqmjBjcX-PBwEH5lOQDuQCADvPY" ;

    public String toJson() {
        //        String x = "{" 
        //       
        //        + "\"touser\":\""+touser+"\", "
        //
        //        + "\"template_id\":\""+template_id+"\", "
        //
        //        + "\"url\":\"http://weixin.qq.com/download\", "
        //
        //        + "\"topcolor\":\"#FF0000\", "
        //
        //        + "\"data\":{ "
        //
        //        + "\"first\": { "
        //
        //        + "\"value\":\"恭喜你购买成功！\", "
        //
        //        + "\"color\":\"#173177\" "
        //
        //        + "}, "
        //
        //        + "\"keynote1\":{ "
        //
        //        + "\"value\":\"巧克力\", "
        //
        //        + "\"color\":\"#173177\""
        //
        //        + "}, "
        //
        //        + "\"keynote2\": { "
        //
        //        + "\"value\":\"39.8元\", "
        //
        //        + "\"color\":\"#173177\" "
        //
        //        + "}, "
        //
        //        + "\"keynote3\": { "
        //
        //        + "\"value\":\"2014年9月16日\", "
        //
        //        + "\"color\":\"#173177\" "
        //
        //        + "},"
        //
        //        + "\"remark\":{"
        //
        //        + "\"value\":\"欢迎再次购买！\","
        //
        //        + "\"color\":\"#173177\""
        //
        //        + "}"
        //
        //        + "}"
        //
        //        + "}" ;

        String x = "{"

        + "\"touser\":\"" + touser + "\", "

        + "\"template_id\":\"" + template_id + "\", "

        + "\"url\":\"http://weixin.qq.com/download\", "

        + "\"topcolor\":\"#FF0000\", "

        + "\"data\":{ "

        + "\"first\": { "

        + "\"value\":\"" + SQLUilts.getIUniqueId() + "\", "

        + "\"color\":\"#173177\" "

        + "}, "

        + "\"keyword1\":{ "

        + "\"value\":\"测试商品\", "

        + "\"color\":\"#173177\""

        + "}, "

        + "\"keyword2\": { "

        + "\"value\":\"0.01元\", "

        + "\"color\":\"#173177\" "

        + "}, "

        + "\"keyword3\": { "

        + "\"value\":\"" + (new Date()).toLocaleString() + "\", "

        + "\"color\":\"#173177\" "

        + "},"

        + "\"remark\":{"

        + "\"value\":\"欢迎再次购买！\","

        + "\"color\":\"#173177\""

        + "}"

        + "}"

        + "}" ;

        return x ;
    }

    public String getTouser() {
        return touser ;
    }

    public void setTouser(String touser) {
        this.touser = touser ;
    }
}
