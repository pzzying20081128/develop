function project_prophase_work_content_update_windows(moduleId, moduleName, params) {

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

	var project_prophase_work_content_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectProphaseWorkContent_save.do",
		params : {
			optType : "update",
			"projectprophaseworkcontent.id":selectId
		},
		reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'projectprophaseworkcontent.workDate',
			mapping : 'workDate'
		}, {
			name : 'projectprophaseworkcontent.content',
			mapping : 'content'
		}]),
		// 字段
		field : [{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{// 第一排
				layout : 'column',
				baseCls : 'x-plain',
				items : [{
					columnWidth : 1,
					layout : 'form',
					defaultType : 'textfield',
					baseCls : 'x-plain',
					defaults : {
						width : 450
					},
					items : [{
						id : 'projectprophaseworkcontent.workDate',
						name : 'projectprophaseworkcontent.workDate',
						fieldLabel : ' 日期',
						xtype : 'datefield',
						format : 'Y-m-d',
						value : new Date(),
						style : NoAllowBlankStyle,
						blankText : '不能为空！',
						allowBlank : false,
						listeners : {
							'specialkey' : function(field, e) {
							}
						}
					}]
				}]
			}, // 1-1 end
			]
		}, {// 第二排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{// 第一排
				layout : 'column',
				baseCls : 'x-plain',
				items : [{
					columnWidth : 1,
					layout : 'form',
					// defaultType : 'textarea',
					baseCls : 'x-plain',
					defaults : {
						width : 450
					},
					items : [{
						id : 'projectprophaseworkcontent.content',
						name : 'projectprophaseworkcontent.content',
						fieldLabel : ' 工作内容',
						xtype : 'textarea',
						height : 80,
						style : NoAllowBlankStyle,
						blankText : '不能为空！',
						allowBlank : false,
						listeners : {
							'specialkey' : function(field, e) {
							}
						}
					}]
				}]
			}, // 1-1 end

			]
		}]

	}

	var project_prophase_work_content_create_window = new project_prophase_work_content_save_update_form_panel_windows(project_prophase_work_content_params);

	project_prophase_work_content_create_window.load({
		url : "./simple_ProjectProphaseWorkContent_get.do?uuid=" + selectId,
		success : function(result) {
			json = result.result;
		}
	});

}