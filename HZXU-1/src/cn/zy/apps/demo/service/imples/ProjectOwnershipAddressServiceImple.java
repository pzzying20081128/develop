 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectOwnershipAddressService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectOwnershipAddressRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectOwnershipAddressSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectOwnershipAddressSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectOwnershipAddressSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectOwnershipAddressService.name)
public class ProjectOwnershipAddressServiceImple extends  ABCommonsService  implements IProjectOwnershipAddressService {

            //@Resource(name="ProjectOwnershipAddressSearchUnits")
			  @Autowired
            @Qualifier("ProjectOwnershipAddressSearchUnits")        
            private  ProjectOwnershipAddressSearchUnits  iProjectOwnershipAddressSearchUnits;
           
           //@Resource(name=" ProjectOwnershipAddressSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectOwnershipAddressSaveUpdateUnits")      
           private ProjectOwnershipAddressSaveUpdateUnits  iProjectOwnershipAddressSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectOwnershipAddressRemoveUnits")
    private ProjectOwnershipAddressRemoveUnits iProjectOwnershipAddressRemoveUnits ;
		   
			@Override
            public ProjectOwnershipAddress saveUpdate(OptType  optType ,   ProjectOwnershipAddress   optProjectOwnershipAddress )throws SystemOptServiceException{
        	     return 	 iProjectOwnershipAddressSaveUpdateUnits.saveUpdate(optType, optProjectOwnershipAddress);
        		}
            
       	   @Override
            public SelectPage<ProjectOwnershipAddress > search(OptType  optType ,    
				   ProjectOwnershipAddressSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectOwnershipAddressSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectOwnershipAddress > searchList(OptType  optType ,    
				           ProjectOwnershipAddressSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectOwnershipAddressSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectOwnershipAddress   remove(OptType  optType ,   ProjectOwnershipAddress   optProjectOwnershipAddress)throws SystemOptServiceException{
			      return   iProjectOwnershipAddressRemoveUnits.remove(optType, optProjectOwnershipAddress);
			  }
			  
			   @Override
            public ProjectOwnershipAddress get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectOwnershipAddress.class) ;
            }
            
            
}
