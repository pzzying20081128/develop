function create_demo_user_window(moduleId, moduleName) {

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
		url : "./list_DemoUser_list.do",
		// grid_column.record
		record : demo_user_grid_column.record,
		// grid_column.column
		column : demo_user_grid_column.column,
		tbar : {
			// plugins : new Ext.ux.ToolbarKeyMap(),
			items : [{
//				id : moduleId + '_add',
				key : "add",
				xtype : "tbbutton",
				text : "增加",
				// keyBinding : createCreateKey(),
				handler : function(bt) {
					demo_user_create_windows(moduleId, moduleName, {
						grid : mainGridModule
						,

					});
				}
			}, {
//				id : moduleId + '_edit',
				xtype : "tbbutton",
				text : "编辑",
				key : "edit",
				// keyBinding : createEditKey(),
				handler : function(bt) {
					demo_user_update_windows(moduleId, moduleName, {
						grid : mainGridModule
						,

					});
				}
			}, {
//				id : moduleId + '_delete',
				xtype : "tbbutton",
				text : "删除",
				key : "delete",
				// keyBinding : createDeleteKey(),
				handler : function(bt) {
					demo_user_delete_windows(moduleId, moduleName, {
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
					var searchWindex = demo_user_search_windows(moduleId, moduleName, {
						grid : mainGridModule,
						searchParams : demo_user_search_params
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

	// var detailModule = new create_demo_user_detail_window(moduleId +
	// "_detail", "明细", {
	// mainGrid : mainGrid
	// });
	// var detailGrid = detailModule.getGrid();
	//
	// mainGridModule.setDetailGrid(detailGrid);

	// var layout = new Ext.Panel({
	// layout : 'border',
	// width : 600,
	// height : 600,
	// minHeight : 100,
	// maxHeight : 500,
	// items : [new Ext.Panel({
	// id : "111",
	// layout : 'fit',
	// region : 'north',
	// margins : '0 0 0 0',
	// split : true,
	// height : 300,
	// items : mainGrid
	// // items : sales_order_store_out_panel_print
	// })
	//
	// , new Ext.Panel({
	// id : "222",
	// layout : 'fit',
	// region : 'center',
	// margins : '0 0 0 0',
	//
	// // height : "atuo",
	// title : '明细',
	// items : detailGrid
	//
	// })
	//
	// ]
	// });

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
			"searchBean.userKey" : "sssd"
		}
	});

	function demo_user_delete_windows(moduleId, moduleName, params) {
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
					url : "./list_DemoUser_remove.do",
					params : {
						"productBrand.id" : selectId
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
