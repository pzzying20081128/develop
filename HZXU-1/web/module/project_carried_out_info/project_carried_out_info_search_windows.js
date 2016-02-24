function project_carried_out_info_search_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();
	var search_params = params.searchParams;
	
	var responsibilityUnitPerson = createERPcombo({
		id : 'searchBean.responsibilityUnitPerson',
		name : 'searchBean.responsibilityUnitPerson',
		fieldLabel : ' 项目责任单位负责人',
		url : "./ProjectProperties_combo.do?selecttype=zrdwglr",
		allowBlank : true,
		disable : true,
		forceSelection : false,
		select : function(result) {
//			responsibilityUnitPhoto.setDisabled(false);
//			var json = result.record.json;
//			var photo = json.result.responsibilityUnitPhoto;
//			responsibilityUnitPhoto.setValue(photo);
		}
	});

//	responsibilityUnitPerson.setDisabled(true);

	var responsibilityUnit = createERPcombo({
		id : 'searchBean.responsibilityUnit',
		name : 'searchBean.responsibilityUnit',
		fieldLabel : ' 项目责任单位',
		url : "./ProjectProperties_combo.do?selecttype=zrdw",
		allowBlank : true,
		forceSelection : false,
		select : function(result) {
//			var record = result.record;
//			var json = record.json;
//			var responsibilityUnit = json.name;
//			responsibilityUnitPerson.load({
//				params : {
//					"zrdw" : responsibilityUnit
//				},
//				success : function() {
//					responsibilityUnitPerson.setDisabled(false);
//				}
//			});
		}
	});

	var responsibilityUnitPhoto = new Ext.form.TextField({
		id : 'searchBean.responsibilityUnitPhoto',
		name : 'searchBean.responsibilityUnitPhoto',
		fieldLabel : ' 责任单位负责人手机号',
		xtype : 'textfield',
		style : AllowBlankStyle,
		blankText : '不能为空！',
		allowBlank : true,
		listeners : {
			'specialkey' : function(field, e) {
			}
		}

	});

	responsibilityUnitPhoto.setDisabled(true);

	var fenGuanMiShuZhang = createERPcombo({
		id : 'searchBean.fenGuanMiShuZhang',
		name : 'searchBean.fenGuanMiShuZhang',
		fieldLabel : ' 分管副秘书长',
		url : "./ProjectProperties_combo.do?selecttype=msz",
		allowBlank : true,
		forceSelection : false,
		select : function(result) {
//			fenGuanMiShuZhangPhoto.setDisabled(false);
//			var json = result.record.json;
//			var photo = json.result.fenGuanMiShuZhangPhoto;
//			fenGuanMiShuZhangPhoto.setValue(photo);

		}
	});

	var fenGuanMiShuZhangPhoto = new Ext.form.TextField({
		id : 'searchBean.fenGuanMiShuZhangPhoto',
		name : 'searchBean.fenGuanMiShuZhangPhoto',
		fieldLabel : ' 分管副秘书长手机号',
		xtype : 'textfield',
		style : AllowBlankStyle,
		blankText : '不能为空！',
		allowBlank : true,
		listeners : {
			'specialkey' : function(field, e) {
			}
		}
	});

	

	var fenGuanHuShiZhang = createERPcombo({
		id : 'searchBean.fenGuanHuShiZhang',
		name : 'searchBean.fenGuanHuShiZhang',
		fieldLabel : ' 分管副市长',
		url : "./ProjectProperties_combo.do?selecttype=fsz",
		allowBlank : true,
		forceSelection : false,
		select : function(result) {
//			fenGuanHuShiZhangPhoto.setDisabled(false);
//			var json = result.record.json;
//			var photo = json.result.fenGuanHuShiZhangPhoto;
//			fenGuanHuShiZhangPhoto.setValue(photo);

		}
	});

	var fenGuanHuShiZhangPhoto = new Ext.form.TextField({
		id : 'searchBean.fenGuanHuShiZhangPhoto',
		name : 'searchBean.fenGuanHuShiZhangPhoto',
		fieldLabel : ' 分管副市长手机号',
		xtype : 'textfield',
		style : AllowBlankStyle,
		blankText : '不能为空！',
		allowBlank : true,
		listeners : {
			'specialkey' : function(field, e) {
			}
		}
	});

	
	
	
	var form_panel = new Ext.form.ERPFormPanel({
		height : 400,
		// autoHeight : false,
		labelWidth : 80,
		items : [{// 第一排
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
					style : AllowBlankStyle,
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
					style : AllowBlankStyle,
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
					style : AllowBlankStyle,
					storeData : [['是', "是"], ['否', '否']],
					// defaultValue : 1,
					allowBlank : true
				})]
			}// 1-3 en
			]
		}, {// 第二排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 858
				},
				items : [{
					id : 'searchBean.projectAddress',
					name : 'searchBean.projectAddress',
					fieldLabel : ' 项目地址',
					xtype : 'textfield',
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}// 1-2end
			]
		},
		// start
		{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [createERPcombo({
					id : 'searchBean.projectTypeId',
					name : 'searchBean.projectTypeId',
					fieldLabel : ' 项目类型',
					url : "./ProjectType_combo.do?searchBean.status=有效",
					allowBlank : true,
					forceSelection : false
				})]
			}, // 1-1 end
			{
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [createERPcombo({
					id : 'searchBean.projectOwnershipAddressId',
					name : 'searchBean.projectOwnershipAddressId',
					fieldLabel : ' 项目归属地',
					url : "./ProjectOwnershipAddress_combo.do?searchBean.status=有效",
					allowBlank : true,
					forceSelection : false
				})]
			}// 1-2end
			, {
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [

				createERPcombo({
					id : 'searchBean.projectProgressTypeId',
					name : 'searchBean.projectProgressTypeId',
					fieldLabel : ' 项目进展类型',
					url : "./ProjectProgressType_combo.do?searchBean.status=有效",
					allowBlank : true,
					forceSelection : false
				})]
			}// 1-3 en
			, {
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [createERPcombo({
					id : 'searchBean.projectMajorTypeId',
					name : 'searchBean.projectMajorTypeId',
					fieldLabel : ' 项目重点分类',
					url : "./ProjectMajorType_combo.do?searchBean.status=有效",
					allowBlank : true,
					forceSelection : false
				})]
			}]
		},

		// end

		{
			// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : .25,
				layout : 'form',
//				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [{
//					id : 'searchBean.buildStartDate',
//					name : 'searchBean.buildStartDate',
//					fieldLabel : ' 建设开始时间',
//					xtype : 'datefield',
//					style : AllowBlankStyle,
//					blankText : '不能为空！',
//					format : 'Y-m-d',
//					allowBlank : true,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
				}]
			}, // 1-1 end
			{
				columnWidth : .25,
				layout : 'form',
//				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [{
//					id : 'searchBean.buildEndDate',
//					name : 'searchBean.buildEndDate',
//					fieldLabel : ' 建设结束时间',
//					xtype : 'datefield',
//					style : AllowBlankStyle,
//					blankText : '不能为空！',
//					format : 'Y-m-d',
//					allowBlank : true,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
				}]
			}// 1-2end
			, {
				columnWidth : .25,
				layout : 'form',
//				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [{
//					id : 'searchBean.totalInvestment',
//					name : 'searchBean.totalInvestment',
//					fieldLabel : ' 总投资',
//					// xtype : 'textfield',
//					// vtype : "money",
//					xtype : 'custnumberfield',
//					style : AllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : true,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
				}]
			}// 1-3 en
			, {
				columnWidth : .25,
				layout : 'form',
				// defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [
				]
			}]
		},
		// /////////////////////////////////////////////////////////////////////
		{
			// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : .50,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 373
				},
				items : [{
					id : 'searchBean.implementationUnit',
					name : 'searchBean.implementationUnit',
					fieldLabel : ' 项目实施单位',
					xtype : 'textfield',
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}, // 1-1 end
			{
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [{
					id : 'searchBean.implementationUnitPerson',
					name : 'searchBean.implementationUnitPerson',
					fieldLabel : ' 实施负责人',
					xtype : 'textfield',
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}// 1-2end
			, {
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [{
					id : 'searchBean.implementationUnitPhoto',
					name : 'searchBean.implementationUnitPhoto',
					fieldLabel : ' 实施负责人手机号',
					// labelStyle:"font-size:10px" ,
					xtype : 'textfield',
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}// 1-3 en
			]
		},
		// /////////////////////////////////////////////////////////////////////////////////////////////////////
		{
			// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : .50,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 373
				},
				items : [responsibilityUnit]
			}, // 1-1 end
			{
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [responsibilityUnitPerson]
			}// 1-2end
			, {
				columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [responsibilityUnitPhoto]
			}// 1-3 en
			]

		},
		// ////////////////////////////////////////////////////////////////////////
		{
			// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [ // 1-1 end
			{
				columnWidth : .25,
				layout : 'form',
				// defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [fenGuanMiShuZhang]
			}// 1-2end
			, {
				columnWidth : .25,
				layout : 'form',
				// defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [fenGuanMiShuZhangPhoto]
			}// 1-3 en

			, {
				columnWidth : .25,
				layout : 'form',
				// defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [fenGuanHuShiZhang]
			}// 1-2end
			, {
				columnWidth : .25,
				layout : 'form',
				// defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [fenGuanHuShiZhangPhoto]
			}// 1-3 en

			]
		}, {// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				// defaultType : 'textarea',
				baseCls : 'x-plain',
				defaults : {
					width : 858
				},
				items : [{
					id : 'searchBean.constructionContent',
					name : 'searchBean.constructionContent',
					fieldLabel : ' 项目建设内容',
					xtype : 'textarea',
					height : 100,
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
		width : 1000,
		// height : 400,
		// autoHeight : false,

		items : [form_panel]
	});
	window.showWin();

}