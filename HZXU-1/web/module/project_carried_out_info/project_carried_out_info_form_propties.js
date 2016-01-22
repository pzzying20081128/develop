
	      {
			name : 'projectcarriedoutinfo.totalInvestment',
			mapping : 'totalInvestment'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.constructionContent',
			mapping : 'constructionContent'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.isKaiGong',
			mapping : 'isKaiGong'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.isProduction',
			mapping : 'isProduction'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectAddress',
			mapping : 'projectAddress'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectOwnershipAddress',
			mapping : 'projectOwnershipAddress'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectOwnershipAddressId',
			mapping : 'projectOwnershipAddressId'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectProgressType',
			mapping : 'projectProgressType'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectProgressTypeId',
			mapping : 'projectProgressTypeId'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectMajorType',
			mapping : 'projectMajorType'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectMajorTypeId',
			mapping : 'projectMajorTypeId'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.kaiGongDate',
			mapping : 'kaiGongDate'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.buildStartDate',
			mapping : 'buildStartDate'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.buildEndDate',
			mapping : 'buildEndDate'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.implementationUnit',
			mapping : 'implementationUnit'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.implementationUnitPerson',
			mapping : 'implementationUnitPerson'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.implementationUnitPhoto',
			mapping : 'implementationUnitPhoto'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.responsibilityUnit',
			mapping : 'responsibilityUnit'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.responsibilityUnitPerson',
			mapping : 'responsibilityUnitPerson'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.responsibilityUnitPhoto',
			mapping : 'responsibilityUnitPhoto'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.fenGuanMiShuZhang',
			mapping : 'fenGuanMiShuZhang'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.fenGuanMiShuZhangPhoto',
			mapping : 'fenGuanMiShuZhangPhoto'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.fenGuanHuShiZhang',
			mapping : 'fenGuanHuShiZhang'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.fenGuanHuShiZhangPhoto',
			mapping : 'fenGuanHuShiZhangPhoto'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.name',
			mapping : 'name'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectProgress',
			mapping : 'projectProgress'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.text',
			mapping : 'text'
		},
		            		 	      {
			name : 'projectcarriedoutinfo.projectDate',
			mapping : 'projectDate'
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
					id : 'projectcarriedoutinfo.totalInvestment',
					name : 'projectcarriedoutinfo.totalInvestment',
					fieldLabel : ' 估算总投资',
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
					id : 'projectcarriedoutinfo.constructionContent',
					name : 'projectcarriedoutinfo.constructionContent',
					fieldLabel : ' 项目建设内容',
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
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.isKaiGong',
//					name : 'projectcarriedoutinfo.isKaiGong',
//					fieldLabel : ' 是否开工',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.isProduction',
//					name : 'projectcarriedoutinfo.isProduction',
//					fieldLabel : ' 是否开工',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectAddress',
//					name : 'projectcarriedoutinfo.projectAddress',
//					fieldLabel : ' 项目地址',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectOwnershipAddress',
//					name : 'projectcarriedoutinfo.projectOwnershipAddress',
//					fieldLabel : ' 项目归属地',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectOwnershipAddressId',
//					name : 'projectcarriedoutinfo.projectOwnershipAddressId',
//					fieldLabel : ' 项目归属地',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectProgressType',
//					name : 'projectcarriedoutinfo.projectProgressType',
//					fieldLabel : ' 项目进展类型',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectProgressTypeId',
//					name : 'projectcarriedoutinfo.projectProgressTypeId',
//					fieldLabel : ' 项目进展类型',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectMajorType',
//					name : 'projectcarriedoutinfo.projectMajorType',
//					fieldLabel : ' 项目重点分类',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.projectMajorTypeId',
//					name : 'projectcarriedoutinfo.projectMajorTypeId',
//					fieldLabel : ' 项目重点分类',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.kaiGongDate',
//					name : 'projectcarriedoutinfo.kaiGongDate',
//					fieldLabel : ' 开工时间',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.buildStartDate',
//					name : 'projectcarriedoutinfo.buildStartDate',
//					fieldLabel : ' 建设开始时间',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.buildEndDate',
//					name : 'projectcarriedoutinfo.buildEndDate',
//					fieldLabel : ' 建设结束时间',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.implementationUnit',
//					name : 'projectcarriedoutinfo.implementationUnit',
//					fieldLabel : ' 项目实施单位',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.implementationUnitPerson',
//					name : 'projectcarriedoutinfo.implementationUnitPerson',
//					fieldLabel : ' 项目实施负责人',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.implementationUnitPhoto',
//					name : 'projectcarriedoutinfo.implementationUnitPhoto',
//					fieldLabel : ' 项目实施负责人手机号',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.responsibilityUnit',
//					name : 'projectcarriedoutinfo.responsibilityUnit',
//					fieldLabel : ' 项目责任单位',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.responsibilityUnitPerson',
//					name : 'projectcarriedoutinfo.responsibilityUnitPerson',
//					fieldLabel : ' 项目责任单位负责人',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
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
					id : 'projectcarriedoutinfo.responsibilityUnitPhoto',
					name : 'projectcarriedoutinfo.responsibilityUnitPhoto',
					fieldLabel : ' 项目责任单位负责人手机号',
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
//				items : [{
//					id : 'projectcarriedoutinfo.fenGuanMiShuZhang',
//					name : 'projectcarriedoutinfo.fenGuanMiShuZhang',
//					fieldLabel : ' 分管副秘书长',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
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
					id : 'projectcarriedoutinfo.fenGuanMiShuZhangPhoto',
					name : 'projectcarriedoutinfo.fenGuanMiShuZhangPhoto',
					fieldLabel : ' 项目责任单位负责人手机号',
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
//				items : [{
//					id : 'projectcarriedoutinfo.fenGuanHuShiZhang',
//					name : 'projectcarriedoutinfo.fenGuanHuShiZhang',
//					fieldLabel : ' 分管副市长',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.fenGuanHuShiZhangPhoto',
//					name : 'projectcarriedoutinfo.fenGuanHuShiZhangPhoto',
//					fieldLabel : ' 分管副市长手机号',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
            		 	      	      // ----------------------------------------------------------------------//
//			  {
//                columnWidth : .25,
//				layout : 'form',
//				defaultType : 'textfield',
//				baseCls : 'x-plain',
//				defaults : {
//					width : 250
//				},
//				items : [{
//					id : 'projectcarriedoutinfo.name',
//					name : 'projectcarriedoutinfo.name',
//					fieldLabel : ' 项目名称',
//					xtype : 'textfield',
//					style : NoAllowBlankStyle,
//					blankText : '不能为空！',
//					allowBlank : false,
//					listeners : {
//						'specialkey' : function(field, e) {
//						}
//					}
//				}]
//			  },
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
					id : 'projectcarriedoutinfo.projectProgress',
					name : 'projectcarriedoutinfo.projectProgress',
					fieldLabel : ' 项目进展',
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
					id : 'projectcarriedoutinfo.text',
					name : 'projectcarriedoutinfo.text',
					fieldLabel : ' 备注',
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
					id : 'projectcarriedoutinfo.projectDate',
					name : 'projectcarriedoutinfo.projectDate',
					fieldLabel : ' 项目时间',
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
