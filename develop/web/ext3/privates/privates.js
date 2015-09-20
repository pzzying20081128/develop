var timeout = 10000 * 8000;

var erp_grid_panel_limit = 40;

function tohome() {
	Ext.MessageBox.alert("提示", "用户没有登录或连接超时,请重新登录!", function(id) {
		window.location = './index.html';
	});

}

/**
 * moduleId: url : record: cm: tbar: init:{ select: status: }
 * 
 * @param {}
 *            properties
 */
function mainGridWindow(properties) {

	var isBbar = typeof ( properties.isBbar ) == "undefined" ? true : properties.isBbar;

	var moduleId = properties.moduleId;
	var store = new Ext.data.ERPStore({
		proxy : new Ext.data.HttpProxy({
			url : properties.url
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'selectPage.count',
			root : 'selectPage.result'
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

	this.getGrid = getGrid_;

	function getGrid_() {
		return grid;
	}

	this.load = function(params) {
		grid.load(params);
	}

}

function mainEditGridWindow(properties) {

	var moduleId = properties.moduleId;
	var store = new Ext.data.ERPStore({
		proxy : new Ext.data.HttpProxy({
			url : properties.url
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'selectPage.count',
			root : 'selectPage.result'
		}, new Ext.data.Record.create(properties.record))
	})

	var grid = new Ext.grid.ERPEditGridPanel({
		id : moduleId + '_GRID',
		width : '100%',
		height : typeof ( properties.height ) != "undefined" ? properties.height : '100%',
		region : 'north',
		split : true,
		cm : new Ext.grid.ColumnModel(properties.column),
		store : store,
		// sm: typeof ( properties.selectionModel ) != "undefined" ?
		// properties.selectionModel : null ,
		moduleId : moduleId,
		bbar : new Ext.PagingToolbar({
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
		}),
		rowdblclickKey : this.moduleId + '_edit',
		tbar : properties.tbar
	});
	grid.initPanel(properties.init);

	this.getGrid = getGrid_;

	function getGrid_() {
		return grid;
	}

}

function showErrorMsg(title, message) {
	var msg = Ext.MessageBox.show({
		title : title,
		buttons : Ext.MessageBox.OK,
		msg : ' <div align="left"  style="width:90%,font-size: 12px;margin-left: 15px;padding-left: 15px">' + message + '</div>',
		width : 300,
		modal : true,
		icon : Ext.Msg.ERROR
	});
}
function showMsg(title, messages) {
	Ext.MessageBox.show({
		title : title,
		buttons : Ext.MessageBox.OK,
		msg : ' <div align="left"  style="width:90%,font-size: 12px;margin-left: 15px;padding-left: 15px">' + messages + '</div>',
		width : 500,
		modal : true,
		icon : Ext.Msg.INFO

	});
}

function showMsgYN(params) {
	Ext.MessageBox.confirm("消息", params.title, function(btn) {
		params.msg(btn);
		// Ext.Msg.alert("提示", "你点击了" + btn + "按钮");
	});
}

Ext.ERPPanel = Ext.extend(Ext.Panel, {
	region : 'center',
	split : true,
	width : "100%",
	autoHeight : true,
	autoWidth : true,
	layoutConfig : {
		columns : 1
	},
	layout : 'fit',
	bodyStyle : "background:#f1f1f1;"// border-left: 1px solid #8db2e3

});

// ////////////////Ext.grid.ERPRowNumberer ///////////////////////////////////
/**
 * PageTotal : 当页合 AllTotal : 总合
 * 
 * @class Ext.grid.ERPRowNumberer
 * @extends Ext.grid.RowNumberer
 */
Ext.grid.ERPRowNumberer = Ext.extend(Ext.grid.RowNumberer, {
	width : 40,
	sortable : false,
	resizable : true,
	draggable : false,
	hideable : false,
	menuDisabled : true,
	renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {
		var gridTotalType = record.get("gridTotalType");
		if (gridTotalType == 'PageTotal') {
			cellmeta.attr = 'style="background-color: #C9D8FC"';// 指定自己的颜色
			return "合计";
		} else if (gridTotalType == 'AllTotal') {
			cellmeta.attr = 'style="background-color: #C9D8FC"';// 指定自己的颜色
			return "总计";
		} else {
			// cellmeta.css = 'x-grid-row-index';//类样式
			if (store.lastOptions != null && store.lastOptions.params != null) {
				var pageindex = store.lastOptions.params.start;
				return pageindex + rowIndex + 1;
				// return ( ( pageindex - 1 ) * erp_grid_panel_limit ) + ( x - 1
				// ) + 1;
			} else {
				return rowIndex + 1;
			}
		}

	}
});

// ////////////////////////////////////////////////////////////////////////////////////

Ext.form.ERPFormPanel = Ext.extend(Ext.form.FormPanel, {
	layout : 'form',
	border : false,
	autoWidth : true,
	autoHeight : true,
	plain : true,
	frame : true,
	labelAlign : 'center',
	bodyStyle : 'border:0 0 0 0;',
	style : 'border:0 0 0 0;',
	sync : true,
	fileUpload : false,
	reset : function() {
		this.getForm().reset();
	},
	/**
	 * properties:{ url , waitMsg, success: }
	 */

	load : function(properties) {

		var fileUpload_ = this.isfileUpload();

		this.getForm().load({
			url : properties.url,
			waitMsg : '正在载入数据...',
			success : function(form, action) {

				var imageUpload = false;

				if (typeof ( properties.imageUpload ) != 'undefined') {
					imageUpload = properties.imageUpload;
				}

				if (imageUpload == false) {

					imageUpload = fileUpload_;
				}

				if (imageUpload == true) {
					var str = action.response.responseText;
					var json = Ext.decode(str);

					var success = json.success;
					if (success) {
						if (typeof ( properties.success ) == 'function') {
							var responseJSON = action.response.responseJSON;
							properties.success(form, action, responseJSON);
						}
					} else {
						var errormsg = json.msg;
						if (typeof ( properties.errors ) == 'function')
							properties.errors(form, action, errormsg, msgcode);
						else
							showErrorMsg("错误提示", errormsg);
					}

				} else {
					var success = action.response.responseJSON.success;
					if (success) {
						if (typeof ( properties.success ) == 'function') {
							var responseJSON = action.response.responseJSON;
							properties.success(form, action, responseJSON);
						}
					} else {
						showErrorMsg("错误提示", "提交请求操作失败");
					}
				}

			},
			failure : function(form, action) {

				var failureType = action.failureType;

				var errormsg = action.response.responseJSON.msg;

				if (errormsg == null || errormsg == "") {

					showErrorMsg("错误提示", "提交请求操作失败【系统错误[" + failureType + "]】");
				} else {
					if (typeof ( properties.errors ) == 'function')
						properties.errors(form, action, errormsg);
					else
						showErrorMsg("错误提示", errormsg);
				}

			}
		});
	},

	isfileUpload : function() {
		return this.fileUpload;
	},
	submit : function(properties) {
		var imageUpload = false;
		if (typeof ( properties.imageUpload ) != 'undefined') {
			imageUpload = properties.imageUpload;
		}

		if (imageUpload == false) {
			var fileUpload_ = this.isfileUpload();
			imageUpload = fileUpload_;
		}

		if (typeof ( properties.waitMsg ) == "undefined")
			properties.waitMsg = '正在载入数据...';
		this.getForm().submit({
			timeout : timeout,
			url : properties.url,
			waitMsg : properties.waitMsg,
			submitEmptyText : false,
			params : properties.params,
			success : function(form, action) {

				if (imageUpload == true) {
					var str = action.response.responseText;
					var json = Ext.decode(str);
					if (json.success) {
						if (typeof ( properties.success ) == 'function') {

							properties.success(json, form, action);
						}
					} else {
						var errormsg = json.msg;
						if (typeof ( properties.errors ) == 'function')
							properties.errors(form, action, errormsg, msgcode);
						else
							showErrorMsg("错误提示", errormsg);
					}
				} else {
					if (typeof ( properties.success ) == 'function') {
						var json = action.response.responseJSON;
						properties.success(json, form, action);
					}
				}

			},
			failure : function(form, action) {
				if (action.failureType == "client") {
					showErrorMsg("错误提示", "请检查要保存的信息是否正确!");
				} else if (action.failureType == "connect") {
					showErrorMsg("错误提示", "系统错误【链接错误】");
				} else {

					var result = action.result;
					var errormsg = result.msg;
					var msgcode = result.msgcode;

					if (errormsg == null || errormsg == "")
						showErrorMsg("错误提示", "提交请求操作失败");
					else {
						if (typeof ( properties.errors ) == 'function')
							properties.errors(form, action, errormsg, msgcode);
						else
							showErrorMsg("错误提示", errormsg);
					}
				}
			}
		});
	}
});

Ext.data.ERPStore = Ext.extend(Ext.data.Store, {
	remoteSort : true,
	listeners : {
// "beforeload" : function(store, options) {
	// var o = store.baseParams;
	// Ext.apply(o, options.params);
	// this.baseParams = o;
	// }
	}
});

// ////////////////////////////////////////////////////////////////////////////////////
// Ext.ux.grid.RowEditor //Ext.grid.GridPanel
Ext.grid.ERPGridPanel = Ext.extend(Ext.grid.GridPanel, {
	region : 'north',
	split : true,
	height : '100%',
	width : '100%',
	columnLines : true,
	stripeRows : true,
	loadMask : true,
	// checkName:"审核",
	inits : false,
	savecol : false,
	// 多选的 column
	checkboxColumn : null,
	// 行双击 对应的是toolbbr 那个 ID
	rowdblclickKey : null,
	powerList : "12",
	setPowerList : function(powerList_) {
		// var grid = this.getGrid();
		this.powerList = powerList_;

	},

	getPowerList : function() {
		var grid = this.getGrid();
		return grid.powerList;
	},

	searchPower : function(key) {
		var powerList_ = this.getPowerList();
		
		for (var i = 0; i < powerList_.length; i++) {
			if (powerList_[i].powerName == key)
				return powerList_[i];
		}
		return null;
	},

	// optName =moduleId+add
	isHavePower : function(optName) {
		if (this.powerList == null)
			return false;
		for (var i = 0; i < this.powerList.length; i++) {
			if (this.powerList[i].optName == optName) {
				return ( this.powerList[i].use === 1 )
			}
		}
		return false;
	},
	setCheckboxColumn : function(checkboxColumn) {
		this.checkboxColumn = checkboxColumn;
	},
	getCheckboxColumn : function() {
		return this.checkboxColumn;
	},

	getGrid : function() {
		return this;
	},

	updateRowfieldValue : function(filedName, value) {
		var record = this.getGrid().getSelectionModel().getSelected();
		record.set(filedName, value);

	},

	insertRow : function(object) {
		var grid = this.getGrid();
		var record = new grid.store.recordType(object, object.id);
		grid.getStore().insert(0, record);
//		var fieldses = record.fields;
//		for (var j = 0; j < fieldses.keys.length; j++) {
//
//			var field_ = fieldses.items[j];
//			var fieldName;
//			if (field_.mapping != null && typeof ( field_.mapping ) != "undefined") {
//				fieldName = field_.mapping;
//			} else {
//				fieldName = field_.name;
//			}
//
//			var value = object[fieldName];
//			record.set(field_.name, value);
//		}
		record.commit();
	},
	updateRow : function(object) {
		// if (record == null || typeof ( record ) == "undefined")
		var record = this.getGrid().getSelectionModel().getSelected();
		var fieldses = record.fields;
		for (var j = 0; j < fieldses.keys.length; j++) {

			var field_ = fieldses.items[j];
			var fieldName;
			if (field_.mapping != null && typeof ( field_.mapping ) != "undefined") {
				fieldName = field_.mapping;
			} else {
				fieldName = field_.name;
			}

			var value = object[fieldName];
			record.set(field_.name, value);
		}
		record.commit();
	},

	viewConfig : {
		forceFit : false,
		autoScroll : true
	},

	onRowDblClick : function(g, rowIndex, e) {

		var rowdblclickKey_ = this.rowdblclickKey;
		var moduleId = this.moduleId;
		if (rowdblclickKey_ == null || typeof ( rowdblclickKey_ ) == "undefined")
			rowdblclickKey_ = moduleId + "_edit";
		if (!this.isHavePower(rowdblclickKey_))
			return;
		var toolbar = this.getTopToolbar();
		// alert(rowdblclickKey_);
		var edittoolbar = toolbar.getComponent(rowdblclickKey_);
		if (typeof ( edittoolbar ) != "undefined")
			edittoolbar.handler.call(edittoolbar);
	},

	initComponent : function() {

		Ext.grid.ERPGridPanel.superclass.initComponent.call(this);

		// 绑定双击//
		this.on('rowdblclick', this.onRowDblClick, this);

		this.store.on("beforeload", function(store, options) {
			// var o = store.baseParams;
			// Ext.apply(o, options.params);
			// this.baseParams = o;
		});

		var cmConfigs = this.colModel.config;
		for (var j = 0; j < cmConfigs.length; j++) {
			var cmConfig_ = cmConfigs[j];
			if (typeof ( cmConfig_.id ) != "undefined" && cmConfig_.id == 'isSelect') {
				this.setCheckboxColumn(cmConfig_);
				this.on("headerclick", function(ct, column, e, t, opts) {
					var cmConfig_ = this.getCheckboxColumn();
					var selectAllParmas = cmConfig_.selectAll;
					var isSelect_ = selectAllParmas.isSelect;
					selectAllParmas.params.isSelect = isSelect_;
					selectAllParmas.params.type = "all";
					this.selectAction(selectAllParmas, this.getColumnModel(), this.store);
				});
			}
		}

	},

	selectAction : function(selectAllParmas, columnModel, store_) {
		var selectAll_ = this.selectAll;
		ERPAjaxRequest({
			url : selectAllParmas.url,
			params : selectAllParmas.params,
			// async: false, //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
			success : function(response, options) {
				var isSelect = response.responseJSON.isSelect;
				// setIsAllSelect(isSelect);
				selectAllParmas.isSelect = isSelect;
				if (isSelect == 0) {
					columnModel.setColumnHeader(1, '<img  src="./images/js/checkbox_no.png">');
				} else {
					columnModel.setColumnHeader(1, '<img  src="./images/js/checkbox_yes.png">');
				}
				selectAll_(isSelect, store_);
			}
		});
	},

	selectAll : function(isSelect, store_) {
		for (var i = 0; i < store_.getCount(); i++) {
			var record = store_.getAt(i);
			record.set("isSelect", isSelect);
		}

	},

	getSelectCount : function() {
		var s = 0;
		for (var i = 0; i < this.store.getCount(); i++) {
			var record = this.store.getAt(i);
			if (record.get("isSelect") == 1) {
				s = s + 1;
			}
		}
		return s;
	},

	getSelectRow : function() {
		var list_id = new Array(0);
		for (var i = 0; i < this.store.getCount(); i++) {
			var record = this.store.getAt(i);
			if (record.get("isSelect") == 1) {
				list_id.push(record);
			}
		}
		return list_id;
	},

	removeAll : function() {

		this.store.removeAll();
	},

	saveColModule : function() {
		// alert(this.getInit()+" "+this.getSaveCol());
		// if(this.savecol==false)return;
		var colModel_ = this.colModel;
		var moduleId = this.moduleId;
		if (typeof ( moduleId ) == "undefined") {
			showErrorMsg("系统错误", "ERPGridPanel saveColModule not find moduleId ! moduleId:TREEID");
			return;
		}
		var cmConfigs = colModel_.config;
		var data_indexs = null;
		var col_indexs = null;
		var col_names = null;
		var col_hiddens = null;
		var col_widths = null;
		for (var i = 1; i < cmConfigs.length; i++) {
			var cmConfig = cmConfigs[i];
			if (typeof ( cmConfig.dataIndex ) == "undefined" || cmConfig.dataIndex == '')
				continue;
			var hidden = ( cmConfig.hidden == null ) ? '0' : ( cmConfig.hidden == true ? 1 : 0 );
			var dataIndex_ = cmConfig.dataIndex;
			if (data_indexs != null)
				data_indexs = data_indexs + ";" + dataIndex_;
			else
				data_indexs = dataIndex_;
			if (col_names != null)
				col_names = col_names + ";" + cmConfig.header;
			else
				col_names = cmConfig.header;
			if (col_widths != null)
				col_widths = col_widths + ";" + cmConfig.width;
			else
				col_widths = cmConfig.width;
			if (col_hiddens != null)
				col_hiddens = col_hiddens + ";" + hidden;
			else
				col_hiddens = hidden;
			if (col_indexs != null)
				col_indexs = col_indexs + ";" + i;
			else
				col_indexs = i;
		}

		Ext.Ajax.request({
			url : 'sysuser/col_chonig_opt.action',
			params : {
				module_name : moduleId,// 'SYSTEM_USER',
				data_indexs : data_indexs,
				col_names : col_names,
				col_hiddens : col_hiddens,
				col_widths : col_widths,
				col_indexs : col_indexs
			},
			success : function(response, options) {
			}
		});

		for (var i = 1; i < cmConfigs.length; i++) {
			var cmConfig = cmConfigs[i];
			var hidden = ( cmConfig.hidden == null ) ? '0' : ( cmConfig.hidden == true ? 1 : 0 );
			var dataIndex_ = cmConfig.dataIndex;
			Ext.Ajax.request({
				url : 'sysuser/col_move_opt.action',
				params : {
					module_name : moduleId,// 'SYSTEM_USER',
					old_colIndex : 0,
					new_colIndex : i,
					dataIndex : dataIndex_,
					col_name : cmConfig.header,
					is_hidden : hidden
				},
				success : function(response, options) {
				}
			});
		}
	},

	removeOptBt : function() {

	},

	setPower : function(modulePower_) {
		var moduleId = this.moduleId;
		// var setPowerList_ = this.setPowerList;
		var grid = this.getGrid();
		ERPAjaxRequest({
			url : './power.jhtml',
			params : {
				moduleId : moduleId
			},
			success : function(response, options) {
				var json = Ext.util.JSON.decode(response.responseText);
				var powerMap = json.powerMap;
				// var grids_ = grids[i];
				var power = powerMap[moduleId];
				if (power == null || typeof ( power ) == 'undefined') {
					grid.setPowerList(null);
				} else {
					grid.setPowerList(power);
					for (var ij = 0; ij < power.length; ij++) {
						var buttonCmp = Ext.getCmp(moduleId + "_" + power[ij].powerName);
						if (typeof ( buttonCmp ) != 'undefined') {
							if (power[ij].isUse == 1) {
								buttonCmp.show();
							} else {
								buttonCmp.hide();
							}
						}
					}
					if (modulePower_ != null && typeof ( modulePower_ ) != 'undefined') {
						modulePower_.modulePower(moduleId, power);
					}

				}
				return json;
			}
		});

	},

	initPanel : function(prams) {
		// this.bottomToolbar.store=this.store;

		var moduleId = this.moduleId;
		if (typeof ( moduleId ) == "undefined") {
			showErrorMsg("系统错误", "ERPGridPanel  initPanel not find moduleId ! moduleId:TREEID");
			return;
		}
		var colModel_ = this.colModel;
		var checkName_ = this.checkName;
		var selModule = this.selModel;
		var thisGrid = this;
		var setInits = this.setInits;

		colModel_.on('hiddenchange', function(cm, columnIndex, hidden) {

			// if(getInit()==false)return;
			// setSaveCol(true);
			// if (hidden) {
			// col_hidden_opt(moduleId, cm, columnIndex, 1);// hide
			// } else {
			// col_hidden_opt(moduleId, cm, columnIndex, 0);// show
			// }
		});

		colModel_.on('columnmoved', function(cm, oldIndex, newIndex) {
			// if(this.getInit()==false)return;
			// {
			// this.setSaveCol(true);
			// //col_hidden_opt(moduleId,cm, columnIndex, 0);// hide
			// //col_move_opt(moduleId, cm, oldIndex, newIndex);// hide
			// }
		});

		// ERPAjaxRequest({
		// url : './sysuser/fetch_hidden_col.action',
		// params : {
		// module_name : moduleId
		// // 'SYSTEM_USER',
		// },
		// error : function() {
		//
		// },
		// success : function(response, options) {
		// var cmConfigs = colModel_.config;
		// var json = Ext.util.JSON.decode(response.responseText);
		// var hiddenCount = 0;
		// for (var i = 0; i < json.root.length; i++) {
		//
		// for (var j = 1; j < cmConfigs.length; j++) {
		// var cmConfig = cmConfigs[j];
		// if (cmConfig.dataIndex == json.root[i].colDataIndex) {
		// if (json.root[i].hidden == 1) {
		// colModel_.setHidden(j, true);
		// hiddenCount++;
		// } else {
		// colModel_.setHidden(j, false);
		// }
		//
		// colModel_.moveColumn(j, json.root[i].colIndex);
		// colModel_.setColumnWidth(json.root[i].colIndex,
		// json.root[i].colWidth, true);
		// break;
		// }
		// }
		//
		// if (hiddenCount == json.root.length)
		// colModel_.setHidden(1, false);
		//
		// }
		// // colModel_.moveColumn(1,3);
		// // setInits(true);
		// }
		// });

		this.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
			if (typeof ( prams ) == "undefined")
				return;
			// alert(checkName_ +" 222 moduleId "+moduleId);
			if (typeof ( Ext.getCmp(moduleId + '_checked') ) == "undefined") {

				if (typeof ( prams.select ) != "undefined" && typeof ( prams.select ) == "function") {
					prams.select(r.data.id, r.data, sm, rowIdx, r);
				}
				return;
			} else {

				Ext.getCmp(moduleId + '_checked').setDisabled(false);
				var checked = null;
				if (typeof ( prams ) != 'undefined') {
					if (typeof ( prams.status ) == 'function')
						checked = prams.status(r.data);
					if (checked == null) {
						showErrorMsg("错误", "状态取值错误[没设置 【status】！");
					}

					if (typeof ( checkName_ ) == "undefined" || checkName_ == null || checkName_ === "")
						checkName_ = "审核";
					if (typeof ( selModule ) == 'undefined') {
						if (checked == "checked" || checked == 1) {
							Ext.getCmp(moduleId + '_checked').setText('取消' + checkName_);
						} else {
							Ext.getCmp(moduleId + '_checked').setText(checkName_);
						}
						if (typeof ( prams.select ) == "function") {

						}
					} else {
						var selection_rows = thisGrid.getSelectionModel().getSelections();
						var xx = null;
						for (var index = 0; index < selection_rows.length; index++) {
							checked = prams.status(selection_rows[index].data);
							if (xx != null && xx != checked) {
								xx = -1;
								break;
							} else {
								xx = checked;
							}
						}
						if (xx == -1) {
							showErrorMsg("错误", "所选择的单据出现二种以上状态!");
							Ext.getCmp(moduleId + '_checked').setDisabled(true);
						} else {
							if (checked == "checked" || checked == 1) {
								Ext.getCmp(moduleId + '_checked').setText('取消' + checkName_);
							} else {
								Ext.getCmp(moduleId + '_checked').setText(checkName_);
							}
						}
					}
					prams.select(r.data.id, sm, rowIdx, r);

				}// if(typeof(prams)!='undefined')

			}
		});
	},// end

	load : function(loadParams) {
		var store_ = this.store;
		store_.removeAll();
		if (typeof ( loadParams ) === "undefined")// params is undefined
		{
			loadParams = {
				params : {
					limit : erp_grid_panel_limit,
					start : 0
				}
			}
		} else {
			if (typeof ( loadParams.params ) === "undefined") {
				loadParams.params = {
					limit : erp_grid_panel_limit,
					start : 0
				}
			} else {
				loadParams.params.limit = erp_grid_panel_limit;
				loadParams.params.start = 0;
			}

		}
		// sortInfo : {
		// sort : 'id',
		// dir : 'DESC'
		// },
		if (typeof ( loadParams.sortInfo ) == "undefined") {
			loadParams.sortInfo = {
				sort : 'id',
				dir : 'desc'
			}
		}
		if (typeof ( store_.sortInfo ) == "undefined") {
			store_.sortInfo = {
				field : loadParams.sortInfo.sort,
				direction : loadParams.sortInfo.dir
			}
		}

		else {
			store_.sortInfo.field = loadParams.sortInfo.sort;
			store_.sortInfo.direction = loadParams.sortInfo.dir;
		}

		store_.load({
			params : loadParams.params,
			// 回调
			// records参数表示获得的数据，
			// options表示执行load()时传递的参数，success表示是否加载成功。
			callback : function(r, options, success, action) {
				// alert("success : "+success);
				if (!success) {
					// showErrorMsg("信息提示", "加载数据失败！");
					if (loadParams != null) {
						var jsonData = store_.reader.jsonData;
						if (typeof ( loadParams.errors ) == "function") {
							// var jsonData = store_.reader.jsonData;
							if (typeof ( jsonData.msg ) === "undefined") {
								// loadParams.errors(r, options,null);
								showErrorMsg("失败", "数据请求失败【未知错误  -1 】！");
							} else {
								if (jsonData.msg == 1001 || jsonData.msg == '1001') {
									Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {

										window.location.href = "./";
									});
								} else {
									if (jsonData.msg == 10000 || jsonData.msg == '10000') {
										showErrorMsg("失败", "数据请求失败【系统错误 10000 】！");
									} else if (jsonData.msg == null) {
										showErrorMsg("失败", "数据请求失败【未知错误 -2 】！");
									} else {
										loadParams.errors(r, options, jsonData.msg);
									}
								}

							}
						} else {
							// ////////////
							if (jsonData.msg == 1001 || jsonData.msg == '1001') {
								Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {

									window.location.href = "./";
								});
							}
							// ///////////////
							else
								Ext.MessageBox.alert("失败", "数据请求失败【系统错误 -3 】！");
						}
					} else {
						showErrorMsg("失败", "数据请求失败【未知错误 -4】！");
					}
				} else {
					if (typeof ( loadParams.success ) == "function") {
						var jsonData = store_.reader.jsonData;
						loadParams.success(r, options, jsonData);
					}

				}
			}
		});
	},
	reload : function(loadParams) {
		var store = this.store;
		// store_.removeAll();
		var o = store.baseParams;
		if (typeof ( store.lastOptions ) != "undefined" && store.lastOptions != null && typeof ( store.lastOptions.params ) != "undefined") {
			Ext.apply(o, store.lastOptions.params);
		}
		if (typeof ( loadParams ) != "undefined" && typeof ( loadParams.params ) != "undefined") {
			Ext.apply(o, loadParams.params);
		}
		store.baseParams = o;

		store.reload({
			// params : loadParams.params,
			// 回调
			// records参数表示获得的数据，
			// options表示执行load()时传递的参数，success表示是否加载成功。
			callback : function(r, options, success) {
				if (!success) {
					// showErrorMsg("信息提示", "加载数据失败！");
					if (loadParams != null) {
						if (typeof ( loadParams.errors ) == "function") {
							var jsonData = store_.reader.jsonData;
							if (typeof ( jsonData.msg ) === "undefined") {
								// loadParams.errors(r, options,null);
								showErrorMsg("失败", "数据请求失败【未知错误  -1 】！");
							} else {
								if (jsonData.msg == 1001 || jsonData.msg == '1001') {
									Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {

										window.location.href = "./";
									});
								} else {
									if (jsonData.msg == 10000 || jsonData.msg == '10000') {
										showErrorMsg("失败", "数据请求失败【系统错误 10000 】！");
									} else if (jsonData.msg == null) {
										showErrorMsg("失败", "数据请求失败【未知错误 -2 】！");
									} else {
										loadParams.errors(r, options, jsonData.msg);
									}
								}

							}
						}
					} else {
						showErrorMsg("信息提示", "加载数据失败！");
					}
				} else {
					if (loadParams != null) {
						if (typeof ( loadParams.success ) == "function")
							loadParams.success(r, options);
					} else {
						// var jsonData= store_.reader.jsonData;
						// if(typeof(jsonData.msg)=="undefined"){
						// loadParams.errors(r, options,jsonData);
						// }else{
						// showErrorMsg("失败", jsonData.msg);
						// }

					}

				}
			}
		});
	}

});
// ////////////////////////////////////////////////////////////////////////////////////////////

