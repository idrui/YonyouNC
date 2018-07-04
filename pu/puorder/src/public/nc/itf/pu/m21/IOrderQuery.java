/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-28 下午04:18:44
 */
package nc.itf.pu.m21;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单查询操作
 * <li>维护查询
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-12-28 下午04:18:44
 */
public interface IOrderQuery {

  /**
   * 方法功能描述：为采购订单关闭提供查询操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-11 上午11:06:31
   */
  OrderCloseVO[] closeQuery(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 方法功能描述：维护订单的查询。
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderVO[] maintainQuery(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 方法功能描述：根据表体id数组查询订单视图VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids 表体主键数组
   * @return key:表体主键 value:视图VO
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-29 下午04:12:10
   */
  Map<String, OrderViewVO> queryOrderViewsByIds(String[] ids)
      throws BusinessException;

  /**
   * 方法功能描述：根据一组表头ID查询订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-3 下午04:57:40
   */
  OrderVO[] queryOrderVOsByIds(String[] ids, UFBoolean isLazy)
      throws BusinessException;

  /**
   * 方法功能描述：采购订单修订查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond 由查询模板拼出的条件
   * @param isLazy 是否懒加载
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-16 上午10:45:04
   */
  OrderVO[] reviseQuery(IQueryScheme queryScheme) throws BusinessException;
}
