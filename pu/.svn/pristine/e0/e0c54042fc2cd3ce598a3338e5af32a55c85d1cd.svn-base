/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 下午04:37:53
 */
package nc.ui.pu.m21.billref.pu.m4t;

import java.awt.Container;

import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单提供给期初暂估单的查询服务类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 下午04:37:53
 */
public class OrderReferQueryFor4t extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public OrderReferQueryFor4t(Container c, TemplateInfo info) {
    super(c, info);
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderQueryDLGInitializer.PK_ORDER_B_PK_PSFINANCEORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(dlgDelegator, POBillType.Order.getCode()).filter();

    // 物料编码，参照库存组织可见的物料档案录入
    QMarterialFilter materialCodeFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_PSFINANCEORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_CODE);
    materialCodeFilter.setbDiscount(UFBoolean.FALSE);
    materialCodeFilter.setbFee(UFBoolean.FALSE);
    materialCodeFilter.addEditorListener();

    QMarterialFilter srcmaterialCodeFilter =
        new QMarterialFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + PUQueryConst.DOT + OrderItemVO.PK_PSFINANCEORG,
            OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_CODE);
    srcmaterialCodeFilter.setbDiscount(UFBoolean.FALSE);
    srcmaterialCodeFilter.setbFee(UFBoolean.FALSE);
    srcmaterialCodeFilter.addEditorListener();

    // 物料基本分类编码，参照库存组织可见的物料基本分类档案录入
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
            + ".pk_material.pk_marbasclass");
    qMarbasclassFilter.setPk_orgCode(OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + OrderItemVO.PK_PSFINANCEORG);
    qMarbasclassFilter.addEditorListener();

    // 供应商
    QSupplierFilter invsupplier =
        new QSupplierFilter(dlgDelegator, "pk_supplier");
    invsupplier.setPk_orgCode("pk_order_b.pk_psfinanceorg");
    invsupplier.addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setPowerEnable(true);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