Ext.ERPWindow = Ext.extend(Ext.Window, {
	closable : true,
	plain : true,
	resizable : true,
	layout : 'fit',
	maximizable : true,// 是否显示最大化按钮
	modal : true,// 是否是模式窗口
	maximized : true,// 默认最大化
	closeAction : 'close',// 关闭时的动作 hide或close
	maximizable : true,// （是否增加最大化，默认没有）
	draggable : true,// （是否可以拖动，默认可以）
	minimizable : true,// （是否增加最小化，默认无）
	closable : true,
	constrain : true,
	width : 700,
	height : 400,
	// manager:mygroup,
	listeners : {
		// 'maximize' : function(e) {
		// },
		'minimize' : function(e) {
			this.hide();
		},
		'bodyresize' : function(el, w, h) {
		},
		'beforeclose' : function() {
			if (typeof ( this.grids ) == "undefined") {
				// showErrorMsg("系统错误","windows not find grids ! grids is null
				// ,grids:[grid] ");
				return;
			}
			var grids = this.grids;
			for (var i = 0; i < grids.length; i++) {
				grids[i].saveColModule();
			}
			// if(typeof(this.close) == "function"){
			// this.close();
			// }
		}

	},

	showWin : function(params) {
		var grids = this.grids;
		if (typeof ( this.moduleId ) == "undefined") {
			showErrorMsg("系统错误", "windows not find moduleId ! moduleId:TREEID");
		}
		var TREEID = null;
		var windows = this;
		var grids = this.grids;

		if (typeof ( grids ) != "undefined") {
			for (var i = 0; i < grids.length; i++) {
				if (typeof ( params ) == "undefined") {
					grids[i].setPower();
				} else {
					if (typeof ( params.modulePower ) != "undefined") {
						grids[i].setPower(params.modulePower);
					}
				}

			}
		}
		Ext.getCmp('center_panel').removeAll();
		Ext.getCmp('center_panel').add(windows);
		Ext.getCmp('center_panel').doLayout();
		windows.show();

		// //////////////////////////////////////////////////////////////////
		if (typeof ( grids ) != "undefined") {
			for (var i = 0; i < grids.length; i++) {
				grids[i].removeOptBt();
			}
		}
		// ///////////////////////////////////////////////////////////////////

	}
});

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
							reqParams.errors(response, options, null);
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
									reqParams.errors(response, options, json.msg);
								} else {
									showErrorMsg("错误提示", "请求操作失败【" + json.msg + "】");
								}

							}
						}

					}
				} else {
					if (typeof ( reqParams.success ) == "function")
						reqParams.success(response, options, response.responseJSON);
				}
			}

		},
		failure : function(resp, opts) {// 失败
			// myMask.hide();
			if (typeof ( reqParams.error ) != "undefined") {
				reqParams.error(resp, opts);
			} else {
				showErrorMsg("失败", "请求数据失败！");
			}
		}
	});
};

