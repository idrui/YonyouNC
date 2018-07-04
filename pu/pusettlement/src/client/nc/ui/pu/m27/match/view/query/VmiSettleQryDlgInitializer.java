package nc.ui.pu.m27.match.view.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m4202.enumeration.VmiQryFieldCode;

/**
 * �����ѯ���Ļ��ܵĶԻ��򹫹���ʼ����
 * 
 * @since 6.0
 * @version 2011-3-24 ����01:46:39
 * @author �����
 */

public class VmiSettleQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initFilters(condDLGDelegator);// ���չ���
    this.setDefaultValue(condDLGDelegator);// Ĭ��ֵ����
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  /**
   * ���չ���
   * 
   * @param condDLGDelegator
   */
  private void initFilters(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ϻ�������
    QMarbasclassFilter classFilter =
        new QMarbasclassFilter(condDLGDelegator,
            VmiQryFieldCode.marbascls.code());
    classFilter.setPk_orgCode(VmiQryFieldCode.financeorg.code());
    classFilter.addEditorListener();

    // ���ϱ���
    QMarterialFilter materialFilter =
        new QMarterialFilter(condDLGDelegator,
            VmiQryFieldCode.financeorg.code(), VmiQryFieldCode.marcode.code());
    materialFilter.addEditorListener();

    // ���ϱ���
    QMarterialFilter srcmaterialFilter =
        new QMarterialFilter(condDLGDelegator,
            VmiQryFieldCode.financeorg.code(),
            VmiQryFieldCode.srcmarcode.code());
    srcmaterialFilter.addEditorListener();

    // ��Ӧ��
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator, VmiQryFieldCode.supplier.code());
    supplierFilter.setPk_orgCode(VmiQryFieldCode.financeorg.code());
    supplierFilter.addEditorListener();

    // �ֿ����
    new QWareHouseFilter(condDLGDelegator, VmiQryFieldCode.storeorg.code(),
        VmiQryFieldCode.stordoc.code()).addEditorListener();
  }

  /**
   * Ĭ��ֵ����
   * 
   * @param condDLGDelegator
   */
  private void setDefaultValue(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.setDefaultValue(null, VmiQryFieldCode.financeorg.code(),
        condDLGDelegator.getLogincontext().getPk_org());
  }

}
