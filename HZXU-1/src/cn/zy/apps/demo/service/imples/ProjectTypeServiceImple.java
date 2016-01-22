 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectTypeService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectTypeRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectTypeSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectTypeSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectTypeService.name)
public class ProjectTypeServiceImple extends  ABCommonsService  implements IProjectTypeService {

            //@Resource(name="ProjectTypeSearchUnits")
			  @Autowired
            @Qualifier("ProjectTypeSearchUnits")        
            private  ProjectTypeSearchUnits  iProjectTypeSearchUnits;
           
           //@Resource(name=" ProjectTypeSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectTypeSaveUpdateUnits")      
           private ProjectTypeSaveUpdateUnits  iProjectTypeSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectTypeRemoveUnits")
    private ProjectTypeRemoveUnits iProjectTypeRemoveUnits ;
		   
			@Override
            public ProjectType saveUpdate(OptType  optType ,   ProjectType   optProjectType )throws SystemOptServiceException{
        	     return 	 iProjectTypeSaveUpdateUnits.saveUpdate(optType, optProjectType);
        		}
            
       	   @Override
            public SelectPage<ProjectType > search(OptType  optType ,    
				   ProjectTypeSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectTypeSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectType > searchList(OptType  optType ,    
				           ProjectTypeSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectTypeSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectType   remove(OptType  optType ,   ProjectType   optProjectType)throws SystemOptServiceException{
			      return   iProjectTypeRemoveUnits.remove(optType, optProjectType);
			  }
			  
			   @Override
            public ProjectType get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectType.class) ;
            }
            
            
}
