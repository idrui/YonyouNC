package nc.ui.pu.m21.billref.dm.m4804;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

/**
 * @since 6.0
 * @version 2010-12-13 下午02:19:49
 * @author wuxla
 */

public class OrderReferQueryFor4804 extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public OrderReferQueryFor4804(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.TRAFFICORGTYPE)) {
        return pk_org;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderQueryDLGInitializer.PK_ORDER_B_PK_FLOWSTOCKORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 物料编码，参照物流组织可见的物料档案录入
    QMarterialFilter materialCodeFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_FLOWSTOCKORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_CODE);
    materialCodeFilter.setbDiscount(UFBoolean.FALSE);
    materialCodeFilter.setbFee(UFBoolean.FALSE);
    materialCodeFilter.addEditorListener();
    QMarterialFilter srcmaterialCodeFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_FLOWSTOCKORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_CODE);
    srcmaterialCodeFilter.setbDiscount(UFBoolean.FALSE);
    srcmaterialCodeFilter.setbFee(UFBoolean.FALSE);
    srcmaterialCodeFilter.addEditorListener();

    // 物料基本分类编码，参照物流组织可见的物料基本分类档案录入
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.MARBASCLASS_CODE);
    qMarbasclassFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_FLOWSTOCKORG);
    qMarbasclassFilter.addEditorListener();

    // 供应商
    QSupplierFilter invsupplier =
        new QSupplierFilter(dlgDelegator, OrderHeaderVO.PK_SUPPLIER);
    invsupplier.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_FLOWSTOCKORG);
    invsupplier.addEditorListener();

    // 收货仓库
    QWareHouseFilter wareHouseFilter =
        new QWareHouseFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_ARRVSTOORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
                + OrderItemVO.PK_RECVSTORDOC);
    wareHouseFilter.setbGubflag(UFBoolean.FALSE);
    wareHouseFilter.addEditorListener();

    // 项目
    new QProjectFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_FLOWSTOCKORG, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + OrderItemVO.CPROJECTID).addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_FLOWSTOCKORG, this.getDefaultPk_OrgValue());
    dlgDelegator.setPowerEnable(true);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
