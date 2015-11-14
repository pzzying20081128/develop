var printPageConfig = {
	"page_220_140" : {
		"page" : {
			"width" : "200mm",
			"height" : "140mm"
		},
		"head" : {
			"width" : "205mm",
			"height" : "24mm",
			"top" : "2mm",
			"left" : "5mm"
		},
		"body" : {
			"width" : "205mm",
			"height" : "75mm",
			"top" : "27mm",
			"left" : "5mm"
		},
		"bottom" : {
			"width" : "205mm",
			"height" : "28mm",
			"top" : "107mm",
			"left" : "5mm"
		}
	}
}

function print_panel_win(url, printParams, gird) {
	var page = 1;
print_panel_wins(url, page, printParams, gird);
//	ERPAjaxRequest({
//		url : './erp_print_count.do',
//		params : {
//			id : printParams.id,
//			modue : printParams.modue
//		},
//		success : function(response, options) { // 当
//			var data = response.responseJSON;
//			var page = data.page;
//
//			if (page == 0) {// 直接打印
//				alert("没有要打印的内容");
//			} else {
//				print_panel_wins(url, page, printParams, gird);
//			}
//
//		}
//	});

}

/**
 * 
 * @param {}
 *            url
 * @param {}
 *            printPage
 * @param {}
 *            printParams
 * @param {}
 *            gird 要打印的那个GRID
 * @param {}
 *            id 要打印的中
 * @return {}
 */
function print_panel_wins(url, printPage, printParams, gird) {
	url = "./" + url + "&uuid=" + Math.random();
	var printPageConfig_ = printPageConfig.page_220_140;
	var prints = createPrinterList();
	var printPageCount = new Array();
	printPageCount[0] = {
		id : -1,
		name : "自动全部打印"
	}
	for (var i = 0; i < printPage; i++) {
		printPageCount[i + 1] = {
			id : ( i + 1 ),
			name : "第" + printPage + "-" + ( i + 1 ) + "部分"
		}
	};

	var toolbar = new Ext.Toolbar({

		width : '100%',
		bottons : [{
			xtype : 'button',
			width : 50,
			text : "打印预览"
		}]
	});
	toolbar.add("->", {
		xtype : 'label',
		text : "请选择打印机:",
		width : 200
	}, new Ext.form.ComboBox({
		store : new Ext.data.JsonStore({

			fields : ['id', 'name'],
			data : prints.print
		}),
		mode : 'local',
		width : 250,
		id : "select_print",
		displayField : 'name',
		valueField : 'name',
		triggerAction : 'all',
		value : prints.defaultPrint
	}), {
		xtype : 'tbspacer',
		width : 20
	}, {
		xtype : 'label',
		text : "打印部分",
		width : 200
	}, new Ext.form.ComboBox({
		store : new Ext.data.JsonStore({

			fields : ['id', 'name'],
			data : printPageCount
		}),
		mode : 'local',
		width : 100,
		id : "select_print_page",
		displayField : 'name',
		valueField : 'id',
		triggerAction : 'all',
		value : -1
	}), {
		xtype : 'tbspacer',
		width : 20
	},

	new Ext.Toolbar.Button({
		text : '打印',
		handler : function(item) {
			var selectPrintPage = Ext.getCmp('select_print_page').getValue();
			if (selectPrintPage === -1) {

				startPrint(3, {
					url : url,
					page : printPage,
					title : "ERP"
				}, gird, printParams);
			} else {
				staretPrintPages(3, {
					url : url,
					page : printPage,
					title : "ERP",
					printPage : selectPrintPage
				});

			}

		}
	}), {
		xtype : 'tbspacer',
		width : 20
	}, new Ext.Toolbar.Button({
		text : '打印预览',
		handler : function(item) {
			var selectPrintPage = Ext.getCmp('select_print_page').getValue();
			if (selectPrintPage === -1) {
				showErrorMsg("错误", "打印预览不能选择全部");
			} else {
				staretPrintPages(2, {
					url : url,
					page : printPage,
					title : "ERP",
					printPage : selectPrintPage
				});

			}
			//			
			// printSeeAction(2);
		}
	}),

	{
		xtype : 'tbspacer',
		width : 20
	});

	var printPanel = new Ext.Panel({
		autoScroll : false,
		autoWidth : true,
		height : 420,
		region : "center",
		defaults : {
			xtype : 'panel',
			bodyStyle : 'border-right:solid 1px #cccccc',
			height : '100%'
		},
		items : [{
			xtype : 'iframepanel',
			id : "iframepanel",
			loadMask : {
				msg : '打印模板加载中...'
			},
			deferredRender : false,
			width : '100%',
			height : 450,
			// bodyStyle:'padding:10px',
			frameConfig : {
				name : 'chatperadminForm',
				id : "printIfame"
			},
			border : false,
			frame : false,
			defaultSrc : url + "&toPage=1"
		}]
	});

	var modal_win = new Ext.ERPDefaultsWindow({
		id : 'print_panel',
		// title : '打印',
		closable : true,
		// autoWidth : true,
		width : 900,
		// maximizable:true,
		// maximized:true,
		// height : 900,// form_panel.height,130
		autoHeight : true,
		plain : true,
		resizable : false,
		layout : 'fit',
		// modal : true,
		closeAction : 'close',
		draggable : true,
		closable : true,
		constrain : true,
		autoDestroy : true,
		items : [printPanel, toolbar]
	});
	modal_win.showWin();
	return modal_win;
}

