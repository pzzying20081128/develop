function updateUserPasswdWindows(){

	var form_panel = new Ext.form.ERPFormPanel({
		labelWidth : 55,
		frame : true,
//		bodyStyle : 'padding:5px 5px 0',
		height : 400,
		autoHeight : false,
		//items : params.field,
		//reader : params.reader,
		buttons : [{
			text : '提交',
			listeners : {
				'click' : function() {
					form_panel.submit({
						url : params.url,
						waitMsg : '正在提交...',
						submitEmptyText : false,
						params : params.params,
						success : function(json) {
							if (params.action == "save") {
								params.grid.insertRow(json[params.pojo]);
								form_panel.reset();
							} else {
								params.grid.updateRow(json[params.pojo]);
								window.close();
							}

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
		width : 450,
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