package nc.ui.pu.m21.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.scmpub.query.refregion.QueryDlgInitialUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-1-13 ����11:06:25
 * @author wuxla
 */

public class OrderReplenishQueryInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderHeaderVO.PK_ORG
    });
    this.filterRef(condDLGDelegator);

    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.PK_ORG, "pk_order_b."
        + OrderItemVO.PK_ORG);
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.DBILLDATE, "pk_order_b."
        + OrderItemVO.DBILLDATE);
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.PK_SUPPLIER, "pk_order_b."
        + OrderItemVO.PK_SUPPLIER);

    condDLGDelegator.setPowerEnable(true);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());

    // ��������ֻ�й��ڲɹ��ͽ��ڲɹ�
    QueryDlgInitialUtils.filterBuySellFlagForBuy(condDLGDelegator,
        "pk_order_b.fbuysellflag");
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // �ɹ����ţ����ղɹ���֯�µĲ��ŵ�������
    QDeptFilter.createQDeptFilterOfPU(condDLGDelegator, "pk_dept")
        .addEditorListener();

    // �ɹ�Ա�����ղɹ���֯�µ���Ա��������
    QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator, "cemployeeid")
        .addEditorListener();
    
    // �������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(condDLGDelegator, POBillType.Order.getCode()).filter();

    // ��Ӧ�̱��룬������ѡ�����֯�ڿɼ��Ĺ�Ӧ��
    new QSupplierFilter(condDLGDelegator, "pk_supplier.code")
        .addEditorListener();

    // ����ֿ�
    new QWareHouseFilter(condDLGDelegator, "pk_order_b.pk_reqstoorg",
        "pk_order_b.pk_reqstordoc").addEditorListener();

    // �ջ��ֿ�
    new QWareHouseFilter(condDLGDelegator, "pk_order_b.pk_arrvstoorg",
        "pk_order_b.pk_recvstordoc").addEditorListener();
    
    // ������֯���˿ͻ�
    new QCustomerFilter(condDLGDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name())
        .addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_order_b.pk_srcmaterial.code",
        "pk_order_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_order_b.pk_srcmaterial.code",
        "pk_order_b.cffileid.vskucode").addEditorListener();
  }

}
