package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 到货单查询报表汇总条件枚举类
 * 
 * @since 6.0
 * @version 2011-2-22 上午09:52:57
 * @author yinfy
 */

public class PuArrivalGroupEnum extends NCMDEnum {

  /** 按到货明细汇总 **/
  public static final PuArrivalGroupEnum ARRIVEDETAIL = MDEnum.valueOf(
      PuArrivalGroupEnum.class, Integer.valueOf(1));

  /** 按物料汇总 **/
  public static final PuArrivalGroupEnum MAR = MDEnum.valueOf(
      PuArrivalGroupEnum.class, Integer.valueOf(0));

  /**
   * @param enumValue
   */
  public PuArrivalGroupEnum(IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}
