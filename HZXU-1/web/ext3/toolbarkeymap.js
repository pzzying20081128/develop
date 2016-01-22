
Ext.ux.ToolbarKeyMap = Ext.extend(Object, ( function() {
	var kb, owner, mappings;
	var showHotKey = true;
	function addKeyBinding(c) {
		// c.showHotKey=true;

		if (kb = c.keyBinding) {
			delete c.keyBinding;
			if (!kb.fn && c.handler) {
				kb.fn = function(k, e) {

					e.preventDefault();
					e.stopEvent();
					c.handler.call(c.scope, c, e);
				}
			}
			mappings.push(kb);
			var t = [];
			if (kb.ctrl)
				t.push('[Ctrl');
			if (kb.alt)
				t.push('Alt');
			if (kb.shift)
				t.push('Shift');
			t.push(kb.key.toUpperCase() + "]");
			// c.hotKey = t.join('+');
			c.tooltip = t.join('+');
			if (c instanceof Ext.menu.Item) {
				c.onRender = c.onRender.createSequence(addMenuItemHotKey);
			} else if ( ( c instanceof Ext.Button ) && ( showHotKey )) {
				c.onRender = c.onRender.createSequence(addButtonHotKey);
			}
		}
		if ( ( c instanceof Ext.Button ) && c.menu) {
			c.menu.cascade(addKeyBinding);
		}
	}

	function findKeyNavs() {
		delete this.onRender;
		if (owner = this.ownerCt) {
			mappings = [];
			this.cascade(addKeyBinding);
			if (!owner.menuKeyMap) {
				owner.menuKeyMap = new Ext.KeyMap(owner.el, mappings);
				owner.el.dom.tabIndex = 0;
			} else {
				owner.menuKeyMap.addBinding(mappings);
			}
		}
	}

	function addMenuItemHotKey() {
		delete this.onRender;
		this.el.setStyle({
			overflow : 'hidden',
			zoom : 1
		});
		this.el.child('.x-menu-item-text').setStyle({
			'float' : 'left',
			width : 80
		});
		this.el.createChild({
			style : {
				padding : '2px 0px 0px 0px',
				float : 'right'

			},
			html : this.hotKey
		});
	}

	function addButtonHotKey() {
		delete this.onRender;
		var p = this.btnEl.up('');
		p.setStyle({
			overflow : 'hidden',
			zoom : 1
		});
		p.up('td').setStyle('text-align', 'left');
		this.btnEl.setStyle('.x-menu-item-text').setStyle({
			'float' : 'left'
		});
		p = p.createChild({
			style : {
				padding : '2px 0px 0px 0px',
				float : 'right',
				position : 'relative',
				bottom : Ext.isWebKit ? '-1px' : '-2px'
			},
			html : this.hotKey
		});
	}

	return {
		init : function(toolbar) {
			Ext.QuickTips.init();
			toolbar.onRender = toolbar.onRender.createSequence(findKeyNavs);
			toolbar.doLayout = toolbar.doLayout.createSequence(findKeyNavs);
		}
	}
} )());