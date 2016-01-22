function project_ownership_address_update_windows(moduleId, moduleName, params) {

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

	var project_ownership_address_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectOwnershipAddress_save.do",
		params : {
			optType : "update",
			"projectownershipaddress.id" : selectId
		},
		reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'projectownershipaddress.address',
			mapping : 'address'
		}]),
		// 字段
		field : [{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 350
				},
				items : [{
					id : 'projectownershipaddress.address',
					name : 'projectownershipaddress.address',
					fieldLabel : ' 项目归属地',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			} // 1-1 end
			]
		}]

	}

	var project_ownership_address_create_window = new project_ownership_address_save_update_form_panel_windows(project_ownership_address_params);

	project_ownership_address_create_window.load({
		url : "./simple_ProjectOwnershipAddress_get.do?uuid=" + selectId,
		success : function(result) {
			json = result.result;
		}
	});

}