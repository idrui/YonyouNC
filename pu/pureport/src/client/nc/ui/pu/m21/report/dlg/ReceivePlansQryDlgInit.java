package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.m21.report.action.ReceivePlansQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �����ƻ���ѯģ����չ�����
 * 
 * @since 6.0
 * @version 2011-9-6 ����9:56:39
 * @author zhaoyha
 */
public class ReceivePlansQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {
  public static final String CEMPLOYEEID = "po_order.cemployeeid";

  public static final String PK_ARRVSTOORG = "po_order_bb1.pk_arrvstoorg";

  public static final String PK_DEPT = "po_order.pk_dept";

  public static final String PK_RECVSTORDOC = "po_order_bb1.pk_recvstordoc";

  public static final String PO_ORDER_CTRANTYPEID = "po_order.ctrantypeid";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // �������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(condDLGDelegator, "po_order.ctrantypeid",
        POBillType.Order.getCode()).filter();
    // �ɹ����ţ����ղɹ���֯�µĲ��ŵ�������
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            ReceivePlansQryDlgInit.PK_DEPT);
    deptFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �ɹ�Ա�����ղɹ���֯�µ���Ա��������
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            ReceivePlansQryDlgInit.CEMPLOYEEID);
    psnFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    psnFilter.addEditorListener();
    // ��Ӧ�̱���
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_CODE);
    supFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    supFilter.addEditorListener();
    // ���ϻ����������
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    marbasFilter.addEditorListener();
    // ���ϱ���(OID)
    new QMarterialoidFilter(condDLGDelegator, ReceivePlansQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // ��������
    new QMarterialoidFilter(condDLGDelegator, ReceivePlansQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // ��Ӧ������
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_NAME);
    supFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    supFilter.addEditorListener();
    // �ջ��ֿ�
    QWareHouseFilter whfilter =
        new QWareHouseFilter(condDLGDelegator,
            ReceivePlansQryDlgInit.PK_ARRVSTOORG,
            ReceivePlansQryDlgInit.PK_RECVSTORDOC);
    // ���������������˲ֿ�
    whfilter.filter();
    whfilter.addEditorListener();
    // ��Ӧ�̵������ࣨӦ���ǻ��������б仯������Ҫ���ˣ�
    // ��Ŀ
    new QProjectFilter(condDLGDelegator, ReceivePlansQryAction.mainOrg,
        "po_order_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // �Զ�������
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new ArriveHeaderVO(), new ArriveItemVO())));
    // ������֯���˿ͻ�
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M21_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    customerFilter.addEditorListener();
  }
}