// /////////////////////////////////////////////////////////////////////////////////////////////

Ext.ERPDefaultsWindow = Ext.extend(Ext.Window, {
	closable : true,
	plain : true,
	resizable : false,
	layout : 'fit',
	modal : true,
	closeAction : 'close',
	draggable : true,
	closable : true,
	constrain : true,
	autoDestroy : true,
	maximized : false,
	maximizable : false,
	autoHeight : true,// form_panel.height,
	// autoWidth:true,
	split : true,
	// manager:mygroup,
	listeners : {
		'maximize' : function(e) {
		},
		'minimize' : function(e) {

		},
		'bodyresize' : function(el, w, h) {
		},
		'beforeclose' : function() {
			if (typeof ( this.grids ) == "undefined") {
				return;
			}
			var grids = this.grids;
			for (var i = 0; i < grids.length; i++) {
				grids[i].saveColModule();
			}
		}
	},

	showWin : function() {
		var windows = this;
		windows.show();
	}
});

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Ext.ux.grid.RowEditor //Ext.grid.GridPanel
Ext.grid.ERPEditGridPanel = Ext.extend(Ext.grid.EditorGridPanel, {
	// erpModule
	region : 'north',
	clicksToEdit : 2,
	split : true,
	height : '100%',
	width : '100%',
	minHeight : 300,
	maxHeight : 500,
	columnLines : true,
	stripeRows : true,
	loadMask : true,
	// checkName:"审核",
	inits : false,
	savecol : false,
	// 多选的 column
	checkboxColumn : null,
	// 行双击 对应的是toolbbr 那个 ID
	rowdblclickKey : null,
	isAllPower : false,
	powerList : null,
	setPowerList : function(powerList_) {
		this.powerList = powerList_;
	},

	getPowerList : function() {
		return this.powerList;
	},

	isHavePower : function(optName) {
		if (this.isAllPower == true)
			return true;
		if (this.powerList == null)
			return false;
		for (var i = 0; i < this.powerList.length; i++) {
			if (this.powerList[i].optName == optName) {
				return ( this.powerList[i].use === 1 )
			}
		}
		return false;
	},

	setIsHaveSelect : function(isSelect) {

		var columnModel = this.getGrid().getColumnModel();
		var isSelectColumnModel = columnModel.getColumnById("isSelect");
		if (typeof ( isSelectColumnModel ) == "undefined")
			return;
		isSelectColumnModel.setIsAllSelect(isSelect);
		if (isSelect == 2)
			columnModel.setColumnHeader(1, '<img  src="./ext3/images/checkbox_head_all.png">');
		else if (isSelect == 1)
			columnModel.setColumnHeader(1, '<img  src="./ext3/images/checkbox_head_yes.png">');
		else
			columnModel.setColumnHeader(1, '<img  src="./ext3/images/checkbox_no.png">');

	},

	selectAll : function(isSelect, grid) {
		var bufferRange = grid.getState().bufferRange;
		for (var i = bufferRange[0]; i <= bufferRange[1]; i++) {
			var record = grid.getStore().getAt(i);
			if (typeof ( record ) != "undefined")
				record.set("isSelect", isSelect);
			else {

			}
		}
		if (isSelect == 1)
			grid.setSelectCount(grid.getStore().getTotalCount());
		else
			grid.setSelectCount(0);

	},

	setCheckboxColumn : function(checkboxColumn) {
		this.checkboxColumn = checkboxColumn;
	},
	getCheckboxColumn : function() {
		return this.checkboxColumn;
	},
	getGrid : function() {
		return this;
	},

	insertRow : function(object) {

		if (typeof ( object.isSelect ) != "undefined") {
			object.isSelect = 0;
		}

		var grid = this.getGrid();
		var record = new grid.store.recordType(object, object.id);
		// var x = grid.getStore().getCount( );

		grid.getStore().insert(0, record);
		record.dirty = true;
		// 只要一个字段值被修改了，该行的所有值都设置为脏读标记
		record.modified = object;
		if (grid.getStore().modified.indexOf(record) == -1) {
			grid.getStore().modified.push(record);
		}

	},
	updateRow : function(object) {
		// if (record == null || typeof ( record ) == "undefined")
		var record = this.getGrid().getSelectionModel().getSelected();
		var fieldses = record.fields;
		for (var j = 0; j < fieldses.keys.length; j++) {

			var field_ = fieldses.items[j];
			var fieldName;
			if (field_.mapping != null && typeof ( field_.mapping ) != "undefined") {
				fieldName = field_.mapping;
			} else {
				fieldName = field_.name;
			}

			var value = object[fieldName];
			record.set(field_.name, value);
		}
		record.commit();
	},

	updateRowfieldValue : function(filedName, value) {
		var record = this.getGrid().getSelectionModel().getSelected();
		record.set(filedName, value);

	},

	updateRowIndexFieldValue : function(row, filedName, value) {
		var record = this.getGrid().getStore().getAt(row);
		record.set(filedName, value);

	},

	updateRow : function(row, object) {
		var record = this.getGrid().getStore().getAt(row);
		var fieldses = record.fields;
		for (var j = 0; j < fieldses.keys.length; j++) {

			var field_ = fieldses.items[j];
			var fieldName;
			if (field_.mapping != null && typeof ( field_.mapping ) != "undefined") {
				fieldName = field_.mapping;
			} else {
				fieldName = field_.name;
			}

			var value = object[fieldName];
			record.set(field_.name, value);
		}
		record.commit();

	},
	viewConfig : {
		forceFit : false,
		autoScroll : true
	},

	initComponent : function() {

		Ext.grid.ERPEditGridPanel.superclass.initComponent.call(this);

		// 绑定双击//
		// this.on('rowdblclick', this.onRowDblClick, this);

		this.store.on("beforeload", function(store, options) {
			var o = store.baseParams;
			Ext.apply(o, options.params);
			this.baseParams = o;
		});

		// this.on("headerclick", function(ct,column,e,t,opts) {
		// this.getColumnModel().setColumnHeader(1,'<img
		// src="./images/js/checkbox_yes.png">');
		// });

		var cmConfigs = this.colModel.config;
		for (var j = 0; j < cmConfigs.length; j++) {
			var cmConfig_ = cmConfigs[j];
			if (typeof ( cmConfig_.id ) != "undefined" && cmConfig_.id == 'isSelect') {
				this.setCheckboxColumn(cmConfig_);
				this.on("headerclick", function(ct, column, e, t, opts) {
					var cmConfig_ = this.getCheckboxColumn();
					var selectAllParmas = cmConfig_.selectAll;
					var isSelect_ = selectAllParmas.isSelect;
					selectAllParmas.params.isSelect = isSelect_;
					selectAllParmas.params.type = "all";
					this.selectAction(selectAllParmas, this.getColumnModel(), this.store);
				});
			}
		}

	},

	selectAction : function(selectAllParmas, columnModel, store_) {
		var selectAll_ = this.selectAll;
		ERPAjaxRequest({
			url : selectAllParmas.url,
			params : selectAllParmas.params,
			async : false, // ASYNC 是否异步( TRUE 异步 , FALSE 同步)
			success : function(response, options) {
				var isSelect = response.responseJSON.isSelect;
				// setIsAllSelect(isSelect);
				selectAllParmas.isSelect = isSelect;
				if (isSelect == 0) {
					columnModel.setColumnHeader(1, '<img  src="./images/js/checkbox_no.png">');
				} else {
					columnModel.setColumnHeader(1, '<img  src="./images/js/checkbox_yes.png">');
				}
				selectAll_(isSelect, store_);
			}
		});
	},

	selectAll : function(isSelect, store_) {
		for (var i = 0; i < store_.getCount(); i++) {
			var record = store_.getAt(i);
			record.set("isSelect", isSelect);
		}

	},

	getSelectCount : function() {
		var s = 0;
		for (var i = 0; i < this.store.getCount(); i++) {
			var record = this.store.getAt(i);
			if (record.get("isSelect") == 1) {
				s = s + 1;
			}
		}
		return s;
	},

	getSelectRow : function() {
		var list_id = new Array(0);
		for (var i = 0; i < this.store.getCount(); i++) {
			var record = this.store.getAt(i);
			if (record.get("isSelect") == 1) {
				list_id.push(record);
			}
		}
		return list_id;
	},

	removeAll : function() {

		this.store.removeAll();
	},

	saveColModule : function() {
		// alert(this.getInit()+" "+this.getSaveCol());
		// if(this.savecol==false)return;
		var colModel_ = this.colModel;
		var erpModule = this.erpModule;
		if (typeof ( this.erpModule ) == "undefined") {
			showErrorMsg("系统错误", "ERPGridPanel saveColModule not find erpModule ! erpModule:TREEID");
			return;
		}
		var cmConfigs = colModel_.config;
		var data_indexs = null;
		var col_indexs = null;
		var col_names = null;
		var col_hiddens = null;
		var col_widths = null;
		for (var i = 1; i < cmConfigs.length; i++) {
			var cmConfig = cmConfigs[i];
			if (typeof ( cmConfig.dataIndex ) == "undefined" || cmConfig.dataIndex == '')
				continue;
			var hidden = ( cmConfig.hidden == null ) ? '0' : ( cmConfig.hidden == true ? 1 : 0 );
			var dataIndex_ = cmConfig.dataIndex;
			if (data_indexs != null)
				data_indexs = data_indexs + ";" + dataIndex_;
			else
				data_indexs = dataIndex_;
			if (col_names != null)
				col_names = col_names + ";" + cmConfig.header;
			else
				col_names = cmConfig.header;
			if (col_widths != null)
				col_widths = col_widths + ";" + cmConfig.width;
			else
				col_widths = cmConfig.width;
			if (col_hiddens != null)
				col_hiddens = col_hiddens + ";" + hidden;
			else
				col_hiddens = hidden;
			if (col_indexs != null)
				col_indexs = col_indexs + ";" + i;
			else
				col_indexs = i;
		}

		Ext.Ajax.request({
			url : 'sysuser/col_chonig_opt.do',
			params : {
				module_name : erpModule,// 'SYSTEM_USER',
				data_indexs : data_indexs,
				col_names : col_names,
				col_hiddens : col_hiddens,
				col_widths : col_widths,
				col_indexs : col_indexs
			},
			success : function(response, options) {
			}
		});

	},

	initPanel : function(prams) {
		var erpModule = this.erpModule;
		if (typeof ( this.erpModule ) == "undefined") {
			showErrorMsg("系统错误", "ERPGridPanel  initPanel not find erpModule ! erpModule:TREEID");
			return;
		}
		var colModel_ = this.colModel;
		var checkName_ = this.checkName;
		var selModule = this.selModel;
		var thisGrid = this;
		var setInits = this.setInits;

		colModel_.on('hiddenchange', function(cm, columnIndex, hidden) {

			if (hidden) {
				col_hidden_opt(erpModule, cm, columnIndex, 1);// hide
			} else {
				col_hidden_opt(erpModule, cm, columnIndex, 0);// show
			}
		});

		colModel_.on('columnmoved', function(cm, oldIndex, newIndex) {

		});

		ERPAjaxRequest({
			url : './sysuser/fetch_hidden_col.do',
			params : {
				module_name : erpModule
				// 'SYSTEM_USER',
			},

			success : function(response, options) {
				var cmConfigs = colModel_.config;
				var json = Ext.util.JSON.decode(response.responseText);
				var hiddenCount = 0;
				for (var i = 0; i < json.root.length; i++) {

					for (var j = 1; j < cmConfigs.length; j++) {
						var cmConfig = cmConfigs[j];
						if (cmConfig.dataIndex == json.root[i].colDataIndex) {
							if (json.root[i].hidden == 1) {
								colModel_.setHidden(j, true);
								hiddenCount++;
							} else {
								colModel_.setHidden(j, false);
							}

							colModel_.moveColumn(j, json.root[i].colIndex);
							colModel_.setColumnWidth(json.root[i].colIndex, json.root[i].colWidth, true);
							break;
						}
					}

					if (hiddenCount == json.root.length)
						colModel_.setHidden(1, false);
				}

			}
		});

		this.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
			if (typeof ( prams ) == "undefined")
				return;
			// alert(checkName_ +" 222 erpModule "+erpModule);
			if (typeof ( Ext.getCmp(erpModule + '_checked') ) == "undefined") {

				if (typeof ( prams.select ) != "undefined" && typeof ( prams.select ) == "function") {
					prams.select(r.data.id, sm, rowIdx, r);
				}
				return;
			} else {

				Ext.getCmp(erpModule + '_checked').setDisabled(false);
				var checked = null;
				if (typeof ( prams ) != 'undefined') {
					if (typeof ( prams.status ) == 'function')
						checked = prams.status(r.data);
					if (checked == null) {
						showErrorMsg("错误", "状态取值错误[没设置 【status】！");
					}

					if (typeof ( checkName_ ) == "undefined" || checkName_ == null || checkName_ === "")
						checkName_ = "审核";
					if (typeof ( selModule ) == 'undefined') {
						if (checked == "checked" || checked == 1) {
							Ext.getCmp(erpModule + '_checked').setText('取消' + checkName_);
						} else {
							Ext.getCmp(erpModule + '_checked').setText(checkName_);
						}
						if (typeof ( prams.select ) == "function") {

						}
					} else {
						var selection_rows = thisGrid.getSelectionModel().getSelections();
						var xx = null;
						for (var index = 0; index < selection_rows.length; index++) {
							checked = prams.status(selection_rows[index].data);
							if (xx != null && xx != checked) {
								xx = -1;
								break;
							} else {
								xx = checked;
							}
						}
						if (xx == -1) {
							showErrorMsg("错误", "所选择的单据出现二种以上状态!");
							Ext.getCmp(erpModule + '_checked').setDisabled(true);
						} else {
							if (checked == "checked" || checked == 1) {
								Ext.getCmp(erpModule + '_checked').setText('取消' + checkName_);
							} else {
								Ext.getCmp(erpModule + '_checked').setText(checkName_);
							}
						}
					}
					prams.select(r.data.id, sm, rowIdx, r);

				}// if(typeof(prams)!='undefined')

			}
		});
	},// end

	reloadParams : function(params) {
		if (typeof ( params ) === "undefined") {
		} else {
			var store_ = this.store;
			var o = store.baseParams;
			Ext.apply(o, params);
			this.baseParams = o;
		}
	},

	load : function(loadParams) {
		var store_ = this.store;
		store_.removeAll();
		if (typeof ( loadParams ) === "undefined")// params is undefined
		{
			loadParams = {
				params : {
					limit : erp_grid_panel_limit,
					start : 0

				}
			}
		} else {
			if (typeof ( loadParams.params ) === "undefined") {
				loadParams.params = {
					limit : erp_grid_panel_limit,
					start : 0
				}
			} else {
				loadParams.params.limit = erp_grid_panel_limit;
				loadParams.params.start = 0;
			}

		}

		if (typeof ( loadParams.sortInfo ) == "undefined") {
			loadParams.sortInfo = {
				sort : 'id',
				dir : 'desc'
			}
		}
		if (typeof ( store_.sortInfo ) == "undefined") {
			store_.sortInfo = {
				field : loadParams.sortInfo.sort,
				direction : loadParams.sortInfo.dir
			}
		}

		else {
			store_.sortInfo.field = loadParams.sortInfo.sort;
			store_.sortInfo.direction = loadParams.sortInfo.dir;
		}
		store_.baseParams = null;
		store_.load({
			params : loadParams.params,
			// 回调
			// records参数表示获得的数据，
			// options表示执行load()时传递的参数，success表示是否加载成功。
			callback : function(r, options, success, action) {
				if (!success) {
					if (loadParams != null) {
						if (typeof ( loadParams.errors ) == "function") {
							var jsonData = store_.reader.jsonData;
							if (typeof ( jsonData.msg ) === "undefined") {
								// loadParams.errors(r, options,null);
								showErrorMsg("失败", "数据请求失败【未知错误  -1 】！");
							} else {
								if (jsonData.msg == 1001 || jsonData.msg == '1001') {
									Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {

										window.location.href = "./";
									});
								} else {
									if (jsonData.msg == 10000 || jsonData.msg == '10000') {
										showErrorMsg("失败", "数据请求失败【系统错误 10000 】！");
									} else if (jsonData.msg == null) {
										showErrorMsg("失败", "数据请求失败【未知错误 -2 】！");
									} else {
										loadParams.errors(r, options, jsonData.msg);
									}
								}

							}
						} else {
							Ext.MessageBox.alert("失败", "数据请求失败【系统错误 -3 】！");
						}
					} else {
						showErrorMsg("失败", "数据请求失败【未知错误 -4】！");
					}
				} else {
					store_.baseParams = store_.lastOptions.params;
					if (typeof ( loadParams.success ) == "function") {
						var jsonData = store_.reader.jsonData;
						loadParams.success(r, options, jsonData);
					}

				}
			}
		});
	},
	reload : function(loadParams) {
		// this.reloadParams(loadParams.params);
		var store_ = this.store;
		// store_.removeAll();
		store_.reload({
			params : loadParams.params,
			// 回调
			// records参数表示获得的数据，
			// options表示执行load()时传递的参数，success表示是否加载成功。
			callback : function(r, options, success) {
				if (!success) {
					// showErrorMsg("信息提示", "加载数据失败！");
					if (loadParams != null) {
						if (typeof ( loadParams.errors ) == "function") {
							var jsonData = store_.reader.jsonData;
							if (typeof ( jsonData.msg ) === "undefined") {
								// loadParams.errors(r, options,null);
								showErrorMsg("失败", "数据请求失败【未知错误  -1 】！");
							} else {
								if (jsonData.msg == 1001 || jsonData.msg == '1001') {
									Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {

										window.location.href = "./";
									});
								} else {
									if (jsonData.msg == 10000 || jsonData.msg == '10000') {
										showErrorMsg("失败", "数据请求失败【系统错误 10000 】！");
									} else if (jsonData.msg == null) {
										showErrorMsg("失败", "数据请求失败【未知错误 -2 】！");
									} else {
										loadParams.errors(r, options, jsonData.msg);
									}
								}

							}
						}
					} else {
						showErrorMsg("信息提示", "加载数据失败！");
					}
				} else {
					if (loadParams != null) {
						if (typeof ( loadParams.success ) == "function")
							loadParams.success(r, options);
					} else {
						// var jsonData= store_.reader.jsonData;
						// if(typeof(jsonData.msg)=="undefined"){
						// loadParams.errors(r, options,jsonData);
						// }else{
						// showErrorMsg("失败", jsonData.msg);
						// }

					}

				}
			}
		});
	}

});
// ///////////////////////////////////////////////////////////////////////////////////////////

