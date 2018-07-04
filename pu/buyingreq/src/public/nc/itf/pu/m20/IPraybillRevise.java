package nc.itf.pu.m20;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修订操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 下午04:02:43
 */
public interface IPraybillRevise {

  /**
   * 方法功能描述：请购单修订历史查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   *          查询条件
   * @param isLazy
   *          是否懒加载
   * @return 历史请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-28 下午04:25:07
   */
  PraybillVO[] queryHistory(String sql, UFBoolean isLazy)
      throws BusinessException;

  /**
   * 方法功能描述：请购单修订查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   *          查询条件
   * @param isLazy
   *          是否懒加载
   * @return 请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-28 下午04:25:07
   */
  PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：请购单修订保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          请购单
   * @return 请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-21 下午04:02:30
   */
  PraybillVO[] reviseSave(PraybillVO[] vos, PraybillContext[] ctxs)
      throws BusinessException;

}
