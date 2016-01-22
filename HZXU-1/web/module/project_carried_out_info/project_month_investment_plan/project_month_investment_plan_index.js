function create_project_month_investment_plan_window(moduleId, moduleName, params) {

	var yearGrid = params.mainGrid;

	var mainGridModule = new mainGridWindow({
		moduleId : moduleId,
		height : 200,
		// list grid
		url : "./list_ProjectMonthInvestmentPlan_list.do",
		// grid_column.record
		record : project_month_investment_plan_grid_column.record,
		// grid_column.column
		column : project_month_investment_plan_grid_column.column,
		tbar : {
			// plugins : new Ext.ux.ToolbarKeyMap(),
			items : [{
				// id : moduleId + '_add',
				key : "add",
				xtype : "tbbutton",
				text : "增加",
				// keyBinding : createCreateKey(),
				handler : function(bt) {
					var yearGrid_selection_rows = yearGrid.getSelectionModel().getSelections();
					if (yearGrid_selection_rows == null) {
						showErrorMsg('提示信息', '请选择年计划');
						return false;
					}

					if (yearGrid_selection_rows.length != 1) {
						showErrorMsg('提示信息', '只能选择一行数据记录');
						return false;
					}
					var YearGrid_selection_rows_selectId = yearGrid_selection_rows[0].id;

					project_month_investment_plan_create_windows(moduleId, moduleName, {
						grid : mainGridModule,
						YearGrid_selection_rows_selectId : YearGrid_selection_rows_selectId

					});
				}
			}, {
				// id : moduleId + '_edit',
				xtype : "tbbutton",
				text : "编辑",
				key : "edit",
				// keyBinding : createEditKey(),
				handler : function(bt) {
					var yearGrid_selection_rows = yearGrid.getSelectionModel().getSelections();
					if (yearGrid_selection_rows == null) {
						showErrorMsg('提示信息', '请选择年计划');
						return false;
					}

					if (yearGrid_selection_rows.length != 1) {
						showErrorMsg('提示信息', '只能选择一行数据记录');
						return false;
					}
					project_month_investment_plan_update_windows(moduleId, moduleName, {
						grid : mainGridModule
					});
				}
			}, {
				// id : moduleId + '_delete',
				xtype : "tbbutton",
				text : "删除",
				key : "delete",
				// keyBinding : createDeleteKey(),
				handler : function(bt) {
					project_month_investment_plan_delete_windows(moduleId, moduleName, {
						grid : mainGridModule

					});
				}
			}

			// {
			// // id : moduleId + '_search',
			// xtype : "tbbutton",
			// text : "查询",
			// key : "search",
			// // keyBinding : createSearchKey(),
			// handler : function() {
			// var searchWindex =
			// project_month_investment_plan_search_windows(moduleId,
			// moduleName, {
			// grid : mainGridModule,
			// searchParams : project_month_investment_plan_search_params
			// });
			// }
			// }

			]

		},
		init : {
			// 行被选择
			select : function(rowDataId, data, sm, rowIdx, r) {
				// stockSelect(data, checkButton, detailGrid);
				// detailGrid.load({
				// params : {
				// // 'searchBean.combinedProductId' : rowDataId
				// }
				// });

			},
			// 返回这一行的状态 1:OK -1 NO OK checkName:
			status : function(data) {

			}
		}
	});

	var mainGrid = mainGridModule.getGrid();

	function project_month_investment_plan_delete_windows(moduleId, moduleName, params) {
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
					url : "./simple_ProjectMonthInvestmentPlan_remove.do",
					params : {
						"projectmonthinvestmentplan.id" : selectId
					},
					// async: false, //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
					success : function(response, options) {
						mainGrid.reload();
					}
				});
			}
		});
	}

	this.getGrid = function() {
		return mainGrid;
	}

}