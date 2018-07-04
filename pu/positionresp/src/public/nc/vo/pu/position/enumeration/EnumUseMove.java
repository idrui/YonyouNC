package nc.vo.pu.position.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>岗位设置 应用/排除 枚举
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-28 下午04:13:09
 */
public class EnumUseMove extends NCMDEnum {
  /** 排除 **/
  public static final int MOVE = ((Integer) MDEnum.valueOf(EnumUseMove.class,
      Integer.valueOf(2)).value()).intValue();

  /** 应用 **/
  public static final int USE = ((Integer) MDEnum.valueOf(EnumUseMove.class,
      Integer.valueOf(1)).value()).intValue();

  public EnumUseMove(IEnumValue enumvalue) {
    super(enumvalue);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

}
