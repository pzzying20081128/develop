 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectMajorType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectMajorTypeService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectMajorTypeRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectMajorTypeSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectMajorTypeSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectMajorTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectMajorTypeService.name)
public class ProjectMajorTypeServiceImple extends  ABCommonsService  implements IProjectMajorTypeService {

            //@Resource(name="ProjectMajorTypeSearchUnits")
			  @Autowired
            @Qualifier("ProjectMajorTypeSearchUnits")        
            private  ProjectMajorTypeSearchUnits  iProjectMajorTypeSearchUnits;
           
           //@Resource(name=" ProjectMajorTypeSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectMajorTypeSaveUpdateUnits")      
           private ProjectMajorTypeSaveUpdateUnits  iProjectMajorTypeSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectMajorTypeRemoveUnits")
    private ProjectMajorTypeRemoveUnits iProjectMajorTypeRemoveUnits ;
		   
			@Override
            public ProjectMajorType saveUpdate(OptType  optType ,   ProjectMajorType   optProjectMajorType )throws SystemOptServiceException{
        	     return 	 iProjectMajorTypeSaveUpdateUnits.saveUpdate(optType, optProjectMajorType);
        		}
            
       	   @Override
            public SelectPage<ProjectMajorType > search(OptType  optType ,    
				   ProjectMajorTypeSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectMajorTypeSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectMajorType > searchList(OptType  optType ,    
				           ProjectMajorTypeSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectMajorTypeSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectMajorType   remove(OptType  optType ,   ProjectMajorType   optProjectMajorType)throws SystemOptServiceException{
			      return   iProjectMajorTypeRemoveUnits.remove(optType, optProjectMajorType);
			  }
			  
			   @Override
            public ProjectMajorType get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectMajorType.class) ;
            }
            
            
}
