Ext.override(Ext.grid.GridView, {
	getColumnStyle : function(colIndex, isHeader) {
		var colModel = this.cm, colConfig = colModel.config, style = isHeader ? '' : colConfig[colIndex].css || '', align = colConfig[colIndex].align;

		if (Ext.isChrome) {
			style += String.format("width: {0};", parseInt(this.getColumnWidth(colIndex)) - 2 + 'px');
		} else {
			style += String.format("width: {0};", this.getColumnWidth(colIndex));
		}

		if (colModel.isHidden(colIndex)) {
			style += 'display: none; ';
		}

		if (align) {
			style += String.format("text-align: {0};", align);
		}

		return style;
	}
});

Ext.grid.ColumnModel.override({
	getTotalWidth : function(includeHidden) {
		var off = 0;
		if (Ext.isChrome) {
			off = 2;
		};
		if (!this.totalWidth) {
			this.totalWidth = 0;
			for (var i = 0, len = this.config.length; i < len; i++) {
				if (includeHidden || !this.isHidden(i)) {
					this.totalWidth += this.getColumnWidth(i) + off;
				};
			};
		};
		return this.totalWidth;
	}
});

Ext.data.ERPStore = Ext.extend(Ext.data.Store, {
	remoteSort : true,
	listeners : {
		"beforeload" : function(store, options) {
			//
			// // if (typeof ( store.lastOptions ) != "undefined") {
			// // if (typeof ( store.lastOptions.params ) != "undefined") {
			// // var o = store.lastOptions.params;
			// // Ext.apply(o, options.params);
			// // this.lastOptions.params = o;
			// // } else {
			// // this.lastOptions.params = options.params;
			// // }
			// // } else {
			// // this.lastOptions = {
			// // params : options.params
			// // }
			// // }
			//
			var o = store.baseParams;
			Ext.apply(o, options.params);
			this.baseParams = o;
		}
	}
});

