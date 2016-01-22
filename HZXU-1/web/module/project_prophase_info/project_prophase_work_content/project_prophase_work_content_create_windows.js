function project_prophase_work_content_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var main_selection_rows_id = params.mainSelectionRowsId;

	var project_prophase_work_content_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectProphaseWorkContent_save.do",
		params : {
			optType : "save",
			"projectprophaseworkcontent.projectProphaseInfoId" : main_selection_rows_id
		},
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

}