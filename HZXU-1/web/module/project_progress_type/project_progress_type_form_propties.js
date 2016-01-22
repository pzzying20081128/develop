
	      {
			name : 'projectprogresstype.typeName',
			mapping : 'typeName'
		},
		            		 

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
					id : 'projectprogresstype.typeName',
					name : 'projectprogresstype.typeName',
					fieldLabel : ' 项目进展类型',
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
            		 

// 1-1