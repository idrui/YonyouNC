package nc.ui.pu.m25.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.scmpub.query.refregion.QueryDlgInitialUtils;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 发票查询初始化器
 * 
 * @since 6.0
 * @version 2011-1-11 下午02:07:58
 * @author 田锋涛
 */
public class InvoiceQryCondDlgInitializer implements
    IQueryConditionDLGInitializer {

  /** 采购员名称 */
  public static final String BIZPSN_NAME = "pk_bizpsn";

  /** 物料编码 */
  public static final String MATERIAL_CODE = "invoicebody.pk_srcmaterial.code";

  /** 物料名称 */
  public static final String MATERIAL_NAME = "invoicebody.pk_srcmaterial.name";

  /** 付款 协议 */
  public static final String PAYTERM_CODE = "pk_payterm.code";

  /** 付款单位 */
  public static final String PAYTOSUPPLIER = "pk_paytosupplier";

  /** 使用部门 */
  public static final String PK_USEDEPT = "invoicebody.pk_usedept";

  /** 库存组织编码 */
  public static final String STOCK_CODE = "invoicebody.pk_stordoc.pk_org.code";

  /** 仓库编码 */
  public static final String STORDOC_CODE = "invoicebody.pk_stordoc.code";

  /** 供应商编码 */
  public static final String SUPPLIER_CODE = "pk_supplier.code";

  /** 供应商名称 */
  public static final String SUPPLIER_NAME = "pk_supplier.name";
  
  /** 项目名称 */
  public static final String PROJECT_CODE = "pk_project.project_code";
  
  /** 项目编码 */
  public static final String PK_PROJECT = "invoicebody.cprojectid";

  private AbstractUIAppModel model = null;

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    QueryDlgInitialUtils.filterBuySellFlagForBuy(condDLGDelegator,
        InvoiceHeaderVO.FBUYSELLFLAG);
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 1.供应商编码： 如果结算财务组织选值唯一，则参照该组织可见的供应商档案录入，否则参照集团范围的档案录入。
    // 2.供应商名称
    // 3.付款单位 ：同供应商
    // 4.付款协议编码： 参照付款协议录入，如果结算财务组织选值唯一，则参照该组织可见的付款协议录入，否则参照集团范围的付款协议
    // 5.仓库编码： 如果“库存组织编码”选值唯一，则参照该库存组织下及可代储该库存组织的仓库档案录入(??)；否则参照该集团下的所有仓库档案录入。
    // 6.设置交易类型参照范围
    new QTransTypeFilter(condDLGDelegator, POBillType.Invoice.getCode())
        .filter();
    // 采购员(人员) -- 根据需求查看集团的所有人员
    QPsndocFilter qpsnfilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            InvoiceQryCondDlgInitializer.BIZPSN_NAME);
    qpsnfilter.setPk_orgCode(null);
    qpsnfilter.addEditorListener();

    // 部门 -- 根据需求查看集团的所有部门
    QDeptFilter qdeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            InvoiceQryFieldCode.deptcode.code());
    qdeptfilter.setPk_orgCode(null);
    qdeptfilter.addEditorListener();
    qdeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            InvoiceHeaderVO.PK_DEPT);
    qdeptfilter.setPk_orgCode(null);
    qdeptfilter.addEditorListener();

    // 使用部门 -- 根据主组织过滤
    QDeptFilter qusedeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            InvoiceQryCondDlgInitializer.PK_USEDEPT);
    qusedeptfilter.addEditorListener();

    // 供应商编码
    new QSupplierFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.SUPPLIER_CODE).addEditorListener();
    // 供应商名称
    new QSupplierFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.SUPPLIER_NAME).addEditorListener();
    // 付款单位
    new QSupplierFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.PAYTOSUPPLIER).addEditorListener();
    // 仓库编码
    new QWareHouseFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.STOCK_CODE,
        InvoiceQryCondDlgInitializer.STORDOC_CODE).addEditorListener();
    // 付款协议编码
    new QPaytermFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.PAYTERM_CODE).addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, "invoicebody."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
    
    // 合并通版补丁NCdp205450398 zhangshqb 2015-09-23 添加项目相关过滤
		QProjectFilter qprojfilter = new QProjectFilter(condDLGDelegator,
				InvoiceHeaderVO.PK_ORG, InvoiceQryCondDlgInitializer.PROJECT_CODE);
		qprojfilter.addEditorListener();
		QProjectFilter qcprojfilter = new QProjectFilter(condDLGDelegator,
				InvoiceHeaderVO.PK_ORG, InvoiceQryCondDlgInitializer.PK_PROJECT);
		qcprojfilter.addEditorListener();

    // 按主组织过滤物料
    new QMarterialFilter(condDLGDelegator, InvoiceHeaderVO.PK_ORG,
        InvoiceQryCondDlgInitializer.MATERIAL_CODE).addEditorListener();
    new QMarterialFilter(condDLGDelegator, InvoiceHeaderVO.PK_ORG,
        InvoiceQryCondDlgInitializer.MATERIAL_NAME).addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "invoicebody.pk_srcmaterial.code",
        "invoicebody.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "invoicebody.pk_srcmaterial.code",
        "invoicebody.cffileid.vskucode").addEditorListener();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      InvoiceHeaderVO.PK_ORG
    });
    condDLGDelegator.setPowerEnable(true);
  }

}
