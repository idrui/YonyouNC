package nc.ui.pu.m21.view;

import nc.ui.pu.m21.action.status.StatusQueryFieldValueElementEditorCreator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * @since 6.0
 * @version 2011-1-13 下午12:57:04
 * @author wuxla
 */

public class OutputDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderHeaderVO.PK_ORG
    });
    // 不支持数据权限，需求wangy确认过
    condDLGDelegator.setPowerEnable(false);

    this.filterRef(condDLGDelegator);

    this.setFieldValueElementEditor(condDLGDelegator);

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 按采购组织过滤采购部门
    QDeptFilter.createQDeptFilterOfPU(condDLGDelegator, OrderHeaderVO.PK_DEPT)
        .addEditorListener();

    // 根据采购组织过来供应商
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator, "pk_supplier");
    supplierFilter.setPk_orgCode("pk_org");
    supplierFilter.addEditorListener();
  }

  private void setFieldValueElementEditor(
      QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.setFieldValueElementEditor("",
        OnwayStatusQryEnum.output.code(),
        new StatusQueryFieldValueElementEditorCreator());
  }

}
