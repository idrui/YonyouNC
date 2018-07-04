package nc.ui.pu.m27.match.view.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.EnumRefRegisterInfo;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m4201.enumeration.StockQryFieldCode;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 结算查询消耗汇总的对话框公共初始化器
 * 
 * @since 6.0
 * @version 2011-3-24 下午01:46:39
 * @author 田锋涛
 */

public class StockSettleQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  private QueryConditionDLGDelegator condDLGDelegator;

  public QueryConditionDLGDelegator getCondDLGDelegator() {
    return this.condDLGDelegator;
  }

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.condDLGDelegator = condDLGDelegator;
    // 不支持数据权限
    condDLGDelegator.setPowerEnable(false);
    this.initFilters(condDLGDelegator);// 参照过滤
    this.setDefaultValue(condDLGDelegator);// 设置默认值
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  public void setCondDLGDelegator(QueryConditionDLGDelegator condDLGDelegator) {
    this.condDLGDelegator = condDLGDelegator;
  }

  protected void initFilters(QueryConditionDLGDelegator condDLGDelegator) {
    // 物料基本分类编码
    QMarbasclassFilter classFilter =
        new QMarbasclassFilter(condDLGDelegator,
            StockQryFieldCode.marbascls.code());
    classFilter.setPk_orgCode(StockQryFieldCode.financeorgbody.code());
    classFilter.addEditorListener();

    // 物料编码
    QMarterialFilter materialFilter =
        new QMarterialFilter(condDLGDelegator,
            StockQryFieldCode.financeorgbody.code(),
            StockQryFieldCode.marcode.code());
    materialFilter.addEditorListener();

    QMarterialFilter srcmaterialFilter =
        new QMarterialFilter(condDLGDelegator,
            StockQryFieldCode.financeorgbody.code(),
            StockQryFieldCode.srcmarcode.code());
    srcmaterialFilter.addEditorListener();

    // 供应商
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            StockQryFieldCode.invoicebsup.code());
    supplierFilter.setPk_orgCode(StockQryFieldCode.financeorgbody.code());
    supplierFilter.addEditorListener();

    // 收货仓库
    QWareHouseFilter warehouseFilter =
        new QWareHouseFilter(condDLGDelegator,
            StockQryFieldCode.financeorgbody.code(),
            StockQryFieldCode.stordoc.code());
    warehouseFilter.addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_stockps_b.pk_srcmaterial.code",
        "pk_stockps_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_stockps_b.pk_srcmaterial.code",
        "pk_stockps_b.cffileid.vskucode").addEditorListener();
  }

  /**
   * @param condDLGDelegator
   */
  protected void setDefaultValue(QueryConditionDLGDelegator condDLGDelegator) {
    // 设置默认值
    String pk_org = condDLGDelegator.getLogincontext().getPk_org();
    condDLGDelegator.setDefaultValue(StockQryFieldCode.financeorgbody.code(),
        pk_org);
    // condDLGDelegator.setDefaultValue("pk_stockps_b", "pk_financeorg",
    // pk_org);

  }
}
