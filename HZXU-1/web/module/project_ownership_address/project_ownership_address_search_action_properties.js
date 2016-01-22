/**
 * 主要用户设置查询的一些参数
 */
var  project_ownership_address_search_params = {
	
	verification : function(from) {
		
		return true;
	},
	params : function() {
		var params = {
			optType : "search",
		}
		return params;
	}

}