Ext.grid.ERPDetailRowNumberer = Ext.extend(Ext.grid.ERPRowNumberer, {
	width : 40,
	sortable : false,
	resizable : true,
	draggable : false,
	hideable : false,
	menuDisabled : true

	// editor : {
	// xtype : 'ERPShowText'
	// // renderer : function(params) {
	// // // alert(params.value);resizable : true,
	// // // var value = params.rowIndex;
	// // return params.value;
	// // }
	// }
});

// ///////////////////////////////////////////////////////////////////////////////////////////
Ext.grid.ERPCheckboxSelectionModel = Ext.extend(Ext.grid.Column, {
	// getColumnModel().setColumnHeader(1,"<img alt=""
	// src="./images/js/checkbox_yes.png">"); <div class="x-grid3-hd-checker"
	// >&#160;</div>
	header : '<img  src="./ext3/images/checkbox_no.png">',
	width : 30,
	sortable : false,
	menuDisabled : true,
	fixed : true,
	hideable : false,
	dataIndex : 'isSelect',
	id : 'isSelect',
	isColumn : true,
	isAllSelect : false,
	islocal : true,
	// selectParams:selectParams,

	setIsAllSelect : function(isSelect) {
		if (isSelect == 2)
			isSelect = 1;
		this.isAllSelect = isSelect;

	},
	getIsAllSelect : function() {
		return this.isisAllSelect;
	},
	constructor : function(configs) {
		Ext.grid.ERPCheckboxSelectionModel.superclass.constructor.apply(this, arguments);
		configs.selectAll.isSelect = 0;
	},

	processEvent : function(name, e, grid, rowIndex, colIndex) {
		// var select_=this.select;
		// alert(name);
		if (name == 'mousedown') {
			var record = grid.store.getAt(rowIndex);
			var value = record.data;
			var selectParams_ = this.selectParams(value);
			this.select(grid, grid.store, rowIndex, record, selectParams_);
			// var isSelect =1;
			// record.set(this.dataIndex, isSelect);
			return false; // Cancel row selection.
		} else {
			return Ext.grid.ActionColumn.superclass.processEvent.apply(this, arguments);
		}
	},

	select : function(grid, store, rowIndex, record, selectParams) {
		var dataIndex_ = this.dataIndex;
		if (typeof ( selectParams.params ) === "undefined") {
			selectParams.params = {
				type : "noall"
			}
		} else {
			selectParams.params.type = "noall";
		}

		// 这一行在store 中的数据 在这主要是作选择并提交和服务器session 同步
		var islocal = this.islocal;
		if (islocal == false) {
			ERPAjaxRequest({
				url : selectParams.url,
				params : selectParams.params,
				// async: false, //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
				success : function(response, options) {
					// grid.reload();
					grid.setIsHaveSelect(response.responseJSON.isHaveSelect);
					var isSelect = response.responseJSON.isSelect;
					record.set(dataIndex_, isSelect);
					if (isSelect == 0) {
						grid.addDelSelectCount(-1);
					} else {
						grid.addDelSelectCount(1);
					}
				}
			});
		} else {

			grid.setIsHaveSelect(selectParams.isHaveSelect);
			record.set(dataIndex_, selectParams.isSelect);
		}

	},

	renderer : function(value, p, record) {

		if (value == 1)
			img = '<img alt="" src="./ext3/images/checkbox_yes.png">';
		else
			img = '<img alt="" src="./ext3/images/checkbox_no.png">';

		return img;

	}
});

