function ${javaScript.module}_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var ${javaScript.module}_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "sss",
		// url
		url : './saveUpdateMaterialManage.action',
		params : {
			optType : "save"
		},
		// 字段
		field : [
		{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
			}, // 1-1 end
			{
			
			}// 1-2end
			, {
			
			}// 1-3 end
			, {
			
			}// 1-3 end
			]
		},
			{// 第二排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{
			}, // 1-1 end
			{
			
			}// 1-2end
			, {
			
			}// 1-3 end
			,{
			
			}// 1-3 end
			]
		}
		]
		
	}

	var ${javaScript.module}_create_window = new ${javaScript.module}_save_update_form_panel_windows(${javaScript.module}_params);

}