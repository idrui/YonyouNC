package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.m21.report.action.OrderPayExecQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ɹ���������ִ�в�ѯģ����չ�����
 * 
 * @since 6.0
 * @version 2011-9-6 ����9:56:39
 * @author zhaoyha
 */
public class OrderPayExecQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {
  public static final String CEMPLOYEEID = "po_order.cemployeeid";

  public static final String PK_DEPT = "po_order.pk_dept";

  public static final String PO_ORDER_CTRANTYPEID = "po_order.ctrantypeid";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // �������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(condDLGDelegator,
        OrderPayExecQryDlgInit.PO_ORDER_CTRANTYPEID, POBillType.Order.getCode())
        .filter();
    // �ɹ����ţ����ղɹ���֯�µĲ��ŵ�������
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderPayExecQryDlgInit.PK_DEPT);
    deptFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �ɹ�Ա�����ղɹ���֯�µ���Ա��������
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            OrderPayExecQryDlgInit.CEMPLOYEEID);
    psnFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    psnFilter.addEditorListener();
    // ��Ӧ�̱���
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_CODE);
    supFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    supFilter.addEditorListener();
    // ���ϻ����������
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    marbasFilter.addEditorListener();
    // ���ϱ���(OID)
    new QMarterialoidFilter(condDLGDelegator, OrderPayExecQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // ��������
    new QMarterialoidFilter(condDLGDelegator, OrderPayExecQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // ���ϲɹ��������
    QMarPuClassFilter marpufilter =
        new QMarPuClassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARPUCLASS_CODE);
    marpufilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    marpufilter.addEditorListener();
    // ��Ӧ������
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_NAME);
    supFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    supFilter.addEditorListener();
    // ��Ӧ�̵������ࣨӦ���ǻ��������б仯������Ҫ���ˣ�
    // ��Ŀ
    new QProjectFilter(condDLGDelegator, OrderPayExecQryAction.mainOrg,
        "po_order_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // �Զ�������
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new OrderHeaderVO(), new OrderItemVO())));
    // ������֯���˿ͻ�
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M21_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    customerFilter.addEditorListener();

  }
}
