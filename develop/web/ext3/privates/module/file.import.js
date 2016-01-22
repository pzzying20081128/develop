/**
 * 
 */

function createImportWindows(params) {
	var isOver = 0;
	var fpFileUpload = new Ext.FormPanel({
		// id:'product_import_panel',
		frame : true,
		fileUpload : true,
		width : 400,
		// url:'./importroduct.do',
		items : [{
			xtype : 'textfield',
			allowBlank : false,
			fieldLabel : '选择文件',
			inputType : 'file',
			name : 'importExcel'
		}],
		buttonAlign : 'center',
		buttons : [{
			text : '上传',
			id : "fpFileUpload",
			handler : function() {
				if (fpFileUpload.form.isValid()) {
					fpFileUpload.form.submit({
						method : 'post',
						url : params.url,
						waitMsg : '文件上传中...',
						success : function(form, action) {
							var json = Ext.util.JSON.decode(action.response.responseText)
							var success = json.success;
							if (success) {
								if (typeof ( params.success == "function" )) {
									params.success(json);
								} else {
									if (json.importErrorSize == 0) {
										showErrorMsg("系统提示", "导入产品信息成功");
									} else {
										{
											showErrorMsg("系统提示", "导入信息中有" + json.importErrorSize + "个错误/重复数据!");
											// showMsgYN({
											// title : "错误",
											// msg : "导入信息中有" +
											// json.importErrorSize +
											// "个错误/重复数据!",
											// buttons : {
											// cancel : '关闭',
											// no : '下载错误数据'
											// },
											// callback : function(btn) {
											// if (btn == "no") {
											// // downExports(downTREEID, {
											// // url : params.down.url,
											// // params : down.params
											// // });
											// }
											// }
											// });
										}
									}
								}

							}
						},
						failure : function(form, action) {
							var json = Ext.util.JSON.decode(action.response.responseText)
							var msg = json.msg;
							showErrorMsg("系统提示", "导入信息错误【" + msg + "】");
						}
					});
				} else {
					showErrorMsg("系统提示", "请选择要导入的文件");
				}
			}
		}, {
			text : '取消',
			handler : function() {
				winFielUpload.close();
			}
		}]
	});

	var winFielUpload = new Ext.Window({
		// id:'product_import_win',
		title : '信息导入',
		resizable : false,
		// ****renderTo:'divWindow',//对于window不要使用renderTo属性，只需要调用show方法就可以显示，添加此属性会难以控制其位置
		width : 400,
		height : 140,
		layout : 'fit',
		autoDestory : true,
		modal : true,
		closeAction : 'close',
		items : [fpFileUpload]
	});
	winFielUpload.show();
	return winFielUpload;
}