Ext.grid.ERPGridPanel = Ext.extend(Ext.grid.GridPanel, {
	region : 'north',
	split : true,
	height : '100%',
	width : '100%',
	columnLines : true,
	stripeRows : true,
	loadMask : true,
	inits : false,
	savecol : false,
	checkboxColumn : null,
	// detailGrid : null,
	// 行双击 对应的是toolbbr 那个 ID
	rowdblclickKey : null,
	powerMap : null,
	isAdmin : false,
	setPowerList : function(powerList_) {
		var map = new Map();
		if (powerList_ == null)
			return;
		for (var i = 0; i < powerList_.length; i++) {
			map.put(powerList_[i].powerName, powerList_[i]);
		}
		this.powerMap = map;

	},
	setIsAdmin : function(isAdmin_) {
		this.isAdmin = isAdmin_;
	},
	getPowerMap : function() {
		var grid = this.getGrid();
		return grid.powerMap;
	},
	getisAdmin : function() {
		var grid = this.getGrid();
		return grid.isAdmin;
	},
	searchPower : function(key) {
		var _map_ = this.getPowerMap();
		power = _map_.get(key);
		return power;
	},

	openAllButton : function(isOpen) {
		var _map_ = this.getPowerMap();
		var tbar = this.getTopToolbar();
		if (typeof ( tbar ) != 'undefined' && typeof ( tbar.items ) != 'undefined' && typeof ( tbar.items.items ) != 'undefined') {

			var items = tbar.items.items;
			for (var i = 0; i < items.length; i++) {
				var opt = items[i];
				if (typeof opt.key == "undefined")
					continue;
				if (isOpen == true) {
					opt.enable();
				} else
					opt.disable()
			}
		}

	},

	openButton : function(params) {
		var grid = this.getGrid();
		var isAdmin = grid.isAdmin;
		if (isAdmin == 1) {
			this.openAllButton(true);
		} else {
			var _map_ = this.getPowerMap();
			var tbar = this.getTopToolbar();
			var items = tbar.items.items;
			for (var i = 0; i < items.length; i++) {
				var opt = items[i];
				if (typeof ( opt.key ) == 'undefined')
					continue;
				var power = _map_.get(opt.key);
				if (power == null) {
					alert(opt.text + "  is set error ! ");
				} else {
					if (power.isUse != 1) {
						opt.disable();
					} else { // ==1
						opt.enable();
						// if (params.check == true) { // 审核状态
						// if (opt.check == 'hide')
						// opt.disable();
						// else
						// opt.enable();
						// } else {
						// opt.enable();
						// }
					}
				}
			}
		}
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
	updateRow : function(object) {
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
		// this.store.on("beforeload", function(store, options) {
		// // if (typeof ( store.lastOptions ) != "undefined") {
		// // if (typeof ( store.lastOptions.params ) != "undefined") {
		// // var o = store.lastOptions.params;
		// // Ext.apply(o, options.params);
		// // this.lastOptions.params = o;
		// // } else {
		// // this.lastOptions.params = options.params;
		// // }
		// // } else {
		// // this.lastOptions = {
		// // params : options.params
		// // }
		// // }
		// Ext.apply(store.proxy.extraParams, options.params);
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
			// async: false, //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
			success : function(result) {
				var isSelect = result.result.isSelect;
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
		var colModel_ = this.colModel;
		var moduleId = this.moduleId;
		var moduleName = this.moduleName;
		if (typeof ( moduleId ) == "undefined") {
			showErrorMsg("系统错误", "ERPGridPanel saveColModule not find moduleId !moduleId:TREEID");
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
			url : './col_chonig_grid.do',
			params : {

				module_name : moduleName,// 'SYSTEM_USER',
				module_key : moduleId,
				data_indexs : data_indexs,
				col_names : col_names,
				col_hiddens : col_hiddens,
				col_widths : col_widths,
				col_indexs : col_indexs
			},
			success : function(response, options) {
			}
		});

		// for (var i = 1; i < cmConfigs.length; i++) {
		// var cmConfig = cmConfigs[i];
		// var hidden = ( cmConfig.hidden == null ) ? '0' : (
		// cmConfig.hidden
		// ==
		// true ? 1 : 0 );
		// var dataIndex_ = cmConfig.dataIndex;
		// Ext.Ajax.request({
		// url : 'sysuser/col_move_opt.action',
		// params : {
		// module_name : moduleId,// 'SYSTEM_USER',
		// old_colIndex : 0,
		// new_colIndex : i,
		// dataIndex : dataIndex_,
		// col_name : cmConfig.header,
		// is_hidden : hidden
		// },
		// success : function(response, options) {
		// }
		// });
		// }
	},

	removeOptBt : function() {

	},

	setPower : function(modulePower_) {
		var moduleId = this.moduleId;
		// var setPowerList_ = this.setPowerList;
		var grid = this.getGrid();
		ERPAjaxRequest({
			url : './power.do',
			params : {
				moduleId : moduleId
			},
			success : function(result) {

				var json = result.result;
				var powerMap = json.powerMap;
				// var grids_ = grids[i];
				var power = powerMap[moduleId];
				var isAdmin_ = json.isroot;
				grid.setIsAdmin(isAdmin_);
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
				grid.openButton({
					check : true
				});
				return json;
			}
		});

	},

	addSetButton : function(params) {
		var grid = this.getGrid();
		if (typeof ( params.addSet ) != "undefined") {
			var toolbar = grid.getTopToolbar();
			if (typeof ( toolbar ) != "undefined") {
				toolbar.addButton(new Ext.Toolbar.Fill());
				toolbar.addButton({
					xtype : "tbbutton",
					text : "保存设置",
					disabled : false,
					// keyBinding : createEditKey(),
					handler : function(bt) {
						if (typeof ( params.addSet.grids ) != "undefined") {
							var grids = params.addSet.grids;
							for (var i = 0; i < grids.length; i++) {
								grids[i].saveColModule();
							}
							showMsg("信息", "保存设置成功");
						}
					}
				});
			}
		};
	},

	initPanel : function(prams) {
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
		this.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {

			if (typeof ( prams ) == "undefined")
				return;
			dataJson = ( typeof ( r.json ) == "undefined" ? r.data : r.json );
			if (typeof ( prams.select ) != "undefined" && typeof ( prams.select ) == "function") {
				prams.select(r.id, dataJson, sm, rowIdx, r);
			}

			// if (typeof ( prams ) == "undefined")
			// return;
			// dataJson =( typeof(r.json)=="undefined" ? r.data :r.json );
			// if (typeof ( Ext.getCmp(moduleId + '_checked') ) == "undefined")
			// {
			//
			// if (typeof ( prams.select ) != "undefined" && typeof (
			// prams.select ) == "function") {
			// prams.select(r.id, dataJson, sm, rowIdx, r);
			// }
			// return;
			// } else {
			//
			// Ext.getCmp(moduleId + '_checked').setDisabled(false);
			// var checked = null;
			// if (typeof ( prams ) != 'undefined') {
			// if (typeof ( prams.status ) == 'function')
			// checked = prams.status(r.data);
			// if (checked == null) {
			// showErrorMsg("错误", "状态取值错误[没设置 【status】！");
			// }
			//
			// if (typeof ( checkName_ ) == "undefined" || checkName_ == null ||
			// checkName_ === "")
			// checkName_ = "审核";
			// if (typeof ( selModule ) == 'undefined') {
			// if (checked == "checked" || checked == 1) {
			// Ext.getCmp(moduleId + '_checked').setText('取消' + checkName_);
			// } else {
			// Ext.getCmp(moduleId + '_checked').setText(checkName_);
			// }
			// if (typeof ( prams.select ) == "function") {
			//
			// }
			// } else {
			// var selection_rows =
			// thisGrid.getSelectionModel().getSelections();
			// var xx = null;
			// for (var index = 0; index < selection_rows.length; index++) {
			// checked = prams.status(selection_rows[index].data);
			// if (xx != null && xx != checked) {
			// xx = -1;
			// break;
			// } else {
			// xx = checked;
			// }
			// }
			// if (xx == -1) {
			// showErrorMsg("错误", "所选择的单据出现二种以上状态!");
			// Ext.getCmp(moduleId + '_checked').setDisabled(true);
			// } else {
			// if (checked == "checked" || checked == 1) {
			// Ext.getCmp(moduleId + '_checked').setText('取消' + checkName_);
			// } else {
			// Ext.getCmp(moduleId + '_checked').setText(checkName_);
			// }
			// }
			// }
			// prams.select(r.id, dataJson , sm, rowIdx, r);
			//
			// }// if(typeof(prams)!='undefined')
			//
			// }
			// /
		});
		// //////////////////////////////////////////////////////////////////////////////////
		ERPAjaxRequest({
			url : './sysuser/fetch_hidden_col.do',
			params : {
				module_name : moduleId
			},

			success : function(result) {
				var cmConfigs = colModel_.config;
				var json = result.result;
				var hiddenCount = 0;
				for (var i = 0; i < json.userGridConfigs.length; i++) {

					for (var j = 1; j < cmConfigs.length; j++) {
						var cmConfig = cmConfigs[j];
						if (cmConfig.dataIndex == json.userGridConfigs[i].colDataIndex) {
							if (json.userGridConfigs[i].hidden == 1) {
								colModel_.setHidden(j, true);
								hiddenCount++;
							} else {
								colModel_.setHidden(j, false);
							}
							colModel_.moveColumn(j, json.userGridConfigs[i].colIndex);
							colModel_.setColumnWidth(json.userGridConfigs[i].colIndex, json.userGridConfigs[i].colWidth, true);
							break;
						}
					}
					if (hiddenCount == json.userGridConfigs.length)
						colModel_.setHidden(1, false);
				}

			}
		});

		// ///////////////////////////////////////////////////////////////////////////////////

	},// end

	load : function(loadParams) {
		var store_ = this.store;
		store_.removeAll();
		store_.baseParams = {};

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

		// 回调
		// records参数表示获得的数据，
		// options表示执行load()时传递的参数，success表示是否加载成功。
		store_.load({
			params : loadParams.params,
			callback : function(r, options, success, action) {
				if (success == false) {
					if (loadParams != null) {
						var jsonData = store_.reader.jsonData;
						if (typeof ( loadParams.errors ) == "function") {
							if (typeof ( jsonData ) === "undefined" || typeof ( jsonData.msg ) === "undefined") {
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
										// loadParams.errors(r, options,
										// jsonData.msg);
										loadParams.errors({
											'r' : r,
											"options" : options,
											"result" : jsonData
										});

									};
								}
							};
						} else {
							if (typeof ( jsonData ) === "undefined" || typeof ( jsonData.msg ) === "undefined") {
								showErrorMsg("失败", "数据请求失败【未知错误  -1 】！");
							} else if (jsonData.msg == 1001 || jsonData.msg == '1001') {
								Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {
									window.location.href = "./";
								});
							} else {
								Ext.MessageBox.alert("失败", "数据请求失败【系统错误 -3 】！");
							}
						};
					} else {
						showErrorMsg("失败", "数据请求失败【未知错误 -4】！");
					};
				} else {
					if (typeof ( loadParams.success ) == "function") {
						var jsonData = store_.reader.jsonData;
						// loadParams.success(r, options, jsonData);
						loadParams.success({
							'r' : r,
							"options" : options,
							"result" : jsonData
						});
					}
				};
			}
		});
	},
	reload : function(loadParams) {
		var store = this.store;
		store.removeAll();
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
							if (typeof ( jsonData ) === "undefined" || typeof ( jsonData.msg ) === "undefined") {
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
										// loadParams.errors(r, options,
										// jsonData.msg);
										loadParams.errors({
											'r' : r,
											"options" : options,
											"result" : jsonData
										});
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
							// loadParams.success(r, options);
							var jsonData = store.reader.jsonData;
						loadParams.success({
							'r' : r,
							"options" : options,
							"result" : jsonData
						});
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