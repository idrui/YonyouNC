package nc.ui.pu.m21.view;

import nc.ui.pu.m21.action.status.StatusQueryFieldValueElementEditorCreator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * @since 6.0
 * @version 2011-1-13 下午02:11:21
 * @author wuxla
 */

public class ConfirmDLGInitializer implements IQueryConditionDLGInitializer {

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
    // 根据采购组织过来供应商编号、供应商名称
    QSupplierFilter supplierCodeFilter =
        new QSupplierFilter(condDLGDelegator, "pk_supplier.code");
    supplierCodeFilter.setPk_orgCode("pk_org");
    supplierCodeFilter.addEditorListener();

    QSupplierFilter supplierNameFilter =
        new QSupplierFilter(condDLGDelegator, "pk_supplier.name");
    supplierNameFilter.setPk_orgCode("pk_org");
    supplierNameFilter.addEditorListener();
  }

  private void setFieldValueElementEditor(
      QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.setFieldValueElementEditor("",
        OnwayStatusQryEnum.confirm.code(),
        new StatusQueryFieldValueElementEditorCreator());
  }
}
