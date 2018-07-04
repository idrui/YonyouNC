/**
 * 
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单维护操作
 * <li>保存
 * <li>删除
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-28 下午04:15:32
 */
public interface IOrderMaintain {

  /**
   * 方法功能描述：删除订单。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos 轻量级vo
   * @param ctxs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-28 下午04:17:17
   */
  public void delete(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException;

  /**
   * 订单删除
   * 
   * @param orderVos 订单的全vo
   * @param ctxs 上下文环境
   * @throws BusinessException
   */
  public void deleteByFullVOs(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException;

  /**
   * 方法功能描述：冻结订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos 要冻结的订单VO数组（轻量级vo）
   * @param freezeReason 冻结原因
   * @return 冻结后的订单VO数组（轻量级vo）
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 下午01:09:17
   */
  public OrderVO[] freezeOrder(OrderVO[] orderVos, String freezeReason)
      throws BusinessException;

  /**
   * 订单冻结-依赖全vo
   * 
   * @param orderVos 订单全vo
   * @param freezeReason
   * @return 冻结后的订单全vo
   * @throws BusinessException
   */
  public OrderVO[] freezeOrderByFullVOs(OrderVO[] orderVos, String freezeReason)
      throws BusinessException;

  /**
   * 新增订单
   * 
   * @param orderClientVos 订单全vo数组
   * @param ctx 上下文环境
   * @return 订单全vo数组
   * @throws BusinessException
   */
  public OrderVO[] insert(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException;

  /**
   * 维护保存
   * 
   * @param orderClientVos 前台全vo
   * @return 保存后的全vo
   * @throws BusinessException
   */
  public OrderVO[] save(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException;

  /**
   * 方法功能描述：解冻订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos 要解冻的订单VO数组（轻量级vo）
   * @return 解冻后的订单VO数组（轻量级vo）
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 下午01:09:46
   */
  public OrderVO[] unfreezeOrder(OrderVO[] orderVos) throws BusinessException;

  /**
   * 订单解冻-依赖全vo
   * 
   * @param orderVos 订单全vo
   * @return 解冻后的订单全vo
   * @throws BusinessException
   */
  public OrderVO[] unfreezeOrderByFullVOs(OrderVO[] orderVos)
      throws BusinessException;

  /**
   * 修改保存（更新）订单
   * 
   * @param orderClientVos 订单全vo数组
   * @param ctx 上下文
   * @return 订单全vo数组
   * @throws BusinessException
   */
  public OrderVO[] update(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException;
}
