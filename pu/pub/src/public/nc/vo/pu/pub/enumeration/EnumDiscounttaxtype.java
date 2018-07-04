package nc.vo.pu.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class EnumDiscounttaxtype extends NCMDEnum {

  // 应税内含
  public static final EnumDiscounttaxtype TAXIN = MDEnum.valueOf(
      EnumDiscounttaxtype.class, Integer.valueOf(0));

  // 应税外加
  public static final EnumDiscounttaxtype TAXOUT = MDEnum.valueOf(
      EnumDiscounttaxtype.class, Integer.valueOf(1));

  public EnumDiscounttaxtype(IEnumValue value) {
    super(value);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

  @Override
  public int hashCode() {
    return ((Integer) this.value()).intValue();
  }

}
