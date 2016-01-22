var project_prophase_info_grid_column = {
	record : [{
		name : 'name',
		mapping : 'name'
	}, {
		name : 'responsibilityUnit',
		mapping : 'responsibilityUnit'
	}, {
		name : 'implementationUnit',
		mapping : 'implementationUnit'
	}, {
		name : 'constructionScale',
		mapping : 'constructionScale'
	}, {
		name : 'estimatedTotalInvestment',
		mapping : 'estimatedTotalInvestment'
	}, {
		name : 'projectStauts',
		mapping : 'projectStauts'
	}, {
		name : 'projectDate',
		mapping : 'projectDate'
	}],

	// ///////////////////////////////////////////////////////////////////////////////////////////
	column : [new Ext.grid.ERPRowNumberer(), {
		header : '项目名称',
		width : 200,
		dataIndex : 'name',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	},

	{
		header : '责任单位',
		width : 200,
		dataIndex : 'responsibilityUnit',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '实施单位',
		width : 200,
		dataIndex : 'implementationUnit',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '建设规模',
		width : 200,
		dataIndex : 'constructionScale',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '估算总投资',
		width : 200,
		dataIndex : 'estimatedTotalInvestment',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目状态',
		width : 200,
		dataIndex : 'projectStauts',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目时间',
		width : 200,
		dataIndex : 'projectDate',
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

