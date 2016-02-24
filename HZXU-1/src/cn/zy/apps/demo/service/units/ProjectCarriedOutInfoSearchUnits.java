package cn.zy.apps.demo.service.units ;

import java.util.List ;
import java.util.Map ;

import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectCarriedOutInfo ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.units.search.bean.ProjectCarriedOutInfoSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

@Component("ProjectCarriedOutInfoSearchUnits")
public class ProjectCarriedOutInfoSearchUnits extends ABCommonsService {

    public SelectPage<ProjectCarriedOutInfo> search(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        SelectPage<ProjectCarriedOutInfo> selectPage = new SelectPage<ProjectCarriedOutInfo>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectCarriedOutInfo> result = list(sqlWhere, value, startLimit) ;

        Long sum = sum(sqlWhere, value) ;

        selectPage.setCount(sum) ;

        selectPage.setResult(result) ;

        return selectPage ;

    }
    
    public List<ProjectCarriedOutInfo> searchCombo(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectCarriedOutInfo> result = combo(sqlWhere, value, startLimit) ;

        return result ;
    }
    
    

    

    public List<ProjectCarriedOutInfo> list(OptType optType, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean, int... startLimit) throws SystemOptServiceException {
        //SelectPage<ProjectCarriedOutInfo> selectPage = new SelectPage<ProjectCarriedOutInfo>() ;

        Map<String, Object> value = ToolsUnits.createSearchMap() ;

        String sqlWhere = createWhere(value, searchBean, commSearchBean) ;

        List<ProjectCarriedOutInfo> result = list(sqlWhere, value, startLimit) ;

        return result ;

    }

    private String sql = "select  projectCarriedOutInfo   from  ProjectCarriedOutInfo  as  projectCarriedOutInfo   " ;

    private List<ProjectCarriedOutInfo> list(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = sql + sqlWhere ;
        List<ProjectCarriedOutInfo> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }
    
    
    
    private String combo = "select  distinct   new ProjectCarriedOutInfo( projectCarriedOutInfo.responsibilityUnit,   " +
    		
            "  projectCarriedOutInfo.responsibilityUnitPerson,  " +
            
            " projectCarriedOutInfo.responsibilityUnitPhoto,  " +
    
            " projectCarriedOutInfo.fenGuanMiShuZhang,   " +
            
            " projectCarriedOutInfo.fenGuanMiShuZhangPhoto, " +
            
            " projectCarriedOutInfo.fenGuanHuShiZhang, " +
            
            "projectCarriedOutInfo.fenGuanHuShiZhangPhoto )  " +
            
            " from  ProjectCarriedOutInfo  as  projectCarriedOutInfo   " ;
    
    private List<ProjectCarriedOutInfo> combo(String sqlWhere, Map<String, Object> value, int... startLimit) throws SystemOptServiceException {
        String sql_ = combo + sqlWhere ;
        List<ProjectCarriedOutInfo> result = baseService.findByHSQL(sql_, value, startLimit) ;
        return result ;
    }

    private String sqlsum = "select  count(projectCarriedOutInfo.id)   from  ProjectCarriedOutInfo  as  projectCarriedOutInfo  " ;

    private Long sum(String sqlWhere, Map<String, Object> value) throws SystemOptServiceException {
        String sql_ = sqlsum + sqlWhere ;
        Long sum = baseService.findSinglenessByHSQL(sql_, value) ;
        return sum ;
    }

