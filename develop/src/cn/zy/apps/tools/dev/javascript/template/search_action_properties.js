/**
 * 主要用户设置查询的一些参数
 */
var  ${javaScript.module}_search_params = {
	
	verification : function(from) {
//                         var submitValues1 = from.getValues();
//						// 对将要提交的参数进行过滤，去掉emptyText文字
//						for (var param in submitValues1) {
//							if (from.findField(param) && from.findField(param).emptyText == submitValues1[param]) {
//							    submitValues1[param] = '';
//							}
//						}
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