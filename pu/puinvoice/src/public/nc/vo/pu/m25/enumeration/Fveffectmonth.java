package nc.vo.pu.m25.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.scmpub.model.enumeration.AbstractIntMDEnum;

/**
 * 生效月
 * 
 * @since 6.31
 * @version 2014-06-23 14:04:10
 * @author mengjian
 */
public class Fveffectmonth extends AbstractIntMDEnum {

  /**
   * 当月生效
   */
  public static final Fveffectmonth CURMON = MDEnum.valueOf(
      Fveffectmonth.class, Integer.valueOf(0));

  /**
   * 下月生效
   */
  public static final Fveffectmonth NEXTMON = MDEnum.valueOf(
      Fveffectmonth.class, Integer.valueOf(1));

  /**
   * @param enumValue
   */
  public Fveffectmonth(IEnumValue enumValue) {
    super(enumValue);

  }

}
