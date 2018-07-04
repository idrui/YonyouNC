package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

public class StoreReqSummaryTypeEnum extends NCMDEnum {

  /** 物料 **/
  public static final StoreReqSummaryTypeEnum MATERIAL = MDEnum.valueOf(
      StoreReqSummaryTypeEnum.class, Integer.valueOf(1));

  /** 需求库存组织 + 物料 **/
  public static final StoreReqSummaryTypeEnum ORG_MATERIAL = MDEnum.valueOf(
      StoreReqSummaryTypeEnum.class, Integer.valueOf(2));

  /** 库存组织 + 需求日期 + 物料 **/
  public static final StoreReqSummaryTypeEnum ORG_REQDATE_MATERIAL = MDEnum
      .valueOf(StoreReqSummaryTypeEnum.class, Integer.valueOf(5));

  /** 需求日期 + 物料 **/
  public static final StoreReqSummaryTypeEnum REQDATE_MATERIAL = MDEnum
      .valueOf(StoreReqSummaryTypeEnum.class, Integer.valueOf(4));

  /** 需求仓库 + 物料 **/
  public static final StoreReqSummaryTypeEnum REQSTORDOC_MATERIAL = MDEnum
      .valueOf(StoreReqSummaryTypeEnum.class, Integer.valueOf(3));

  public StoreReqSummaryTypeEnum(IEnumValue enumValue) {
    super(enumValue);
    //
  }
}
