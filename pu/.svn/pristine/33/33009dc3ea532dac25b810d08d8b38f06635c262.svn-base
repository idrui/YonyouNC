package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class EnumActive extends NCMDEnum {

  // 激活
  public static final EnumActive ACTIVE = MDEnum.valueOf(EnumActive.class,
      Integer.valueOf(0));

  // 关闭
  public static final EnumActive CLOSE = MDEnum.valueOf(EnumActive.class,
      Integer.valueOf(1));

  // 修正历史
  public static final EnumActive REVISEHISTORY = MDEnum.valueOf(
      EnumActive.class, Integer.valueOf(3));

  public EnumActive(IEnumValue value) {
    super(value);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

  /**
   * 父类方法重写
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

}
