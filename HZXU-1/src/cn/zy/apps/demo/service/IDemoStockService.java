 package  cn.zy.apps.demo.service;

 
 
 import java.util.List ;

import cn.zy.apps.demo.pojos.DemoStock ;
import cn.zy.apps.demo.units.search.bean.DemoStockSearchBean ;
import cn.zy.apps.tools.units.CommSearchBean ;
import cn.zy.apps.tools.units.ToolsUnits.OptType ;
import cn.zy.apps.tools.web.SelectPage ;

public interface IDemoStockService   { 
    
            public String name="IDemoStockService";
			
			
			 /**
             *  增加或更新
             */
            public DemoStock   saveUpdate(OptType  optType ,   DemoStock   optDemoStock )throws SystemOptServiceException;
            
       	  
            public SelectPage<DemoStock > search(OptType  optType ,    
				           DemoStockSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
			public List<DemoStock > searchList(OptType  optType ,    
				           DemoStockSearchBean  searchBean,CommSearchBean  commSearchBean ,int... startLimit )throws SystemOptServiceException;
            
            public  DemoStock    remove(OptType  optType ,  DemoStock   optDemoStock)throws SystemOptServiceException;
            
            
           public  DemoStock get(Integer id)throws SystemOptServiceException;
            
                
        
            
            
            

}
