/**
 * 主要用户设置查询的一些参数
 */
var  ${javaScript.module}_search_params = {
	formField : [
		
	{ // 第一排
		layout : 'column',
		baseCls : 'x-plain',
		items : [
		{
			columnWidth : .18,
			layout : 'form',
			defaultType : 'textfield',
			baseCls : 'x-plain',
			hideLabels : true,
			items : [{
			    // 复选框
				id : 'staffSearchBean.selectName',
				xtype : "checkbox",
				boxLabel : "员工姓名"
			}]
		}, {
			columnWidth : .82,
			layout : 'form',
			baseCls : 'x-plain',
			defaultType : 'textfield',
			hideLabels : true,
			defaults : {
				width : 360
			},
			items : [{
				// 查询框
				id : 'staffSearchBean.name',
				listeners : {
					"change" : function(field) {
					}
				}
			}]
		}
	 ]
	}// end
    
// ,{ // start
// layout : 'column',
// baseCls : 'x-plain',
// items : [{
// columnWidth : .18,
// layout : 'form',
// defaultType : 'textfield',
// baseCls : 'x-plain',
// hideLabels : true,
// items : [{
// id : 'staffSearchBean.selectName',
// xtype : "checkbox",
// boxLabel : "员工姓名"
// }]
// }, {
// columnWidth : .82,
// layout : 'form',
// baseCls : 'x-plain',
// defaultType : 'textfield',
// hideLabels : true,
// defaults : {
// width : 360
// },
// items : [{
// id : 'staffSearchBean.name',
// listeners : {
// "change" : function(field) {
// }
// }
// }]
// }]
// }// end
	
	],
	verification : function() {
 if (Ext.getCmp('staffSearchBean.selectName').getValue() == true && 
                                                     ( Ext.getCmp('staffSearchBean.name').getValue() == null ||Ext.getCmp('staffSearchBean.name').getValue() == "" )) {
 showErrorMsg("提示", "请输入员工姓名信息！");
 return false;
 }

	
		return true;
	},
	params : function() {
		var params = {
			optType : "search",
			'staffSearchBean.selectName' : Ext.getCmp('staffSearchBean.selectName').getValue(),
			'staffSearchBean.name' : Ext.getCmp('staffSearchBean.name').getValue()
		}
		return params;
	}

}