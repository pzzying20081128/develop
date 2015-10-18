Ext.form.ERPFormPanel = Ext.extend(Ext.form.FormPanel, {
	layout : 'form',
	border : false,
	autoWidth : true,
	autoHeight : true,
	plain : true,
	frame : true,
	labelAlign : 'center',
	bodyStyle : 'border:0 0 0 0;',
	style : 'border:0 0 0 0;',
	sync : true,
	fileUpload : false,
	reset : function() {
		this.getForm().reset();
	},
	/**
	 * properties:{ url , waitMsg, success: }
	 */
	load : function(properties) {

		var fileUpload_ = this.isfileUpload();

		this.getForm().load({
			url : properties.url,
			waitMsg : '正在载入数据...',
			success : function(form, action) {
				var imageUpload = false;
				if (typeof ( properties.imageUpload ) != 'undefined') {
					imageUpload = properties.imageUpload;
				}

				if (imageUpload == false) {

					imageUpload = fileUpload_;
				}

				if (imageUpload == true) {
					var str = action.response.responseText;
					var json = Ext.decode(str);

					var success = json.success;
					if (success) {
						if (typeof ( properties.success ) == 'function') {
							var responseJSON = action.response.responseJSON;
							properties.success(form, action, responseJSON);
						}
					} else {
						var errormsg = json.msg;
						if (typeof ( properties.errors ) == 'function')
							properties.errors(form, action, errormsg, msgcode);
						else
							showErrorMsg("错误提示", errormsg);
					}

				} else {
					var success = action.response.responseJSON.success;
					if (success) {
						if (typeof ( properties.success ) == 'function') {
							var responseJSON = action.response.responseJSON;
							properties.success(form, action, responseJSON);
						}
					} else {
						showErrorMsg("错误提示", "提交请求操作失败");
					}
				}

			},
			failure : function(form, action) {

				var failureType = action.failureType;

				var errormsg = action.response.responseJSON.msg;

				if (errormsg == null || errormsg == "") {

					showErrorMsg("错误提示", "提交请求操作失败【系统错误[" + failureType + "]】");
				} else if (jsonData.msg == 1001 || jsonData.msg == '1001') {
					Ext.MessageBox.alert('标题', '用户没有登录/用户超时，请重新登录系统！ ', function() {
						window.location.href = "./";
					});
				} else {
					if (typeof ( properties.errors ) == 'function')
						properties.errors(form, action, errormsg);
					else
						showErrorMsg("错误提示", errormsg);
				}

			}
		});
	},

	isfileUpload : function() {
		return this.fileUpload;
	},
	submit : function(properties) {
		var imageUpload = false;
		if (typeof ( properties.imageUpload ) != 'undefined') {
			imageUpload = properties.imageUpload;
		}

		if (imageUpload == false) {
			var fileUpload_ = this.isfileUpload();
			imageUpload = fileUpload_;
		}

		if (typeof ( properties.waitMsg ) == "undefined")
			properties.waitMsg = '正在提交数据...';

		thisForm = this.getForm();
		if (this.getForm().isValid()) {
			var submitValues1 = thisForm.getValues();
			// 对将要提交的参数进行过滤，去掉emptyText文字
			for (var param in submitValues1) {
				if (thisForm.findField(param) && thisForm.findField(param).emptyText == submitValues1[param]) {
					// submitValues1[param] = '';
					thisForm.findField(param).setValue("");
				}
			}
		} else {
			showErrorMsg("提交错误", "系统错误");
			return;
		}

		thisForm.submit({
			timeout : timeout,
			url : properties.url,
			waitMsg : properties.waitMsg,
			submitEmptyText : false,
			params : properties.params,
			success : function(form, action) {

				if (imageUpload == true) {
					var str = action.response.responseText;
					var json = Ext.decode(str);
					if (json.success) {
						if (typeof ( properties.success ) == 'function') {

							properties.success(json, form, action);
						}
					} else {
						var errormsg = json.msg;
						if (typeof ( properties.errors ) == 'function')
							properties.errors(form, action, errormsg, msgcode);
						else
							showErrorMsg("错误提示", errormsg);
					}
				} else {
					if (typeof ( properties.success ) == 'function') {
						var json = action.response.responseJSON;
						properties.success(json, form, action);
					}
				}

			},
			failure : function(form, action) {
				if (action.failureType == "client") {
					showErrorMsg("错误提示", "请检查要保存的信息是否正确!");
				} else if (action.failureType == "connect") {
					showErrorMsg("错误提示", "系统错误【链接错误】");
				} else {

					var result = action.result;
					var errormsg = result.msg;
					var msgcode = result.msgcode;

					if (errormsg == null || errormsg == "")
						showErrorMsg("错误提示", "提交请求操作失败");
					else {
						if (typeof ( properties.errors ) == 'function')
							properties.errors(form, action, errormsg, msgcode);
						else
							showErrorMsg("错误提示", errormsg);
					}
				}
			}
		});
	}
});