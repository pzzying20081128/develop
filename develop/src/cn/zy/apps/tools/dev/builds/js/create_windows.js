function ${javaScript.module}_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var ${javaScript.module}_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "sss",
		//url
		url : './saveUpdateMaterialManage.action',
		params : {
			optType : "save"
		},
		//字段
		field : [
		{// 第一排
			layout : 'column',
			baseCls : 'x-plain',
			items : [{// 1-1
				columnWidth : .34,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 200
				},
				items : [{
					id : 'goods.name',
					name : 'goods.name',
					fieldLabel : ' 物料名字',
					xtype : 'textfield',
					style : 'background:#fff1a4;',
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}, // 1-1 end
			{// 1-2
				columnWidth : .33,
				layout : 'form',
				baseCls : 'x-plain',
				defaultType : 'textfield',
				defaults : {
					width : 200
				},
				items : [createERPcombo({
					id : 'goods.classification',
					name : 'goods.classification',
					label : "物料类别",
					url : "./searchMatrialClassification.action",
					allowBlank : false,
					forceSelection : false
					// width : 150
				})]
			}// 1-2end
			, {// 1-3
				columnWidth : .33,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 200
				},
				items : [{

					id : 'goods.serialNumber',
					name : 'goods.serialNumber',
					fieldLabel : ' 物料编号',
					xtype : 'textfield',
					style : 'background:#fff1a4;',
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}

				}]
			}// 1-3 end
			]

		}
		]
		
	}

	var ${javaScript.module}_create_window = new ${javaScript.module}_save_update_form_panel_windows(${javaScript.module}_params);

}