Ext.data.ERPComboStore = Ext.extend(Ext.data.Store, {
	autoLoad : false,
	remoteSort : true,
	listeners : {
		"beforeload" : function(store, options) {
			var o = store.baseParams;
			Ext.apply(o, options.params);
			this.baseParams = o;
		}
	},
	reloads : function(properties) {

		this.reload({
			params : properties.params,
			callback : function(r, options, success) {
				if (success) {
					if (typeof ( properties.success ) == 'function')
						properties.success(r, options);
				} else {
					Ext.MessageBox.alert('提示', "加载相关数据失败(no success)！");
				}
			},
			failure : function(form, action) {
				Ext.MessageBox.alert('提示', "加载相关数据失败(failure)！");
			}
		});
	},
	loads : function(properties) {
		this.load({
			params : properties.params,
			callback : function(r, options, success) {
				if (success) {
					if (typeof ( properties.success ) == 'function')
						properties.success(r, options, true);
				} else {
					Ext.MessageBox.alert('提示', "加载相关数据失败(no success)！");
				}
			},
			failure : function(form, action) {
				Ext.MessageBox.alert('提示', "加载相关数据失败(failure)！");
			}
		});
	}

});

// ///////////////////////////////////////////////////////////////////////
Ext.form.ERPComboBox = Ext.extend(Ext.form.ComboBox, {
	valueField : 'id',
	displayField : "name",
	// queryDelay:2000,
	mode : 'remote',
	triggerAction : 'all',
	blankText : '不能为空！',
	emptyText : '请输入查询值',
	allowBlank : false,
	// editable : false,
	triggerAction : 'all',
	queryParam : 'name',
	editable : true,
	readOnly : false,
	// allowBlank : false,
	forceSelection : true,
	minChars : 1,
	style : 'background:#fff1a4;',
	// style : this.allowBlank ==false?
	// 'background:#fff1a4;':'background:#ffffff;',
	// getRawValue : function() {
	//
	// var xx = Ext.form.ERPComboBox.superclass.getRawValue.call(this);
	//		
	// if (xx == '请输入查询值')
	// return "";
	// else {
	// var store = this.getStore();
	// if (store.data.length == 0)
	// return "";
	// else
	// return xx;
	// }
	//
	// },
	getValue : function() {

		var xx = Ext.form.ERPComboBox.superclass.getValue.call(this);
		// alert(xx);
		if (xx == '请输入查询值')
			return null;
		else {
			var store = this.getStore();
			if (store.data.length == 0)
				return null;
			else
				return xx;
		}

		// if (isNaN(xx)) {
		// // showErrorMsg("提示","选择数据错误");
		// return null;
		// } else{
		// if(store.data.length==0)
		// return null;
		// else
		// return xx;
		// }

	},
	initComponent : function() {
		var comboBox = this;
		Ext.form.ERPComboBox.superclass.initComponent.call(this);
		this.store.on("beforeload", function(store, options) {
			var o = store.baseParams;
			// o.id=comboBox.getValue();
			Ext.apply(o, options.params);
			this.baseParams = o;
			if (isNaN(comboBox.getValue())) {
				if ('请选择' != comboBox.getValue()) {
					this.baseParams.name = comboBox.getValue();
				}
			} else {
				if (comboBox.getValue() != null && comboBox.getValue() != "")
					this.baseParams.id = comboBox.getValue();
			}

		});
	},
	load : function(params) {
		this.store.loads(params);
	},
	// constructor:function(){
	// Ext.form.ERPComboBox.superclass.constructor.call(this);
	// alert('ERPComboBox先构造函数启动...');
	// },

	listeners : {
		keyup : function(textField, e) {
			// if (e.getKey() == 8) {
			// this.setValue(null);
		}
	},
	listeners : {
		load : function(textField, e) {
			this.setValue(null);
		}
	},
	listeners : {
		expand : function(textField, e) {
			this.store.on("beforeload", function(store, options) {
				var o = store.baseParams;
				Ext.apply(o, options.params);
				this.baseParams = o;
			})
		}

	}

});
Ext.reg('ERPcombo', Ext.form.ERPComboBox);

