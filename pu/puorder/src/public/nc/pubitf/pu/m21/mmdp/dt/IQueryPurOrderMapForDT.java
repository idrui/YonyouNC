package nc.pubitf.pu.m21.mmdp.dt;

import nc.vo.pub.BusinessException;

/**
 * PU-需求跟踪查询供给-采购订单字段映射接口
 * 
 * @since 6.0
 * @version 2012-11-6 上午10:33:25
 * @author liuyand
 */
public interface IQueryPurOrderMapForDT {

  /**
   * 采购订单作为需求跟踪供给的字段映射
   * <ul>
   * <li>约定
   * <ol>
   * <li>字段映射包括别名，别名都用表名。
   * <li>接口不考虑红字单据,只要蓝字单据。
   * </ol>
   * <li>需求跟踪过滤条件
   * <ol>
   * <li>未删除
   * <li>非红字单据
   * </ul>
   * 
   * @return
   * @throws BusinessException
   */
  PurchaseOrderDTSupplyMapVO getDTSupplyMapVO() throws BusinessException;

}
