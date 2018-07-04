package nc.vo.pu.report.template.m422x;

import nc.scmmm.pub.scmpub.report.rptutil.SCMConditionVOUtil;
import nc.scmmm.pub.scmpub.report.rptutil.SCMProviderMetaUtil;
import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.pub.scmpub.report.smart.templet.SimpleAbsRptViewSQLTemplet;
import nc.scmmm.vo.scmpub.report.entity.metadata.SCMProviderMetaData;
import nc.scmmm.vo.scmpub.report.viewfactory.define.SCMView;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.report.enumeration.StoreReqSummaryQryFieldCode;
import nc.vo.pu.report.enumeration.StoreReqSummaryTypeEnum;
import nc.vo.pu.report.view.m422x.StoreReqSummaryRptView;
import nc.vo.pub.BusinessException;
import nc.vo.pub.query.ConditionVO;

public class StoreReqSummaryRptTemplate extends SimpleAbsRptViewSQLTemplet {

  @Override
  protected SCMRptAbsScalePrcStrategy getScalePrcStrategy() {
    return null;
  }

  @Override
  protected SCMProviderMetaData getSCMProviderMetaData()
      throws BusinessException {
    SCMProviderMetaData metaData = new SCMProviderMetaData();
    // 增加物资需求申请单表头字段
    SCMProviderMetaUtil.getSCMProviderMetaData(metaData,
        new StoreReqAppHeaderVO().getMetaData(), true);
    // 增加物资需求申请单表体字段
    SCMProviderMetaUtil.getSCMProviderMetaData(metaData,
        new StoreReqAppItemVO().getMetaData(), true);
    return metaData;
  }

  @Override
  protected SCMView getSCMView() {
    StoreReqSummaryRptView view =
        new StoreReqSummaryRptView(this.getScmReportContext());

    Integer summaryType = StoreReqSummaryTypeEnum.MATERIAL.toInteger();
    ConditionVO vo = SCMConditionVOUtil.getConditionVO(
        this.getScmQueryCondition().getAllQueryConditions(),
        StoreReqSummaryQryFieldCode.freqtypeflag.getFieldCode());
    String freqtypeflag =
        vo==null ? null : vo.getValue();
    if (freqtypeflag.contains("0") && freqtypeflag.contains("1")) {
      String SqlWhere = this.getScmQueryCondition().getSqlWhere();
      StringBuilder builder = new StringBuilder();
      builder.append(SqlWhere);
      builder.append(" and csourcetypecode!='m422x'");
    }
    if (this.getScmQueryCondition().getLogicConditions() != null) {
      // 获取查询条件中的汇总方式（默认取物料，如果条件不为空时，从条件中取，在语义模型点完成时条件会为空。）
      summaryType =
          Integer.valueOf(SCMConditionVOUtil.getConditionVO(
              this.getScmQueryCondition().getLogicConditions(),
              StoreReqSummaryQryFieldCode.summarytype.getFieldCode())
              .getValue());
      // 处理from和where
      view.processFromAndWhere(this.getScmQueryCondition()
          .getGeneralConditions());
    }
    // 根据物料汇总
    if (StoreReqSummaryTypeEnum.MATERIAL.toInteger().equals(summaryType)) {
      view.addFieldsByMaterial();
    }
    // 根据需求库存组织 + 物料汇总
    else if (StoreReqSummaryTypeEnum.ORG_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByOrgMaterial();
    }
    // 根据需求库存组织 + 需求日期 + 物料汇总
    else if (StoreReqSummaryTypeEnum.ORG_REQDATE_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByOrgReqDateMaterial();
    }
    // 根据需求日期 + 物料汇总
    else if (StoreReqSummaryTypeEnum.REQDATE_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByReqDateMaterial();
    }
    // 根据需求仓库 + 物料汇总
    else if (StoreReqSummaryTypeEnum.REQSTORDOC_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByReqStorDocMaterial();
    }
    return view;
  }
}
