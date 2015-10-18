package cn.zy.apps.tools.units ;

import java.io.InputStream ;
import java.util.Properties ;

import org.apache.log4j.Logger ;

import cn.zy.apps.tools.logger.Loggerfactory ;




/**
 * 加载配置文件
 * 
 * @author pzzy2000
 * 
 */
public class SystemConfig  {
    
    public static String system_config_file = "system.properties" ;

    private static Logger logger = Loggerfactory.instance(SystemConfig.class) ;

    public String configName(String configName) {

        if (configName == null) return null ;
        if (configName.startsWith("{") && configName.endsWith("}")) {
            String configName_ = configName.substring(1, configName.length() - 1) ;
            configName_ = selConfig(configName_) ;
            return configName_ ;
        } else {
            return configName ;
        }
    }

    private static SystemConfig config ;

    private Properties properties = new Properties() ;

    public synchronized static SystemConfig instances() {

        if (config == null) {
            config = new SystemConfig() ;
            config.loadConfig() ;
        }

        return config ;
    }

    public void loadConfig() {

        try {
           
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(SystemConfig.system_config_file) ;
            if (in == null) in = getClass().getClassLoader().getResourceAsStream(SystemConfig.system_config_file) ;
            if (in == null) in = this.getClass().getResourceAsStream(SystemConfig.system_config_file) ;
            if (in == null) throw new RuntimeException(" not find  load  Config  file : " + SystemConfig.system_config_file) ;
            properties.load(in) ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
        }

    }

    public String selConfig(String config) {

        try {

            String str = properties.getProperty(config) ;
            if (str == null) return null ;
            String reStr = new String(str.getBytes("ISO-8859-1"), "UTF-8") ;
            return reStr ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;
            return null ;
        }
    }
}
