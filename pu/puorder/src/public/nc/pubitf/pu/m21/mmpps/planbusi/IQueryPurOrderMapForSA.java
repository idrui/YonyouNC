package nc.pubitf.pu.m21.mmpps.planbusi;

import nc.vo.pub.BusinessException;

/**
 * PU-供给影响分析查询供给-采购订单字段映射接口
 * 
 * @since 6.0
 * @version 2012-11-6 上午11:25:13
 * @author liuyand
 */
public interface IQueryPurOrderMapForSA {

  /**
   * 供给影响分析查询采购订单的字段映射
   * <ul>
   * <li>约定
   * <ol>
   * <li>字段映射包括别名，别名都用表名。
   * <li>接口不考虑红字单据,只要蓝字单据。
   * </ol>
   * <li>供给影响分析过滤条件
   * <ol>
   * <li>未删除
   * <li>非红字单据
   * </ul>
   * 
   * @return
   * @throws BusinessException
   */
  PurchaseOrderSASupplyMapVO getSASupplyMapVO() throws BusinessException;
}
