function project_prophase_info_search_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();
	var search_params = params.searchParams;
	var form_panel = new Ext.form.ERPFormPanel({
		height : 400,
		// autoHeight : false,
		labelWidth : 60,
		items : [{// 第一排
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
					id : 'searchBean.name',
					name : 'searchBean.name',
					fieldLabel : ' 项目名称',
					xtype : 'textfield',
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
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
					id : 'searchBean.estimatedTotalInvestment',
					name : 'searchBean.estimatedTotalInvestment',
					fieldLabel : ' 估算总投资',
					xtype : 'textfield',
					style : AllowBlankStyle,
					vtype : "money",
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
				columnWidth : 0.6,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 310
				},
				items : [createERPcombo({
					id : 'searchBean.responsibilityUnit',
					name : 'searchBean.responsibilityUnit',
					fieldLabel : ' 责任单位',
					url : "./ResponsibilityUnit_combo.do?searchBean.status=有效",
					allowBlank : true,
					forceSelection : false
				})
				// {
				// id : 'searchBean.responsibilityUnit',
				// name : 'searchBean.responsibilityUnit',
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
					id : 'searchBean.projectDate',
					name : 'searchBean.projectDate',
					fieldLabel : ' 项目时间',
					xtype : 'datefield',
					style : AllowBlankStyle,
					blankText : '不能为空！',
					format : 'Y-m-d',
//					value : new Date(),
					allowBlank : true,
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
					id : 'searchBean.implementationUnit',
					name : 'searchBean.implementationUnit',
					fieldLabel : ' 实施单位',
					xtype : 'textfield',
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
					id : 'searchBean.constructionScale',
					name : 'searchBean.constructionScale',
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
					id : 'searchBean.text',
					name : 'searchBean.text',
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
		width : 700,
		// height : 400,
		// autoHeight : false,

		items : [form_panel]
	});
	window.showWin();

}