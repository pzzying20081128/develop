 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProgressType ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectProgressTypeService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectProgressTypeRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectProgressTypeSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectProgressTypeSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectProgressTypeSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectProgressTypeService.name)
public class ProjectProgressTypeServiceImple extends  ABCommonsService  implements IProjectProgressTypeService {

            //@Resource(name="ProjectProgressTypeSearchUnits")
			  @Autowired
            @Qualifier("ProjectProgressTypeSearchUnits")        
            private  ProjectProgressTypeSearchUnits  iProjectProgressTypeSearchUnits;
           
           //@Resource(name=" ProjectProgressTypeSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectProgressTypeSaveUpdateUnits")      
           private ProjectProgressTypeSaveUpdateUnits  iProjectProgressTypeSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectProgressTypeRemoveUnits")
    private ProjectProgressTypeRemoveUnits iProjectProgressTypeRemoveUnits ;
		   
			@Override
            public ProjectProgressType saveUpdate(OptType  optType ,   ProjectProgressType   optProjectProgressType )throws SystemOptServiceException{
        	     return 	 iProjectProgressTypeSaveUpdateUnits.saveUpdate(optType, optProjectProgressType);
        		}
            
       	   @Override
            public SelectPage<ProjectProgressType > search(OptType  optType ,    
				   ProjectProgressTypeSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectProgressTypeSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectProgressType > searchList(OptType  optType ,    
				           ProjectProgressTypeSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectProgressTypeSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectProgressType   remove(OptType  optType ,   ProjectProgressType   optProjectProgressType)throws SystemOptServiceException{
			      return   iProjectProgressTypeRemoveUnits.remove(optType, optProjectProgressType);
			  }
			  
			   @Override
            public ProjectProgressType get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectProgressType.class) ;
            }
            
            
}
