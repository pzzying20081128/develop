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