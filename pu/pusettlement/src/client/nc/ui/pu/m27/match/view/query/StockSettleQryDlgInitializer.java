package nc.ui.pu.m27.match.view.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.EnumRefRegisterInfo;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m4201.enumeration.StockQryFieldCode;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �����ѯ���Ļ��ܵĶԻ��򹫹���ʼ����
 * 
 * @since 6.0
 * @version 2011-3-24 ����01:46:39
 * @author �����
 */

public class StockSettleQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  private QueryConditionDLGDelegator condDLGDelegator;

  public QueryConditionDLGDelegator getCondDLGDelegator() {
    return this.condDLGDelegator;
  }

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.condDLGDelegator = condDLGDelegator;
    // ��֧������Ȩ��
    condDLGDelegator.setPowerEnable(false);
    this.initFilters(condDLGDelegator);// ���չ���
    this.setDefaultValue(condDLGDelegator);// ����Ĭ��ֵ
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  public void setCondDLGDelegator(QueryConditionDLGDelegator condDLGDelegator) {
    this.condDLGDelegator = condDLGDelegator;
  }

  protected void initFilters(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ϻ����������
    QMarbasclassFilter classFilter =
        new QMarbasclassFilter(condDLGDelegator,
            StockQryFieldCode.marbascls.code());
    classFilter.setPk_orgCode(StockQryFieldCode.financeorgbody.code());
    classFilter.addEditorListener();

    // ���ϱ���
    QMarterialFilter materialFilter =
        new QMarterialFilter(condDLGDelegator,
            StockQryFieldCode.financeorgbody.code(),
            StockQryFieldCode.marcode.code());
    materialFilter.addEditorListener();

    QMarterialFilter srcmaterialFilter =
        new QMarterialFilter(condDLGDelegator,
            StockQryFieldCode.financeorgbody.code(),
            StockQryFieldCode.srcmarcode.code());
    srcmaterialFilter.addEditorListener();

    // ��Ӧ��
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            StockQryFieldCode.invoicebsup.code());
    supplierFilter.setPk_orgCode(StockQryFieldCode.financeorgbody.code());
    supplierFilter.addEditorListener();

    // �ջ��ֿ�
    QWareHouseFilter warehouseFilter =
        new QWareHouseFilter(condDLGDelegator,
            StockQryFieldCode.financeorgbody.code(),
            StockQryFieldCode.stordoc.code());
    warehouseFilter.addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_stockps_b.pk_srcmaterial.code",
        "pk_stockps_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_stockps_b.pk_srcmaterial.code",
        "pk_stockps_b.cffileid.vskucode").addEditorListener();
  }

  /**
   * @param condDLGDelegator
   */
  protected void setDefaultValue(QueryConditionDLGDelegator condDLGDelegator) {
    // ����Ĭ��ֵ
    String pk_org = condDLGDelegator.getLogincontext().getPk_org();
    condDLGDelegator.setDefaultValue(StockQryFieldCode.financeorgbody.code(),
        pk_org);
    // condDLGDelegator.setDefaultValue("pk_stockps_b", "pk_financeorg",
    // pk_org);

  }
}
