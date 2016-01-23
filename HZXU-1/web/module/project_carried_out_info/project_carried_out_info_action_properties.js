var project_carried_out_info_grid_column = {
	record : [{
		name : 'totalInvestment',
		mapping : 'totalInvestment'
	}, {
		name : 'constructionContent',
		mapping : 'constructionContent'
	}, {
		name : 'isKaiGong',
		mapping : 'isKaiGong'
	}, {
		name : 'isProduction',
		mapping : 'isProduction'
	}, {
		name : 'projectAddress',
		mapping : 'projectAddress'
	}, {
		name : 'projectOwnershipAddress.address',
		mapping : 'projectOwnershipAddress'
	}, {
		name : 'projectOwnershipAddressId',
		mapping : 'projectOwnershipAddressId'
	}, {
		name : 'projectProgressType.typeName',
		mapping : 'projectProgressType'
	}, {
		name : 'projectProgressTypeId',
		mapping : 'projectProgressTypeId'
	}, {
		name : 'projectMajorType.name',
		mapping : 'projectMajorType'
	}, {
		name : 'projectMajorTypeId',
		mapping : 'projectMajorTypeId'
	}, {
		name : 'kaiGongDate',
		mapping : 'kaiGongDate'
	}, {
		name : 'buildStartDate',
		mapping : 'buildStartDate'
	}, {
		name : 'buildEndDate',
		mapping : 'buildEndDate'
	}, {
		name : 'implementationUnit',
		mapping : 'implementationUnit'
	}, {
		name : 'implementationUnitPerson',
		mapping : 'implementationUnitPerson'
	}, {
		name : 'implementationUnitPhoto',
		mapping : 'implementationUnitPhoto'
	}, {
		name : 'responsibilityUnit',
		mapping : 'responsibilityUnit'
	}, {
		name : 'responsibilityUnitPerson',
		mapping : 'responsibilityUnitPerson'
	}, {
		name : 'responsibilityUnitPhoto',
		mapping : 'responsibilityUnitPhoto'
	}, {
		name : 'fenGuanMiShuZhang',
		mapping : 'fenGuanMiShuZhang'
	}, {
		name : 'fenGuanMiShuZhangPhoto',
		mapping : 'fenGuanMiShuZhangPhoto'
	}, {
		name : 'fenGuanHuShiZhang',
		mapping : 'fenGuanHuShiZhang'
	}, {
		name : 'fenGuanHuShiZhangPhoto',
		mapping : 'fenGuanHuShiZhangPhoto'
	}, {
		name : 'name',
		mapping : 'name'
	}, {
		name : 'projectProgress',
		mapping : 'projectProgress'
	}, {
		name : 'status',
		mapping : 'status'
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
	}, {
		header : '估算总投资',
		width : 200,
		dataIndex : 'totalInvestment',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '是否开工',
		width : 200,
		dataIndex : 'isKaiGong',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '是否投产',
		width : 200,
		dataIndex : 'isProduction',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目地址',
		width : 200,
		dataIndex : 'projectAddress',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目归属地',
		width : 200,
		dataIndex : 'projectOwnershipAddress.address',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else if (value.address == null || typeof ( value.address ) == 'undefined')
				return null
			else
				return value.address;

		}
	},  {
		header : '项目进展类型',
		width : 200,
		dataIndex : 'projectProgressType.typeName',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else if (value.typeName == null || typeof ( value.typeName ) == 'undefined')
				return null
			else
				return value.typeName;

		}
	}, {
		header : '项目重点分类',
		width : 200,
		dataIndex : 'projectMajorType.name',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else if (value.name == null || typeof ( value.name ) == 'undefined')
				return null
			else
				return value.name;

		}
	}, {
		header : '开工时间',
		width : 200,
		dataIndex : 'kaiGongDate',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '建设开始时间',
		width : 200,
		dataIndex : 'buildStartDate',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '建设结束时间',
		width : 200,
		dataIndex : 'buildEndDate',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目实施单位',
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
		header : '项目实施负责人',
		width : 200,
		dataIndex : 'implementationUnitPerson',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目实施负责人手机号',
		width : 200,
		dataIndex : 'implementationUnitPhoto',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目责任单位',
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
		header : '项目责任单位负责人',
		width : 200,
		dataIndex : 'responsibilityUnitPerson',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目责任单位负责人手机号',
		width : 200,
		dataIndex : 'responsibilityUnitPhoto',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '分管副秘书长',
		width : 200,
		dataIndex : 'fenGuanMiShuZhang',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '项目责任单位负责人手机号',
		width : 200,
		dataIndex : 'fenGuanMiShuZhangPhoto',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '分管副市长',
		width : 200,
		dataIndex : 'fenGuanHuShiZhang',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		header : '分管副市长手机号',
		width : 200,
		dataIndex : 'fenGuanHuShiZhangPhoto',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	},{
		header : '项目建设内容',
		width : 200,
		dataIndex : 'constructionContent',
		sortable : true,
		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {

			if (value == null || typeof ( value ) == 'undefined')
				return null
			else
				return value;

		}
	}, {
		
		header : '状态',
		width : 200,
		dataIndex : 'status',
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

