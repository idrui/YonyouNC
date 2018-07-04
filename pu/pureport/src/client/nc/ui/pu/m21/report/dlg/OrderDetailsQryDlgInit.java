package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.m21.report.action.OrderDetailsQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ɹ�������ϸ��ѯģ����չ�����
 * 
 * @since 6.0
 * @version 2011-9-6 ����9:56:39
 * @author zhaoyha
 */
public class OrderDetailsQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // �������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(condDLGDelegator, POBillType.Order.getCode()).filter();
    // �ɹ����ţ����ղɹ���֯�µĲ��ŵ�������
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderHeaderVO.PK_DEPT);
    deptFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �ɹ�Ա�����ղɹ���֯�µ���Ա��������
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            OrderHeaderVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    psnFilter.addEditorListener();
    // ��Ӧ�̱���
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_SUPPLIER_CODE);
    supFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    supFilter.addEditorListener();
    // ���ϻ����������
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_MATERIAL_PK_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    marbasFilter.addEditorListener();
    // ���ϱ���(OID)
    new QMarterialoidFilter(condDLGDelegator, OrderDetailsQryAction.mainOrg,
        OrderDetailsQryAction.THIS_PK_SRCMATERIAL_CODE).addEditorListener();
    // ��������
    new QMarterialoidFilter(condDLGDelegator, OrderDetailsQryAction.mainOrg,
        OrderDetailsQryAction.THIS_PK_SRCMATERIAL_NAME).addEditorListener();
    // ���ϲɹ��������
    QMarPuClassFilter marpufilter =
        new QMarPuClassFilter(
            condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_MATERIAL_MATERIALSTOCK_PK_MARPUCLASS_CODE);
    marpufilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    marpufilter.addEditorListener();
    // ��Ӧ������
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_SUPPLIER_NAME);
    supFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    supFilter.addEditorListener();
    // ��Ӧ�̵������ࣨӦ���ǻ��������б仯������Ҫ���ˣ�
    // ��Ŀ
    new QProjectFilter(condDLGDelegator, OrderDetailsQryAction.mainOrg,
        OrderItemVO.CPROJECTID).addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // �Զ�������
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new OrderHeaderVO(), new OrderItemVO())));
    // ������֯���˿ͻ�
    new QCustomerFilter(condDLGDelegator, PuAttrNameEnum.casscustid.name())
        .addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilter(condDLGDelegator, OrderDetailsQryAction.THIS_PK_SRCMATERIAL_CODE,
        OrderItemVO.CFFILEID).addEditorListener();
    new QFfileFilter(condDLGDelegator, OrderDetailsQryAction.THIS_PK_SRCMATERIAL_CODE,
        "this.cffileid.vskucode").addEditorListener();
  }
}
