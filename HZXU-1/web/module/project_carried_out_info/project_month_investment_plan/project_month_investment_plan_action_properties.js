var project_month_investment_plan_grid_column = {
	record : [{
		name : 'month',
		mapping : 'month'
	}, {
		name : 'investmentPlan',
		mapping : 'investmentPlan'
	}, {
		name : 'constructionContent',
		mapping : 'constructionContent'
	}, {
		name : 'existingProblems',
		mapping : 'existingProblems'
	}, {
		name : 'imageProgress',
		mapping : 'imageProgress'
	},],

	// ///////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '月',
		width : 100,
		dataIndex : 'month',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '月投资计划',
		width : 300,
		dataIndex : 'investmentPlan',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '主要建设内容',
		width : 300,
		dataIndex : 'constructionContent',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '存在问题',
		width : 300,
		dataIndex : 'existingProblems',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '形象进度',
		width : 300,
		dataIndex : 'imageProgress',
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