function startPrint(action, params, gird, printParams) {
	var page = params.page;
	var selectPrint = Ext.getCmp('select_print').getValue();
	if (selectPrint == null | selectPrint === "") {
		showErrorMsg("错误提示", "请选择打印机");
		return;
	}
	// 当前打印页
	var printPage = 1;
	var nextPrintPage = 2;
	var windows_ = new printWindows({
		page : page,
		printPage : printPage,
		nextPrintPage : nextPrintPage,
		url : params.url,
		title : params.title,
		selectPrint : selectPrint,
		backcall : function(params_) {
			if (printAction(action, params.title + "_" + printPage, windows_.panel, params_)) {

				windows_.closeWin();
				var x = params_.printPage;
				if (params_.page === x) {
					//showMsg("错误", "打印完成");
					updatePrintCount(gird, printParams);
					return;
				}
				startomePrint(action, params_, gird, printParams);
			} else {
				showMsg("信息", "打印失败");
				windows_.closeWin();
			}

		}
	});

}

/**
 * 
 * @param {}
 *            action
 * @param {}
 *            params
 */
function staretPrintPages(action, params) {
	var selectPrint = Ext.getCmp('select_print').getValue();
	if (selectPrint == null | selectPrint === "") {
		showErrorMsg("错误提示", "请选择打印机");
		return;
	}
	var windows_ = new printWindows({
		page : params.page,
		printPage : params.printPage,
		nextPrintPage : ( params.printPage + 1 ),
		url : params.url,
		title : params.title,
		selectPrint : selectPrint,
		backcall : function(params_) {
			if (printAction(action, params.title + "_" + params.printPage, windows_.panel, params_)) {
				 showMsg("信息","打印完成");
				windows_.closeWin();
//				updatePrintCount(gird, printParams);
			} else {
				windows_.closeWin();
			}

		}
	});

}

function updatePrintCount(gird, printParams) {
//	if (typeof ( gird ) == "undefined" || gird == null) {
//		showMsg("信息", "打印完成");
//	} else
	
//		{
//	ERPAjaxRequest({
//		url : './update_print_sum.do',
//		params : {
//			id : printParams.id,
//			modue : printParams.modue
//		},
//		success : function(response, options) { // 当
//			
//			showMsg("信息", "打印完成");
//				if (typeof ( gird ) == "undefined" || gird == null) {
//	             }else{
//	             	json = response.responseJSON;
//			       gird.updateRowfieldValue("printCount",json.printSum);
//	             }
//			
//		},
//		errors:function(){
//			
//		}
//	});
//	}
}

function startomePrint(action, params, gird, printParams) {
	var page = params.page;
	// 当前打印页
	var printPage = params.nextPrintPage;
	var nextPrintPage = printPage + 1;
	var windows_ = new printWindows({
		page : page,
		printPage : printPage,
		title : params.title,
		nextPrintPage : nextPrintPage,
		url : params.url,
		selectPrint : params.selectPrint,
		backcall : function(params_) {
			if (printAction(action, params_.title + "_" + params_.printPage, windows_.panel, params_)) {
				windows_.closeWin();
				var x = params_.printPage;
				if (page == x) {
					windows_.closeWin();
					updatePrintCount(gird, printParams);
					// showMsg("信息","打印完成");
					return;
				} else {
					startomePrint(action, params_);
				}

			} else {
				showMsg("信息", "打印失败");
				windows_.closeWin();
			}

		}
	});
}

function printWindows(params) {
	this.closeWin = function() {
		this.windows.close();
	};
	this.shownWin = function() {
		this.windows.show();
	};

	this.panel = new Ext.ux.ManagedIFramePanel({
		xtype : 'iframepanel',
		loadMask : {
			msg : '数据加载中... ...'
		},
		deferredRender : false,
		width : '100%',
		height : 400,
		frameConfig : {
			name : 'printIfame',
			id : "printIfame"
		},
		border : false,
		frame : false,
		defaultSrc : params.url + "&toPage=" + params.printPage + "&page=" + params.page + "&selectPrint=" + params.selectPrint,
		listeners : {
			"documentloaded" : function() {
				// print.close();
				params.backcall(params);

			}
		}

	});

	this.windows = new Ext.ERPDefaultsWindow({
		title : params.title,
		closable : true,
		width : 900,
		autoHeight : 400,
		plain : true,
		resizable : false,
		layout : 'fit',
		closeAction : 'close',
		draggable : true,
		closable : true,
		constrain : true,
		autoDestroy : true,
		items : [this.panel]
	});
	this.windows.show();
}

