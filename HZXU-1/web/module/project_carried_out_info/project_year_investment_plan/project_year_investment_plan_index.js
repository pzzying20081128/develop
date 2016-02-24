function create_project_year_investment_plan_window(moduleId, moduleName, params) {

	/**
	 * var checkButton = new Ext.Toolbar.Button({ // id : moduleId + '_search',
	 * xtype : "tbbutton", text : "审核", key : "check", // keyBinding :
	 * createSearchKey(), handler : function() {
	 * base_combined_product_check_windows(moduleId, moduleName, { grid :
	 * mainGridModule }); }
	 * 
	 * });
	 */

	var projectGridModule = params.projectGridModule;

	var projectGrid = projectGridModule.getGrid();

	var projectGrid_selection_rows = projectGrid.getSelectionModel().getSelections();
	
	var  power = projectGrid.searchPower("investment");
	
	var isUse = projectGrid.getisAdmin()==true ? 1:  (power==null ?   0:  power.isUse ) ;
	

	if (projectGrid_selection_rows == null) {
		showErrorMsg('提示信息', '请选择重点项目');
		return false;
	}

	if (projectGrid_selection_rows.length != 1) {
		showErrorMsg('提示信息', '只能选择一行数据记录');
		return false;
	}
	var projectGrid_id = projectGrid_selection_rows[0].id;

	var mainGridModule = new mainGridWindow({
		moduleId : moduleId,
		height : 100,
		// list grid
		url : "./list_ProjectYearInvestmentPlan_list.do",
		// grid_column.record
		record : project_year_investment_plan_grid_column.record,
		// grid_column.column
		column : project_year_investment_plan_grid_column.column,
		tbar : {
			// plugins : new Ext.ux.ToolbarKeyMap(),
			items : [{
				// id : moduleId + '_add',
				key : "add",
				xtype : "tbbutton",
				text : "增加",
				 hidden: isUse==1 ? false:true,
				// keyBinding : createCreateKey(),
				handler : function(bt) {
					project_year_investment_plan_create_windows(moduleId, moduleName, {
						grid : mainGridModule,
						projectGrid:{
							projectGrid_id:projectGrid_id,
							projectGrid:projectGrid
							
						}

					});
				}
			}, {
				// id : moduleId + '_edit',
				xtype : "tbbutton",
				text : "编辑",
				key : "edit",
				 hidden: isUse==1 ? false:true,
				// keyBinding : createEditKey(),
				handler : function(bt) {
					project_year_investment_plan_update_windows(moduleId, moduleName, {
						grid : mainGridModule
			
						// searchParams:test_search_params
					});
				}
			}, {
				// id : moduleId + '_delete',
				xtype : "tbbutton",
				text : "删除",
				 hidden: isUse==1 ? false:true,
				key : "delete",
				// keyBinding : createDeleteKey(),
				handler : function(bt) {
					project_year_investment_plan_delete_windows(moduleId, moduleName, {
						grid : mainGridModule
				
					});
				}
			}
//			{
//				// id : moduleId + '_search',
//				xtype : "tbbutton",
//				text : "查询",
//				key : "search",
//				// keyBinding : createSearchKey(),
//				handler : function() {
//					var searchWindex = project_year_investment_plan_search_windows(moduleId, moduleName, {
//						grid : mainGridModule,
//						searchParams : project_year_investment_plan_search_params
//					});
//				}
//			}
			]

		},
		init : {
			// 行被选择
			select : function(rowDataId, data, sm, rowIdx, r) {
				// stockSelect(data, checkButton, detailGrid);
				 monthGrid.load({
				 params : {
				  'searchBean.projectYearInvestmentPlanId' : rowDataId
				 }
				 });

			},
			// 返回这一行的状态 1:OK -1 NO OK checkName:
			status : function(data) {

			}
		}
	});

	var mainGrid = mainGridModule.getGrid();
	
	var month_grid = new create_project_month_investment_plan_window("project_month_investment_plan", "月进度", {
		mainGrid : mainGrid,
				projectGrid:{
							projectGrid_id:projectGrid_id,
							projectGrid:projectGrid
							
						},
		power:{
			isUse:isUse,
			admin:projectGrid.getisAdmin(),
			power:power
		}

	});
	
	var monthGrid  =month_grid.getGrid();

	var layout = new Ext.Panel({
		layout : 'border',
		width : 1000,
		height : 500,
		minHeight : 100,
		maxHeight : 300,
		items : [new Ext.Panel({
			layout : 'fit',
			region : 'north',
			margins : '0 0 0 0',
			split : true,
			height : 200,
			items : mainGrid
		})

		, new Ext.Panel({
			layout : 'fit',
			region : 'center',
			margins : '0 0 0 0',
			title : '月投资计划',
			items : monthGrid
		})

		]
	});
	
	var window = new Ext.ERPDefaultsWindow({
		title : moduleName,
		width : 1000,
		items : [layout],// 里面所包含的组件
		// 用于权限
		grids : [mainGrid],
		moduleId : moduleId,
		listeners : {}
	});
	window.showWin();

	mainGrid.load({
		params : {
			"searchBean.projectCarriedOutInfoId" : projectGrid_id
		}
	});

	function project_year_investment_plan_delete_windows(moduleId, moduleName, params) {
		var mainGridModule = params.grid;
		var mainGrid = mainGridModule.getGrid();
		var selection_rows = mainGrid.getSelectionModel().getSelections();

		if (selection_rows == null) {
			showErrorMsg('提示信息', '请选择要删除的数据记录！！');
			return false;
		}

		if (selection_rows.length != 1) {
			showErrorMsg('提示信息', '只能选择一行数据记录！！');
			return false;
		}
		var selectId = selection_rows[0].id;
		showMsgYN({
			msg : "是否要删除该条信息",
			yes : function(YN) {
				ERPAjaxRequest({
					url : "./simple_ProjectYearInvestmentPlan_remove.do",
					params : {
						"projectyearinvestmentplan.id" : selectId
					},
					// async: false, //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
					success : function(response, options) {
						mainGrid.reload({
							success:function(){
							month_grid.getGrid().removeAll();	
							}
						}
						);
					}
				});
			}
		});
	}

}