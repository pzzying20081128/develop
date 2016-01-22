 package  cn.zy.apps.demo.service;

 
 
 import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectOwnershipAddress ;
import cn.zy.apps.demo.units.search.bean.ProjectOwnershipAddressSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectOwnershipAddressService   { 
    
            public String name="IProjectOwnershipAddressService";
			
			
			 /**
             *  增加或更新
             */
            public ProjectOwnershipAddress   saveUpdate(OptType  optType ,   ProjectOwnershipAddress   optProjectOwnershipAddress )throws SystemOptServiceException;
            
       	  
            public SelectPage<ProjectOwnershipAddress > search(OptType  optType ,    
				           ProjectOwnershipAddressSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
			public List<ProjectOwnershipAddress > searchList(OptType  optType ,    
				           ProjectOwnershipAddressSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
            public  ProjectOwnershipAddress    remove(OptType  optType ,  ProjectOwnershipAddress   optProjectOwnershipAddress)throws SystemOptServiceException;
            
            
           public  ProjectOwnershipAddress get(Integer id)throws SystemOptServiceException;
            
                
        
            
            
            

}
