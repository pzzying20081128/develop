package cn.zy.apps.tools.units.powers ;

import java.util.ArrayList ;



import java.util.List ;





/**
 * 
 * <action name="showSystemWorkMeuns" class="ShowOptTreeAction" method="showSystemWorkMeuns">
            <result type="json">
                <param name="ignoreHierarchy">false</param>
                <param name="excludeNullProperties">false</param>
                <param name="includeProperties">
                    success,msg,
                    children,
                    children\[\d+\]\.\w+,
                    children\[\d+\]\.children\[\d+\]\.\w+,
                    children\[\d+\]\.children\[\d+\]\.children\[\d+\]\.\w+
                </param>
            </result>
        </action>
 * 
 * 
 * 查询用户可以操作的模块
 * @author you
 * 
 * 
 * 
 *
 */
public class SearchUserOptModuleTreeUnits {

    private IUserPowerMdouleService userMdoulePowerService ;

    public SearchUserOptModuleTreeUnits(IUserPowerMdouleService userMdoulePowerService) {
        this.userMdoulePowerService = userMdoulePowerService ;
    }

    // list --> treeData --> list -->treeData
    private void filterTreeData(List<?> list, String treeDatas[][], String userId) throws Exception {

        boolean ret = false ;
        List<String> powerList = userMdoulePowerService.listUserModulePowerBySysUserId(userId) ;
        List<String[]> aboutList = new ArrayList<String[]>() ;
        for (int i = 0; i < powerList.size(); i++) {
            String moduleId = powerList.get(i) ;
            fetchAboutList(aboutList, list, moduleId) ;
        }

        while (true) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    ret = true ;
                }
                String tmp[] = (String[]) list.get(i) ;
                if (!aboutList.contains(tmp)) {
                    list.remove(i) ;
                    break ;
                }
            }
            if (ret) {
                break ;
            }
        }

    }

    /**
     * 
     * @param aboutList
     * @param list
     * @param treeId  moduleId
     */
    private void fetchAboutList(List<String[]> aboutList, List<?> list, String treeId) {
        for (int i = 0; i < list.size(); i++) {
            String[] node = (String[]) list.get(i) ;
            if (node[0].equals(treeId)) {
                aboutList.add(node) ;
                if (!node[4].equals("0")) {
                    // treeId = node[4];
                    fetchAboutList(aboutList, list, node[4]) ;
                }
                break ;
            }
        }
    }

    protected List<TreeData> listTree(String loginUserId, boolean isFilterTreeData) throws Exception {
        return listTree(loginUserId, false, isFilterTreeData) ;
    }

    /**
     * 获得用户权限 
     * @param loginUserId
     * @param isAdmin
     * @return
     * @throws Exception
     */
    protected List<TreeData> listUserPowerTree(String loginUserId, boolean isAdmin) throws Exception {
        if (isAdmin) {

            return listTree(loginUserId, false, false) ;

        } else {
            return listTree(loginUserId, false, true) ;
        }
    }

    /**
     * 
     * @param loginUserId
     * @param is_power_set 是否设在权限   if false 则返回的数据没有用户权限设置
     * @param isFilterTreeData   true 这过滤掉用户没有权限的模块
     * @return
     * @throws Exception
     */
    private List<TreeData> listTree(String loginUserId, boolean is_power_sets, boolean isFilterTreeData) throws Exception {

        List<TreeData> children = new ArrayList<TreeData>() ;

        {
            String treeData[][] = userMdoulePowerService.getInitTreeMeuns() ;
            if (isFilterTreeData) {
                List<String[]> tmpList = new ArrayList<String[]>() ;
                for (int i = 0; i < treeData.length; i++) {
                    tmpList.add(treeData[i]) ;
                }
                filterTreeData(tmpList, treeData, loginUserId) ;
                treeData = new String[tmpList.size()][7] ;
                for (int i = 0; i < tmpList.size(); i++) {
                    treeData[i] = (String[]) tmpList.get(i) ;
                }
            }
            List<TreeData> list = null ;
            TreeData td = null ;
            for (int i = 0; i < treeData.length; i++) {
                if (treeData[i][4].equals("0")) {
                    td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_sets ? treeData[i][6] : null) ;
                    list = new ArrayList<TreeData>() ;
                    td.setChildren(list) ;
                    children.add(td) ;
                    findChildren(treeData, is_power_sets, treeData[i][0], list) ;
                }
            }

        }
        return children ;
    }

    private void findChildren(String[][] treeData, boolean is_power_set, String parent_id, List<TreeData> child_list) {

        TreeData td = null ;
        List<TreeData> list = null ;
        for (int i = 0; i < treeData.length; i++) {
            if (treeData[i][4].equals(parent_id)) {
                if (is_power_set) {
                    if (treeData[i][3].equals("font_weight") && treeData[i][5].equals("1")) {
                        td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_set ? treeData[i][6] : null) ;
                        child_list.add(td) ;

                        list = new ArrayList<TreeData>() ;
                        td.setChildren(list) ;
                        findChildren(treeData, is_power_set, treeData[i][0], list) ;
                    } else if (treeData[i][5].equals("1")) {
                        td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_set ? treeData[i][6] : null) ;
                        child_list.add(td) ;
                    } else if (treeData[i][3].equals("font_weight") && treeData[i][5].equals("2")) {
                        td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_set ? treeData[i][6] : null) ;
                        child_list.add(td) ;

                        list = new ArrayList<TreeData>() ;
                        td.setChildren(list) ;
                        findChildren(treeData, is_power_set, treeData[i][0], list) ;
                    } else {
                        td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_set ? treeData[i][6] : null) ;
                        child_list.add(td) ;

                        list = new ArrayList<TreeData>() ;
                        td.setChildren(list) ;
                        findChildren(treeData, is_power_set, treeData[i][0], list) ;
                    }
                } else {
                    if (treeData[i][3].equals("font_weight") && treeData[i][5].equals("1")) {
                        td = new TreeData(treeData[i][0], treeData[i][1], true, "", is_power_set ? treeData[i][6] : null) ;
                        child_list.add(td) ;
                    } else if (!treeData[i][5].equals("1")) {
                        if (treeData[i][5].equals("2")) {
                            td = new TreeData(treeData[i][0], treeData[i][1], true, "", is_power_set ? treeData[i][6] : null) ;
                            child_list.add(td) ;
                        } else {
                            td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_set ? treeData[i][6] : null) ;
                            child_list.add(td) ;
                            if (treeData[i][5].equals("0")) {
                                list = new ArrayList<TreeData>() ;
                                td.setChildren(list) ;
                                findChildren(treeData, is_power_set, treeData[i][0], list) ;
                            }
                        }
                    }
                }
            }
        }
    }

    public List<TreeData>  filterUserPowerTree(String loginUSerId , boolean is_power_set, boolean isFilterTreeData) throws Exception {
        
        
        
        List<TreeData> children=new ArrayList<TreeData>();
        // if (session.get(Constants.USER) != null)

        String treeData[][] = userMdoulePowerService.getInitTreeMeuns();
        if (isFilterTreeData) {
            List tmpList = new ArrayList() ;
            for (int i = 0; i < treeData.length; i++) {
                tmpList.add(treeData[i]) ;
            }
            filterTreeData(tmpList, treeData, loginUSerId) ;
            treeData = new String[tmpList.size()][7] ;
            for (int i = 0; i < tmpList.size(); i++) {
                treeData[i] = (String[]) tmpList.get(i) ;
            }
        }

        List<TreeData> list = null ;
        TreeData td = null ;
        for (int i = 0; i < treeData.length; i++) {
            if (treeData[i][4].equals("0")) {
                td = new TreeData(treeData[i][0], treeData[i][1], new Boolean(treeData[i][2]).booleanValue(), treeData[i][3], is_power_set ? treeData[i][6] : null) ;
                list = new ArrayList<TreeData>() ;
                td.setChildren(list) ;
                children.add(td) ;
                findChildren(treeData, is_power_set, treeData[i][0], list) ;
            }
        }

    
        return children;
    }

}
