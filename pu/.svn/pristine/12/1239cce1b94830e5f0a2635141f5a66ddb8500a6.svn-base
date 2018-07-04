package nc.ui.pu.m27.match.view.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m4202.enumeration.VmiQryFieldCode;

/**
 * 结算查询消耗汇总的对话框公共初始化器
 * 
 * @since 6.0
 * @version 2011-3-24 下午01:46:39
 * @author 田锋涛
 */

public class VmiSettleQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initFilters(condDLGDelegator);// 参照过滤
    this.setDefaultValue(condDLGDelegator);// 默认值设置
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  /**
   * 参照过滤
   * 
   * @param condDLGDelegator
   */
  private void initFilters(QueryConditionDLGDelegator condDLGDelegator) {
    // 物料基本分类
    QMarbasclassFilter classFilter =
        new QMarbasclassFilter(condDLGDelegator,
            VmiQryFieldCode.marbascls.code());
    classFilter.setPk_orgCode(VmiQryFieldCode.financeorg.code());
    classFilter.addEditorListener();

    // 物料编码
    QMarterialFilter materialFilter =
        new QMarterialFilter(condDLGDelegator,
            VmiQryFieldCode.financeorg.code(), VmiQryFieldCode.marcode.code());
    materialFilter.addEditorListener();

    // 物料编码
    QMarterialFilter srcmaterialFilter =
        new QMarterialFilter(condDLGDelegator,
            VmiQryFieldCode.financeorg.code(),
            VmiQryFieldCode.srcmarcode.code());
    srcmaterialFilter.addEditorListener();

    // 供应商
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator, VmiQryFieldCode.supplier.code());
    supplierFilter.setPk_orgCode(VmiQryFieldCode.financeorg.code());
    supplierFilter.addEditorListener();

    // 仓库编码
    new QWareHouseFilter(condDLGDelegator, VmiQryFieldCode.storeorg.code(),
        VmiQryFieldCode.stordoc.code()).addEditorListener();
  }

  /**
   * 默认值设置
   * 
   * @param condDLGDelegator
   */
  private void setDefaultValue(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.setDefaultValue(null, VmiQryFieldCode.financeorg.code(),
        condDLGDelegator.getLogincontext().getPk_org());
  }

}
