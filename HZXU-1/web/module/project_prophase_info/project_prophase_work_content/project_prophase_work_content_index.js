function create_project_prophase_work_content_window(moduleId, moduleName, params) {

	var main_grid = params.grid.getGrid();

	var main_selection_rows = main_grid.getSelectionModel().getSelections();

	if (main_selection_rows == null) {
		showErrorMsg('提示信息', '请选择要编辑的数据记录！！');
		return false;
	}

	if (main_selection_rows.length != 1) {
		showErrorMsg('提示信息', '编辑只能选择一行数据记录！！');
		return false;
	}
	var main_selection_rows_id = main_selection_rows[0].id;

	var mainGridModule = new mainGridWindow({
		moduleId : moduleId,
		height : 400,
		// list grid
		url : "./list_ProjectProphaseWorkContent_list.do",

		// grid_column.record
		record : project_prophase_work_content_grid_column.record,
		// grid_column.column
		column : project_prophase_work_content_grid_column.column,
		tbar : {
			// plugins : new Ext.ux.ToolbarKeyMap(),
			items : [{
				// id : moduleId + '_add',
				key : "add",
				xtype : "tbbutton",
				text : "增加",
				// keyBinding : createCreateKey(),
				handler : function(bt) {
					project_prophase_work_content_create_windows(moduleId, moduleName, {
						grid : mainGridModule,
						mainSelectionRowsId : main_selection_rows_id
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
					project_prophase_work_content_update_windows(moduleId, moduleName, {
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
					project_prophase_work_content_delete_windows(moduleId, moduleName, {
						grid : mainGridModule
			
					});
				}
			}, 

			// {
			// // id : moduleId + '_search',
			// xtype : "tbbutton",
			// text : "查询",
			// key : "search",
			// // keyBinding : createSearchKey(),
			// handler : function() {
			// var searchWindex =
			// project_prophase_work_content_search_windows(moduleId,
			// moduleName, {
			// grid : mainGridModule,
			// searchParams : project_prophase_work_content_search_params
			//					});
			//				}
			//			}
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

	var window = new Ext.ERPDefaultsWindow({
		width : 900,
		// height : 400,
		// autoHeight : false,
		title : moduleName,
		items : [mainGrid],// 里面所包含的组件
		// 用于权限
		grids : [mainGrid],
		moduleId : moduleId,
		listeners : {}
	});
	window.showWin();
	
	mainGrid.load({
	    params:{
	    	"searchBean.projectProphaseInfoId":main_selection_rows_id
	    }
	});

	function project_prophase_work_content_delete_windows(moduleId, moduleName, params) {
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
					url : "./simple_ProjectProphaseWorkContent_remove.do",
					params : {
						"projectprophaseworkcontent.id" : selectId
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