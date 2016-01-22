 package  cn.zy.apps.demo.service;

 
 
 import java.util.List ;

import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IProjectMajorTypeService   { 
    
            public String name="IProjectMajorTypeService";
			
			
			 /**
             *  增加或更新
             */
            public ProjectMajorType   saveUpdate(OptType  optType ,   ProjectMajorType   optProjectMajorType )throws SystemOptServiceException;
            
       	  
            public SelectPage<ProjectMajorType > search(OptType  optType ,    
				           ProjectMajorTypeSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
			public List<ProjectMajorType > searchList(OptType  optType ,    
				           ProjectMajorTypeSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
            public  ProjectMajorType    remove(OptType  optType ,  ProjectMajorType   optProjectMajorType)throws SystemOptServiceException;
            
            
           public  ProjectMajorType get(Integer id)throws SystemOptServiceException;
            
                
        
            
            
            

}
