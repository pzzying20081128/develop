function demo_stock_create_windows(moduleId, moduleName, params) {

	var grid = params.grid.getGrid();

	var demo_stock_params = {
		title : "新增" + moduleName,
		action : "save",
		grid : grid,
		// 结果路径
		pojo : "result",
		// url
		url : "./simple_DemoStock_save.do",
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
					id : 'demostock.stockName',
					name : 'demostock.stockName',
					fieldLabel : ' 采购单',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			}, {
				columnWidth : .5,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 250
				},
				items : [createERPcombo({
					id : 'demostock.stockUserId',
					name : 'demostock.stockUserId',
					fieldLabel : ' 采购人',
					url : "./DemoUser_combo.do?searchBean.status=全部",
					allowBlank : true,
					forceSelection : false,
					width : 160
				})]

			}// 1-2end
			, {

			}// 1-3 end
			, {

			}// 1-3 end
			]
		}]

	}

	var demo_stock_create_window = new demo_stock_save_update_form_panel_windows(demo_stock_params);

}