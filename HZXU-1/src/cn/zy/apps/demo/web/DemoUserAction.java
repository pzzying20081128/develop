package cn.zy.apps.demo.web ;

import java.util.ArrayList ;
import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.pojos.DemoUserOptPower ;
import cn.zy.apps.demo.pojos.DemoUserPower ;
import cn.zy.apps.demo.service.IDemoUserService ;
import cn.zy.apps.demo.units.search.bean.DemoUserSearchBean ;
import cn.zy.apps.tools.units.SQLUilts ;
import cn.zy.apps.tools.units.powers.SystemUserPowerTools ;
import cn.zy.apps.tools.units.powers.UserOptPower ;
import cn.zy.apps.tools.units.powers.UserPower ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("DemoUserAction")
@org.springframework.context.annotation.Scope(DemoUserAction.Scope)
public class DemoUserAction extends ABDemoSystemAction<DemoUser, DemoUserSearchBean> {

    private static final long serialVersionUID = 5097422831159826456L ;

    @Autowired
    @Qualifier(IDemoUserService.name)
    private IDemoUserService service ;

    private String power ;

    private DemoUser demouser ;

    public String save() throws Exception {
        try {
            /////////////////////
            List<UserPower<UserOptPower>> userPowers = SystemUserPowerTools.paserUserPower(power) ;
            List<DemoUserPower> systemUserPowers = new ArrayList<DemoUserPower>() ;
            for (UserPower<UserOptPower> userPower : userPowers) {
                DemoUserPower systemUserPower = new DemoUserPower() ;
                systemUserPowers.add(systemUserPower) ;
                systemUserPower.setUuid(SQLUilts.getIUniqueId()) ;
                systemUserPower.setModuleId(userPower.getModuleId()) ;
                systemUserPower.setModuleName(userPower.getModuleName()) ;
                List<DemoUserOptPower> systemUserOptPowers = new ArrayList<DemoUserOptPower>() ;
                systemUserPower.setDemoUserOptPowers(systemUserOptPowers) ;
                for (UserOptPower userOptPower : userPower.getUserOptPowers()) {
                    DemoUserOptPower systemUserOptPower = new DemoUserOptPower() ;
                    systemUserOptPower.setUuid(SQLUilts.getIUniqueId()) ;
                    systemUserOptPower.setIsUse(userOptPower.getIsUse()) ;
                    systemUserOptPower.setPowerCode(userOptPower.getPowerCode()) ;
                    systemUserOptPower.setPowerName(userOptPower.getPowerName()) ;
                    systemUserOptPowers.add(systemUserOptPower) ;
                }
            }

            ////////////////////////////////////
            demouser.setSystemUserPowers(systemUserPowers) ;

            this.result = service.saveUpdate(optType, demouser) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String remove() throws Exception {
        try {
            service.remove(optType, demouser) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String get() throws Exception {
        try {
            this.result = service.get(uuid) ;
            writeObjectService.intToPrpertiesUnits(this.result) ;

        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String list() throws Exception {
        try {
            SelectPage<DemoUser> selectPage = service.search(optType, searchBean, commSearchBean, start, limit) ;
            writeObjectService.intToPrpertiesUnits(selectPage) ;
            this.setSelectPage(selectPage) ;
        } catch (Exception e) {
            this.success = false ;
            this.msg = handError(e) ;
        }

        return SUCCESS ;
    }

    public String getPower() {
        return power ;
    }

    public void setPower(String power) {
        this.power = power ;
    }

    public DemoUser getDemouser() {
        return demouser ;
    }

    public void setDemouser(DemoUser demouser) {
        this.demouser = demouser ;
    }

}
