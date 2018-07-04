package nc.vo.pu.report.template.order;

import nc.scmmm.pub.scmpub.report.rptutil.SCMProviderMetaUtil;
import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.pub.scmpub.report.smart.templet.SimpleAbsRptViewSQLTemplet;
import nc.scmmm.vo.scmpub.report.entity.metadata.SCMField;
import nc.scmmm.vo.scmpub.report.entity.metadata.SCMProviderMetaData;
import nc.scmmm.vo.scmpub.report.viewfactory.define.SCMView;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.enumeration.OrderSummaryDynamicFieldCode;
import nc.vo.pu.report.view.order.OrderSummaryRptView;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.query.ConditionVO;

public class OrderSummaryRptTemplate extends SimpleAbsRptViewSQLTemplet {

  @Override
  protected SCMRptAbsScalePrcStrategy getScalePrcStrategy() {
    return null;
  }

  @Override
  protected SCMProviderMetaData getSCMProviderMetaData()
      throws BusinessException {
    SCMProviderMetaData metaData = new SCMProviderMetaData();
    // 增加采购订单表头字段
    SCMProviderMetaUtil.getSCMProviderMetaData(metaData,
        new OrderHeaderVO().getMetaData(), true);
    // 增加采购订单表体字段
    SCMProviderMetaUtil.getSCMProviderMetaData(metaData,
        new OrderItemVO().getMetaData(), true);

    // 添加退货金额动态字段标识
    SCMField bReturNMNY =
        SCMProviderMetaUtil.getDynamicField(
            OrderSummaryDynamicFieldCode.returnmny.getCode(),
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("40042004",
                "1400420040061")/*
                                 * @res
                                 * "退货金额"
                                 */, JavaType.UFDouble);
    metaData.addField(bReturNMNY);
    return metaData;
  }

  @Override
  protected SCMView getSCMView() {
    OrderSummaryRptView view =
        new OrderSummaryRptView(this.getScmReportContext());

    ConditionVO[] conds = this.getScmQueryCondition().getGeneralConditions();
    // 在语义模型完成保存时可能为空，会报空指针。
    if (conds != null) {
      view.processFromAndWhere(conds);
    }
    return view;
  }

}
