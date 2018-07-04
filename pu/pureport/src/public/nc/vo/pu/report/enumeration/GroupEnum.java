package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class GroupEnum extends NCMDEnum {

  public static final GroupEnum DATE = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(6));

  public static final GroupEnum DEPT = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(3));

  /**
   * 物料
   * 
   * @param enumValue
   */

  public static final GroupEnum MAR = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(1));

  /**
   * 物料基本分类
   */
  public static final GroupEnum MARCLASS = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(0));

  /**
   * 月份
   */
  public static final GroupEnum MONTH = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(5));

  /**
   * 采购员
   */
  public static final GroupEnum PSNDOC = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(4));

  /**
   * 采购组织
   */
  public static final GroupEnum PUR_ORG = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(2));

  /**
   * 供应商
   */
  public static final GroupEnum SUPPLIER = MDEnum.valueOf(GroupEnum.class,
      Integer.valueOf(7));

  /**
   * @param enumValue
   */
  public GroupEnum(IEnumValue enumValue) {
    super(enumValue);
  }
}
