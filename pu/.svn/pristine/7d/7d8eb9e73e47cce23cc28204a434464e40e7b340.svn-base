package nc.ui.pu.m27.match.view.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;

/**
 * 结算查询发票的对话框公共初始化器
 * 
 * @since 6.0
 * @version 2011-1-13 下午06:43:06
 * @author zhaoyha
 */

public class BaseInvoicStlQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 不支持数据权限
    condDLGDelegator.setPowerEnable(false);
    this.initRef(condDLGDelegator);// 初始化参照
    this.setDefaultValue(condDLGDelegator);// 设置默认值
    this.appendReduInfo(condDLGDelegator);// 设置冗余字段
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void appendReduInfo(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.addRedundancyInfo(InvoiceHeaderVO.PK_ORG,
        InvoiceQryFieldCode.invoiceborg.code());
    condDLGDelegator.addRedundancyInfo(InvoiceHeaderVO.PK_GROUP,
        InvoiceQryFieldCode.invoicebgroup.code());
    condDLGDelegator.addRedundancyInfo(InvoiceHeaderVO.PK_SUPPLIER,
        InvoiceQryFieldCode.invoicebsup.code());
    // condDLGDelegator.addRedundancyInfo(InvoiceHeaderVO.DBILLDATE,
    // InvoiceQryFieldCode.invoicebdate.code());
  }

  private String getPk_OrgCode() {
    return InvoiceHeaderVO.PK_ORG;
  }

  protected void initRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 物料基本分类
    QMarbasclassFilter classFilter =
        new QMarbasclassFilter(condDLGDelegator,
            InvoiceQryFieldCode.marbascls.code());
    classFilter.setPk_orgCode(this.getPk_OrgCode());
    classFilter.addEditorListener();

    // 物料编码
    QMarterialFilter materialFilter =
        new QMarterialFilter(condDLGDelegator, this.getPk_OrgCode(),
            InvoiceQryFieldCode.marcode.code());
    materialFilter.addEditorListener();

    // 物料名称
    QMarterialFilter materialNameFilter =
        new QMarterialFilter(condDLGDelegator, this.getPk_OrgCode(),
            InvoiceQryFieldCode.marname.code());
    materialNameFilter.addEditorListener();

    // 物料编码
    QMarterialFilter srcmaterialFilter =
        new QMarterialFilter(condDLGDelegator, this.getPk_OrgCode(),
            InvoiceQryFieldCode.srcmarcode.code());
    srcmaterialFilter.addEditorListener();

    // 物料名称
    QMarterialFilter srcmaterialNameFilter =
        new QMarterialFilter(condDLGDelegator, this.getPk_OrgCode(),
            InvoiceQryFieldCode.srcmarname.code());
    srcmaterialNameFilter.addEditorListener();

    // 供应商
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            InvoiceQryFieldCode.supplier.code());
    supplierFilter.setPk_orgCode(this.getPk_OrgCode());
    supplierFilter.addEditorListener();

    // 仓库
    QWareHouseFilter warehouseFilter =
        new QWareHouseFilter(condDLGDelegator, "pk_stockorg",
            "invoicebody.pk_stordoc");
    warehouseFilter.addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "invoicebody.pk_srcmaterial.code",
        "invoicebody.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "invoicebody.pk_srcmaterial.code",
        "invoicebody.cffileid.vskucode").addEditorListener();
  }

  /**
   * @param condDLGDelegator
   */
  protected void setDefaultValue(QueryConditionDLGDelegator condDLGDelegator) {
    // 设置默认值
    String pk_org = condDLGDelegator.getLogincontext().getPk_org();
    condDLGDelegator.setDefaultValue(null, InvoiceHeaderVO.PK_ORG, pk_org);
  }

}
