
	      {
			name : 'projectyearinvestmentplan.year',
			mapping : 'year'
		},
		            		 	      {
			name : 'projectyearinvestmentplan.investmentPlan',
			mapping : 'investmentPlan'
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
					id : 'projectyearinvestmentplan.year',
					name : 'projectyearinvestmentplan.year',
					fieldLabel : ' 年',
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
					id : 'projectyearinvestmentplan.investmentPlan',
					name : 'projectyearinvestmentplan.investmentPlan',
					fieldLabel : ' 投资计划',
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