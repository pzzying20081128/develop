function createModule(node, userId, params) {
	var moduleId = node.id;
	var moduleName = node.text;
	//if (moduleId == "stock_report")return;
	var loadjs = ["./module/" + moduleId + "/" + moduleId + "_index.js", "./module/" + moduleId + "/" + moduleId + "_action_properties.js", "./module/" + moduleId + "/" + moduleId + "_create_windows.js", "./module/" + moduleId + "/" + moduleId + "_save_update_windows.js", "./module/" + moduleId + "/" + moduleId + "_search_action_properties.js", "./module/" + moduleId + "/" + moduleId + "_search_windows.js", "./module/" + moduleId + "/" + moduleId + "_update_windows.js"

	];
	/**
	if (moduleId.substring(0, 5) == 'base_' || moduleId.substring(0, 4) == 'sys_' || moduleId == 'sys_opt_history'

	|| moduleId == 'store_product_info_stock' || moduleId == 'stock_invoice' || moduleId == 'stock_adjust_bill'

	|| moduleId == "stock_in_out_detail_report"

	) {

	} else {

	}
       **/

	// alert("|"+moduleId+"|");
	switch (moduleId) {
		case "project_prophase_info" : {
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_index.js");
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_action_properties.js"); 
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_create_windows.js");
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_save_update_windows.js");
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_search_action_properties.js");
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_search_windows.js");
			loadjs.push("./module/" + moduleId + "/project_prophase_work_content/project_prophase_work_content_update_windows.js");

		

			break;
		}
		case "project_carried_out_info" : {
			
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_index.js");
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_action_properties.js"); 
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_create_windows.js");
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_save_update_windows.js");
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_search_action_properties.js");
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_search_windows.js");
			loadjs.push("./module/" + moduleId + "/project_year_investment_plan/project_year_investment_plan_update_windows.js");
			
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_index.js");
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_action_properties.js"); 
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_create_windows.js");
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_save_update_windows.js");
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_search_action_properties.js");
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_search_windows.js");
			loadjs.push("./module/" + moduleId + "/project_month_investment_plan/project_month_investment_plan_update_windows.js");

			break;
		}

		case "store_product_info_stock" : {

			break;
		}
		case "stock_invoice_reconcile" : {
			// alert("|"+moduleId+"|");

			break;
		}

		case "stock_invoice" : {
			// alert("|"+moduleId+"|");

			break;
		}

		case "stock_payment_reconcile" : {
			// alert("|"+moduleId+"|");

			break;
		}
			// stock_payment_reconcile
		case "stock_payment" : {

			break;

		}

		case "base_store_info" : {

			break;
		}

		default : {

		}
	}

	Ext.Loader.load(loadjs, function(success) {
		eval("create_" + moduleId + "_window" + "( '" + moduleId + "','" + moduleName + "')");
	});

}
