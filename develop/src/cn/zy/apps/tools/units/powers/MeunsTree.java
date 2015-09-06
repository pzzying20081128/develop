//package cn.zy.apps.tools.units.powers ;
//
//public class MeunsTree {
//
//    private static MeunsTree meunsTree = new MeunsTree() ;
//
//    private MeunsTree() {
//
//    }
//
//    public static MeunsTree instance() {
//        return meunsTree ;
//    }
//
//    public static String[][] initTreeDatas() {
//        return meunsTree.initTreeData() ;
//    }
//
//    private String p1[] = { "SYSTEM_MANAGE", "系统管理", "false", "font_weight", "0", "0", "[]" } ;
//
//    private String p2[] = { "SYSTEM_MANAGE_DEPARTMENT_DEPT", "部门管理", "false", "font_weight", "SYSTEM_MANAGE", "1", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;
//
//    private String p3[] = { "SYSTEM_MANAGE_DEPARTMENT_DEPT_STAFF", "员工管理", "true", "", "SYSTEM_MANAGE_DEPARTMENT_DEPT", "1", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'}]" } ;
//
//    // ==========================================================================
//
//    //    private String p100[] = { "BASE_DATA", "基础数据", "false", "font_weight", "0", "0", "[]" } ;
//    //
//    //    private String p106[] = { "SIGN_IN_AND_SIGN_OUT", "签到管理", "true", "", "BASE_DATA", "0", "[{signIn:1,label:'签到'},{search:1,label:'查询'},{export:1,label:'导出'},{passWord:1,label:'修改密码'}]" } ;
//    //
//    //    private String p101[] = { "BASE_DATA_MATERIAL", "物料管理", "true", "", "BASE_DATA", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //    private String p102[] = { "BASE_DATA_MACHINE", "整机管理", "true", "", "BASE_DATA", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //    private String p103[] = { "BASE_DATA_STORE", "仓库信息", "true", "", "BASE_DATA", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //    private String p104[] = { "BASE_DATA_PROVIDER", "供应商信息", "true", "", "BASE_DATA", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //    private String p105[] = { "BASE_DATA_SERVICE_STATION", "区域/服务站信息", "true", "", "BASE_DATA", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //
//    //    private String p107[] = { "DIVERSITY_MANAGE", "多元化管理", "true", "", "BASE_DATA", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //
//    //
//    //    // ==========================================================================
//    //
//    //    private String p200[] = { "WORK_ORDER_MANAGEMENT", "工单管理", "false", "font_weight", "0", "0", "[]" } ;
//    //
//    //    private String p201[] = { "WORK_ORDER_MANAGEMENT_SERVICE_STATION", "服务站工单管理", "true", "", "WORK_ORDER_MANAGEMENT", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //    private String p202[] = { "WORK_ORDER_MANAGEMENT_REPAIR", "维修工单管理", "true", "", "WORK_ORDER_MANAGEMENT", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//    //
//    //    private String p203[] = { "WORK_ORDER_MANAGEMENT_CHECK", "检测工单管理", "true", "", "WORK_ORDER_MANAGEMENT", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//    //
//    //
//    //
//    //    // ==========================================================================
//    //    private String p204[] = { "STORE_MANAGE", "仓库管理", "false", "font_weight", "0", "0", "[]" } ;
//    //
//    //    private String p205[] = { "STORE_ALLOCATION", "调拨仓库", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p206[] = { "STORE_STATISTICAL_REPORT", "仓库统计", "true", "", "STORE_MANAGE", "0", "[{search:1,label:'查询'},{export:1,label:'导出'},{import:1,label:'导入'}]" } ;
//    //
//    //    private String p207[] = { "STOCK_STORE_IN", "采购入库单", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p208[] = { "SALES_STORE_OUT", "销售出库单", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p209[] = { "GOODS_DAMAGE", "报损单据", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p210[] = { "UNASSEMBLED_GOODS", "拆装单据", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p211[] = { "BORROW_OWE", "借欠单据", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p212[] = { "SERVICE_STATION_APPLY", "服务站申请单", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p213[] = { "STORE_WARNING", "库存预警", "true", "", "STORE_MANAGE", "0", "[{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//    //
//    //    private String p214[] = { "STORE_ALLOCATION_ROAD", "调拨汇总", "true", "", "STORE_MANAGE", "0", "[{search:1,label:'查询'},{export:1,label:'导出'},{print:1,label:'打印'}]" } ;
//    //
//    //    private String p215[] = { "STORE_CHECKED", "库存盘点", "true", "", "STORE_MANAGE", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{search:1,label:'查询'},{checked:1,label:'审核'},{print:1,label:'打印'}]" } ;
//    //
//    //
//    //    // ==========================================================================
//    //
//    //    private String p500[] = { "REPORT_MANAGEMENT", "统计", "false", "font_weight", "0", "0", "[]" } ;
//    //
//    //    private String p501[] = { "REPORT_SERVICE_STATION_WORK_ORDER", "服务站维修工单", "true", "", "REPORT_MANAGEMENT", "0", "[{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//    //
//    //    private String p502[] = { "REPORT_WORKSHOP_REPAIR_WORK_ORDER", "车间维修工单", "true", "", "REPORT_MANAGEMENT", "0", "[{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//    //
//    //    private String p503[] = { "REPORT_WORKSHOP_CHECK_WORK_ORDER", "检测工单", "true", "", "REPORT_MANAGEMENT", "0", "[{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//    //
//    //    private String p504[] = { "WARRANTY_USERS", "延保用户", "true", "", "REPORT_MANAGEMENT", "0", "[{search:1,label:'查询'},{export:1,label:'导出'}]" } ;
//
//    //    private String[][] treeData = { p1, p2,p3, p100, p106,p101, p102, p103,p104, p105,p107, p200, p201, p202, p203, p204,p207 ,p208,p209,p210,p211,p212, p205,p214, p206,p213,p215,p500,p501,p502,p503,p504} ;
//    private String[][] treeData = { p1, p2, p3 } ;
//
//    private String[][] initTreeData() {
//        return treeData ;
//    }
//
//}
