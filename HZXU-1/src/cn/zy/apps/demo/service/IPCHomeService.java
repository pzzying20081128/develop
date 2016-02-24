package cn.zy.apps.demo.service;

import java.util.List ;

import cn.zy.apps.demo.HZXUProjectConfig.Complete ;
import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.units.beans.PCHomeSumBean ;

public interface IPCHomeService {
    
    public String name="IPCHomeService";
    
    public PCHomeSumBean  searchKeyStatisticsSum()throws SystemOptServiceException; 
    
    
    public List<ProjectCarriedOutInfo> listYearMonthProjectCarriedOutInfo(Complete monthComplete) throws SystemOptServiceException;

}
