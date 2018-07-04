/**
 * $$文件说明$$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-01-26 18:32:07
 */
package nc.vo.pu.m20.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-01-26 18:32:07
 */
public class EnumBillStatue extends POEnumBillStatus {

  /** 关闭 */
  public static final EnumBillStatue CLOSE = MDEnum.valueOf(
      EnumBillStatue.class, Integer.valueOf(5));

  /**
   * EnumBillStatue 的构造子
   * 
   * @param enumvalue
   */
  public EnumBillStatue(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
