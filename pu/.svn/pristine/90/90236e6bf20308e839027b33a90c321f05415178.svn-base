/**
 * $$文件说明$$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-01-06 14:50:20
 */
package nc.vo.pu.m21.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单冻结类型
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-01-06 14:50:20
 */
public class EnumFreezeType extends NCMDEnum {
  /** 手工冻结 **/
  public static final EnumFreezeType MANUAL_FREEZE = MDEnum.valueOf(
      EnumFreezeType.class, Integer.valueOf(0));

  /** 超最高限价自动冻结 **/
  public static final EnumFreezeType MAXPRICE_OVER_FREEZE = MDEnum.valueOf(
      EnumFreezeType.class, Integer.valueOf(1));

  /** 超最高库存自动冻结 **/
  public static final EnumFreezeType MAXSTOCKS_OVER_FREEZE = MDEnum.valueOf(
      EnumFreezeType.class, Integer.valueOf(2));

  public EnumFreezeType(IEnumValue enumvalue) {
    super(enumvalue);
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
