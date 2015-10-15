function showErrorMsg(title, message) {
	var msg = Ext.MessageBox.show({
		title : title,
		buttons : Ext.MessageBox.OK,
		// msg : ' <div style="width:95%,font-size: 12px;margin-left:
		// 8px;padding-left: 8px;text-align:center">' + message + '</div>',
		msg : message,
		width : 350,
		modal : true,
		icon : Ext.Msg.ERROR
	});
}

/**
 * params={ id: name: label: url: select: }
 * 
 * @param {}
 *            params
 */
function createLocalCombo(params) {
	var xx = {
		// itemid : params.id,
		itemid : 'combobox_type',
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
		allowBlank : typeof ( params.allowBlank ) == 'undefined' ? false : params.allowBlank,
		style : typeof ( params.allowBlank ) == 'undefined' ? NoAllowBlankStyle : ( params.allowBlank == true ? AllowBlankStyle : NoAllowBlankStyle ),
		value : typeof ( params.defaultValue ) == 'undefined' ? null : params.defaultValue,
		store : new Ext.data.SimpleStore({
			fields : ['id', "value"],
			// data : [[0, "否"], [1, "是"]]
			data : params.storeData,
			listeners : params.listeners
		}

		// combo.setValue(1);
		)
		,
		// store:new Ext.data.SimpleStore(
		// params.storeValue
		// ),

	};
	return xx;

}

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