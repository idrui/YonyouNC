package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 采购订单退货明细查询退货类型枚举
 * 
 * @since 6.0
 * @version 2011-3-10 下午07:38:55
 * @author yinfy
 */

public class ReturntypeEnum extends NCMDEnum {
  /** 到货退货 **/
  public static final ReturntypeEnum ARRIVAL_RTN = MDEnum.valueOf(
      ReturntypeEnum.class, Integer.valueOf(0));

  /** 库存退货 **/
  public static final ReturntypeEnum INV_RTN = MDEnum.valueOf(
      ReturntypeEnum.class, Integer.valueOf(1));

  /**
   * @param enumValue
   */
  public ReturntypeEnum(IEnumValue enumValue) {
    super(enumValue);
    //
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }

}
