function project_month_investment_plan_save_update_form_panel_windows(params) {

	var monthSelect = params.monthSelect;

	var form_panel = new Ext.form.ERPFormPanel({
		labelWidth : 80,
		frame : true,
		// bodyStyle : 'padding:5px 5px 0',
		// height : 400,
		// autoHeight : false,
		items : params.field,
		reader : params.reader,
		buttons : [{
			text : '提交',
			listeners : {
				'click' : function() {

					showMsgButtons({
						title : "信息",
						msg : "是否要"+( params.action =="save"?"提交月投资进度":"更新月投资进度"),
						buttons : {
							no : "保存",
							yes:"提交并短信提醒",
							cancel:"关闭"
						
						},
						callback : function(btn) {
						     if(btn =='cancel')return ;
							form_panel.submit({
								url : params.url,
								waitMsg : '正在提交...',
								submitEmptyText : false,
								params : params.params,
								success : function(result) {
									json = result.result;

									if (params.action == "save") {
										params.grid.insertRow(json[params.pojo]);
										monthSelect.load({
											params : {
												"searchBean._ac" : 1
											}
										});
										form_panel.reset();
									} else {
										params.grid.updateRow(json[params.pojo]);
										window.close();
									}

								}
							});

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
		title : params.title,
		closable : true,
		width : 900,
		// height : 400,
		// autoHeight : false,
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