function project_year_investment_plan_update_windows(moduleId, moduleName, params) {

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

	var project_year_investment_plan_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectYearInvestmentPlan_save.do",
		params : {
			optType : "update",
			"projectyearinvestmentplan.id":selectId
		},
		reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'projectyearinvestmentplan.year',
			mapping : 'year'
		}, {
			name : 'projectyearinvestmentplan.investmentPlan',
			mapping : 'investmentPlan'
		},]),
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
					width : 200
				},
				items : [{
					id : 'projectyearinvestmentplan.year',
					name : 'projectyearinvestmentplan.year',
					fieldLabel : ' 年',
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
					width : 500
				},
				items : [{
					id : 'projectyearinvestmentplan.investmentPlan',
					name : 'projectyearinvestmentplan.investmentPlan',
					fieldLabel : ' 投资计划',
					xtype : 'textarea',
					height : 100,
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}]
		}]

	}

	var project_year_investment_plan_create_window = new project_year_investment_plan_save_update_form_panel_windows(project_year_investment_plan_params);

	project_year_investment_plan_create_window.load({
		url : "./simple_ProjectYearInvestmentPlan_get.do?uuid=" + selectId,
		success : function(result) {
			json = result.result;
		}
	});

}