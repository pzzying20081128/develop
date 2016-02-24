function project_month_investment_plan_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();
	
	var projectGrid=  params.projectGrid;

	var YearGrid_selection_rows_selectId = params.YearGrid_selection_rows_selectId;
	
	var monthSelect =
				createERPcombo({
					id : 'projectmonthinvestmentplan.month',
					name : 'projectmonthinvestmentplan.month',
					fieldLabel : ' 月份',
					url : "./ProjectMonth_combo.do?projectId="+projectGrid,
					allowBlank : false,
					forceSelection : false
				});
				
				
				

	var project_month_investment_plan_params = {
		title : "新增" + moduleName,
		monthSelect:monthSelect,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectMonthInvestmentPlan_save.do",
		params : {
			optType : "save",
			"projectmonthinvestmentplan.projectYearInvestmentPlanId" : YearGrid_selection_rows_selectId
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
				items : [monthSelect]
			},// 1-1 en
			{
				columnWidth : .5,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				labelWidth : 120,
				defaults : {
					width : 285
				},
				items : [{
					id : 'projectmonthinvestmentplan.truthInvestment',
					name : 'projectmonthinvestmentplan.truthInvestment',
					fieldLabel : ' 实际投资金额(万元)',
					xtype : 'custnumberfield',
					// vtype:"money",
					labelWidth : 120,
					style : AllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : true,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}]
		},

		{// 第二排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 760
				},
				items : [{
					id : 'projectmonthinvestmentplan.truthCompletionStatus',
					name : 'projectmonthinvestmentplan.truthCompletionStatus',
					fieldLabel : ' 实际完成情况',
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
			} // 1-1 end

			]
		},

		{// 第二排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 760
				},
				items : [{
					id : 'projectmonthinvestmentplan.investmentPlan',
					name : 'projectmonthinvestmentplan.investmentPlan',
					fieldLabel : ' 月投资计划',
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
			} // 1-1 end

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
					width : 760
				},
				items : [{
					id : 'projectmonthinvestmentplan.constructionContent',
					name : 'projectmonthinvestmentplan.constructionContent',
					fieldLabel : ' 主要建设内容',
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
			} // 1-1 end

			]
		}, {
			// 第二排
			layout : 'column',
			baseCls : 'x	,-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 760
				},
				items : [{
					id : 'projectmonthinvestmentplan.existingProblems',
					name : 'projectmonthinvestmentplan.existingProblems',
					fieldLabel : ' 存在问题',
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
			} // 1-1 end

			]

		}, {
			// 第二排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 760
				},
				items : [{
					id : 'projectmonthinvestmentplan.imageProgress',
					name : 'projectmonthinvestmentplan.imageProgress',
					fieldLabel : ' 形象进度',
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
			} // 1-1 end

			]

		}]

	}

	var project_month_investment_plan_create_window = new project_month_investment_plan_save_update_form_panel_windows(project_month_investment_plan_params);

}