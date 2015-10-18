var ${javaScript.module}_grid_column = {
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
		    
						  $!record.rendererValueKey ;
						 
		          }
                },
            #end
		 #end
	 
	
	
	]
};
////////////////////////////////////////////////////////////////////////////////////

