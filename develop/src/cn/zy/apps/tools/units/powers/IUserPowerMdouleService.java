package cn.zy.apps.tools.units.powers ;

import java.util.List ;

/**
 * 用户权限接口 
 * @author you
 *
 */
public interface IUserPowerMdouleService {
    
    public List<String> listUserModulePowerBySysUserId(String loginUserId) throws Exception ;
    
    public   List<UserPower<UserOptPower>>  searchUserPower(String moduleId ,String loginUserId ) throws Exception;
    
    public String[][] getInitTreeMeuns() ;

}
