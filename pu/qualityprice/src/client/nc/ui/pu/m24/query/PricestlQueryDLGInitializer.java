package nc.ui.pu.m24.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.m24.entity.PricestlHeaderVO;

/**
 * @since 6.0
 * @version 2011-1-13 上午09:38:59
 * @author 高光荣
 */

public class PricestlQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 按采购组织过滤供应商
    new QSupplierFilter(condDLGDelegator, PricestlHeaderVO.PK_SUPPLIER
        + ".code").addEditorListener();
    new QSupplierFilter(condDLGDelegator, PricestlHeaderVO.PK_SUPPLIER
        + ".name").addEditorListener();

    // 按主组织过滤采购部门
    QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
        PricestlHeaderVO.PK_DEPT).addEditorListener();

    // 按主组织过滤采购员
    QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
        PricestlHeaderVO.PK_EMPLOYEE).addEditorListener();

    // 按主组织过滤物料基本分类
    new QMarbasclassFilter(condDLGDelegator,
        "pk_pricesettle_b.pk_material.pk_marbasclass.code").addEditorListener();

    // 按主组织过滤物料
    new QMarterialFilter(condDLGDelegator, PricestlHeaderVO.PK_ORG,
        "pk_pricesettle_b.pk_material.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PricestlHeaderVO.PK_ORG,
        "pk_pricesettle_b.pk_material.name").addEditorListener();

    new QMarterialFilter(condDLGDelegator, PricestlHeaderVO.PK_ORG,
        "pk_pricesettle_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PricestlHeaderVO.PK_ORG,
        "pk_pricesettle_b.pk_srcmaterial.name").addEditorListener();

  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PricestlHeaderVO.PK_ORG
    });
  }
}
