function create_project_carried_out_info_window(moduleId, moduleName) {

	/**
	 * var checkButton = new Ext.Toolbar.Button({ // id : moduleId + '_search',
	 * xtype : "tbbutton", text : "审核", key : "check", // keyBinding :
	 * createSearchKey(), handler : function() {
	 * base_combined_product_check_windows(moduleId, moduleName, { grid :
	 * mainGridModule }); }
	 * 
	 * });
	 */

	var mainGridModule = new mainGridWindow({
		moduleId : moduleId,
		// list grid
		url : "./list_ProjectCarriedOutInfo_list.do",
		// grid_column.record
		record : project_carried_out_info_grid_column.record,
		// grid_column.column
		column : project_carried_out_info_grid_column.column,
		tbar : {
			// plugins : new Ext.ux.ToolbarKeyMap(),
			items : [{
				// id : moduleId + '_add',
				key : "add",
				xtype : "tbbutton",
				text : "增加",
				// keyBinding : createCreateKey(),
				handler : function(bt) {
					project_carried_out_info_create_windows(moduleId, moduleName, {
						grid : mainGridModule
						,

					});
				}
			}, {
				// id : moduleId + '_edit',
				xtype : "tbbutton",
				text : "编辑",
				key : "edit",
				// keyBinding : createEditKey(),
				handler : function(bt) {
					project_carried_out_info_update_windows(moduleId, moduleName, {
						grid : mainGridModule
						,
						// searchParams:test_search_params
					});
				}
			}, {
				// id : moduleId + '_delete',
				xtype : "tbbutton",
				text : "删除",
				key : "delete",
				// keyBinding : createDeleteKey(),
				handler : function(bt) {
					project_carried_out_info_delete_windows(moduleId, moduleName, {
						grid : mainGridModule
						,
					});
				}
			}, {
				// id : moduleId + '_search',
				xtype : "tbbutton",
				text : "查询",
				key : "search",
				// keyBinding : createSearchKey(),
				handler : function() {
					var searchWindex = project_carried_out_info_search_windows(moduleId, moduleName, {
						grid : mainGridModule,
						searchParams : project_carried_out_info_search_params
					});
				}
			}, {
				// id : moduleId + '_search',
				xtype : "tbbutton",
				text : "投资计划",
				key : "search",
				// keyBinding : createSearchKey(),
				handler : function() {
					var searchWindex = create_project_year_investment_plan_window("project_year_investment_plan", "投资计划", {
						projectGridModule : mainGridModule
					});
				}
			}]

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

	

	var window = new Ext.ERPWindow({
		title : moduleName,
		items : [mainGrid],// 里面所包含的组件
		// 用于权限
		grids : [mainGrid],
		moduleId : moduleId,
		listeners : {}
	});
	window.showWin();

	mainGrid.load({
		params : {
			"searchBean.status" : "有效"
		}
	});

	function project_carried_out_info_delete_windows(moduleId, moduleName, params) {
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
					url : "./simple_ProjectCarriedOutInfo_remove.do",
					params : {
						"projectcarriedoutinfo.id" : selectId
					},
					// async: false, //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
					success : function(response, options) {
						mainGrid.reload();
					}
				});
			}
		});
	}

}