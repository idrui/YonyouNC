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
 * @version 2011-1-13 上午09:45:29
 * @author wuxla
 */

public class InitialEstDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.initRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());

    // 购销类型只有国内采购和进口采购
    QueryDlgInitialUtils.filterBuySellFlagForBuy(condDLGDelegator,
        "po_initialest_b.fbuysellflag");
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      InitialEstHeaderVO.PK_ORG
    });
    // 不支持数据权限
    condDLGDelegator.setPowerEnable(false);
  }

  private void initRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 物料基本分类编码
    new QMarbasclassFilter(condDLGDelegator,
        "po_initialest_b.pk_material.pk_marbasclass").addEditorListener();

    // 物料编码
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "po_initialest_b.pk_material.code").addEditorListener();
    // 物料编码
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "po_initialest_b.pk_srcmaterial.code").addEditorListener();

    // 供应商
    new QSupplierFilter(condDLGDelegator, "pk_supplier").addEditorListener();

    // 仓库
    new QWareHouseFilter(condDLGDelegator, "pk_stockorg", "pk_stordoc")
        .addEditorListener();
    
    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, "po_initialest_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "po_initialest_b.pk_srcmaterial.code",
        "po_initialest_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "po_initialest_b.pk_srcmaterial.code",
        "po_initialest_b.cffileid.vskucode").addEditorListener();
  }
}
