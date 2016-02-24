var demo_user_grid_column = {
	record : [{
		name : 'userName',
		mapping : 'userName'
	},{
		name : 'userXMing',
		mapping : 'userXMing'
	}],

	// ///////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '用户姓名',
		width : 200,
		dataIndex : 'userXMing',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	},{
		header : '登录名',
		width : 200,
		dataIndex : 'userName',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}

	]
};
// //////////////////////////////////////////////////////////////////////////////////

