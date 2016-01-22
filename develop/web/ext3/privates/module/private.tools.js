function showErrorMsg(title, message) {
	var msg = Ext.MessageBox.show({
		title : title,
		buttons : Ext.MessageBox.OK,
		// msg : ' <div style="width:95%,font-size: 12px;margin-left:
		// 8px;padding-left: 8px;text-align:center">' + message + '</div>',
		msg : message,
		width : 400,
		modal : true,
		icon : Ext.Msg.ERROR
	});
}

function showMsg(title, messages) {
	Ext.MessageBox.show({
		title : title,
		buttons : Ext.MessageBox.OK,
		msg : messages,
		width : 400,
		modal : true,
		icon : Ext.Msg.INFO

	});
}

function showMsgYN(params) {
	Ext.MessageBox.confirm("消息", params.msg, function(btn) {
		if (btn == 'yes') {
			params.yes();
		} else {
			if (typeof ( params.no ) == "function") {
				params.no();
			}
		}
	});
}
function showMsgButtonsYN(params) {
	Ext.MessageBox.confirm("消息", params.msg, params.buttons, function(btn) {
		params.callback(btn)
	})
}

// /////////////////////////////////////////////////////////////////////////////////////////
Ext.form.ERPShowTextField = Ext.extend(Ext.form.TextField, {
	disabled : true,
	disabledClass : 'my-disabled',
	xtype : 'textfield',
	blankText : '不能为空!',
	allowBlank : false,
	style : showBlankStyle
	// enableKeyEvents : true

});
Ext.reg('ERPShowText', Ext.form.ERPShowTextField);

Ext.form.ERPShowEditText = Ext.extend(Ext.form.TextField, {
	disabled : false,
	disabledClass : 'my-disabled',
	xtype : 'textfield',
	blankText : '不能为空!',
	allowBlank : false,
	style : showBlankStyle,
	// enableKeyEvents : true,
	editable : false,
	readOnly : true

});
Ext.reg('ERPShowEditText', Ext.form.ERPShowTextField);

// //////////////////////////////////////////////////////////////////////////////////////////////

function ERPAjaxRequest(reqParams) {
	var myMask = new Ext.LoadMask(Ext.getBody(), {
		msg : '正在处理数据...'
	});
	// myMask.show();
	Ext.Ajax.request({
		timeout : timeout,
		url : reqParams.url,
		params : reqParams.params,
		async : ( typeof ( reqParams.async ) == "undefined" ) ? false : reqParams.async, // ASYNC
		// 是否异步(
		// TRUE
		// 异步 ,
		// FALSE
		// 同步)
		// waitMsg : '正在处理数据...',
		success : function(response, options) {
			// myMask.hide();
			var json = Ext.util.JSON.decode(response.responseText);
			if (typeof ( json.success ) == "undefined") {
				if (typeof ( reqParams.success ) == "function")
					reqParams.success(response, options);
			} else {
				var success_ = json.success;
				// alert(success_);
				if (success_ == false) {
					if (typeof ( json.msg ) == "undefined") {
						if (typeof ( reqParams.errors ) == "function") {

							// reqParams.errors(response, options, null);

							reqParams.errors({
								'response' : response,
								'options' : options,
								"result" : null,
								"msg" : null
							});

						}
					} else {
						if (json.msg == 1001 || json.msg == '1001') {
							Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {

								window.location.href = "./";
							});
						} else {
							if (json.msg == 10000 || json.msg == '10000') {
								showErrorMsg("错误提示", "请求操作失败【系统错误】");
							} else if (json.msg == null) {
								showErrorMsg("错误提示", "请求操作失败【未知错误】");
							} else {
								if (typeof ( reqParams.errors ) == "function") {
									// reqParams.errors(response, options,
									// json.msg);

									reqParams.errors({
										'response' : response,
										'options' : options,
										"msg" : json.msg,
										"result" : null
									});
								} else {
									showErrorMsg("错误提示", "请求操作失败【" + json.msg + "】");
								}
							}
						}
					}
				} else {
					if (typeof ( reqParams.success ) == "function")
						// reqParams.success(response, options,
						// response.responseJSON);
						reqParams.success({
							'response' : response,
							'options' : options,
							"result" : response.responseJSON
						});

				}
			}

		},
		failure : function(resp, opts) {// 失败
			// myMask.hide();
			if (typeof ( reqParams.error ) != "undefined") {
				reqParams.error(resp, opts);
				reqParams.errors({
					'response' : resp,
					'options' : opts,
					"result" : null,
					'msg' : null
				});
			} else {
				showErrorMsg("失败", "请求数据失败！");
			}
		}
	});
};

