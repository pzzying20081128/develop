package cn.zy.apps.tools.web ;

public interface IBaseAction {

    public String RETURNS = "returns" ;

    public static String user_no_login_or_timeout = "1001" ;

    public static String system_config_file = "system.properties" ;

    public static String session_key_userinfo = "user_info" ;
    
    public static String session_key_userinfo_main = "user_main_info" ;

    public static boolean test = false ;

    public static int ajax_result_success = 1 ;

    public static int ajax_result_no_success = -1 ;

    public static final String CHARSET = "UTF-8" ;

    public static final String CHARSET_OUT = "gb2312" ;

    public static final String session_time_out = "sessiontimeout" ;

}
