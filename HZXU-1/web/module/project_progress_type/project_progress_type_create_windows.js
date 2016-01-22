function project_progress_type_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var project_progress_type_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
			url : "./simple_ProjectProgressType_save.do",
		params : {
			optType : "save"
		},
		// 字段
		field : [{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
				columnWidth : 1,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 350
				},
				items : [{
					id : 'projectprogresstype.typeName',
					name : 'projectprogresstype.typeName',
					fieldLabel : ' 项目进展类型',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			} // 1-1 end
			]
		}]
		
	}

	var project_progress_type_create_window = new project_progress_type_save_update_form_panel_windows(project_progress_type_params);

}