// /////////////////////////////////////////////////////////////////////////////////////////////

/**
 * params={ id: name: label: url: select: }
 * 
 * @param {}
 *            params
 */
function createLocalCombo(params) {
	var xx = {
		id : params.id,
		// itemid : 'combobox_type',
		name : params.name,
		hiddenName : params.name,
		fieldLabel : params.fieldLabel,
		xtype : 'combo',
		mode : 'local',
		valueField : 'id',
		displayField : "value",
		triggerAction : 'all',
		editable : false,
		defaultValue : typeof ( params.defaultValue ) == 'undefined' ? null : params.defaultValue,
		reset : function() {
			if (this.clearFilterOnReset && this.mode == 'local') {
				this.store.clearFilter();
			}
			Ext.form.ComboBox.superclass.reset.call(this);
			this.setValue(this.defaultValue);
		},
		disabled : typeof ( params.disabled ) == 'undefined' ? false : params.disabled,
		allowBlank : typeof ( params.allowBlank ) == 'undefined' ? false : params.allowBlank,
		style : typeof ( params.allowBlank ) == 'undefined' ? NoAllowBlankStyle : ( params.allowBlank == true ? AllowBlankStyle : NoAllowBlankStyle ),
		value : typeof ( params.defaultValue ) == 'undefined' ? null : params.defaultValue,
		listeners : params.listeners,
		store : new Ext.data.SimpleStore({
			fields : ['id', "value"],
			// data : [[0, "否"], [1, "是"]]
			data : params.storeData,
			listeners : params.storelisteners
		}

		// combo.setValue(1);
		)

		// store:new Ext.data.SimpleStore(
		// params.storeValue
		// ),

	};
	var comboBox = new Ext.form.ComboBox(xx);
	return comboBox;

}

function mainGridWindow(properties) {

	var detailGrid = typeof ( properties.detailGrid ) == "undefined" ? null : properties.detailGrid;

	var isPrint = typeof ( properties.isPrint ) == "undefined" ? false : true;

	var isAddSet = typeof ( properties.isAddSet ) == "undefined" ? false : true;

	var reader_root = typeof ( properties.reader_root ) == "undefined" ? "selectPage.result" : properties.reader_root;

	var reader_totalProperty = typeof ( properties.reader_totalProperty ) == "undefined" ? "selectPage.count" : properties.reader_totalProperty;

	this.setDetailGrid = function(detailGrid) {
		this.detailGrid = detailGrid;
	}
	this.getDetailGrid = function() {
		return this.detailGridl
	}

	var isBbar = typeof ( properties.isBbar ) == "undefined" ? true : properties.isBbar;

	var moduleId = properties.moduleId;
	var moduleName = properties.moduleName;
	var store = new Ext.data.ERPStore({
		paramNames : {
			// The parameter name which specifies the start row
			start : 'start',

			// The parameter name which specifies number of rows to return
			limit : 'limit',

			// The parameter name which specifies the column to sort on
			sort : 'commSearchBean.sort',

			dir : 'commSearchBean.dir'
		},
		proxy : new Ext.data.HttpProxy({
			url : properties.url
		}),
		reader : new Ext.data.JsonReader({
			// totalProperty : 'selectPage.count',
			// root : 'selectPage.result'

			totalProperty : reader_totalProperty,

			root : reader_root

		}, new Ext.data.Record.create(properties.record))
	})

	var grid = new Ext.grid.ERPGridPanel({
		id : moduleId + '_GRID',
		width : '100%',
		height : typeof ( properties.height ) != "undefined" ? properties.height : '100%',
		region : 'center',
		cm : new Ext.grid.ColumnModel(properties.column),
		store : store,
		moduleId : moduleId,
		moduleName : moduleName,
		bbar : isBbar == true ? ( new Ext.PagingToolbar({
			store : store,
			pageSize : erp_grid_panel_limit,
			displayInfo : true,
			displayMsg : '本页显示第{0}条到第{1}条的记录，一共{2}条。',
			emptyMsg : '没有记录',
			doRefresh : function() {
				this.store.reload({
					callback : function(r, options, success) {
						if (!success) {
							Ext.MessageBox.alert('提示', "加载相关数据失败【系统错误】！");
						} else {
						}
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('提示', "加载相关数据失败(failure)！");
					}
				});
			}
		}) ) : null,
		rowdblclickKey : this.moduleId + '_edit',
		tbar : properties.tbar
	});
	grid.initPanel(properties.init);

	var tbar = grid.getTopToolbar();

	if (isPrint) {

		if (typeof ( tbar ) != 'undefined' && typeof ( tbar.items ) != 'undefined' && typeof ( tbar.items.items ) != 'undefined') {

			tbar.addButton(new Ext.Toolbar.Button({
				xtype : "tbbutton",
				text : "打印",
				key : "print",
				// keyBinding:createPrintKey(),
				handler : function() {
					var selection_rows = grid.getSelectionModel().getSelections();
					if (selection_rows.length != 1) {
						showMsg('提示信息', '请选择要打印的一行数据！');
						return false;
					}
					var moduleName = moduleId;
					var id = selection_rows[0].id;

					// var url = "print/erp_print.do?id=" + id + "&modue=" +
					// stock_order_detail_grid_panel.erpModule;
					var url = "http://www.163.com";
					print_panel_win(url, {
						id : id,
						modue : moduleId + "_detail"
					}, grid);
				}
			}));
			var items = tbar.items.items;
			for (var i = 0; i < items.length; i++) {
				items[i].disable();
			}
		}
	}

	// if (isAddSet == true) {
	// grid.addSetButton({
	// addSet : {
	// grids : ( detailGrid == null ) ? [grid] : [grid, detailGrid]
	// }
	// });
	// }

	this.getGrid = getGrid_;

	function getGrid_() {
		return grid;
	}

	this.load = function(params) {
		grid.load(params);
	}

}

