/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 ����03:57:40
 */
package nc.ui.pu.m21.billref.ic.m45;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������ṩ���ɹ���ⵥ�Ĳ�ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-29 ����03:57:40
 */
public class OrderReferQueryFor45 extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public OrderReferQueryFor45(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
        return pk_org;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderQueryDLGInitializer.PK_ORDER_B_PK_ARRVSTOORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // �ɹ����ţ������ѯһ���ɹ���֯�����ݣ���ɲ��ո���֯�����Ĺ�˾�µĲ��ŵ���������ɲ��ռ��ŷ�Χ�Ĳ��ŵ�����
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, OrderHeaderVO.PK_DEPT);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    QDeptFilter reqdeptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_REQDEPT);
    reqdeptFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_REQSTOORG);
    reqdeptFilter.setbUsedflag(UFBoolean.TRUE);
    reqdeptFilter.addEditorListener();

    // �ɹ�Ա�������ѯһ���ɹ���֯�����ݣ���ɲ��ո���֯�����Ĺ�˾�µ���Ա����������ɲ��ռ��ŷ�Χ����Ա������
    QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
        OrderHeaderVO.CEMPLOYEEID).addEditorListener();

    // �����������ͣ����ղɹ�������������¼��
    new QTransTypeFilter(dlgDelegator, POBillType.Order.getCode()).filter();

    // ��Ӧ�̱��룬���տ����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, PUQueryConst.SUPPLIER_CODE);
    qSupplierFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG);
    qSupplierFilter.addEditorListener();

    // ��Ӧ�����ƣ����տ����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator, PUQueryConst.SUPPLIER_NAME);
    qSupplierNameFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG);
    qSupplierNameFilter.addEditorListener();

    // ���ϻ���������룬���տ����֯�ɼ������ϻ������൵��¼��
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.MARBASCLASS_CODE);
    qMarbasclassFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG);
    qMarbasclassFilter.addEditorListener();

    // ���ϲɹ�������룬���տ����֯�ɼ������ϲɹ����൵��¼��
    QMarPuClassFilter qMarPuClassFilter =
        new QMarPuClassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.MARPUCLASS_CODE);
    qMarPuClassFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG);
    qMarPuClassFilter.addEditorListener();

    // ���ϱ��룬���տ����֯�ɼ������ϵ���¼��
    QMarterialFilter materialCodeFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_CODE);
    materialCodeFilter.setbDiscount(UFBoolean.FALSE);
    materialCodeFilter.setbFee(UFBoolean.FALSE);
    materialCodeFilter.addEditorListener();

    // ��������
    QMarterialFilter materialNameFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_NAME);
    materialNameFilter.setbDiscount(UFBoolean.FALSE);
    materialNameFilter.setbFee(UFBoolean.FALSE);
    materialNameFilter.addEditorListener();

    QMarterialFilter srcmaterialCodeFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_CODE);
    srcmaterialCodeFilter.setbDiscount(UFBoolean.FALSE);
    srcmaterialCodeFilter.setbFee(UFBoolean.FALSE);
    srcmaterialCodeFilter.addEditorListener();

    // ��������
    QMarterialFilter srcmaterialNameFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_NAME);
    srcmaterialNameFilter.setbDiscount(UFBoolean.FALSE);
    srcmaterialNameFilter.setbFee(UFBoolean.FALSE);
    srcmaterialNameFilter.addEditorListener();

    // ��Ʊ��Ӧ��
    QSupplierFilter invsupplier =
        new QSupplierFilter(dlgDelegator, OrderHeaderVO.PK_INVCSUPLLIER);
    invsupplier.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG);
    invsupplier.addEditorListener();

    // �ջ��ͻ�
    QCustomerFilter recvcustomer =
        new QCustomerFilter(dlgDelegator, OrderHeaderVO.PK_RECVCUSTOMER);
    recvcustomer.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG);
    recvcustomer.addEditorListener();

    // ����Э��
    new QPaytermFilter(dlgDelegator, OrderHeaderVO.PK_PAYTERM)
        .addEditorListener();

    // ����ֿ�
    new QWareHouseFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_REQSTOORG, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.PK_REQSTORDOC).addEditorListener();

    // �ջ��ֿ�
    QWareHouseFilter wareHouseFilter =
        new QWareHouseFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
                + OrderItemVO.PK_RECVSTORDOC);
    wareHouseFilter.setbGubflag(UFBoolean.FALSE);
    wareHouseFilter.addEditorListener();

    // ��Ŀ
    new QProjectFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.CPROJECTID).addEditorListener();
    // ������֯���˿ͻ�
    new QCustomerFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);

    dlgDelegator.addRedundancyInfo(OrderHeaderVO.PK_ORG, "pk_order_b."
        + OrderItemVO.PK_ORG);
    dlgDelegator.addRedundancyInfo(OrderHeaderVO.DBILLDATE, "pk_order_b."
        + OrderItemVO.DBILLDATE);
    dlgDelegator.addRedundancyInfo(OrderHeaderVO.PK_SUPPLIER, "pk_order_b."
        + OrderItemVO.PK_SUPPLIER);

    dlgDelegator.setDefaultValue(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_ARRVSTOORG, this.getDefaultPk_OrgValue());
    dlgDelegator.setPowerEnable(true);

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
