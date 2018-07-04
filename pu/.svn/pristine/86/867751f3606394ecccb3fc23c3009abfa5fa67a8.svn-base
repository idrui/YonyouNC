package nc.vo.pu.report.adjustor;

import java.util.HashSet;
import java.util.Set;

import nc.pub.smart.data.DataSet;
import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.SmartModel;
import nc.scmmm.pub.scmpub.report.adjustor.SCMRptDefaultAdjustor;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.report.enumeration.StoreReqSummaryQryFieldCode;
import nc.vo.pu.report.enumeration.StoreReqSummaryTypeEnum;

public class StoreReqSummaryAdjustor extends SCMRptDefaultAdjustor {

  private static final long serialVersionUID = -9035337053207824840L;

  @Override
  protected DataSet dataSetProcessor(DataSet ds, SmartModel smart) {
    return null;
  }

  @Override
  protected Field[] getHidenFieldAry() {
    return null;
  }

  @Override
  protected String[] getHidenFields() {
    this.setHiddenRelatedField(true);
    Set<String> set = new HashSet<String>();
    String summaryType =
        this.getTranMap()
            .getConditionVO(
                StoreReqSummaryQryFieldCode.summarytype.getFieldCode())
            .getValue();

    // 根据物料汇总
    if (StoreReqSummaryTypeEnum.MATERIAL.getEnumValue().getValue()
        .equals(summaryType)) {
      set.add(StoreReqAppHeaderVO.PK_ORG_V);
      set.add(StoreReqAppItemVO.PK_REQSTORDOC);
      set.add(StoreReqAppItemVO.DREQDATE);
      return set.toArray(new String[] {});
    }
    // 根据需求库存组织 + 物料汇总
    else if (StoreReqSummaryTypeEnum.ORG_MATERIAL.getEnumValue().getValue()
        .equals(summaryType)) {
      set.add(StoreReqAppItemVO.PK_REQSTORDOC);
      set.add(StoreReqAppItemVO.DREQDATE);
      return set.toArray(new String[] {});
    }
    // 根据需求库存组织 + 需求日期 + 物料汇总
    else if (StoreReqSummaryTypeEnum.ORG_REQDATE_MATERIAL.getEnumValue()
        .getValue().equals(summaryType)) {
      set.add(StoreReqAppItemVO.PK_REQSTORDOC);
      return set.toArray(new String[] {});
    }
    // 根据需求日期 + 物料汇总
    else if (StoreReqSummaryTypeEnum.REQDATE_MATERIAL.getEnumValue().getValue()
        .equals(summaryType)) {
      set.add(StoreReqAppHeaderVO.PK_ORG_V);
      set.add(StoreReqAppItemVO.PK_REQSTORDOC);
      return set.toArray(new String[] {});
    }
    // 根据需求仓库 + 物料汇总
    else if (StoreReqSummaryTypeEnum.REQSTORDOC_MATERIAL.getEnumValue()
        .getValue().equals(summaryType)) {
      set.add(StoreReqAppHeaderVO.PK_ORG_V);
      set.add(StoreReqAppItemVO.DREQDATE);
      return set.toArray(new String[] {});
    }
    return null;
  }

  @Override
  protected Set<String> getShowFields() {
    return null;
  }

}
