function project_ownership_address_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var project_ownership_address_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_ProjectOwnershipAddress_save.do",
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
					id : 'projectownershipaddress.address',
					name : 'projectownershipaddress.address',
					fieldLabel : ' 项目归属地',
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

	var project_ownership_address_create_window = new project_ownership_address_save_update_form_panel_windows(project_ownership_address_params);

}