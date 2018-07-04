package nc.pubitf.pu.m4201;

import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存采购入库单签字写入库存财务
 * <li>库存采购入库单取消签字删除库存财务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-27 下午04:08:04
 */
public interface IStockFinanceMaintain {
  /**
   * 方法功能描述：库存采购入库单取消签字删除库存财务
   * <p>
   * <b>参数说明</b>
   * 
   * @param cpurchaseinids
   *          采购入库单的表头ID数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-27 下午04:13:08
   */
  public void deleteForM45Unsign(String[] cpurchaseinids)
      throws BusinessException;

  /**
   * 方法功能描述：库存采购入库单签字写入库存财务
   * <p>
   * <b>参数说明</b>
   * 
   * @param purchaseinVos
   *          采购入库单的VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-27 下午04:13:03
   */
  public void insertForM45Sign(PurchaseInVO[] purchaseinVos)
      throws BusinessException;
}
