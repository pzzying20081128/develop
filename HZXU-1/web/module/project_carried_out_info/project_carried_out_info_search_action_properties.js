/**
 * 主要用户设置查询的一些参数
 */
var  project_carried_out_info_search_params = {
	
	verification : function(from) {
		
		return true;
	},
	params : function() {
		var params = {
			optType : "search",
			"searchBean.status":"有效"
		}
		return params;
	}

}