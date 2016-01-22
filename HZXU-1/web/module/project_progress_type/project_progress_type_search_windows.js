function project_progress_type_search_windows(moduleId, moduleName, params) {

	
	var grid = params.grid.getGrid();
	var search_params = params.searchParams;
	var form_panel = new Ext.form.ERPFormPanel({
        height : 400,
// autoHeight : false,
		labelWidth : 60,
		items : [
		
		
	{ // 第一排
		layout : 'column',
		baseCls : 'x-plain',
		items : [
		{
			columnWidth : .18,
			layout : 'form',
			defaultType : 'textfield',
			baseCls : 'x-plain',
			hideLabels : true,
			items : [{
			    // 复选框
				id : 'staffSearchBean.selectName',
				xtype : "checkbox",
				boxLabel : "员工姓名"
			}]
		}, {
			columnWidth : .82,
			layout : 'form',
			baseCls : 'x-plain',
			defaultType : 'textfield',
			hideLabels : true,
			defaults : {
				width : 360
			},
			items : [{
				// 查询框
				id : 'staffSearchBean.name',
				listeners : {
					"change" : function(field) {
					}
				}
			}]
		}
	 ]
	}// end
  	],
		buttons : [{
			text : '提交',
			listeners : {
				'click' : function() {
					from = form_panel.getForm();
					if (!search_params.verification(from))return;
						grid.removeAll();	
					if (from.isValid()) {
						var submitValues1 = from.getValues();
						// 对将要提交的参数进行过滤，去掉emptyText文字
						for (var param in submitValues1) {
							if (from.findField(param) && from.findField(param).emptyText == submitValues1[param]) {
							    submitValues1[param] = '';
// thisForm.findField(param).setValue("");
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
		title : "查询",
		closable : true,
		width : 500,
// height : 400,
// autoHeight : false,
		
		items : [form_panel]
	});
	window.showWin();


	
	
}