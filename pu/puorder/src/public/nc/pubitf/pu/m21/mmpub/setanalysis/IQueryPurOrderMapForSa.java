package nc.pubitf.pu.m21.mmpub.setanalysis;

import nc.vo.pub.BusinessException;

/**
 * 齐套分析查询供给-采购订单字段映射接口
 * 
 * @since 6.0
 * @version 2012-11-5 下午04:04:28
 * @author liuyand
 */
public interface IQueryPurOrderMapForSa {
  /**
   * 齐套分析查询供给-采购订单字段映射
   * <ul>
   * <li>约定:
   * <ol>
   * <li>字段映射包括别名，别名都用表名。
   * </ol>
   * <li>齐套分析过滤条件
   * <ol>
   * <li>最新版本
   * <li>行关闭为false
   * <li>主数量-累计入库主数量-预留主数量大于零
   * <li>非红字单据
   * </ul>
   * 
   * @return
   * @throws BusinessException
   */
  PurchaseOrderSaSupplyMapVO getSaSupplyMapVO() throws BusinessException;
}
