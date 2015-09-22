Array.prototype.del = function(index) {
	if (isNaN(index) || index >= this.length) {
		return false;
	}
	for (var i = 0, n = 0; i < this.length; i++) {
		if (this[i] != this[index]) {
			this[n++] = this[i];
		}
	}
	this.length -= 1;
};

function UserPowerPanel() {

	// var powerArray = [];
	var powerMenus = {};// 全部的权限
	var oldUserPowerMenus = [];
	var userPowerMenus = {
	/*
	 * "children" : [{ id : '123', "children" : [{ id : '123', "children" : [{
	 * "children" : [], "cls" : "", "id" : "BASE_DATA_STORE_POSITION_", "leaf" :
	 * true, "powers" :
	 * "[{use:1,label:'使用'},{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'}]",
	 * "text" : "库位信息" }] }] }]
	 */
	};// 用户权限
	// var powerMenuIds = [];

	var left_tree_id = "";
	var right_tree_id = "";
	var update_power = false;
	var update_provider = false;
	var update_guest = false;
	var userPowerMenus = {};

	this.getUserPowerMenus = function() {
		// alert(userPowerMenus);
		return userPowerMenus;
	}

	var root = new Ext.tree.AsyncTreeNode({
		text : "根节点",
		loader : new Ext.tree.TreeLoader({
			preloadChildren : false,
			dataUrl : './filterUserPowerTree.jhtml',
			listeners : {
				load : function(thiz, node, response) {
					powerMenus = Ext.decode(response.responseText);
				}
			}

		})
	});

	var left_tree = new Ext.tree.TreePanel({
		border : false,
		root : root,// 设置为根节点
		animate : false,
		autoScroll : true,
		width : 500,

		rootVisible : false,// 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		listeners : {
			'click' : function(node, e) {
				// if (node.isLeaf()) {
				left_tree_id = node.id;
				// }
			}
		}
	});

	var root2 = new Ext.tree.AsyncTreeNode({
		text : "根节点",
		loader : new Ext.tree.TreeLoader({
			preloadChildren : false,
			dataUrl : 'myStruts/ShowTree_showExistPowerTree.do'
		})
	});
	var right_tree = new Ext.tree.TreePanel({
		id : 'system_user_right_tree',
		border : false,
		// root : root2,// 设置为根节点
		root : new Ext.tree.TreeLoader(),
		animate : false,
		rootVisible : false,// 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		width : 500,
		listeners : {
			'click' : function(node, e) {
				right_tree_id = node.id;
				redrawCheckPanel(null);
			},
			'afterlayout' : function(node, e) {
				// BASE_DATA_BRAND

			}
		}
	});

	// 权限设置form
	var userPowerPanel = new Ext.Panel({
		id : 'power_panel',
		region : 'center',
		layout : 'column',
		autoScroll : false,
		defaults : {
			xtype : 'panel',
			bodyStyle : 'border-right:solid 1px #cccccc',
			height : '100%'
		},
		items : [{
			columnWidth : .33,
			baseCls : 'x-plain',
			autoScroll : false,
			items : [{
				title : '可选模块'
			}, new Ext.Panel({
				bodyStyle : 'background:#FFFFFF; padding:1px;',
				region : 'center',
				layout : 'column',
				defaults : {
					xtype : 'panel',
					bodyStyle : 'background:#FFFFFF; padding:1px;',
					height : 520,
					width : 298
				},
				items : [{
					baseCls : 'x-plain',
					autoScroll : true,
					items : [left_tree]
				}]
			})]
		}, {
			columnWidth : .2,
			// width :150,
			autoWidth : true,
			baseCls : 'x-plain',
			defaults : {
				style : 'margin:20px 0'
			},
			items : [{
				xtype : 'button',
				width : 40,
				text : '<',
				style : 'margin-top:80px',
				handler : function() {
					power_manage_remove_right_select_node();
				}
			}, {
				xtype : 'button',
				width : 40,
				text : '>',
				handler : function() {
					power_manage_add_selected_module();
				}
			}, {
				xtype : 'button',
				width : 40,
				text : '<<',
				handler : function() {
					power_manage_remove_all_node();
				}
			}, {
				xtype : 'button',
				width : 40,
				text : '>>',
				handler : function() {
					power_manage_add_all_module();
				}
			}]
		}, {
			columnWidth : .34,
			baseCls : 'x-plain',
			autoScroll : true,
			items : [{
				title : '已选模块'
			}, new Ext.Panel({
				region : 'center',
				layout : 'column',
				defaults : {
					xtype : 'panel',
					bodyStyle : 'background:#FFFFFF; padding:1px;',
					height : 520,
					width : 308
				},
				items : [{
					baseCls : 'x-plain',
					autoScroll : true,
					items : [right_tree]
				}]
			})]
		}, {
			columnWidth : .27,
			baseCls : 'x-plain',
			bodyStyle : 'background:#FFFFFF; padding:1px;',
			autoScroll : true,
			items : [{
				title : '已选权限'
			},

			new Ext.Panel({
				region : 'center',
				layout : 'column',
				id : 'power_check_panel',
				defaults : {
					xtype : 'panel',
					// bodyStyle : 'border-right:solid 0px #cccccc',
					bodyStyle : 'background:#FFFFFF; padding:1px;',
					height : 520,
					width : 308
				},
				items : [{
					baseCls : 'x-plain',
					autoScroll : true,
					items : []
				}]
			})

			// {
			// xtype : "checkbox",
			// boxLabel : "全选",
			// name : ""
			// }
			]
		}]
	});
	this.getPanel = function() {
		return userPowerPanel;
	}
	// //////////////////////////////////

	function cloneObj(obj) {
		/*
		 * function Clone(obj) { } Clone.prototype = obj; var o = new Clone();
		 * for (var a in o) { if (typeof o[a] == "object") { o[a] =
		 * cloneObj(o[a]); } }
		 */
		return Ext.decode(Ext.encode(obj));
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////
//	this.setOldUserPowerMenus = setOldUserPowerMenus_;

	function setOldUserPowerMenus_(oldUserPowerMenus_) {
		oldUserPowerMenus = oldUserPowerMenus_
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

	function getUserPowerJson(myUserPowerMenus) {
		for (var i = 0; i < myUserPowerMenus.children.length; i++) {
			if (typeof ( myUserPowerMenus.children[i]['id'] ) != 'undefined') {
				if (right_tree_id == myUserPowerMenus.children[i]['id']) {
					return myUserPowerMenus.children[i]['powers'];
				} else {
					var res = getUserPowerJson(myUserPowerMenus.children[i]);
					if (res != null) {
						return res;
					}
				}
			}
		}
		return null;
	}

	function setUserPowerJson(myUserPowerMenus, powerText) {
		for (var i = 0; i < myUserPowerMenus.children.length; i++) {
			if (typeof ( myUserPowerMenus.children[i]['id'] ) != 'undefined') {
				if (right_tree_id == myUserPowerMenus.children[i]['id']) {
					myUserPowerMenus.children[i]['powers'] = powerText;
					return 1;
				} else {
					var res = setUserPowerJson(myUserPowerMenus.children[i], powerText);
					if (res != null) {
						return;
					}
				}
			}
		}
		return null;
	}

	var isAllCheckedEnable = false;
	var isSingleCheckedEnable = false;
	function redrawCheckPanel(data) {
		if (data != null) {
			setUserPowerJson(userPowerMenus, Ext.encode(data));
		}
		var powerCheckPanel = Ext.getCmp('power_check_panel');
		powerCheckPanel.items.items[0].removeAll();
		var data = Ext.decode(getUserPowerJson(userPowerMenus));
		if (data.length > 0) {
			powerCheckPanel.items.items[0].add({
				id : 'power_all',
				xtype : "checkbox",
				boxLabel : '全选',
				tvalue : tmp_key,
				handler : function(f, c) {
					if (isAllCheckedEnable) {
						isAllCheckedEnable = false;
						isSingleCheckedEnable = false;
						checkedAll(powerCheckPanel, data, c);
					}
				}
			});

			var is_check_all = true;
			for (var i = 0; i < data.length; i++) {
				var tmp_key = "";
				for (var key in data[i]) {
					if (key != 'label') {
						tmp_key = key;
						if (data[i][key] != 1 && is_check_all) {
							is_check_all = false;
						}
						break;
					}
				}
				// alert(powerCheckPanel.items.items.length );
				powerCheckPanel.items.items[0].add({
					id : 'power_' + i,
					xtype : "checkbox",
					boxLabel : data[i].label,
					tvalue : tmp_key,
					checked : data[i][tmp_key] == 1 ? true : false,
					handler : function(f, c) {
						if (isSingleCheckedEnable) {
							isAllCheckedEnable = false;
							isSingleCheckedEnable = false;
							resetData(powerCheckPanel, data, f.tvalue, c);
						}
					}
				});
			}

			if (is_check_all) {
				Ext.getCmp('power_all').setValue(true);
			}
			isAllCheckedEnable = true;
			isSingleCheckedEnable = true;
		}
		powerCheckPanel.doLayout();
	}

	function checkedAll(panel, data, isCheck) {
		for (var i = 0; i < data.length; i++) {
			for (var key in data[i]) {
				if (key != 'label') {
					data[i][key] = isCheck == true ? 1 : 0;
					break;
				}
			}
		}
		// refreshCheckboxes(panel, data);
		redrawCheckPanel(data);
	}
	function resetData(panel, data, check_key, isCheck) {
		for (var i = 0; i < data.length; i++) {
			for (var key in data[i]) {
				if (key == check_key) {
					data[i][key] = isCheck == true ? 1 : 0;
					break;
				}
			}
		}
		// refreshCheckboxes(panel, data);
		redrawCheckPanel(data);
	}

	function addSelectGuestToSelectPanel(listTree, selectTree, guest_left_menus) {

		var root = listTree.getRootNode();
		var selectTreeRoot = selectTree.getRootNode();
		root.eachChild(function(child) {
			if (child.attributes.checked == true) {
				copyNode(child, selectTreeRoot, true);
				child.remove();
			}
		});

	}

	function copyNode(node, parent, join) {
		var parentNode = new Ext.tree.TreeNode({
			id : node.attributes.id,
			text : node.attributes.text,
			draggable : false,
			leaf : node.attributes.leaf,
			cls : node.attributes.cls,
			checked : node.checked,
			attributes : {
				checked : node.attributes.checked,
				seltype : node.attributes.seltype,
				id : node.attributes.id,
				text : node.attributes.text,
				leaf : node.attributes.leaf,
				cls : node.attributes.cls
			}
		});
		if (join)
			parent.appendChild(parentNode);
		node.eachChild(function(child) {
			copyNode(child, parentNode, true);
		});

	}

	function addGuestToSelectPanel(listTree, selectTree) {

		var root = listTree.getRootNode();
		var selectTreeRoot = selectTree.getRootNode();
		var records = new Array();
		var index = 0;
		root.eachChild(function(child) {
			if (child.attributes.checked == true) {
				records[index] = child;
				index++;

			}
		});

		for (var i = 0; i < index; i++) {
			selectTreeRoot.appendChild(records[i]);
		}
	}

	function removeGuestfromSelectPanel(listTree, selectTree) {

		var root = selectTree.getRootNode();
		var selectTreeRoot = listTree.getRootNode();
		var records = new Array();
		var index = 0;
		root.eachChild(function(child) {
			if (child.attributes.checked == false) {
				records[index] = child;
				index++;

			}
		});

		for (var i = 0; i < index; i++) {
			selectTreeRoot.appendChild(records[i]);
		}
	}

	function addAllGuestToSelectPanel(listTree, selectTree) {

		var root = listTree.getRootNode();
		var selectTreeRoot = selectTree.getRootNode();
		var records = new Array();
		var index = 0;
		root.eachChild(function(child) {
			// if(child.attributes.checked==true)
			{
				records[index] = child;
				index++;

			}
		});

		for (var i = 0; i < index; i++) {
			selectTreeRoot.appendChild(records[i]);
		}
	}

	function removeAllGuestfromSelectPanel(listTree, selectTree) {

		var root = selectTree.getRootNode();
		var selectTreeRoot = listTree.getRootNode();
		var records = new Array();
		var index = 0;
		root.eachChild(function(child) {
			// if(child.attributes.checked==false)
			{
				records[index] = child;
				index++;

			}
		});

		for (var i = 0; i < index; i++) {
			selectTreeRoot.appendChild(records[i]);
		}
	}

	function copyValue(records, selGuestDatas) {
		selGuestDatas.seltype = records.attributes.seltype;
		selGuestDatas.checked = records.attributes.checked;
		var children = selGuestDatas.children;
		var index = 0;
		records.eachChild(function(child) {
			var selGuestData = children[index];
			selGuestData.seltype = child.attributes.seltype;
			selGuestData.checked = child.attributes.checked;
			copyValue(child, selGuestData);
			index++;
		});
	}

	// 选择客户

	function getSelectGuetPanelInfo(selectTree) {
		var selGuestData = new Array();
		var root = selectTree.getRootNode();
		root.eachChild(function(child) {
			if (child.attributes.checked == true && ( child.attributes.seltype == 1 || child.attributes.seltype == 0 )) {
				traversalNode(child, selGuestData);
			}
		});
		return selGuestData;
		// alert(Ext.encode(selGuestData) );
	}

	function traversalNode(node, selGuestData) {
		if (node.attributes.checked == true && ( node.attributes.seltype == 1 || node.attributes.seltype == 0 )) {
			if (node.attributes.seltype == 1) {
				var guest = {
					id : node.attributes.id
				};
				selGuestData.push(guest);
			}
			node.eachChild(function(child) {
				if (child.attributes.checked == true && ( child.attributes.seltype == 1 || child.attributes.seltype == 0 )) {
					traversalNode(child, selGuestData);
				}
			});
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function initGuestselRightNode(root) {
		root.eachChild(function(child) {
			if (child.attributes.seltype == 0) {
				child.attributes.checked = true;
				child.getUI().checkbox.indeterminate = true;
			}
			child.expand(true);
			initGuestselRightNode(child);
		});
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function power_manage_remove_all_node() {
		right_tree_id = "";
		userPowerMenus = {};
		var root = right_tree.getRootNode();
		root.removeAll(true);
		var powerCheckPanel = Ext.getCmp('power_check_panel');
		powerCheckPanel.items.items[0].removeAll();
	}
	function power_manage_add_all_module() {
		// left_tree_id = "";
		var powerMenuLineArr = [];
		function powerMenuToLineArray(node) {
			if (typeof ( node.children ) != 'undefined') {
				powerMenuLineArr.push(node);
				for (var i = 0; i < node.children.length; i++) {
					if (typeof ( node.children[i]['id'] ) != 'undefined') {
						powerMenuToLineArray(node.children[i]);
					}
				}
			}
		}
		var tmpMenus = cloneObj(userPowerMenus);
		powerMenuToLineArray(tmpMenus);
		userPowerMenus.children = cloneObj(powerMenus.children);
		pushOldPowerMenuToUserPowerMenus(userPowerMenus, powerMenuLineArr);// 将旧的权限复制到新的权限中
		powerMenuDraw(right_tree, right_tree.getRootNode(), userPowerMenus);
	}

	function power_manage_remove_right_select_node() {
		if (right_tree_id != '') {
			remove_right_select(userPowerMenus, right_tree_id);
			powerMenuDraw(right_tree, right_tree.getRootNode(), userPowerMenus);
			var powerCheckPanel = Ext.getCmp('power_check_panel');
			powerCheckPanel.items.items[0].removeAll();
		} else {
			Ext.MessageBox.show({
				title : "提示信息",
				msg : "请选择左边的权限模块",
				width : 200,
				icon : Ext.MessageBox.INFO,
				closable : false,
				buttons : {
					"ok" : "确定"
				}
			});
		}

	}

	function pushOldPowerMenuToUserPowerMenus(myUserPowerMenus, existLinePowerMenu) {
		for (var j = 0; j < existLinePowerMenu.length; j++) {
			setPowerData(myUserPowerMenus, existLinePowerMenu[j].id, existLinePowerMenu[j].powers);
		}
	}

	// ///////////////////////////////////////////////////////
	function power_manage_add_selected_module() {
		// 删除根节点下所有的模块
		if (left_tree_id != '') {
			var root = right_tree.getRootNode();
			root.removeAll(true);
			getChainNodesMain(cloneObj(powerMenus), left_tree_id);
			powerMenuDraw(right_tree, root, userPowerMenus);
			// var expandNode = null;
			// function powerMenuDraw(right_tree, node, menus) {

		} else {
			Ext.MessageBox.show({
				title : "提示信息",
				msg : "请选择左边的权限模块",
				width : 200,
				icon : Ext.MessageBox.INFO,
				closable : false,
				buttons : {
					"ok" : "确定"
				}
			});
		}
	}
	function setPowerData(menus, id, powers) {
		for (var i = 0; i < menus.children.length; i++) {
			if (typeof ( menus.children[i]['id'] ) != 'undefined') {
				if (menus.children[i]['id'] == id) {
					menus.children[i].powers = powers;
					return 1
				} else {
					var res = setPowerData(menus.children[i], id, powers);
					if (res != null) {
						return;
					}
				}
			}
		}
		return null;
	}
	function pushOldPowerMenuToUserPowerMenus(myUserPowerMenus, existLinePowerMenu) {
		for (var j = 0; j < existLinePowerMenu.length; j++) {
			setPowerData(myUserPowerMenus, existLinePowerMenu[j].id, existLinePowerMenu[j].powers);
		}
	}

	function power_manage_remove_all_node() {
		right_tree_id = "";
		userPowerMenus = {};
		var root = right_tree.getRootNode();
		root.removeAll(true);
		var powerCheckPanel = Ext.getCmp('power_check_panel');
		powerCheckPanel.items.items[0].removeAll();
	}
	function power_manage_add_all_module() {
		// left_tree_id = "";
		var powerMenuLineArr = [];
		function powerMenuToLineArray(node) {
			if (typeof ( node.children ) != 'undefined') {
				powerMenuLineArr.push(node);
				for (var i = 0; i < node.children.length; i++) {
					if (typeof ( node.children[i]['id'] ) != 'undefined') {
						powerMenuToLineArray(node.children[i]);
					}
				}
			}
		}
		var tmpMenus = cloneObj(userPowerMenus);
		powerMenuToLineArray(tmpMenus);
		userPowerMenus.children = cloneObj(powerMenus.children);
		pushOldPowerMenuToUserPowerMenus(userPowerMenus, powerMenuLineArr);// 将旧的权限复制到新的权限中
		powerMenuDraw(right_tree, right_tree.getRootNode(), userPowerMenus);
	}

	function powerMenuDraw(right_tree, node, menus) {
		node.removeAll(true);
		
		for (var i = 0; i < menus.children.length; i++) {
			if (typeof ( menus.children[i]['id'] ) != 'undefined') {
				var new_node = new Ext.tree.TreeNode({
					id : menus.children[i]['id'],
					text : menus.children[i]['text'],
					draggable : false,
					leaf : menus.children[i]['leaf'],
					cls : menus.children[i]['cls']
				})
				node.appendChild(new_node);
				if (menus.children[i]['id'] == left_tree_id) {
					// node.expand();
					expandNode(right_tree, node);
				}
				powerMenuDraw(right_tree, new_node, menus.children[i]);
			}
		}
	}
	function expandNode(right_tree, node) {
		if (node != null) {
			var nodes = [node];
			var root = right_tree.getRootNode();
			var tmpnode = node.parentNode;// .parentNode.parentNode;
			while (tmpnode != null) {
				nodes.push(tmpnode);
				tmpnode = tmpnode.parentNode;
				if (tmpnode == root)
					break;
			}
			nodes.reverse();
			for (var i = 0; i < nodes.length; i++) {
				nodes[i].expand();
			}
		}
	}
	function getChainNodesMain(myPowerMenus, id) {
		// 直线型的，非复杂型
		lineArr = [];
		getAllParentNodes(myPowerMenus, id, 0);
		lineArr.reverse();
		getAllChildrenNodes(myPowerMenus, id);
		var s = lineArr;
		var powerMenuLineArr = pushUserPowerMenus(lineArr);// 添加后新的userPowerMenus
		userPowerMenus = {};
		genNewUserPowerTree(powerMenuLineArr);
	}

	function remove_right_select(menus, id) {
		for (var i = 0; i < menus.children.length; i++) {
			if (typeof ( menus.children[i]['id'] ) != 'undefined') {
				if (menus.children[i]['id'] == id) {
					menus.children.del(i);
					return 1
				} else {
					var res = remove_right_select(menus.children[i], id);
					if (res != null) {
						return;
					}
				}
			}
		}
	}

	function getAllParentNodes(myPowerMenus, id, arr_len) {
		getParentObjById(null, myPowerMenus, id);
		if (lineArr.length > arr_len && lineArr.length > 0 && typeof ( lineArr[lineArr.length - 1].id ) != 'undefined') {
			getAllParentNodes(myPowerMenus, lineArr[lineArr.length - 1].id, lineArr.length);
		}
	}

	var lineArr = [];
	function getParentObjById(parentObj, nodeObj, id) {
		if (parentObj != null && typeof ( parentObj.id ) != 'undefined' && nodeObj.id == id) {
			lineArr.push(parentObj);
		} else {
			// if (typeof(nodeObj.children) != 'undefined') {
			for (var i = 0; i < nodeObj.children.length; i++) {
				if (typeof ( nodeObj.children[i]['id'] ) != 'undefined') {
					getParentObjById(nodeObj, nodeObj.children[i], id);
				}
			}
			// }
		}
	}

	function getAllChildrenNodes(myPowerMenus, id) {
		var node = findNodeByid(myPowerMenus, id);
		if (node != null) {
			toLineArray(node);
		}
	}

	function findNodeByid(myPowerMenus, id) {
		for (var i = 0; i < myPowerMenus.children.length; i++) {
			if (typeof ( myPowerMenus.children[i]['id'] ) != 'undefined' && myPowerMenus.children[i]['id'] == id) {
				return myPowerMenus.children[i];
			} else {
				var obj = getAllChildrenNodes(myPowerMenus.children[i], id);
				if (obj != null) {
					return obj;
				}
			}
		}
		return null;
	}

	function toLineArray(node) {
		lineArr.push(node);
		for (var i = 0; i < node.children.length; i++) {
			if (typeof ( node.children[i]['id'] ) != 'undefined') {
				toLineArray(node.children[i]);
			}
		}
	}
	function pushUserPowerMenus(newPowerArr) {
		var powerMenuLineArr = [];
		function powerMenuToLineArray(node) {
			if (typeof ( node.children ) != 'undefined') {
				powerMenuLineArr.push(node);
				for (var i = 0; i < node.children.length; i++) {
					if (typeof ( node.children[i]['id'] ) != 'undefined') {
						powerMenuToLineArray(node.children[i]);
					}
				}
			}
		}
		// 从newPowerArr过滤掉已经存在的权限模块
		var tmp_arr = [];
		for (var i = 0; i < newPowerArr.length; i++) {
			if (!powerMenuCompare(newPowerArr[i].id, userPowerMenus)) {
				tmp_arr.push(newPowerArr[i]);
			}
		}
		powerMenuToLineArray(userPowerMenus);
		powerMenuLineArr = powerMenuLineArr.concat(tmp_arr);
		return powerMenuLineArr;
	}

	function powerMenuCompare(newPowerArrayId, powerArr) {
		var compareIsAdd = false;
		if (typeof ( powerArr.children ) != 'undefined') {
			for (var i = 0; i < powerArr.children.length; i++) {
				if (typeof ( powerArr.children[i]['id'] ) != 'undefined') {
					if (newPowerArrayId == powerArr.children[i]['id']) {
						return true;
					} else {
						if (powerMenuCompare(newPowerArrayId, powerArr.children[i])) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	

	function genNewUserPowerTree(powerMenuLineArr) {
		userPowerMenus.children = cloneObj(powerMenus.children);
		
		while (true) {
			var r = filterUserPowerMenus(userPowerMenus, powerMenuLineArr);
			if (!r) {
				break;
			}
		}
	}

	function filterUserPowerMenus(myUserPowerMenus, existLinePowerMenu) {
		//alert("myUserPowerMenus  filterUserPowerMenus  > "+myUserPowerMenus.children.length +"   existLinePowerMenu >   "+existLinePowerMenu.length);
		for (var i = 0; i < myUserPowerMenus.children.length; i++) {
			if (typeof ( myUserPowerMenus.children[i]['id'] ) != 'undefined') {
				// 过滤
				var b = true;
				for (var j = 0; j < existLinePowerMenu.length; j++) {
					if (myUserPowerMenus.children[i]['id'] == existLinePowerMenu[j].id) {
						// 保留旧的权限
						b = false;
						var t = Ext.decode(existLinePowerMenu[j].powers);
						// Ext.apply(Ext.decode(myUserPowerMenus.children[i].powers),Ext.decode(existLinePowerMenu[j].powers));
						var oldPowers = Ext.decode(myUserPowerMenus.children[i].powers);
						var newPowers = Ext.decode(existLinePowerMenu[j].powers);
						for (var ii = 0; ii < oldPowers.length; ii++) {
							for (var key in oldPowers[ii]) {
								if (key != 'label') {
									for (var jj = 0; jj < newPowers.length; jj++) {
										for (var key2 in newPowers[jj]) {
											if (key == key2) {
												oldPowers[ii][key] = newPowers[jj][key2];
												if (newPowers[jj][key2] == 0) {
													var sds = existLinePowerMenu[j];
												}
											}
										}
									}
								}
							}
						}
						myUserPowerMenus.children[i].powers = Ext.encode(oldPowers);
						// var sds = existLinePowerMenu[j];
					}
				}
				if (b) {
					myUserPowerMenus.children.del(i);
					return true;
				} else {
					if (filterUserPowerMenus(myUserPowerMenus.children[i], existLinePowerMenu)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	function genNewUserPowerTree(powerMenuLineArr) {
		userPowerMenus.children = cloneObj(powerMenus.children);
		while (true) {
			if (!filterUserPowerMenus(userPowerMenus, powerMenuLineArr)) {
				break;
			}
		}
	}

	function userPowerMenuDraw() {
		//alert(" userPowerMenus  >   "+userPowerMenus.children.length);
		powerMenuDraw(right_tree, right_tree.getRootNode(), userPowerMenus);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////
	this.load = function(params) {
		Ext.Ajax.request({
			url : params.url,
			params : params.params,
			success : function(response, options) {
				update_power = true;
				isPowerTabClick = 1;
				var json = Ext.decode(response.responseText);
				if (json.children.length > 0) {
					oldUserPowerMenus = cloneObj(json.children);
					setOldUserPowerMenus_(oldUserPowerMenus);
					genNewUserPowerTree(json.children);
					userPowerMenuDraw();
				}
			}
		});
	}

	// ////////////////////////////////////////////////////////////
}