var project_major_type_grid_column = {
   record : [
         			  {
                    name : 'name',
					mapping : 'name'
                },
		    ],
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	column : [
	 new Ext.grid.ERPRowNumberer(),
	   	      			  {
                    header : '项目重点分类',
		            width :   200,
		            dataIndex :'name',
		            sortable :  true,
		            renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {
		    
						   if(value==null ||  typeof(value) =='undefined' )   
  return null   
   else  
 return value ;
						 
		          }
                },
            		 	 
	
	
	]
};
////////////////////////////////////////////////////////////////////////////////////

