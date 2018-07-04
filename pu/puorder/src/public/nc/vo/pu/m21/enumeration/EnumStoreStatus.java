package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 供应商交货状态
 * 
 * @since 6.0
 * @version 2011-3-15 下午03:19:35
 * @author wuxla
 */

public class EnumStoreStatus extends NCMDEnum {
  /**
   * 全部发货
   */
  public static final EnumStoreStatus ALLSENDOUT = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(5));

  /**
   * 在途
   */
  public static final EnumStoreStatus ONLOAD = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(4));

  /**
   * 生产
   */
  public static final EnumStoreStatus ONPRODUCE = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(2));

  /**
   * 采购
   */
  public static final EnumStoreStatus PURCHASE = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(1));

  /**
   * 库存
   */
  public static final EnumStoreStatus STOCK = MDEnum.valueOf(
      EnumStoreStatus.class, Integer.valueOf(3));

  /**
   * @param enumValue
   */
  public EnumStoreStatus(IEnumValue enumValue) {
    super(enumValue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

}
