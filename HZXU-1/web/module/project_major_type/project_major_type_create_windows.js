function project_major_type_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var project_major_type_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectMajorType_save.do",
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
					id : 'projectmajortype.name',
					name : 'projectmajortype.name',
					fieldLabel : ' 项目重点分类',
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

	var project_major_type_create_window = new project_major_type_save_update_form_panel_windows(project_major_type_params);

}