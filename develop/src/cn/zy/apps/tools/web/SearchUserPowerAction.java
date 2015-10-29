package cn.zy.apps.tools.web ;

import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import cn.zy.apps.tools.units.powers.DefaultSystemMeunsService ;
import cn.zy.apps.tools.units.powers.ISystemMeunsService ;
import cn.zy.apps.tools.units.powers.IUserPowerMdouleService ;
import cn.zy.apps.tools.units.powers.TreeData ;
import cn.zy.apps.tools.units.powers.UserOptPower ;
import cn.zy.apps.tools.units.powers.UserPower ;

public abstract class SearchUserPowerAction extends GeneralAction {

    private static final long serialVersionUID = 8586106054067838201L ;

    protected List<TreeData> children ;

    protected abstract IUserPowerMdouleService getIUserMdoulePowerService() ;

    private boolean isroot ;

    protected abstract boolean isAdmin() ;

    protected String moduleId ;

    protected Map<String, List<UserOptPower>> powerMap ;

    public String showUserPowerMeuns() throws Exception {

        try {
            String loginUserId = getLoginUserId() ;

            boolean isAdmin = isAdmin() ;

            IUserPowerMdouleService userMdoulePowerService = getIUserMdoulePowerService() ;

            ISystemMeunsService units = new DefaultSystemMeunsService(userMdoulePowerService) ;

            children = units.searchUserPowerTree(loginUserId, isAdmin) ;
            this.success = true ;
        } catch (Exception e) {
            this.msg = handError(e) ;
            this.success = false ;
        }

        return SUCCESS ;

    }

    public String filterUserPowerTree() throws Exception {

        try {

            IUserPowerMdouleService userMdoulePowerService = getIUserMdoulePowerService() ;

            ISystemMeunsService units = new DefaultSystemMeunsService(userMdoulePowerService) ;

            String loginUserId = getLoginUserId() ;

            children = units.filterUserPowerTree(loginUserId) ;

            success = true ;

        } catch (Exception e) {
            this.msg = handError(e) ;
            this.success = false ;
            success = false ;
            this.msg = e.getMessage() ;
        }
        return SUCCESS ;
    }

    /**
     *      <action name="power" class="SystemUserPowerAction" method="listPowerByUser">
            <result type="json">
                <param name="ignoreHierarchy">false</param>
                <param name="excludeNullProperties">false</param>
                <param name="includeProperties">
                    success,
                    powerMap,
                    powerMap.\w+,
                    powerMap.\w+\[\d+\]\.powerName,
                    powerMap.\w+\[\d+\]\.optName,
                    powerMap.\w+\[\d+\]\.use

                </param>
            </result>
        </action>
     * 
     * @return
     * @throws Exception
     */
    public String listPowerByUser() throws Exception {

        try {
            IUserPowerMdouleService userMdoulePowerService = getIUserMdoulePowerService() ;
            List<UserPower<UserOptPower>> powerList = userMdoulePowerService.searchUserPower(moduleId, getLoginUserId()) ;
            Map<String, List<UserOptPower>> powerMap = new HashMap<String, List<UserOptPower>>() ;
            for (UserPower<UserOptPower> systemUserPower : powerList) {
                List<UserOptPower> x = systemUserPower.getUserOptPowers() ;
                powerMap.put(systemUserPower.getModuleId(), x) ;
            }
            this.powerMap = powerMap ;
            boolean isAdmin = isAdmin() ;
            this.isroot = isAdmin ;
            this.success = true ;
        } catch (Exception e) {
            this.msg = handError(e) ;
            this.success = false ;
            this.msg = "获得用户权限错误" ;
        }
        return SUCCESS ;
    }

    public List<TreeData> getChildren() {
        return children ;
    }

    public void setChildren(List<TreeData> children) {
        this.children = children ;
    }

    public String getModuleId() {
        return moduleId ;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId ;
    }

    public Map<String, List<UserOptPower>> getPowerMap() {
        return powerMap ;
    }

    public void setPowerMap(Map<String, List<UserOptPower>> powerMap) {
        this.powerMap = powerMap ;
    }

    public boolean isIsroot() {
        return isroot ;
    }

    public void setIsroot(boolean isroot) {
        this.isroot = isroot ;
    }

}
