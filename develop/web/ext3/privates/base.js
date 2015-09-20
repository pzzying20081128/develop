// var mainScrollerMenu = new Ext.ux.TabScrollerMenu({
// maxText : 15,
// pageSize : 5
// });

function updateUserPasswd() {
	var loadjs = ["./ext3/privates/update_password/update_passwd_windows.js"];
	Ext.Loader.load(loadjs, function(success) {
		updateUserPasswdWindows();
	});
}

function IndexPanel(userId, userName, loginUser, regGUest, properties) {

	Ext.Msg.buttonText.yes = '确定';
	Ext.Msg.buttonText.no = "取消";
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	var win, wp, window, tabs, grid;
	// ==========头页面个
	var north = new Ext.Panel({
		region : "north",
		margins : "3 3 0 3",
		height : 68,
		html : '<table id="img"  width="100%" height="68" border="0" cellpadding="0" cellspacing="0" >' + '<tr><td width="30%">&nbsp;</td><td width="70%" valign="bottom">' + '<div align="right" style="font-size: 16px;color: #0FFFFF;font-weight: bold;padding-bottom: 15px"  id="welcome">'

		+ regGUest

		+ ' 欢迎：'

		+ loginUser

		+ '（' + userName + '） '

		+ '<a style="cursor: pointer;"  onclick="updateUserPasswd()" >修改密码</a>&nbsp; <a style="cursor: pointer;"  onclick="unlogin()" >退出 </a>  &nbsp;&nbsp;&nbsp;</div></td></tr></table>',

		bodyStyle : "background:url(ext3/privates/images/top.gif)  left top no-repeat;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale');-moz-background-size:100% 100%;background-size:100% 100%"
	});

	// ==========主页面
	var center = new Ext.Panel({
		id : 'center_panel',
		region : "center",
		margins : "3 3 3 0",
		width : '100%',
		// html : "<img src='images/work_area.gif' id='img'>",
		bodyStyle : "background:url(ext3/privates/images/work_area.gif)  left top no-repeat;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale');-moz-background-size:100% 100%;background-size:100% 100%"

		// bbar :{
		// items : [{
		//			
		// xtype : "tbbutton",
		// text : "增加",
		// //keyBinding : createCreateKey(),
		// handler : function(bt) {
		// test_tools_create_windows(moduleId, moduleName, {
		// grid : mainGridModule,
		//				
		// });
		// }
		// }]
		// }
	});

	// 树
	var root = new Ext.tree.AsyncTreeNode({
		id : "root",
		text : "根节点",
		loader : new Ext.tree.TreeLoader({
			preloadChildren : true,
			dataUrl : './showUserPowerMeuns.jhtml',
			listeners : {
				//load( This, node, response )
				'load' : function(this_, node, response) {
					    var responseText    =    response.responseText;
					    var json = Ext.decode(responseText);
					    if(json.success ==false  && json.msg==1001 ){
					    	   tohome();
					    }
					
				},
		
			}
		})
	});
	var tree = new Ext.tree.TreePanel({
		root : root,// 设置为根节点
		animate : true,
		rootVisible : false,// 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		autoWidth : true,
		autoHeigth : true,
		listeners : {
			'dblclick' : function(node) {
			},
			'click' : function(node) {
				properties.openModule(node, userId);
				// node.setIcon("./ext3/privates/images/top.gif");
				// createModule(node,userId,ipaddress);
			}
		}
	});

	// ==========左页面
	var west = new Ext.Panel({
		region : "west",
		split : true, // 出现拖动条
		width : 200,
		minSize : 200,
		maxSize : 200,
		collapsible : false, // 允许隐藏
		margins : "2 0 2 2",
		cmargins : "0 0 0 0",
		rootVisible : false,
		lines : false,
		autoScroll : true, // 自动出现滚动条
		animCollapse : false, // 面板闭合过程附有动画效果
		animate : false,
		// collapseMode : "mini", // 样式
		bodyStyle : "background:#f1f1f1",
		title : '功能菜单',
		layout : 'fit',
		items : [tree],
		listeners : {
			'collapse' : function() {

			},

			'expand' : function() {
				// grid.width = '100%';
				// center.doLayout();
			}
		}
	});
	west.on('render', function() {
		west.getEl().on('dblclick', function() {

		});
	}, this);

	// ==========建立视图2
	var createWin = function() {
		win = new Ext.Viewport({
			layout : 'border',
			items : [north, west, center]

		});

		Ext.EventManager.onWindowResize(function() {
			center.width = '100%';
			center.doLayout();
		});
		tree.getRootNode().expand(true);

		return;
	};
	createWin();
	win.show();

};