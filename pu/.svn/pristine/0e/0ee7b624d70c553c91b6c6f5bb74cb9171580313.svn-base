package nc.itf.pu.m23.maintain;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>到货单的基本操作接口定义，本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的新增保存
 * <li>到货单的修改保存
 * <li>到货单的删除
 * <li>到货单的查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午01:41:42
 */
public interface IArriveMaintain {

  /**
   * 方法功能描述：完成到货单的删除功能
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVOArray 全vo。
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-11 下午02:07:01
   */
  public void deleteArrive(ArriveVO[] aggVOArray, ArrivalUIToBSEnv env)
      throws BusinessException;

  /**
   * 新增保存
   * 
   * @param aggVOArray
   * @param env
   * @return
   * @throws BusinessException
   */
  public ArriveVO[] insert(ArriveVO[] aggVOArray, ArrivalUIToBSEnv env)
      throws BusinessException;

  /**
   * 方法功能描述：完成到货单的查询功能
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme 查询条件VO
   * @return 到货单VO数组
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-11 下午02:10:44
   */
  public ArriveVO[] queryArrive(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：检验到货单的查询服务
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme 查询条件VO
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-12 下午01:37:00
   */
  public ArriveViewVO[] queryCheckArrive(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：检验到货单的查询服务
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-12 下午01:37:00
   */
  public ArriveViewVO[] queryCheckArrive(String sql) throws BusinessException;

  /**
   * 方法功能描述：完成到货单的保存，包括新增保存、修改保存、退货保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param Vos 全vo。
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-2-25 上午10:41:21
   */
  public ArriveVO[] saveBase(ArriveVO[] aggVOArray, ArrivalUIToBSEnv[] envs)
      throws BusinessException;

  /**
   * 修改保存
   * 
   * @param aggVOArray
   * @param env
   * @return
   * @throws BusinessException
   */
  public ArriveVO[] update(ArriveVO[] aggVOArray, ArrivalUIToBSEnv env)
      throws BusinessException;

  /**
   * 懒加载时刷新表体
   * 
   * @param vos 懒加载时只有表体VO的聚合VO
   * @return
   * @throws BusinessException
   */
  ArriveVO[] refreshItems(ArriveVO[] vos) throws BusinessException;
}
