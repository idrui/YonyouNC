/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午07:25:25
 */
package nc.ui.pu.m21.billref.pu.m30;

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
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSaleOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单参照协同采购订单的查询服务类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午07:25:25
 */
public class OrderReferQueryFor30 extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public OrderReferQueryFor30(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.SALEORGTYPE)) {
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
      OrderQueryDLGInitializer.PK_SALEORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 销售组织
    QSaleOrgFilter saleOrgFilter =
        new QSaleOrgFilter(dlgDelegator, "pk_saleorg");
    saleOrgFilter.setIsCoop(UFBoolean.TRUE);
    saleOrgFilter.setVbilltype_src(POBillType.Order.getCode());
    saleOrgFilter.filter();

    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
        OrderHeaderVO.CEMPLOYEEID).addEditorListener();

    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, OrderHeaderVO.PK_DEPT);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 订单交易类型，参照采购订单交易类型录入
    new QTransTypeFilter(dlgDelegator, POBillType.Order.getCode()).filter();

    // 物料基本分类编码
    new QMarbasclassFilter(dlgDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.MARBASCLASS_CODE).addEditorListener();

    // 物料编码
    new QMarterialFilter(dlgDelegator, OrderHeaderVO.PK_ORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_CODE)
        .addEditorListener();

    // 物料编码
    new QMarterialFilter(dlgDelegator, OrderHeaderVO.PK_ORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_CODE)
        .addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, OrderItemVO.PK_ORDER_B + PUQueryConst.DOT
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue("pk_saleorg", this.getDefaultPk_OrgValue());
    dlgDelegator.setDefaultValue(OrderHeaderVO.PK_ORG, null);
    dlgDelegator.setPowerEnable(true);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

}
