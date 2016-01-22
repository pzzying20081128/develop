var project_year_investment_plan_grid_column = {
	record : [{
		name : 'year',
		mapping : 'year'
	}, {
		name : 'investmentPlan',
		mapping : 'investmentPlan'
	},],

	// ///////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '年',
		width : 200,
		dataIndex : 'year',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '投资计划',
		width : 500,
		dataIndex : 'investmentPlan',
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