Ext.data.ERPComboStore = Ext.extend(Ext.data.Store, {
	autoLoad : false,
	remoteSort : true,
	// initComponent: function(){
	// alert("ERPComboStore");
	// Ext.data.ERPComboStore.superclass.initComponent.call(this);
	// alert("ERPComboStore");
	// },
	// initComponent : function(config){
	// alert("ssssssssssss");
	// Ext.Container.superclass.initComponent.call(this,config);
	// },
	// ww//
	listeners : {
		"beforeload" : function(store, options) {
			var o = store.baseParams;
			Ext.apply(o, options.params);
			this.baseParams = o;
		}
	},
	reloads : function(properties) {
		// this.on("beforeload",function(store, options) {
		// var o = store.baseParams;
		// Ext.applyIf(o, options.params);
		// this.baseParams = o;
		// }
		// );
		this.reload({
			params : properties.params,
			callback : function(r, options, success) {
				if (success) {
					if (typeof ( properties.success ) == 'function')
						properties.success(r, options);
				} else {
					Ext.MessageBox.alert('提示', "加载相关数据失败(no success)！");
				}
			},
			failure : function(form, action) {
				Ext.MessageBox.alert('提示', "加载相关数据失败(failure)！");
			}
		});
	},
	loads : function(properties) {
		// this.on("beforeload",function(store, options) {
		// var o = store.baseParams;
		// Ext.applyIf(o, options.params);
		// this.baseParams = o;
		// }
		// );
		this.load({
			params : properties.params,
			callback : function(r, options, success) {
				if (success) {
					if (typeof ( properties.success ) == 'function')
						properties.success(r, options, true);
				} else {
					Ext.MessageBox.alert('提示', "加载相关数据失败(no success)！");
				}
			},
			failure : function(form, action) {
				Ext.MessageBox.alert('提示', "加载相关数据失败(failure)！");
			}
		});
	}

});

