function updateUserPasswdWindows(){

	var form_panel = new Ext.form.ERPFormPanel({
		labelWidth : 55,
		frame : true,
//		bodyStyle : 'padding:5px 5px 0',
		height : 400,
		autoHeight : false,
		items : [
		{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [
			{// 1-2
				columnWidth : 1,
				layout : 'form',
				baseCls : 'x-plain',
				defaultType : 'textfield',
				defaults : {
					width : 300
				},
				items : [{
					id : 'systemUserInfo.accessPassword1',
					name : 'systemUserInfo.accessPassword',
					fieldLabel : ' 新密码',
					xtype : 'textfield',
					inputType : "password",
					style : 'background:#fff1a4;',
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}// 1-2end,
			,
			
			]
		},		{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [
			{// 1-2
				columnWidth : 1,
				layout : 'form',
				baseCls : 'x-plain',
				defaultType : 'textfield',
				defaults : {
					width : 300
				},
				items : [{
					id : 'systemUserInfo.accessPassword2',
//					name : 'systemUserInfo.accessPassword',
					fieldLabel : ' 重复密码',
					xtype : 'textfield',
					inputType : "password",
					style : 'background:#fff1a4;',
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}// 1-2end,
			,
			
			]
		},
	
		
		
		],
		//reader : params.reader,
		buttons : [{
			text : '提交',
			listeners : {
				'click' : function() {
					var p2 = Ext.getCmp("systemUserInfo.accessPassword2").getValue();
					var p1 = Ext.getCmp("systemUserInfo.accessPassword1").getValue();
					if(p1 != p2 ){
						showErrorMsg("错误","两次输入的密码不一样");
						return ;
					}
					form_panel.submit({
						url : "./updatePassword.do",
						waitMsg : '正在提交...',
						submitEmptyText : false,
						//params : params.params,
						success : function(json) {
							window.close();
							showErrorMsg("成功","修改密码成功");
//							if (params.action == "save") {
//								//params.grid.insertRow(json[params.pojo]);
//								window.close();
//							} else {
//								//params.grid.updateRow(json[params.pojo]);
//								window.close();
//							}

						}
					});
				}
			}
		}, {
			text : '关闭',
			listeners : {
				'click' : function() {
					window.close();
				}
			}
		}]
	});
	var window = new Ext.ERPDefaultsWindow({
		title : "修改密码",
		closable : true,
		width : 300,
		height : 200,
		autoHeight : false,
		items : [form_panel]
	});
	window.showWin();

	this.load = function(params) {
		form_panel.load(params);
	}

	this.closeWindow = function() {
		window.close();
	}

	// return form_panel;

	   
	
}