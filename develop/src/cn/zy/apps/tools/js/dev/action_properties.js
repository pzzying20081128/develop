var ${javaScript.packages}_grid_column = {
	record : [{
		name : 'id'
	}, {
		name : 'name'
	}, {
		name : 'job'
	}, {
		name : 'sex'
	}, {
		name : 'access'
	}, {
		name : 'departmentParents.name',
		mapping : "departmentParents"
	}],
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '员工名字',
		width : 140,
		dataIndex : 'name',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {
			return value;
		}
	}, {
		header : '职务',
		width : 140,
		dataIndex : 'job',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {
			return value;
		}
	}]
};
////////////////////////////////////////////////////////////////////////////////////

