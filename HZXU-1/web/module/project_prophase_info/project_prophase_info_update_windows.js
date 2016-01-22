function project_prophase_info_update_windows(moduleId, moduleName, params) {

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

	var responsibilityUnit =

	createERPcombo({
		id : 'projectprophaseinfo.responsibilityUnit',
		name : 'projectprophaseinfo.responsibilityUnit',
		fieldLabel : ' 责任单位',
		url : "./ResponsibilityUnit_combo.do?searchBean.status=有效",
		allowBlank : false,
		forceSelection : false
	});
	
	responsibilityUnit.load(
	{
		params:{
			"searchBean.name:":selection_rows[0].data.responsibilityUnit
		}
	}
	);

	var project_prophase_info_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectProphaseInfo_save.do",
		params : {
			optType : "update",
			"projectprophaseinfo.id" : selectId

		},
		reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'projectprophaseinfo.name',
			mapping : 'name'
		}, {
			name : 'projectprophaseinfo.responsibilityUnit',
			mapping : 'responsibilityUnit'
		}, {
			name : 'projectprophaseinfo.implementationUnit',
			mapping : 'implementationUnit'
		}, {
			name : 'projectprophaseinfo.constructionScale',
			mapping : 'constructionScale'
		}, {
			name : 'projectprophaseinfo.estimatedTotalInvestment',
			mapping : 'estimatedTotalInvestment'
		}, {
			name : 'projectprophaseinfo.projectProgress',
			mapping : 'projectProgress'
		}, {
			name : 'projectprophaseinfo.projectDate',
			mapping : 'projectDate'
		}, {
			name : 'projectprophaseinfo.text',
			mapping : 'text'
		},

		]),
		// 字段
		field : [{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 0.6,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 310
				},
				items : [{
					id : 'projectprophaseinfo.name',
					name : 'projectprophaseinfo.name',
					fieldLabel : ' 项目名称',
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
				columnWidth : 0.4,
				layout : 'form',
				defaultType : 'textfield',
				labelWidth : 70,
				baseCls : 'x-plain',
				defaults : {
					width : 140
				},
				items : [{
					id : 'projectprophaseinfo.estimatedTotalInvestment',
					name : 'projectprophaseinfo.estimatedTotalInvestment',
					fieldLabel : ' 估算总投资',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					vtype : "money",
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}]
		}, {// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 0.6,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 310
				},
				items : [responsibilityUnit
				// {
				// id : 'projectprophaseinfo.responsibilityUnit',
				// name : 'projectprophaseinfo.responsibilityUnit',
				// fieldLabel : ' 责任单位',
				// xtype : 'textfield',
				// style : NoAllowBlankStyle,
				// blankText : '不能为空！',
				// allowBlank : false,
				// listeners : {
				// 'specialkey' : function(field, e) {
				// }
				// }
				// }
				]
			}, {
				columnWidth : 0.4,
				layout : 'form',
				defaultType : 'textfield',
				labelWidth : 70,
				baseCls : 'x-plain',
				defaults : {
					width : 140
				},
				items : [{
					id : 'projectprophaseinfo.projectDate',
					name : 'projectprophaseinfo.projectDate',
					fieldLabel : ' 项目时间',
					xtype : 'datefield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					format : 'Y-m-d',
					value : new Date(),
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}

			]
		}, {// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 560
				},
				items : [{
					id : 'projectprophaseinfo.implementationUnit',
					name : 'projectprophaseinfo.implementationUnit',
					fieldLabel : ' 实施单位',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}]
		}, {// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				// defaultType : 'textarea',
				baseCls : 'x-plain',
				defaults : {
					width : 560
				},
				items : [{
					id : 'projectprophaseinfo.constructionScale',
					name : 'projectprophaseinfo.constructionScale',
					fieldLabel : ' 建设规模',
					xtype : 'textarea',
					height : 50,
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}]
		}, {// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				// defaultType : 'textarea',
				baseCls : 'x-plain',
				defaults : {
					width : 560
				},
				items : [{
					id : 'projectprophaseinfo.text',
					name : 'projectprophaseinfo.text',
					fieldLabel : ' 备注',
					xtype : 'textarea',
					height : 50,
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}]
		}]

	}

	var project_prophase_info_create_window = new project_prophase_info_save_update_form_panel_windows(project_prophase_info_params);

	project_prophase_info_create_window.load({
		url : "./simple_ProjectProphaseInfo_get.do?uuid=" + selectId,
		success : function(result) {
			json = result.result;
		}
	});

}