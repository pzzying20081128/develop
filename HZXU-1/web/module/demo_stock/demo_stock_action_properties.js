var demo_stock_grid_column = {
	record : [{
		name : 'stockUser.userName',
		mapping : 'stockUser'
	}, {
		name : 'stockUserId',
		mapping : 'stockUserId'
	}, {
		name : 'stockName',
		mapping : 'stockName'
	}],

	// ///////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '采购人',
		width : 200,
		dataIndex : 'stockUser.userName',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else if (value.userName == null || typeof ( value.userName ) == 'undefined')
				return null
			else
				return value.userName;

		}
	}, {
		header : '采购单名字',
		width : 200,
		dataIndex : 'stockName',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	},

	]
};
// //////////////////////////////////////////////////////////////////////////////////

