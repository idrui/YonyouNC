package nc.ui.pu.m21.report.initializer;

import nc.scmmm.ui.scmpub.report.action.SCMRptQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.report.enumeration.OrderSummaryQryFieldCode;
import nc.vo.scmpub.res.billtype.POBillType;

public class OrderSummaryRptRefInitializer extends
    SCMRptQueryConditionDLGInitializer {

  @Override
  public String getMainOrgField() {
    return OrderSummaryQryFieldCode.pk_org.getFieldCode();
  }

  @Override
  protected void initRef(QueryConditionDLGDelegator delegator) {
    // �������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(delegator, POBillType.Order.getCode()).filter();

    // �ɹ�Ա
    QPsndocFilter.createQPsndocFilterOfPU(delegator,
        OrderSummaryQryFieldCode.cemployeeid.getFieldCode())
        .addEditorListener();

    // �ɹ�����
    QDeptFilter.createQDeptFilterOfPU(delegator,
        OrderSummaryQryFieldCode.pk_dept.getFieldCode()).addEditorListener();

    // ��Ӧ�̱���
    new QSupplierFilter(delegator,
        OrderSummaryQryFieldCode.suppliercode.getFieldCode())
        .addEditorListener();

    // ��Ӧ������
    new QSupplierFilter(delegator,
        OrderSummaryQryFieldCode.suppliername.getFieldCode())
        .addEditorListener();

    // ���ϻ�������
    new QMarbasclassFilter(delegator,
        OrderSummaryQryFieldCode.materialclasscode.getFieldCode())
        .addEditorListener();

    // ���ϲɹ��������
    new QMarPuClassFilter(delegator,
        OrderSummaryQryFieldCode.marpuclasscode.getFieldCode())
        .addEditorListener();

    // ���ϱ���
    new QMarterialoidFilter(delegator,
        OrderSummaryQryFieldCode.pk_org.getFieldCode(),
        OrderSummaryQryFieldCode.materialcode.getFieldCode())
        .addEditorListener();

    // ��������
    new QMarterialoidFilter(delegator,
        OrderSummaryQryFieldCode.pk_org.getFieldCode(),
        OrderSummaryQryFieldCode.materialname.getFieldCode())
        .addEditorListener();

    // ��Ŀ
    new QProjectFilter(delegator,
        OrderSummaryQryFieldCode.pk_org.getFieldCode(),
        OrderSummaryQryFieldCode.cprojectid.getFieldCode()).addEditorListener();

    // �ͻ�
    new QCustomerFilter(delegator,
        OrderSummaryQryFieldCode.casscustid.getFieldCode()).addEditorListener();
  }

}
