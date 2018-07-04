/**
 * $$文件说明$$
 * 
 * @author zhangyhh
 * @version 6.31
 * @see
 * @since 6.31
 * @time 2013-09-10 09:15:31
 */
package nc.vo.pu.m27.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class EnumSettleOrderType extends NCMDEnum {
  /** 进口结算 **/
  public static final EnumSettleOrderType IT = MDEnum.valueOf(
      EnumSettleOrderType.class, Integer.valueOf(1));

  /** 采购结算 **/
  public static final EnumSettleOrderType PU = MDEnum.valueOf(
      EnumSettleOrderType.class, Integer.valueOf(0));

  public EnumSettleOrderType(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}
