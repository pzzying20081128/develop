function demo_user_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var userPowerPanel_ = new UserPowerPanel({
		height : 280
	});

	var demo_user_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		userPowerPanel : userPowerPanel_,
		// url
		url : "./simple_DemoUser_save.do",
		params : {
			optType : "save"
		},
		// 字段
		field : [{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{

				columnWidth : .36,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 190
				},
				items : [{
					id : 'demouser.userXMing',
					name : 'demouser.userXMing',
					fieldLabel : ' 用户姓名',
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
				columnWidth : .32,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 190
				},
				items : [{
					id : 'demouser.userName',
					name : 'demouser.userName',
					fieldLabel : ' 登录名',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]

			}, {
				columnWidth : .32,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 185
				},
				items : [{
					id : 'demouser.password',
					name : 'demouser.password',
					fieldLabel : ' 用户密码',
					xtype : 'textfield',
					inputType : 'password', // 密码
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
		}, userPowerPanel_.getPanel()]

	}

	var demo_user_create_window = new demo_user_save_update_form_panel_windows(demo_user_params);

}