/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午08:03:48
 */
package nc.pubitf.pu.m21.mm;

import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生成制造与排程：采购订单供给量接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-6 下午08:03:48
 */
public interface IOrderQueryExecForMM {

  /**
   * <p>
   * 使用场景：产品成本模块生成成本BOM材料子项时，需要取材料的最新采购价作为材料子项的单价。
   * <ul>
   * <li>
   * <li>查找工厂组织的物料的最新采购价
   * </ul>
   * 
   * @param pk_arrvstoorgs 工厂组织（收货库存组织）
   * @param moids 物料oid
   * @return OrderPriceData 查到的最新价、本币币种
   */
  public OrderPriceData[] getLatestPrice(String[] pk_arrvstoorgs, String[] moids)
      throws BusinessException;

  /**
   * mrp/mps查询采购订单的供给的SQL片段
   * 
   * @param includeRed 是否查询退库的单据
   * @return 含有sql片段的vo，并包含一些字段信息等
   * @throws BusinessException
   */
  public ISupplyResultForCalc getSupplyResult(boolean includeRed)
      throws BusinessException;
}
