package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class PrayBillGroupEnum extends NCMDEnum {

  /** 按请购单明细 **/
  public static final PrayBillGroupEnum EXEDETAIL = MDEnum.valueOf(
      PrayBillGroupEnum.class, Integer.valueOf(0));

  /** 按请购单汇总 **/
  public static final PrayBillGroupEnum EXEGROUP = MDEnum.valueOf(
      PrayBillGroupEnum.class, Integer.valueOf(1));

  /** 按物料汇总 **/
  public static final PrayBillGroupEnum MAR = MDEnum.valueOf(
      PrayBillGroupEnum.class, Integer.valueOf(2));

  /**
   * @param enumValue
   */
  public PrayBillGroupEnum(IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

}
