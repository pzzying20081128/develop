function ${javaScript.module}_search_windows(moduleId, moduleName, params) {

	
	var grid = params.grid.getGrid();
	var search_params = params.searchParams;
	var form_panel = new Ext.form.ERPFormPanel({
        height : 400,
//		autoHeight : false,
		labelWidth : 60,
		items : search_params.formField,
		buttons : [{
			text : '提交',
			listeners : {
				'click' : function() {
					
					if (!search_params.verification())
						return;
						grid.removeAll();
						from = form_panel.getForm();
					if (from.isValid()) {
						var submitValues1 = from.getValues();
						// 对将要提交的参数进行过滤，去掉emptyText文字
						for (var param in submitValues1) {
							if (from.findField(param) && from.findField(param).emptyText == submitValues1[param]) {
							    submitValues1[param] = '';
//								thisForm.findField(param).setValue("");
							}
						}
                        
						Ext.apply(submitValues1 ,search_params.params() );
						grid.load({
							params : submitValues1,
							success : function() {
								window.close();
							}
						});
						window.close();
					} else {
						showErrorMsg("错误", "请检查查询数据的正确性");
					}
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
		title : "查询工单",
		closable : true,
		width : 500,
//		height : 400,
//		autoHeight : false,
		
		items : [form_panel]
	});
	window.showWin();


	
	
}