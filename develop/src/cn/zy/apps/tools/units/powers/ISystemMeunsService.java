package cn.zy.apps.tools.units.powers;

import java.util.List ;


/**
 * 呈现TREE
 * @author you
 *
 */

public interface  ISystemMeunsService {
      /**
       * 查询用户已有的权限模块
       * @param loginUserId
       * @return
       * @throws SystemMeunsException
       */
    public List<TreeData> searchUserPowerTree(String loginUserId,boolean isAdmin) throws SystemMeunsException;
    
    /**
     * 查询用户已有的权限模块
     * @param loginUserId
     * @return
     * @throws SystemMeunsException
     */
  public List<TreeData> filterUserPowerTree(String loginUserId) throws SystemMeunsException;

}
