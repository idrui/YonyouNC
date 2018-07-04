package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class EnumRowStatus extends NCMDEnum {

  // 确认
  public static final EnumRowStatus CONFIRM = MDEnum.valueOf(
      EnumRowStatus.class, Integer.valueOf(1));

  // 自由
  public static final EnumRowStatus FREE = MDEnum.valueOf(EnumRowStatus.class,
      Integer.valueOf(0));

  public EnumRowStatus(IEnumValue value) {
    super(value);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_Integer;
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
