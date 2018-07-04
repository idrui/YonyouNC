package nc.ui.pu.m27.settlebill.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;

/**
 * 结算单维护查询对话框的初始化器
 * 
 * @since 6.0
 * @version 2011-1-13 上午09:46:32
 * @author zhaoyha
 */

public class SettleBillQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  // 物料基本分类，跟查询模板保持一致
  private String mar_basclass =
      "po_settlebill_b.pk_material.pk_marbasclass.name";

  // 物料编码,跟查询模板保持一致
  private String material_code = "po_settlebill_b.pk_material.code";

  // 采购部门，跟查询模板保持一致
  private String pur_dept = "po_settlebill_b.pk_dept";

  // 采购员，跟查询模板保持一致
  private String pur_psn = "po_settlebill_b.pk_psndoc";

  private String srcmaterial_code = "po_settlebill_b.pk_srcmaterial.code";

  // 供应商，跟查询模板保持一致
  private String supplier = "po_settlebill_b.pk_supplier.name";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SettleBillHeaderVO.PK_ORG
    });
    // 不支持数据权限
    condDLGDelegator.setPowerEnable(false);
    this.initRef(condDLGDelegator);// 参照过滤
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initRef(QueryConditionDLGDelegator condDLGDelegator) {
    new QMarterialFilter(condDLGDelegator, SettleBillHeaderVO.PK_ORG,
        this.material_code).addEditorListener();
    new QMarterialFilter(condDLGDelegator, SettleBillHeaderVO.PK_ORG,
        this.srcmaterial_code).addEditorListener();
    // 按主组织过滤物料基本分类
    new QMarbasclassFilter(condDLGDelegator, this.mar_basclass)
        .addEditorListener();
    // 按主组织过滤采购员
    QPsndocFilter filter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator, this.pur_psn);
    filter.setPk_orgCode(null);
    filter.addEditorListener();
    // 按主组织过滤采购部门
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator, this.pur_dept);
    deptFilter.setPk_orgCode(null);
    deptFilter.addEditorListener();
    // 按主组织过滤供应商
    new QSupplierFilter(condDLGDelegator, this.supplier).addEditorListener();
  }

}