var printPageConfig = {
	"page_220_140" : {
		"page" : {
			"width" : "200mm",
			"height" : "140mm"
		},
		"head" : {
			"width" : "205mm",
			"height" : "24mm",
			"top" : "2mm",
			"left" : "5mm"
		},
		"body" : {
			"width" : "205mm",
			"height" : "75mm",
			"top" : "27mm",
			"left" : "5mm"
		},
		"bottom" : {
			"width" : "205mm",
			"height" : "28mm",
			"top" : "107mm",
			"left" : "5mm"
		}
	}
}

function createPrinterList() {
	var arrTemp = new Array();
	var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
	var iPrinterCount = LODOP.GET_PRINTER_COUNT();
	// alert(iPrinterCount);
	for (var i = 0; i < iPrinterCount; i++) {
		// alert(LODOP.GET_PRINTER_NAME(i));
		arrTemp[i] = {
			id : i,
			name : LODOP.GET_PRINTER_NAME(i)
		}
	};

	var defaultPrint = LODOP.GET_PRINTER_NAME(-1);

	return {
		print : arrTemp,
		defaultPrint : defaultPrint

	};
};

// 0 PRINT 1 PRINT_DESIGN type 1 直接打印 2 打印预览 3 导出 PDF
function prn_preview(doc, head, tables, bottom, printPageConfig, action, type, title, params) {
	var LODOP1 = CreateOneFormPage(doc, head, tables, bottom, printPageConfig, title);

	// LODOP1.PREVIEW();
	if (typeof ( action ) == "undefined" || action === 0) {
		// alert("prn_preview "+params.selectPrint+"|");
		// LODOP1.SET_PRINTER_INDEXA(params.selectPrint);
		// return LODOP1.PRINT();
		// if(type ==1){
		// LODOP1.SET_PRINTER_INDEX(-1);//指定打印机
		// LODOP1.PRINT();
		// }else
		if (type == 3) {
			// Adobe PDF
			// LODOP1.SET_PRINTER_INDEXA("Adobe PDF");//指定打印机
			// return LODOP1.PRINT();
			LODOP1.SET_PRINTER_INDEXA(params.selectPrint);
			return LODOP1.PRINT();
		} else {
			LODOP1.PREVIEW();
//		    LODOP1.PRINT_DESIGN();
		}
	} else
		LODOP1.PRINT_DESIGN();

};

var height_ = 0;
var prtHtml = "";

function CreateOneFormPage(doc, head, tables, bottom, printPageConfig, title) {
	// alert("CreateOneFormPage "+title);
	var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
	LODOP.PRINT_INIT(title);
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>"
	LODOP.SET_PRINT_PAGESIZE(1, printPageConfig.page.width, printPageConfig.page.height, "xxxx");

	LODOP.ADD_PRINT_TABLE(printPageConfig.body.top, printPageConfig.body.left, printPageConfig.body.width, printPageConfig.body.height, strStyle + tables);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(printPageConfig.head.top, printPageConfig.head.left, printPageConfig.head.width, printPageConfig.head.height, head);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.ADD_PRINT_HTM(printPageConfig.bottom.top, printPageConfig.bottom.left, printPageConfig.bottom.width, printPageConfig.bottom.height, bottom);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Horient", 3);
	return LODOP;
};

// 1 直接打印 2 打印预览
function printAction(type, title, panel, params_) {
	// windows_.panel.getFrameDocument() , windows_.panel.getWindow()
	var ifm = panel.getFrameWindow();
	var doc = panel.getFrameDocument();

	var head = doc.getElementById("head").innerHTML;
	var tables = doc.getElementById("tables").innerHTML;
	var bottom = doc.getElementById("bottom").innerHTML;
	// alert(head);
	// alert(tables);
	// alert( ifm.printPageSet );
	// alert(bottom);
	var printPageConfig_ = printPageConfig.page_220_140;
	// alert(ifm.printPageSet );
	if (typeof ( ifm.printPageSet ) != 'undefined' && typeof ( ifm.printPageSet() ) == "object") {
		printPageConfig_ = ifm.printPageSet();
	} else {
		printPageConfig_ = printPageConfig.page_220_140;
	}
	// alert(printPageConfig_.page.width +" "+printPageConfig_.page.height );
	return prn_preview(doc, head, tables, bottom, printPageConfig_, 0, type, title, params_);
}

function printSeeAction(action, title) {
	// alert("printAction "+title);
	var ifm;
	var doc;
	if (document.all) {// IE
		ifm = document.frames["printIfame"];
		doc = ifm.document;
	} else {// Firefox
		ifm = document.getElementById("printIfame");
		doc = ifm.contentDocument;
	}

	var head = doc.getElementById("head").innerHTML;
	var tables = doc.getElementById("tables").innerHTML;
	var bottom = doc.getElementById("bottom").innerHTML;

	var printPageConfig_ = printPageConfig.page_220_140;

	if (typeof ( ifm.contentWindow.printPageSet ) != 'undefined' && typeof ( ifm.contentWindow.printPageSet() ) == "object") {
		printPageConfig_ = ifm.contentWindow.printPageSet();
	} else {
		printPageConfig_ = printPageConfig.page_220_140;
	}
	// alert(printPageConfig_.page.width +" "+printPageConfig_.page.height );
	prn_preview(doc, head, tables, bottom, printPageConfig_, 0, action, title);

}
