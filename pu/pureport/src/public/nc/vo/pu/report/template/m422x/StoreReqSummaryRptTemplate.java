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
    // ���������������뵥��ͷ�ֶ�
    SCMProviderMetaUtil.getSCMProviderMetaData(metaData,
        new StoreReqAppHeaderVO().getMetaData(), true);
    // ���������������뵥�����ֶ�
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
      // ��ȡ��ѯ�����еĻ��ܷ�ʽ��Ĭ��ȡ���ϣ����������Ϊ��ʱ����������ȡ��������ģ�͵����ʱ������Ϊ�ա���
      summaryType =
          Integer.valueOf(SCMConditionVOUtil.getConditionVO(
              this.getScmQueryCondition().getLogicConditions(),
              StoreReqSummaryQryFieldCode.summarytype.getFieldCode())
              .getValue());
      // ����from��where
      view.processFromAndWhere(this.getScmQueryCondition()
          .getGeneralConditions());
    }
    // �������ϻ���
    if (StoreReqSummaryTypeEnum.MATERIAL.toInteger().equals(summaryType)) {
      view.addFieldsByMaterial();
    }
    // ������������֯ + ���ϻ���
    else if (StoreReqSummaryTypeEnum.ORG_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByOrgMaterial();
    }
    // ������������֯ + �������� + ���ϻ���
    else if (StoreReqSummaryTypeEnum.ORG_REQDATE_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByOrgReqDateMaterial();
    }
    // ������������ + ���ϻ���
    else if (StoreReqSummaryTypeEnum.REQDATE_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByReqDateMaterial();
    }
    // ��������ֿ� + ���ϻ���
    else if (StoreReqSummaryTypeEnum.REQSTORDOC_MATERIAL.toInteger().equals(
        summaryType)) {
      view.addFieldsByReqStorDocMaterial();
    }
    return view;
  }
}