/**
 * params={ id: name: label: url: select: }
 * 
 * @param {}
 *            params
 */
function createERPcombo(params) {
	var xx = {
		id : params.id,
		// name : params.id,
		hiddenName : params.name,
		fieldLabel : params.label,
		xtype : 'ERPcombo',
		valueField : 'id',
		displayField : "name",
		width : typeof ( params.width ) == 'undefined' ? 200 : params.width,
		forceSelection : typeof ( params.forceSelection ) == 'undefined' ? true : params.forceSelection,
		allowBlank : typeof ( params.allowBlank ) == 'undefined' ? false : params.allowBlank,
		style : typeof ( params.allowBlank ) == 'undefined' ? 'background:#fff1a4;' : ( params.allowBlank == true ? "background:#ffffff" : "background:#fff1a4" ),
		store : new Ext.data.ERPComboStore({
			autoLoad : false,
			proxy : new Ext.data.HttpProxy({
				url : params.url
			}),
			reader : new Ext.data.JsonReader({
				id : "id",
				root : 'results'
			}, Ext.data.Record.create([{
				name : 'id'
			}, {
				name : 'name'
			}])),
			listeners : {
				'load' : function() {
				}
			}
		}),
		listeners : {
			'select' : function(combo, record, index) {
				if (typeof ( params.select ) != "undefined")
					params.select(combo, record, index);
			}
		}
	};
	return xx;

}

