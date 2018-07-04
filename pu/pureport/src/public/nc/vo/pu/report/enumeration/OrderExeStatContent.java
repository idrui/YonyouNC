package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 采购订单执行查询
 * 统计内容
 * 
 * @since 6.0
 * @version 2011-7-22 上午09:54:58
 * @author wuxla
 */

public class OrderExeStatContent extends NCMDEnum {

  /**
   * 入库
   */
  public static final OrderExeStatContent ICPURIN = MDEnum.valueOf(
      OrderExeStatContent.class, Integer.valueOf(1));

  /**
   * 到货
   */
  public static final OrderExeStatContent PUARRIVE = MDEnum.valueOf(
      OrderExeStatContent.class, Integer.valueOf(0));

  /**
   * 发票
   */
  public static final OrderExeStatContent PUINVOICE = MDEnum.valueOf(
      OrderExeStatContent.class, Integer.valueOf(2));

  /**
   * 发货、装运、报关、出关
   */
  public static final OrderExeStatContent STATUS = MDEnum.valueOf(
      OrderExeStatContent.class, Integer.valueOf(3));

  public OrderExeStatContent(IEnumValue enumValue) {
    super(enumValue);
  }

}
