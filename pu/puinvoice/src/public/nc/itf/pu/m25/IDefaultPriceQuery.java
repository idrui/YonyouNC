/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 上午10:57:29
 */
package nc.itf.pu.m25;

import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票默认询价操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-19 上午10:57:29
 */
public interface IDefaultPriceQuery {
  /**
   * 优质优价的询价
   * 
   * @param para
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryHqHpPrice(InvoicePriceQueryVO[] para)
      throws BusinessException;

  /**
   * 询计划价
   * 
   * @param para
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryPlanPrice(InvoicePriceQueryVO[] para)
      throws BusinessException;

  /**
   * 只根据价格来源询价
   * 
   * @param para
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryPriceBySysPara(InvoicePriceQueryVO[] para)
      throws BusinessException;

  /**
   * 包含优质优价的询价，同时也会根据价格来源询价，优质优价的价格处理在前。
   * 
   * @param para
   * @param hqhp 是否优质优价
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryPriceWithHqHp(InvoicePriceQueryVO[] para)
      throws BusinessException;
}