// ///////////////////////////////////////////////////////////////////////////////////////////

/**
 * @version 1.0
 * @author cuisuqiang@163.com 用于实现页面 Map 对象，Key只能是String，对象随意
 */
var Map = function() {
	this._entrys = new Array();

	this.put = function(key, value) {
		if (key == null || key == undefined) {
			return;
		}
		var index = this._getIndex(key);
		if (index == -1) {
			var entry = new Object();
			entry.key = key;
			entry.value = value;
			this._entrys[this._entrys.length] = entry;
		} else {
			this._entrys[index].value = value;
		}
	};
	this.get = function(key) {
		var index = this._getIndex(key);
		return ( index != -1 ) ? this._entrys[index].value : null;
	};
	this.remove = function(key) {
		var index = this._getIndex(key);
		if (index != -1) {
			this._entrys.splice(index, 1);
		}
	};
	this.clear = function() {
		this._entrys.length = 0;;
	};
	this.contains = function(key) {
		var index = this._getIndex(key);
		return ( index != -1 ) ? true : false;
	};
	this.getCount = function() {
		return this._entrys.length;
	};
	this.getEntrys = function() {
		return this._entrys;
	};
	this._getIndex = function(key) {
		if (key == null || key == undefined) {
			return -1;
		}
		var _length = this._entrys.length;
		for (var i = 0; i < _length; i++) {
			var entry = this._entrys[i];
			if (entry == null || entry == undefined) {
				continue;
			}
			if (entry.key === key) {// equal
				return i;
			}
		}
		return -1;
	};
}

function createERPImportWindows(params) {
	// var importPanel= createImportProductPanel();
	var mainGridModule = params.mainGridModule;
	var treeid = mainGridModule.moduleId;
	var grid = mainGridModule.getGrid();
	var importPanel = createImportWindows({
		treeid : treeid,
		// url : "./importproduct.do?random_id=1",
		url : params.url,
		success : function(json) {
			var load = false;
			grid.reload();
			importPanel.close();
			if (!load) {
				load = true;
				if (json.importErrorSize > 0) {
					showMsgButtonsYN({
						title : "错误",
						msg : "导入信息中有" + json.importErrorSize + "个错误/重复产品数据!",
						buttons : {
							cancel : '关闭',
							no : '下载错误数据'
						},
						callback : function(btn) {
							if (btn == "no") {
								downExports(downTREEID, {
									url : "./exportExcel.do",
									params : {}
								});
							}
						}
					});
				} else {
					showMsg('提示信息', '数据导入成功');
				}

			}
		},
		errors : function(r, options, msg) {
			showMsg('提示信息', "加载上传数据错误[" + msg + "]");
		}
	});

}
