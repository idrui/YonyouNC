package nc.ui.pu.m21.billref.srm.m4s21;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �������뵥���ն�����ѯ����
 * 
 * @since 6.31
 * @version 2014-3-26 ����03:10:00
 * @author zhangyhh
 */

@SuppressWarnings("restriction")
public class BillReferQueryFor4s21 extends DefaultBillReferQuery {

  public BillReferQueryFor4s21(Container c, TemplateInfo info) {
    super(c, info);
  }

  private void setupFiler(QueryConditionDLGDelegator dlgDelegator) {

    // ���ϻ����������
    new QMarbasclassFilter(dlgDelegator, "pk_org",
        "pk_order_b.pk_material.pk_marbasclass").addEditorListener();

    // ���ϱ���
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_order_b.pk_srcmaterial.code").addEditorListener();

    // ��������
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_order_b.pk_srcmaterial.name").addEditorListener();

    // �������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(dlgDelegator, POBillType.Order.getCode()).filter();

    // ��Ӧ�̣����ղɹ���֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, OrderItemVO.PK_SUPPLIER);
    qSupplierFilter.addEditorListener();

    // ��Ŀ
    new QProjectFilter(dlgDelegator, OrderItemVO.PK_ORG, OrderItemVO.CPROJECTID)
        .addEditorListener();

    // ���ţ����ղɹ���֯�µĲ��ŵ�������
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, OrderHeaderVO.PK_DEPT);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // ��Ա�����ղɹ���֯�µ���Ա��������
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            OrderHeaderVO.CEMPLOYEEID);
    psndocFilter.addEditorListener();

    // ���˿ͻ���������
    QCustomerFilter custFilter =
        new QCustomerFilter(dlgDelegator, OrderItemVO.CASSCUSTID);
    custFilter.setPk_orgCode(OrderItemVO.PK_ORG);
    custFilter.addEditorListener();

  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    // ����֯Ȩ��
    // ת����ѯ������֧֯�ֹ���Ȩ�޵�����֯Ȩ�ޣ���Ҫ���ս�ɫ�������֯����
    dlgDelegator.registerNeedPermissionOrgFieldCode(OrderHeaderVO.PK_ORG);

    // ���� �������ɸ������Դ�����
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // ����ʹ��Ȩ��
    dlgDelegator.setPowerEnable(true);

    // ���ò��չ���
    this.setupFiler(dlgDelegator);

  }
}
