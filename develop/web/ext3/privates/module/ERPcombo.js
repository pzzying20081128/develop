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
	// style : 'background:#fff1a4;',
	initComponent : function() {
		var comboBox = this;
		Ext.form.ERPComboBox.superclass.initComponent.call(this);
		this.store.on("beforeload", function(store, options) {
			var o = store.baseParams;
			Ext.apply(o, options.params);
			this.baseParams = o;

		});
	},
	load : function(params) {
		this.store.loads(params);
	},
	// constructor:function(){
	// Ext.form.ERPComboBox.superclass.constructor.call(this);
	// alert('ERPComboBox先构造函数启动...');
	// },

	clear : function() {
		this.clearValue();
	},

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

function createERPcombo(params) {
	var xx = {
		id : params.id,
		// name : params.id,
		hiddenName : params.name,
		fieldLabel : params.fieldLabel,
		// xtype : 'ERPcombo',
		valueField : 'id',
		displayField : "name",
		width : typeof ( params.width ) == 'undefined' ? 200 : params.width,
		forceSelection : typeof ( params.forceSelection ) == 'undefined' ? true : params.forceSelection,
		allowBlank : typeof ( params.allowBlank ) == 'undefined' ? false : params.allowBlank,
		style : typeof ( params.allowBlank ) == 'undefined' ? NoAllowBlankStyle : ( params.allowBlank == true ? AllowBlankStyle : NoAllowBlankStyle ),
		store : new Ext.data.ERPComboStore({
			autoLoad : typeof ( params.autoLoad ) == 'undefined' ? false : params.autoLoad,
			baseParams : params.params,
			proxy : new Ext.data.HttpProxy({
				url : params.url
				,
				// params:params.params
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
					if (typeof ( params.load ) != "undefined")
						params.load();
				}
			}
		}),
		listeners : {
			'select' : function(combo, record, index) {
				if (typeof ( params.select ) != "undefined")
					params.select({
						combo : combo,
						record : record,
						index : index
					});
			}
		}
	};

	sss = new Ext.form.ERPComboBox(xx);

	return sss;

}

// ////////////////////////////////////////////////////////////////////////////////////////
Ext.form.ERPBoxSelect = Ext.extend(Ext.ux.form.SuperBoxSelect, {
	resizable : true,
	allowAddNewData : true,
	valueField : 'id',
	displayField : "name",
	mode : 'remote',
	triggerAction : 'all',
	blankText : '不能为空！',
	emptyText : '请输入查询值',
	allowBlank : true,
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
	initComponent : function() {
		var comboBox = this;
		Ext.form.ERPBoxSelect.superclass.initComponent.call(this);
		this.store.on("beforeload", function(store, options) {
			var o = store.baseParams;
			Ext.apply(o, options.params);
			this.baseParams = o;

		});
	},
	load : function(params) {
		this.store.loads(params);
	}
	,

	// listeners : {
	// keyup : function(textField, e) {
	//
	// }
	// },
	// listeners : {
	// load : function(textField, e) {
	// this.setValue(null);
	// }
	// },
	// listeners : {
	// expand : function(textField, e) {
	// this.store.on("beforeload", function(store, options) {
	// var o = store.baseParams;
	// Ext.apply(o, options.params);
	// this.baseParams = o;
	// })
	// }
	//
	// }
});
Ext.reg('ERPBoxSelect', Ext.form.ERPBoxSelect);

function createERPBoxSelect(params) {
	var xx = {
		id : params.id,
		// name : params.id,
		hiddenName : params.name,
		fieldLabel : params.fieldLabel,
		xtype : 'ERPBoxSelect',
		valueField : 'id',
		displayField : "name",
		width : typeof ( params.width ) == 'undefined' ? 200 : params.width,
		// forceSelection : typeof ( params.forceSelection ) == 'undefined' ?
		// true : params.forceSelection,
		allowBlank : typeof ( params.allowBlank ) == 'undefined' ? false : params.allowBlank,
		style : typeof ( params.allowBlank ) == 'undefined' ? NoAllowBlankStyle : ( params.allowBlank == true ? AllowBlankStyle : NoAllowBlankStyle ),
		store : new Ext.data.ERPComboStore({
			autoload : typeof ( params.autoload ) == 'undefined' ? false : params.autoload,
			baseParams : params.params,
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
					if (typeof ( params.load ) != "undefined")
						params.load();
				}
			}
		}),
		listeners : {
			'select' : function(combo, record, index) {
				if (typeof ( params.select ) != "undefined")
					params.select({
						combo : combo,
						record : record,
						index : index
					});
			}
		}
	};

	sss = new Ext.form.ERPBoxSelect(xx);

	return sss;

}

function createERPlocalBoxSelect(params) {
	var xx = {
		id : params.id,
		// name : params.id,
		hiddenName : params.name,
		fieldLabel : params.fieldLabel,
		mode : 'local',
		xtype : 'ERPBoxSelect',
		valueField : 'id',
		displayField : "name",
		editable : false,
		width : typeof ( params.width ) == 'undefined' ? 200 : params.width,
		allowBlank : typeof ( params.allowBlank ) == 'undefined' ? false : params.allowBlank,
		style : typeof ( params.allowBlank ) == 'undefined' ? NoAllowBlankStyle : ( params.allowBlank == true ? AllowBlankStyle : NoAllowBlankStyle ),
		defaultValue : typeof ( params.defaultValue ) == 'undefined' ? null : params.defaultValue,
		reset : function() {
			if (this.clearFilterOnReset && this.mode == 'local') {
				this.store.clearFilter();
			}
			Ext.form.ComboBox.superclass.reset.call(this);
			this.setValue(this.defaultValue);
		},
		store : new Ext.data.SimpleStore({
			fields : ['id', "name"],
			data : params.localdata,
			listeners : {
				'load' : function() {
					if (typeof ( params.load ) != "undefined")
						params.load();
				}
			}
		}),
		listeners : {
			'select' : function(combo, record, index) {
				if (typeof ( params.select ) != "undefined")
					params.select({
						combo : combo,
						record : record,
						index : index
					});
			}
		}
	};

	sss = new Ext.form.ERPBoxSelect(xx);

	return sss;

}
