function demo_user_update_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var selection_rows = grid.getSelectionModel().getSelections();

	var userPowerPanel_ = new UserPowerPanel({
		height : 400
	});

	if (selection_rows == null) {
		showErrorMsg('提示信息', '请选择要编辑的数据记录！！');
		return false;
	}

	if (selection_rows.length != 1) {
		showErrorMsg('提示信息', '编辑只能选择一行数据记录！！');
		return false;
	}
	var selectId = selection_rows[0].id;

	var demo_user_params = {
		title : "编辑" + moduleName,
		action : "update",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_DemoUser_save.do",
		params : {
			optType : "update"
		},
		reader : new Ext.data.JsonReader({
			successProperty : 'success',
			root : 'result',
			totalProperty : 'totalProperty'
		}, [{
			name : 'demouser.userName',
			mapping : 'userName'
		}]),
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

	demo_user_create_window.load({
		url : "./simple_DemoUser_get.do?uuid=" + selectId,
		success : function(result) {
			json = result.result;
			userPowerPanel_.load({
				url : "./listPowerByUserId.do",
				params : {
					userId : selectId
				}
			});
		}
	});

}