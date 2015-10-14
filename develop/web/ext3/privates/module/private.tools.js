function showErrorMsg(title, message) {
	var msg = Ext.MessageBox.show({
		title : title,
		buttons : Ext.MessageBox.OK,
//		msg : ' <div  style="width:95%,font-size: 12px;margin-left: 8px;padding-left: 8px;text-align:center">' + message + '</div>',
		msg : message,
		width : 350,
		modal : true,
		icon : Ext.Msg.ERROR
	});
} 