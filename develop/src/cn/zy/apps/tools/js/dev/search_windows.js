function ${javaScript.packages}_search_windows(moduleId, moduleName, params) {

	
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
					grid.load({
						params : search_params.params(),
						success : function() {
							window.close();
						}

					});

					window.close();
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