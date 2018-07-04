package nc.vo.pu.m23.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class RespStatusEnum extends NCMDEnum {

  /** 接受 **/
  public static final RespStatusEnum ACCEPT = MDEnum.valueOf(
      RespStatusEnum.class, Integer.valueOf(2));

  /** 待确认 **/
  public static final RespStatusEnum ONCONFIRM = MDEnum.valueOf(
      RespStatusEnum.class, Integer.valueOf(1));

  /** 拒绝 **/
  public static final RespStatusEnum REFUSE = MDEnum.valueOf(
      RespStatusEnum.class, Integer.valueOf(3));

  public RespStatusEnum(IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}
