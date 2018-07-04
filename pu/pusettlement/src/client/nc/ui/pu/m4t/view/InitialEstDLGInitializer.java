package nc.ui.pu.m4t.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.scmpub.query.refregion.QueryDlgInitialUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * @since 6.0
 * @version 2011-1-13 ����09:45:29
 * @author wuxla
 */

public class InitialEstDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.initRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());

    // ��������ֻ�й��ڲɹ��ͽ��ڲɹ�
    QueryDlgInitialUtils.filterBuySellFlagForBuy(condDLGDelegator,
        "po_initialest_b.fbuysellflag");
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      InitialEstHeaderVO.PK_ORG
    });
    // ��֧������Ȩ��
    condDLGDelegator.setPowerEnable(false);
  }

  private void initRef(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ϻ����������
    new QMarbasclassFilter(condDLGDelegator,
        "po_initialest_b.pk_material.pk_marbasclass").addEditorListener();

    // ���ϱ���
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "po_initialest_b.pk_material.code").addEditorListener();
    // ���ϱ���
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "po_initialest_b.pk_srcmaterial.code").addEditorListener();

    // ��Ӧ��
    new QSupplierFilter(condDLGDelegator, "pk_supplier").addEditorListener();

    // �ֿ�
    new QWareHouseFilter(condDLGDelegator, "pk_stockorg", "pk_stordoc")
        .addEditorListener();
    
    // ������֯���˿ͻ�
    new QCustomerFilter(condDLGDelegator, "po_initialest_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilterByMaterCode(condDLGDelegator, "po_initialest_b.pk_srcmaterial.code",
        "po_initialest_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "po_initialest_b.pk_srcmaterial.code",
        "po_initialest_b.cffileid.vskucode").addEditorListener();
  }
}
