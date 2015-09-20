var ${javaScript.module}_grid_column = {
//	record : [{
//		name : 'id'
//	}, {
//		name : 'name'
//	}, {
//		name : 'job'
//	}, {
//		name : 'sex'
//	}, {
//		name : 'access'
//	}, {
//		name : 'departmentParents.name',
//		mapping : "departmentParents"
//	}],
	
   record : [
         #foreach($record in $records)
			  {
                    name : '$record.name',
					mapping : '$record.mapping'
                },
		 #end
   ],
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	column : [
	 new Ext.grid.ERPRowNumberer(),
	   #foreach($record in $records)
	      #if($$record.canShow)
			  {
                    header : '$record.showName',
		            width :   $record.showWidth,
		            dataIndex :'$record.name',
		            sortable :  $record.canSort,
		            renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {
		            	if(value==null ){
		            		return value ;
		            	}
		            	 else
		            	 {
		            	     $!record.rendererValueKey;
		            	 }
		          }
                },
            #end
		 #end
	 
	
	
	]
};
////////////////////////////////////////////////////////////////////////////////////

