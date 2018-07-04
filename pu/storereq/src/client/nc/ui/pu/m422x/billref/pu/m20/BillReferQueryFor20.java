package nc.ui.pu.m422x.billref.pu.m20;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.querytemplate.TemplateInfo;

public class BillReferQueryFor20 extends DefaultBillReferQuery {

  public BillReferQueryFor20(Container c, TemplateInfo info) {
    super(c, info);
  }

  private void setupFiler(QueryConditionDLGDelegator dlgDelegator) {
    // ���ϻ����������
    new QMarbasclassFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_material.pk_marbasclass").addEditorListener();

    // ���ϱ���
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.code").addEditorListener();

    // ��������
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.name").addEditorListener();

    // ����ֿ�
    new QWareHouseFilter(dlgDelegator, "pk_org", "pk_storereq_b.pk_reqstordoc")
        .addEditorListener();

    // ���벿�ţ������ѯһ�������֯�����ݣ���ֻ�ɲ��ոÿ����֯�ɼ��Ĳ��ŵ�����������ռ��ŷ�Χ�Ĳ��ŵ���¼�롣
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(dlgDelegator,
            "pk_storereq_b.pk_appdept");
    deptFilter.setPk_orgCode("pk_org");
    deptFilter.addEditorListener();

    // �����ˣ������ѯһ�������֯�����ݣ���ֻ�ɲ��ոÿ����֯�ɼ�����Ա������������ռ��ŷ�Χ����Ա����¼�롣
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(dlgDelegator,
            "pk_storereq_b.pk_apppsn");
    psndocFilter.setPk_orgCode("pk_org");
    psndocFilter.addEditorListener();

    // ��Ŀ
    new QProjectFilter(dlgDelegator, "pk_org", "pk_storereq_b.cprojectid")
        .addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      StoreReqAppHeaderVO.PK_ORG
    });

    // ����ʹ��Ȩ��
    dlgDelegator.setPowerEnable(true);

    // ���ò��չ���
    this.setupFiler(dlgDelegator);

    //
    // dlgDelegator.setDefaultValue("pk_org", this.getDefaultPk_OrgValue());
    // dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
