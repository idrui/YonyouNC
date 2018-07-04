package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

public class EnumBillStatus extends POEnumBillStatus {

  /** ��� */
  public static final EnumBillStatus EXPORT = MDEnum.valueOf(
      EnumBillStatus.class, Integer.valueOf(5));

  public EnumBillStatus(IEnumValue value) {
    super(value);
  }

}
