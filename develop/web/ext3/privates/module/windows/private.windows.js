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
	autoHeight : true,
	// form_panel.height,
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