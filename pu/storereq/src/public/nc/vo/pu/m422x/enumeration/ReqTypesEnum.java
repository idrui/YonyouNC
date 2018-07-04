package nc.vo.pu.m422x.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class ReqTypesEnum extends NCMDEnum {

  /** 毛需求 **/
  public static final ReqTypesEnum GROSS_REQUIREMENT = MDEnum.valueOf(
      ReqTypesEnum.class, Integer.valueOf(0));

  /** 净需求 **/
  public static final ReqTypesEnum NET_REQUIREMENT = MDEnum.valueOf(
      ReqTypesEnum.class, Integer.valueOf(1));

  public ReqTypesEnum(
      IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}
