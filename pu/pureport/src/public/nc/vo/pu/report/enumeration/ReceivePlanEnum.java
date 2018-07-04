package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 到货计划 统计方式枚举类
 * 
 * @since 6.0
 * @version 2011-3-23 上午09:25:27
 * @author wangfengd
 */
public class ReceivePlanEnum extends NCMDEnum {

  /** 到货计划明细 **/
  public static final ReceivePlanEnum PLAN_DETAIL = MDEnum.valueOf(
      ReceivePlanEnum.class, Integer.valueOf(1));

  /** 到货计划汇总 **/
  public static final ReceivePlanEnum PLAN_GATHER = MDEnum.valueOf(
      ReceivePlanEnum.class, Integer.valueOf(0));

  public ReceivePlanEnum(IEnumValue enumValue) {
    super(enumValue);
  }
}
