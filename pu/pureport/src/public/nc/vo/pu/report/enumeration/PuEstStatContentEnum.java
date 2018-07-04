package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * 暂估月统计统计内容枚举
 * 
 * @since 6.0
 * @version 2011-3-5 下午03:24:02
 * @author yinfy
 */

public class PuEstStatContentEnum extends NCMDEnum {
  /** 查询包括期初暂估 **/
  public static final PuEstStatContentEnum INITEST = MDEnum.valueOf(
      PuEstStatContentEnum.class, Integer.valueOf(4213));

  /** 查询包括采购入库暂估 **/
  public static final PuEstStatContentEnum STOCKPS = MDEnum.valueOf(
      PuEstStatContentEnum.class, Integer.valueOf(4201));

  /** 查询包括委托加工入库暂估 **/
  public static final PuEstStatContentEnum SUBCONTIN = MDEnum.valueOf(
      PuEstStatContentEnum.class, Integer.valueOf(4203));

  /** 查询包括消耗汇总暂估 **/
  public static final PuEstStatContentEnum VMIFI = MDEnum.valueOf(
      PuEstStatContentEnum.class, Integer.valueOf(4202));

  /**
   * @param enumValue
   */
  public PuEstStatContentEnum(IEnumValue enumValue) {
    super(enumValue);
  }

}
