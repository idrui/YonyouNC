package nc.ui.pu.m422x.report.dlg;

import nc.ui.pu.m422x.report.action.StoreReqDetailsQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ������������ִ�в�ѯģ����չ�����
 * 
 * @since 6.32
 * @version 2014-5-26 ����9:56:39
 * @author zhangyhh
 */
public class StoreReqDetailsQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    // ���벿�ţ����տ����֯�µĲ��ŵ������롣
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            PUEntity.M422X_B_TABLE + "." + StoreReqAppItemVO.PK_APPDEPT);
    deptFilter.setPk_orgCode(StoreReqDetailsQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �����ˣ������빺��֯�µ���Ա��������
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            PUEntity.M422X_B_TABLE + "." + StoreReqAppItemVO.PK_APPPSN);
    psnFilter.setPk_orgCode(StoreReqDetailsQryAction.mainOrg);
    psnFilter.addEditorListener();

    QMarterialoidFilter filter =
        new QMarterialoidFilter(condDLGDelegator,
            StoreReqDetailsQryAction.mainOrg,
            StoreReqDetailsQryAction.THIS_PK_SRCMATERIAL_CODE);
    // ���ϱ���(OID)
    filter.addEditorListener();

    // ��������
    new QMarterialoidFilter(condDLGDelegator, StoreReqDetailsQryAction.mainOrg,
        StoreReqDetailsQryAction.THIS_PK_SRCMATERIAL_NAME).addEditorListener();

    // ������֯�������ϻ�������
    new QMarbasclassFilter(condDLGDelegator, StoreReqDetailsQryAction.mainOrg,
        "bd_marbasclass.code").addEditorListener();
    // ����ֿ�
    QWareHouseFilter whfilter =
        new QWareHouseFilter(condDLGDelegator,
            StoreReqDetailsQryAction.mainOrg, "po_storereq_b.pk_reqstordoc");
    // ���������������˲ֿ�
    whfilter.filter();
    whfilter.addEditorListener();
    // ��Ŀ
    new QProjectFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_storereq.pk_project").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // ����������������
    new QTransTypeFilter(condDLGDelegator, POBillType.MRBill.getCode())
        .filter();

    // // �Զ�������
    // condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
    // .getQueryParams(new PraybillHeaderVO(), new PraybillItemVO())));

  }
}
