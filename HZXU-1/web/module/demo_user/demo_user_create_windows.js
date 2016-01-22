function demo_user_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var userPowerPanel_ = new UserPowerPanel({
		height : 400
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

				columnWidth : .5,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 250
				},
				items : [{
					id : 'demouser.userName',
					name : 'demouser.userName',
					fieldLabel : ' 用户信息',
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

			}// 1-2end
			, {

			}// 1-3 end
			, {

			}// 1-3 end
			]
		}, userPowerPanel_.getPanel()]

	}

	var demo_user_create_window = new demo_user_save_update_form_panel_windows(demo_user_params);

}