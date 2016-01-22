function project_carried_out_info_search_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();
	var search_params = params.searchParams;
	var form_panel = new Ext.form.ERPFormPanel({
		height : 400,
		// autoHeight : false,
		labelWidth : 60,
		items : [{
			// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : .6,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 450
				},
				items : [{
					id : 'searchBean.name',
					name : 'searchBean.name',
					fieldLabel : ' 项目名称',
					xtype : 'textfield',
					style :AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}, // 1-1 end
			{
				columnWidth : .20,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 80
				},
				items : [createLocalCombo({
					id : 'searchBean.isKaiGong',
					name : 'searchBean.isKaiGong',
					fieldLabel : ' 是否开工',
					style : NoAllowBlankStyle,
					storeData : [['是', "是"], ['否', '否']],
					// defaultValue : 1,
					allowBlank : true
				})]
			}// 1-2end
			, {
				columnWidth : .20,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 80
				},
				items : [createLocalCombo({
					id : 'searchBean.isProduction',
					name : 'searchBean.isProduction',
					fieldLabel : ' 是否投产',
					style : NoAllowBlankStyle,
					storeData : [['是', "是"], ['否', '否']],
					// defaultValue : 1,
					allowBlank : true
				})]
			}// 1-3 en
			]

		}],
		buttons : [{
			text : '提交',
			listeners : {
				'click' : function() {
					from = form_panel.getForm();
					if (!search_params.verification(from))
						return;
					grid.removeAll();
					if (from.isValid()) {
						var submitValues1 = from.getValues();
						// 对将要提交的参数进行过滤，去掉emptyText文字
						for (var param in submitValues1) {
							if (from.findField(param) && from.findField(param).emptyText == submitValues1[param]) {
								submitValues1[param] = '';
								// thisForm.findField(param).setValue("");
							}
						}

						Ext.apply(submitValues1, search_params.params());
						grid.load({
							params : submitValues1,
							success : function() {
								window.close();
							}
						});
						window.close();
					} else {
						showErrorMsg("错误", "请检查查询数据的正确性");
					}
				}
			}

		}, {
			text : '关闭',
			listeners : {
				'click' : function() {
					window.close();
				}
			}

		}]
	});

	var window = new Ext.ERPDefaultsWindow({
		title : "查询",
		closable : true,
		width : 1000,
		// height : 400,
		// autoHeight : false,

		items : [form_panel]
	});
	window.showWin();

}