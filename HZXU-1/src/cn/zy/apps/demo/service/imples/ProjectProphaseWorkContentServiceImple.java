 package  cn.zy.apps.demo.service.imples;
 

  import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.beans.factory.annotation.Qualifier ;
import org.springframework.stereotype.Component ;

import cn.zy.apps.demo.pojos.ProjectProphaseWorkContent ;
import cn.zy.apps.demo.service.ABCommonsService ;
import cn.zy.apps.demo.service.IProjectProphaseWorkContentService ;
import cn.zy.apps.demo.service.SystemOptServiceException ;
import cn.zy.apps.demo.service.units.ProjectProphaseWorkContentRemoveUnits ;
import cn.zy.apps.demo.service.units.ProjectProphaseWorkContentSaveUpdateUnits ;
import cn.zy.apps.demo.service.units.ProjectProphaseWorkContentSearchUnits ;
import cn.zy.apps.demo.units.search.bean.ProjectProphaseWorkContentSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;
 

@Component(IProjectProphaseWorkContentService.name)
public class ProjectProphaseWorkContentServiceImple extends  ABCommonsService  implements IProjectProphaseWorkContentService {

            //@Resource(name="ProjectProphaseWorkContentSearchUnits")
			  @Autowired
            @Qualifier("ProjectProphaseWorkContentSearchUnits")        
            private  ProjectProphaseWorkContentSearchUnits  iProjectProphaseWorkContentSearchUnits;
           
           //@Resource(name=" ProjectProphaseWorkContentSaveUpdateUnits")
		     @Autowired
            @Qualifier("ProjectProphaseWorkContentSaveUpdateUnits")      
           private ProjectProphaseWorkContentSaveUpdateUnits  iProjectProphaseWorkContentSaveUpdateUnits;
			
		   
		      @Autowired
    @Qualifier("ProjectProphaseWorkContentRemoveUnits")
    private ProjectProphaseWorkContentRemoveUnits iProjectProphaseWorkContentRemoveUnits ;
		   
			@Override
            public ProjectProphaseWorkContent saveUpdate(OptType  optType ,   ProjectProphaseWorkContent   optProjectProphaseWorkContent )throws SystemOptServiceException{
        	     return 	 iProjectProphaseWorkContentSaveUpdateUnits.saveUpdate(optType, optProjectProphaseWorkContent);
        		}
            
       	   @Override
            public SelectPage<ProjectProphaseWorkContent > search(OptType  optType ,    
				   ProjectProphaseWorkContentSearchBean  searchBean , CommSearchBean  commSearchBean ,int... startLimit)throws SystemOptServiceException{
				    return  iProjectProphaseWorkContentSearchUnits.search(optType, searchBean,
					commSearchBean ,startLimit );
            }
			
			 @Override
			public List<ProjectProphaseWorkContent > searchList(OptType  optType ,    
				           ProjectProphaseWorkContentSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException{
             
			  return  iProjectProphaseWorkContentSearchUnits.list(optType, searchBean,
					commSearchBean ,startLimit );
            
    }
            
			@Override
            public  ProjectProphaseWorkContent   remove(OptType  optType ,   ProjectProphaseWorkContent   optProjectProphaseWorkContent)throws SystemOptServiceException{
			      return   iProjectProphaseWorkContentRemoveUnits.remove(optType, optProjectProphaseWorkContent);
			  }
			  
			   @Override
            public ProjectProphaseWorkContent get(Integer id) throws SystemOptServiceException {
                
                return baseService.get(id, ProjectProphaseWorkContent.class) ;
            }
            
            
}
