function project_year_investment_plan_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();
	
	var projectGrid= params.projectGrid;
	
	var projectGridId =projectGrid.projectGrid_id;

	var project_year_investment_plan_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectYearInvestmentPlan_save.do",
		params : {
			optType : "save",
			"projectyearinvestmentplan.projectCarriedOutInfoId":projectGridId
		},
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

}