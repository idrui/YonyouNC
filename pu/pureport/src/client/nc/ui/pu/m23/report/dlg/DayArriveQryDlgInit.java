package nc.ui.pu.m23.report.dlg;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.report.queryinfo.arrival.PuArrivalQryInfoPara;

/**
 * �ɹ�����-�յ��������ѯģ����չ�����
 * 
 * @since 6.3
 * @version 2012-8-11 ����10:36:09
 * @author fanly3
 */
public class DayArriveQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  /** �ͻ� */
  public static final String CASSCUSTID = "po_order_b.casscustid";

  /** ��Ŀ */
  public static final String CPROJECTID = "po_order_b.cprojectid";

  /** �����֯ */
  public static final String MAIN_ORG = "pk_stockorg";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ϻ�������
    new QMarbasclassFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        PuArrivalQryInfoPara.BD_MARBASCLASS_CODE).addEditorListener();
    // ���ϱ���
    new QMarterialFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        PuArrivalQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // ��������
    new QMarterialFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        PuArrivalQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // ��Ŀ
    new QProjectFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        DayArriveQryDlgInit.CPROJECTID).addEditorListener();

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // �Զ�������
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new ArriveHeaderVO(), new ArriveItemVO())));
    // ������֯���˿ͻ�
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, DayArriveQryDlgInit.CASSCUSTID);
    customerFilter.setPk_orgCode(DayArriveQryDlgInit.MAIN_ORG);
    customerFilter.addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilter(condDLGDelegator, "po_arriveorder_b.pk_srcmaterial",
        "po_arriveorder_b.cffileid").addEditorListener();
    new QFfileFilter(condDLGDelegator, "po_arriveorder_b.pk_srcmaterial",
        "po_arriveorder_b.cffileid.vskucode").addEditorListener();
  }
}