Ext.form.ERPBoxSelect = Ext.extend(Ext.ux.form.SuperBoxSelect, {

	// ////////////////////////////////////////////////////////////
	resizable : true,
	allowAddNewData : true,
	// anchor:'100%',
	valueField : 'id',
	displayField : "name",
	// displayFieldTpl: '{name} ({id})',
	mode : 'remote',
	triggerAction : 'all',
	blankText : '不能为空！',
	emptyText : '请选择',
	allowBlank : true,
	// editable : false,
	triggerAction : 'all',
	queryParam : 'name',
	editable : true,
	readOnly : false,
	forceSelection : true,
	minChars : 1,
	style : 'background:FFFFFF;',
	forceFormValue : false,
	value : null,
	inType : 'int',
	getValue : function() {
		var xx1 = Ext.form.ERPBoxSelect.superclass.getValue.call(this);
		if (xx1 == '请选择' || xx1 == null || xx1.length == 0)
			return null;
		xx = xx1.split(",");
		for (var x = 0; x < xx.length; x++) {
			if (this.inType == 'int') {
				if (isNaN(xx[x])) {
					showErrorMsg("提示", "查询系统错误[" + xx1 + "]");
					return null;
				}
			}

		}
		return xx;
	},
	initComponent : function() {
		var comboBox = this;
		Ext.form.ERPBoxSelect.superclass.initComponent.call(this);
		// this.store.on("beforeload", function(store, options) {
		// var o = store.baseParams;
		// Ext.apply(o, options.params);
		// this.baseParams = o;
		// if (isNaN(comboBox.getValue())) {
		// if ('请选择' != comboBox.getValue()) {
		// this.baseParams.name = comboBox.getValue();
		// }
		// } else {
		// if (comboBox.getValue() != null
		// && comboBox.getValue() != "")
		// this.baseParams.id = comboBox.getValue();
		// }
		//
		// });
	},
	load : function(params) {
		this.store.loads(params);
	},

	listeners : {
		keyup : function(textField, e) {

		}
	},
	listeners : {
		load : function(textField, e) {
			this.setValue(null);
		}
	},
	listeners : {
		expand : function(textField, e) {
			this.store.on("beforeload", function(store, options) {
				var o = store.baseParams;
				Ext.apply(o, options.params);
				this.baseParams = o;
			})
		}

	}

});
Ext.reg('ERPBoxSelect', Ext.form.ERPBoxSelect);
