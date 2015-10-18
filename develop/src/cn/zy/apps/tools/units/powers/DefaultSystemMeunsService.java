package cn.zy.apps.tools.units.powers ;

import java.util.List ;



public class DefaultSystemMeunsService implements ISystemMeunsService {

    private IUserPowerMdouleService userMdoulePowerService ;

    public DefaultSystemMeunsService(IUserPowerMdouleService userMdoulePowerService) {
        super() ;
        this.userMdoulePowerService = userMdoulePowerService ;
    }

    @Override
    public List<TreeData> searchUserPowerTree(String loginUserId,boolean isAdmin) throws SystemMeunsException {
        SearchUserOptModuleTreeUnits units = new SearchUserOptModuleTreeUnits(userMdoulePowerService) ;
        try {
            return units.listUserPowerTree(loginUserId, isAdmin) ; 
        } catch (Exception e) {
            throw new SystemMeunsException(e) ;
        }
    }

    @Override
    public List<TreeData> filterUserPowerTree(String loginUserId) throws SystemMeunsException {
      
        try {
            SearchUserOptModuleTreeUnits ta = new SearchUserOptModuleTreeUnits(userMdoulePowerService);
            List<TreeData> children = ta.filterUserPowerTree(loginUserId, true, false);
            
            return children;
        } catch (Exception e) {
            throw new SystemMeunsException(e);
        }
       
    }

}