    private String createWhere(Map<String, Object> value, ProjectCarriedOutInfoSearchBean searchBean, CommSearchBean commSearchBean) {
        String sqlWhere = "where   1=1   " ;
        if (searchBean.getStatus() != null) {
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.status =:status  " ;
            value.put("status", searchBean.getStatus()) ;
        }

        if (searchBean.getName() != null) {
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.name like :getName  " ;
            value.put("getName", "%" + searchBean.getName() + "%") ;
        }

        if (searchBean.getIsKaiGong() != null) {
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.isKaiGong like :isKaiGong  " ;
            value.put("isKaiGong", "%" + searchBean.getIsKaiGong() + "%") ;
        }

        if (searchBean.getIsProduction() != null) {
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.isProduction like :isProduction  " ;
            value.put("isProduction", "%" + searchBean.getIsProduction() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getResponsibilityUnit() )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.responsibilityUnit like :responsibilityUnit  " ;
            value.put("responsibilityUnit", "%" + searchBean.getResponsibilityUnit() + "%") ;
        }
        if(ToolsUnits.isNOtNulll(  searchBean.getResponsibilityUnitPerson() )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.responsibilityUnitPerson like :responsibilityUnitPerson  " ;
            value.put("responsibilityUnitPerson", "%" + searchBean.getResponsibilityUnitPerson() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getResponsibilityUnitPhoto() )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.responsibilityUnitPhoto like :responsibilityUnitPhoto  " ;
            value.put("responsibilityUnitPhoto", "%" + searchBean.getResponsibilityUnitPhoto() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getFenGuanHuShiZhang())){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.fenGuanHuShiZhang like :fenGuanHuShiZhang  " ;
            value.put("fenGuanHuShiZhang", "%" + searchBean.getFenGuanHuShiZhang() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getFenGuanHuShiZhangPhoto())){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.fenGuanHuShiZhangPhoto like :fenGuanHuShiZhangPhoto  " ;
            value.put("fenGuanHuShiZhangPhoto", "%" + searchBean.getFenGuanHuShiZhangPhoto() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getFenGuanMiShuZhang())){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.fenGuanMiShuZhang like :fenGuanMiShuZhang  " ;
            value.put("fenGuanMiShuZhang", "%" + searchBean.getFenGuanMiShuZhang() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getFenGuanMiShuZhangPhoto())){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.fenGuanHuShiZhangPhoto like :fenGuanMiShuZhangPhoto  " ;
            value.put("fenGuanMiShuZhangPhoto", "%" + searchBean.getFenGuanMiShuZhangPhoto() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getName())){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.name like :name  " ;
            value.put("name", "%" + searchBean.getName() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getIsKaiGong()  )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.isKaiGong like :isKaiGong  " ;
            value.put("isKaiGong", "%" + searchBean.getIsKaiGong() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getIsProduction() )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.isProduction like :isProduction  " ;
            value.put("isProduction", "%" + searchBean.getIsProduction() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getProjectAddress())){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectAddress like :projectAddress  " ;
            value.put("projectAddress", "%" + searchBean.getProjectAddress() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getImplementationUnit()  )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.implementationUnit like :implementationUnit  " ;
            value.put("implementationUnit", "%" + searchBean.getImplementationUnit() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getImplementationUnitPerson()  )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.implementationUnitPerson like :implementationUnitPerson  " ;
            value.put("implementationUnitPerson", "%" + searchBean.getImplementationUnitPerson() + "%") ;
        }
        
        if(ToolsUnits.isNOtNulll(  searchBean.getImplementationUnitPhoto()  )){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.implementationUnitPhoto like :getImplementationUnitPhoto  " ;
            value.put("getImplementationUnitPhoto", "%" + searchBean.getImplementationUnitPhoto() + "%") ;
        }
        
        if(searchBean.getProjectTypeId() !=  null){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectTypeId =: projectTypeId  " ;
            value.put("projectTypeId", searchBean.getProjectTypeId()) ;
        }
        
        if(searchBean.getProjectOwnershipAddressId() !=  null){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectOwnershipAddressId =: getProjectOwnershipAddressId  " ;
            value.put("getProjectOwnershipAddressId", searchBean.getProjectOwnershipAddressId()) ;
        }
        
        if(searchBean.getProjectOwnershipAddressId() !=  null){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectOwnershipAddressId =: getProjectOwnershipAddressId  " ;
            value.put("getProjectOwnershipAddressId", searchBean.getProjectOwnershipAddressId()) ;
        }
        
        if(searchBean.getProjectProgressTypeId() !=  null){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectProgressTypeId =: projectProgressTypeId  " ;
            value.put("projectProgressTypeId", searchBean.getProjectProgressTypeId()) ;
        }
        
        if(searchBean.getProjectProgressTypeId() !=  null){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectProgressTypeId =: projectProgressTypeId  " ;
            value.put("projectProgressTypeId", searchBean.getProjectProgressTypeId()) ;
        }
        
        if(searchBean.getProjectMajorTypeId() !=  null){
            sqlWhere = sqlWhere + "   and  projectCarriedOutInfo.projectMajorTypeId =: projectMajorTypeId  " ;
            value.put("projectMajorTypeId", searchBean.getProjectMajorTypeId()) ;
        }
        
        
        

        return sqlWhere ;
    }


}
