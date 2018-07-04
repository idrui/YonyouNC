package nc.ui.pu.report.estdiffer;

import nc.ui.pu.report.pub.query.refregion.QOrgAccountPeriod;
import nc.ui.pu.report.settlestat.SettleStatQryDlgInitializer;
import nc.ui.pu.report.settlestat.SettleStatSubscribeAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.report.enumeration.EstMonthStatQryFieldCode;
import nc.vo.pub.lang.UFBoolean;

public class EstDifferQryDlgInitializer extends SettleStatQryDlgInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯Ȩ��,����֯�ǲ�����֯
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "pk_financeorg"
    });
    // ���չ���
    this.refFilter(condDLGDelegator);
  }

  @Override
  protected void refFilter(QueryConditionDLGDelegator dlgDelegator) {
    // ��Ӧ�̱��룬���ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, SettleStatSubscribeAction.PK_SUPPLIER);
    qSupplierFilter.setPk_orgCode(SettleStatSubscribeAction.mainOrg);
    qSupplierFilter.addEditorListener();
    // ����
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            SettleStatSubscribeAction.PK_DEPT);
    deptFilter.setPk_orgCode(SettleStatSubscribeAction.PK_PURCHASEORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �ɹ�Ա
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            SettleStatSubscribeAction.PK_PSNDOC);
    psndoc.setPk_orgCode(SettleStatSubscribeAction.PK_PURCHASEORG);
    psndoc.addEditorListener();
    // ���ϻ����������
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(dlgDelegator,
            SettleStatSubscribeAction.PK_MARBASCLASS);
    marbasFilter.setPk_orgCode(SettleStatSubscribeAction.mainOrg);
    marbasFilter.addEditorListener();
    // ���ϱ���(OID)
    new QMarterialoidFilter(dlgDelegator, SettleStatSubscribeAction.mainOrg,
        SettleStatSubscribeAction.PK_SRCMATERIAL).addEditorListener();
    // ��������
    new QMarterialoidFilter(dlgDelegator, SettleStatSubscribeAction.mainOrg,
        SettleStatSubscribeAction.MATERIALNAME).addEditorListener();
    // ����ڼ�ȡ������֯��ҵ��Ԫ���Ļ���ڼ�
    new QOrgAccountPeriod(dlgDelegator, SettleStatSubscribeAction.mainOrg,
        EstMonthStatQryFieldCode.fperiod.code()).addEditorListener();
  }
}
