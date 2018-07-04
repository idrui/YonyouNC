package nc.vo.pu.m23.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 检验状态枚举类
 * 
 * @since 6.0
 * @version 2011-7-6 上午09:56:51
 * @author liugxa
 */
public class POCheckStatus extends NCMDEnum {
  /** 检验完成 **/
  public static final POCheckStatus CHECKED =
      MDEnum.valueOf(POCheckStatus.class, Integer.valueOf(2));

  /** 已报检未返回结算 **/
  public static final POCheckStatus CHECKING =
      MDEnum.valueOf(POCheckStatus.class, Integer.valueOf(1));

  /** 未报检 **/
  public static final POCheckStatus UNCHECK =
      MDEnum.valueOf(POCheckStatus.class, Integer.valueOf(0));

  /**
   * POEnumBillStatus 的构造子
   * 
   * @param enumvalue
   */
  public POCheckStatus(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int getReturnType() {
    return IType.TYPE_INT;
  }
}
