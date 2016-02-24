package cn.zy.apps.demo ;

public class MeunsTree {

    private static String p1[] = { "system_manage", "系统信息", "false", "font_weight", "0", "0", "[]" } ;

    private static String p2[] = { "demo_user", "系统用户", "true", "", "system_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;

    private static String p3[] = { "project_type", "项目类型", "true", "", "system_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;

    private static String p4[] = { "project_progress_type", "项目进展类型", "true", "", "system_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;

    private static String p5[] = { "project_ownership_address", "项目归属地", "true", "", "system_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;

    private static String p6[] = { "project_major_type", "项目重点分类", "true", "", "system_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;

    private static String p100[] = { "project_manage", "项目管理", "false", "font_weight", "0", "0", "[]" } ;

//    private static String p101[] = { "project_prophase_info", "前期预备项目", "true", "", "project_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'}]" } ;

    private static String p102[] = { "project_carried_out_info", "重点项目", "true", "", "project_manage", "0", "[{add:1,label:'增加'},{edit:1,label:'编辑'},{delete:1,label:'删除'},{investment:1,label:'投资计划'}]" } ;

    private static String[][] treeData = { p1 ,p2 ,p3,p4, p5,p6, p100 ,p102 } ;

    public static String[][] initTreeData() {
        return treeData ;
    }

}
