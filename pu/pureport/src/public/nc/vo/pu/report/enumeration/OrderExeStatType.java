package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 采购订单执行查询
 * 统计方式
 * 
 * @since 6.0
 * @version 2011-7-22 上午09:36:31
 * @author wuxla
 */

public class OrderExeStatType extends NCMDEnum {

  /**
   * 采购订单行执行汇总
   */
  public static final OrderExeStatType EXECGATHER = MDEnum.valueOf(
      OrderExeStatType.class, Integer.valueOf(1));

  /**
   * 采购订单行执行明细
   */
  public static final OrderExeStatType EXECLIST = MDEnum.valueOf(
      OrderExeStatType.class, Integer.valueOf(0));

  public OrderExeStatType(IEnumValue enumValue) {
    super(enumValue);
  }
}
