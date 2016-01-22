function demo_stock_update_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var selection_rows = grid.getSelectionModel().getSelections();

	if (selection_rows == null) {
		showErrorMsg('提示信息', '请选择要编辑的数据记录！！');
		return false;
	}

	if (selection_rows.length != 1) {
		showErrorMsg('提示信息', '编辑只能选择一行数据记录！！');
		return false;
	}
	var selectId = selection_rows[0].id;

	var stockUserId = createERPcombo({
		id : 'demostock.stockUserId',
		name : 'demostock.stockUserId',
		fieldLabel : ' 采购人',
		url : "./DemoUser_combo.do?searchBean.status=全部",
		allowBlank : false,
		forceSelection : false
	});
	stockUserId.load({
		params : {
			"searchBean.id" : selection_rows[0].data.stockUserId
		}
	});

	var demo_stock_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_DemoStock_save.do",
		params : {
			optType : "update",
			"demostock.id":selectId
		},
		reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'demostock.stockUser',
			mapping : 'stockUser'
		}, {
			name : 'demostock.stockUserId',
			mapping : 'stockUserId'
		},{
			name : 'demostock.stockName',
			mapping : 'stockName'
		},
		
		]),
		// 字段
		field : [{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : .5,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 250
				},
				items : [{
					id : 'demostock.stockName',
					name : 'demostock.stockName',
					fieldLabel : ' 采购单',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}, {
				columnWidth : .5,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 250
				},
				items : [stockUserId]

			}// 1-2end
			, {

			}// 1-3 end
			, {

			}// 1-3 end
			]
		}]

	}



var demo_stock_create_window = new demo_stock_save_update_form_panel_windows(demo_stock_params);

demo_stock_create_window.load({
	url : "./simple_DemoStock_get.do?uuid=" + selectId,
	success : function(result) {
		json = result.result;
	}
});
}