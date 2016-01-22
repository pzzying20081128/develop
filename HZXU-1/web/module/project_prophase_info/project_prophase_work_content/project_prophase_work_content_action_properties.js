var project_prophase_work_content_grid_column = {
	record : [{
		name : 'workDate',
		mapping : 'workDate'
	}, {
		name : "content",
		mapping : "content"
	}],

	// ///////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '日期',
		width : 200,
		dataIndex : 'workDate',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '工作内容',
		width : 500,
		dataIndex : 'content',
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

