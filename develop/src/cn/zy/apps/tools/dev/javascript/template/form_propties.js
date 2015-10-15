


#foreach($record in $records)
	      #if($$record.canShow)
	      // ----------------------------------------------------------------------//
			  {
                columnWidth : .25,
				layout : 'form',
				defaultType : 'textfield',
				baseCls : 'x-plain',
				defaults : {
					width : 250
				},
				items : [{
					id : '$record.classfiled.$record.mapping',
					name : '$record.classfiled.$record.mapping',
					fieldLabel : ' $record.showName',
					xtype : 'textfield',
					style : NoAllowBlankStyle,
					blankText : '不能为空！',
					allowBlank : false,
					listeners : {
						'specialkey' : function(field, e) {
						}
					}
				}]
			  },
            #end
		 #end


// 1-1