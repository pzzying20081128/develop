function project_type_update_windows(moduleId, moduleName, params) {

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
	

	var project_type_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
			url : "./simple_ProjectType_save.do",
		params : {
			optType : "update"
		},
	   reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'dept.name',
			mapping : 'name'
		}]),
		// 字段
		field : [
		{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{// 1-1
				columnWidth : .34,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 200
				},
				items : [{
					id : 'goods.name',
					name : 'goods.name',
					fieldLabel : ' 物料名字',
					xtype : 'textfield',
					style : 'background:#fff1a4;',
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}, // 1-1 end
			{// 1-2
				columnWidth : .33,
				layout : 'form',
				baseCls : 'x-plain',
				defaultType : 'textfield',
				defaults : {
					width : 200
				},
				items : [createERPcombo({
					id : 'goods.classification',
					name : 'goods.classification',
					label : "物料类别",
					url : "./searchMatrialClassification.action",
					allowBlank : false,
					forceSelection : false
					// width : 150
				})]
			}// 1-2end
			, {// 1-3
				columnWidth : .33,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 200
				},
				items : [{

					id : 'goods.serialNumber',
					name : 'goods.serialNumber',
					fieldLabel : ' 物料编号',
					xtype : 'textfield',
					style : 'background:#fff1a4;',
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}

				}]
			}// 1-3 end
			]

		}
		]
		
	}

	var project_type_create_window = new project_type_save_update_form_panel_windows(project_type_params);
	
		project_type_create_window.load({
			url : "./simple_ProjectType_get.do?uuid=" + selectId,
		success : function(result) {
		               json = result.result;
		}
	});

}