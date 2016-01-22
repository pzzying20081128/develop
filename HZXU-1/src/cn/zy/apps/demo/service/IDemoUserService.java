package cn.zy.apps.demo.service ;

import java.util.List ;

import cn.zy.apps.demo.pojos.DemoUser ;
import cn.zy.apps.demo.units.search.bean.DemoUserSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IDemoUserService {

    public String name = "IDemoUserService" ;

    /**
    *  增加或更新
    */
    public DemoUser saveUpdate(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException ;

    public SelectPage<DemoUser> search(OptType optType, DemoUserSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public List<DemoUser> searchList(OptType optType, DemoUserSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException ;

    public DemoUser remove(OptType optType, DemoUser optDemoUser) throws SystemOptServiceException ;

    public DemoUser get(Integer id) throws SystemOptServiceException ;
    
    public DemoUser  searchByName(String  userName)throws SystemOptServiceException;

}
