function project_carried_out_info_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var responsibilityUnitPerson = createERPcombo({
		id : 'projectcarriedoutinfo.responsibilityUnitPerson',
		name : 'projectcarriedoutinfo.responsibilityUnitPerson',
		fieldLabel : ' 项目责任单位负责人',
		url : "./ProjectProperties_combo.do?selecttype=zrdwglr",
		allowBlank : false,
		disable : true,
		forceSelection : false,
		select : function(result) {
			responsibilityUnitPhoto.setDisabled(false);
			var json = result.record.json;
			var photo = json.result.responsibilityUnitPhoto;
			responsibilityUnitPhoto.setValue(photo);
		}
	});

	responsibilityUnitPerson.setDisabled(true);

	var responsibilityUnit = createERPcombo({
		id : 'projectcarriedoutinfo.responsibilityUnit',
		name : 'projectcarriedoutinfo.responsibilityUnit',
		fieldLabel : ' 项目责任单位',
		url : "./ProjectProperties_combo.do?selecttype=zrdw",
		allowBlank : false,
		forceSelection : false,
		select : function(result) {
			var record = result.record;
			var json = record.json;
			var responsibilityUnit = json.name;
			responsibilityUnitPerson.load({
				params : {
					"zrdw" : responsibilityUnit
				},
				success : function() {
					responsibilityUnitPerson.setDisabled(false);
				}
			});
		}
	});

	var responsibilityUnitPhoto = new Ext.form.TextField({
		id : 'projectcarriedoutinfo.responsibilityUnitPhoto',
		name : 'projectcarriedoutinfo.responsibilityUnitPhoto',
		fieldLabel : ' 责任单位负责人手机号',
		xtype : 'textfield',
		style : NoAllowBlankStyle,
		blankText : '不能为空！',
		allowBlank : false,
		listeners : {
			'specialkey' : function(field, e) {
			}
		}

	});

	responsibilityUnitPhoto.setDisabled(true);

	var fenGuanMiShuZhang = createERPcombo({
		id : 'projectcarriedoutinfo.fenGuanMiShuZhang',
		name : 'projectcarriedoutinfo.fenGuanMiShuZhang',
		fieldLabel : ' 分管副秘书长',
		url : "./ProjectProperties_combo.do?selecttype=msz",
		allowBlank : false,
		forceSelection : false,
		select : function(result) {
			fenGuanMiShuZhangPhoto.setDisabled(false);
			var json = result.record.json;
			var photo = json.result.fenGuanMiShuZhangPhoto;
			fenGuanMiShuZhangPhoto.setValue(photo);

		}
	});

	var fenGuanMiShuZhangPhoto = new Ext.form.TextField({
		id : 'projectcarriedoutinfo.fenGuanMiShuZhangPhoto',
		name : 'projectcarriedoutinfo.fenGuanMiShuZhangPhoto',
		fieldLabel : ' 分管副秘书长手机号',
		xtype : 'textfield',
		style : NoAllowBlankStyle,
		blankText : '不能为空！',
		allowBlank : false,
		listeners : {
			'specialkey' : function(field, e) {
			}
		}
	});

	fenGuanMiShuZhangPhoto.setDisabled(true);

	var fenGuanHuShiZhang = createERPcombo({
		id : 'projectcarriedoutinfo.fenGuanHuShiZhang',
		name : 'projectcarriedoutinfo.fenGuanHuShiZhang',
		fieldLabel : ' 分管副市长',
		url : "./ProjectProperties_combo.do?selecttype=fsz",
		allowBlank : false,
		forceSelection : false,
		select : function(result) {
			fenGuanHuShiZhangPhoto.setDisabled(false);
			var json = result.record.json;
			var photo = json.result.fenGuanHuShiZhangPhoto;
			fenGuanHuShiZhangPhoto.setValue(photo);

		}
	});

	var fenGuanHuShiZhangPhoto = new Ext.form.TextField({
		id : 'projectcarriedoutinfo.fenGuanHuShiZhangPhoto',
		name : 'projectcarriedoutinfo.fenGuanHuShiZhangPhoto',
		fieldLabel : ' 分管副市长手机号',
		xtype : 'textfield',
		style : NoAllowBlankStyle,
		blankText : '不能为空！',
		allowBlank : false,
		listeners : {
			'specialkey' : function(field, e) {
			}
		}
	});

	fenGuanHuShiZhangPhoto.setDisabled(true);

	function reset() {
		responsibilityUnitPhoto.setValue(null);
		responsibilityUnitPerson.clear();
		responsibilityUnitPhoto.setDisabled(true);
		responsibilityUnitPerson.setDisabled(true);

		fenGuanHuShiZhangPhoto.setValue(null);
		fenGuanHuShiZhang.clear();
		fenGuanHuShiZhangPhoto.setDisabled(true);

		fenGuanMiShuZhangPhoto.setValue(null);
		fenGuanMiShuZhang.clear();
		fenGuanMiShuZhangPhoto.setDisabled(true);

	}

	function v() {
		if (responsibilityUnit.getValue() != null || responsibilityUnit.getValue() == "") {
			if (responsibilityUnitPerson.getValue() == null || responsibilityUnitPerson.getValue() == "" || responsibilityUnitPhoto.getValue() == null || responsibilityUnitPhoto.getValue() == "") {
				showErrorMsg("错误", '项目责任单位的负责人及手机号不能为空');
				return false;
			}

		}

		if (fenGuanMiShuZhang.getValue() != null || fenGuanMiShuZhang.getValue() == "") {
			if (fenGuanMiShuZhangPhoto.getValue() == null || fenGuanMiShuZhangPhoto.getValue() == "") {
				showErrorMsg("错误", '分管副秘书长手机号不能为空');
				return false;
			}

		}
		if (fenGuanHuShiZhang.getValue() != null || fenGuanHuShiZhang.getValue() == "") {
			if (fenGuanHuShiZhangPhoto.getValue() == null || fenGuanHuShiZhangPhoto.getValue() == "") {
				showErrorMsg("错误", '分管副市长手机号不能为空');
				return false;
			}

		}
		return true;
	}

	var project_carried_out_info_params = {
		verification : v,
		reset : reset,
		title : "新增" + moduleName,
		moduleId : moduleId,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectCarriedOutInfo_save.do",
		params : {
			optType : "save"
		},
		// 字段
		field : [{// 第一排
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
					id : 'projectcarriedoutinfo.name',
					name : 'projectcarriedoutinfo.name',
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
					id : 'projectcarriedoutinfo.isKaiGong',
					name : 'projectcarriedoutinfo.isKaiGong',
					fieldLabel : ' 是否开工',
					style : NoAllowBlankStyle,
					storeData : [['是', "是"], ['否', '否']],
					// defaultValue : 1,
					allowBlank : false
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
					id : 'projectcarriedoutinfo.isProduction',
					name : 'projectcarriedoutinfo.isProduction',
					fieldLabel : ' 是否投产',
					style : NoAllowBlankStyle,
					storeData : [['是', "是"], ['否', '否']],
					// defaultValue : 1,
					allowBlank : false
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
					id : 'projectcarriedoutinfo.projectAddress',
					name : 'projectcarriedoutinfo.projectAddress',
					fieldLabel : ' 项目地址',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
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
				items : [{
					id : 'projectcarriedoutinfo.kaiGongDate',
					name : 'projectcarriedoutinfo.kaiGongDate',
					fieldLabel : ' 开工时间',
					xtype : 'datefield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					format : 'Y-m-d',
					// value : new Date(),
					allowBlank : false,
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
				items : [createERPcombo({
					id : 'projectcarriedoutinfo.projectOwnershipAddressId',
					name : 'projectcarriedoutinfo.projectOwnershipAddressId',
					fieldLabel : ' 项目归属地',
					url : "./ProjectOwnershipAddress_combo.do?searchBean.status=有效",
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.projectProgressTypeId',
					name : 'projectcarriedoutinfo.projectProgressTypeId',
					fieldLabel : ' 项目进展类型',
					url : "./ProjectProgressType_combo.do?searchBean.status=有效",
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.projectMajorTypeId',
					name : 'projectcarriedoutinfo.projectMajorTypeId',
					fieldLabel : ' 项目重点分类',
					url : "./ProjectMajorType_combo.do?searchBean.status=有效",
					allowBlank : false,
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
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 130
				},
				items : [{
					id : 'projectcarriedoutinfo.buildStartDate',
					name : 'projectcarriedoutinfo.buildStartDate',
					fieldLabel : ' 建设开始时间',
					xtype : 'datefield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					format : 'Y-m-d',
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.buildEndDate',
					name : 'projectcarriedoutinfo.buildEndDate',
					fieldLabel : ' 建设结束时间',
					xtype : 'datefield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					format : 'Y-m-d',
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.totalInvestment',
					name : 'projectcarriedoutinfo.totalInvestment',
					fieldLabel : ' 总投资',
					// xtype : 'textfield',
					// vtype : "money",
					xtype : 'custnumberfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
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
				items : [createERPcombo({
					id : 'projectcarriedoutinfo.projectTypeId',
					name : 'projectcarriedoutinfo.projectTypeId',
					fieldLabel : ' 项目类型',
					url : "./ProjectType_combo.do?searchBean.status=有效",
					allowBlank : false,
					forceSelection : false
				})]
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
					id : 'projectcarriedoutinfo.implementationUnit',
					name : 'projectcarriedoutinfo.implementationUnit',
					fieldLabel : ' 项目实施单位',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.implementationUnitPerson',
					name : 'projectcarriedoutinfo.implementationUnitPerson',
					fieldLabel : ' 实施负责人',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.implementationUnitPhoto',
					name : 'projectcarriedoutinfo.implementationUnitPhoto',
					fieldLabel : ' 实施负责人手机号',
					// labelStyle:"font-size:10px" ,
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
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
					id : 'projectcarriedoutinfo.constructionContent',
					name : 'projectcarriedoutinfo.constructionContent',
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
		}]
	}

	var project_carried_out_info_create_window = new project_carried_out_info_save_update_form_panel_windows(project_carried_out_info_params);

}