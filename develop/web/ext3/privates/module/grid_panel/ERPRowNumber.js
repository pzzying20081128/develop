// ////////////////Ext.grid.ERPRowNumberer ///////////////////////////////////
/**
 * PageTotal : 当页合 AllTotal : 总合
 * 
 * @class Ext.grid.ERPRowNumberer
 * @extends Ext.grid.RowNumberer
 */
Ext.grid.ERPRowNumberer = Ext.extend(Ext.grid.RowNumberer, {
	width : 40,
	sortable : false,
	resizable : true,
	draggable : false,
	hideable : false,
	menuDisabled : true,
	renderer : function(value, cellmeta, record, rowIndex, columnIndex, store) {
		var gridTotalType = record.get("gridTotalType");
		if (gridTotalType == 'PageTotal') {
			cellmeta.attr = 'style="background-color: #C9D8FC"';// 指定自己的颜色
			return "合计";
		} else if (gridTotalType == 'AllTotal') {
			cellmeta.attr = 'style="background-color: #C9D8FC"';// 指定自己的颜色
			return "总计";
		} else {
			// cellmeta.css = 'x-grid-row-index';//类样式
			if (store.lastOptions != null && store.lastOptions.params != null) {
				var pageindex = store.lastOptions.params.start;
				return pageindex + rowIndex + 1;
				// return ( ( pageindex - 1 ) * erp_grid_panel_limit ) + ( x - 1
				// ) + 1;
			} else {
				return rowIndex + 1;
			}
		}

	}
});

// ////////////////////////////////////////////////////////////////////////////////////

