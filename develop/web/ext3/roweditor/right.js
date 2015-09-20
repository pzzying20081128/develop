Ext.namespace("Ext.ux");
Ext.ux.ToastWindowMgr = {
	positions : []
};
Ext.ux.ToastWindow = Ext.extend(Ext.Window, {
	initComponent : function() {
		Ext.apply(this, {
			iconCls : this.iconCls || 'information',
			width : 250,
			height : 150,
			autoScroll : true,
			autoDestroy : true,
			plain : false,
			shadow : false
		});
		this.task = new Ext.util.DelayedTask(this.hide, this);
		Ext.ux.ToastWindow.superclass.initComponent.call(this);
	},
	setMessage : function(msg) {
		this.body.update(msg);
	},
	setTitle : function(title, iconCls) {
		Ext.ux.ToastWindow.superclass.setTitle.call(this, title, iconCls
				|| this.iconCls);
	},
	onRender : function(ct, position) {
		Ext.ux.ToastWindow.superclass.onRender.call(this, ct, position);
	},
	onDestroy : function() {
		Ext.ux.ToastWindowMgr.positions.remove(this.pos);
		Ext.ux.ToastWindow.superclass.onDestroy.call(this);
	},
	afterShow : function() {
		Ext.ux.ToastWindow.superclass.afterShow.call(this);
		this.on('move', function() {
			Ext.ux.ToastWindowMgr.positions.remove(this.pos);
			this.task.cancel();
		}, this);
//		this.task.delay(4000);
	},
	animShow : function() {
		this.pos = 0;
		while (Ext.ux.ToastWindowMgr.positions.indexOf(this.pos) > -1)
			this.pos++;
		Ext.ux.ToastWindowMgr.positions.push(this.pos);
		this.setSize(250, 150);
		this.el.alignTo(document, "br-br", [ -20,
				-20 - ((this.getSize().height + 10) * this.pos) ]);
		this.el.slideIn('b', {
			duration : 2,
			callback : this.afterShow,
			scope : this
		});
	},
	animHide : function() {
		Ext.ux.ToastWindowMgr.positions.remove(this.pos);
		this.el.ghost("b", {
			duration : 2,
			remove : true,
			scope : this,
			callback : this.destroy
		});
	}
}); /*
	 * Ext.onReady(function(){ new Ext.ux.ToastWindow({ title: '提示窗口', html:
	 * '测试信息', iconCls: 'error' }).show(document); })
	 */

Ext.namespace("Ext.mango");
(function() {

Ext.mango.showToolbar = Ext.extend(Ext.Toolbar, {
	height:25,
	margins: {right: 10},
//	getShowMsg:null,
//	setShowMessage : function(data) {
//		this.showMessages.setText(msg);
//	},

	initComponent : function() {
		
		this.displayItem =new Ext.Toolbar.TextItem({});''
		 this.items=  []; // Fill
		 this.items.push('->');
         this.items.push(this.displayItem = new Ext.Toolbar.TextItem({}));
		 
		 Ext.mango.showToolbar.superclass.initComponent.call(this);
		 this.bindStore(this.store);
		 
	},
	
	bindStore : function(store, initial){
        var doLoad;
        if(!initial && this.store){
            this.store.un('beforeload', this.beforeLoad, this);
            this.store.un('load', this.onLoad, this);
            this.store.un('exception', this.onLoadError, this);
            if(store !== this.store && this.store.autoDestroy){
                this.store.destroy();
            }
        }
        if(store){
            store = Ext.StoreMgr.lookup(store);
//            
//            store.on('load', 
//            		
//            		function (records, options, success,action) {
//              alert("ww");
//             }
//            
//            );
            
            store.on({
                scope: this,
                load:   		
            		this.onLoad
                  
         
        });
        this.store = store;
        }
       
    },
    // private
    onLoad : function (records, options, success) {
    	var text;
    	if(typeof(getShowMsg)==null){
    		
    	}else{
    		 var data=records.reader.jsonData;
    		 text=this.getShowMsg(data);
    		 this.updateInfo(text);
    	}
       
    	
       },
    // private
    updateInfo : function(text){
        if(this.items){
         	 this.displayItem.setText(text);
        }
    },
	
}); 
})();
Ext.preg('ERPShowToolbar', Ext.mango.showToolbar);


