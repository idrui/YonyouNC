package nc.ui.pu.m25.report.action;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.ui.pu.m25.report.dlg.PuInvoiceQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.report.enumeration.PuInvoiceEnum;
import nc.vo.pu.report.scale.m25.InvoiceDetailScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 采购发票查询action
 * 
 * @since 6.0
 * @version 2011-3-25 上午09:20:57
 * @author lizhengb
 */
public class PuInvoiceReportQryAction extends PURptDefaultQueryAction {

  /** 发票主子关联名称 **/
  public static final String INVOICEBODY = "invoicebody";

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    ICReportConditionUtils.setDescription(context, queryScheme);
    // 权限设置
    this.setRptPermission(context);

    String whereSql = queryScheme.getWhereSQLOnly();
    SqlBuilder condtionSql = new SqlBuilder();
    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      ConditionVO[] conds =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      if (!ArrayUtils.isEmpty(conds)) {

        for (ConditionVO cond : conds) {
          // 来源单据类型的处理
          if (InvoiceQryFieldCode.csourcetypecode.code().equals(
              cond.getFieldCode())) {
            String srcSql = this.getSourceType(cond);
            if (null != srcSql) {
              condtionSql.append(" and ");
              condtionSql.append(this.getSourceType(cond));
            }
            break;
          }
        }
        whereSql = whereSql + condtionSql.toString();
      }
    }
    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setExpression(whereSql.toString());
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    BaseQueryCondition result =
        (BaseQueryCondition) super.doQueryByScheme(parent, context,
            reportModel, queryScheme);
    result.setDescriptors(new Descriptor[] {
      filter
    });
    this.setMainOrgFilter(filter, context);
    return result;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new PuInvoiceQryDlgInit();
  }

  /**
   * 枚举类型与实际单据类型的对照
   * 
   * @return
   */
  private Map<String, String> getBillTypeMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(PuInvoiceEnum.IC_PURCHASEIN.strValue(),
        ICBillType.PurchaseIn.getCode());// 45-采购入库
    map.put(PuInvoiceEnum.IC_INBAND.strValue(),
        ICBillType.SubContinIn.getCode());// 47-委外加工入库单
    map.put(PuInvoiceEnum.IC_VMI.strValue(), ICBillType.VmiSum.getCode());// 50-消耗汇总
    map.put(PuInvoiceEnum.PU_INITIALEST.strValue(),
        POBillType.InitEstimate.getCode());// 4T-期初暂估单
    map.put(PuInvoiceEnum.PU_ORDER.strValue(), POBillType.Order.getCode());// 21-采购订单
    map.put(PuInvoiceEnum.SC_ORDER.strValue(), SCBillType.Order.getCode());// 61-委外订单

    return map;

  }

  private String getConditionSql(QueryConditionDLG queryDlg) {
    SqlBuilder condtionSql = new SqlBuilder();
    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    if (ArrayUtils.isEmpty(conds)) {
      return null;
    }
    for (ConditionVO cond : conds) {
      // 来源单据类型的处理
      if (InvoiceQryFieldCode.csourcetypecode.code()
          .equals(cond.getFieldCode())) {
        String srcSql = this.getSourceType(cond);
        if (null != srcSql) {
          condtionSql.append(" and ");
          condtionSql.append(this.getSourceType(cond));
        }
        break;
      }
    }
    return condtionSql.toString();
  }

  /**
   * 来源单据类型处理
   * 
   * @param condvo
   * @return
   */
  private String getSourceType(ConditionVO condvo) {

    String voValue = condvo.getValue();
    if (null == voValue || voValue.trim().length() == 0) {
      return null;
    }
    voValue = voValue.replace(")", "").replace("(", "");
    String[] values = voValue.split(",");
    List<String> types = new ArrayList<String>();
    for (String value : values) {
      String key = value.replace("'", "").trim();
      String billtype = this.getBillTypeMap().get(key);
      if (!StringUtils.isEmpty(billtype)) {
        types.add(billtype);
      }
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append(InvoiceQryFieldCode.csourcetypecode.code(),
        types.toArray(new String[types.size()]));
    return sql.toString();
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(InvoiceQryFieldCode.material_code.code(),
        PuInvoiceReportQryAction.INVOICEBODY + "."
            + InvoiceItemVO.PK_SRCMATERIAL);
    columnMapping.put(InvoiceQryFieldCode.material_name.code(),
        PuInvoiceReportQryAction.INVOICEBODY + "."
            + InvoiceItemVO.PK_SRCMATERIAL);
    columnMapping.put(InvoiceQryFieldCode.supplier_code.code(),
        InvoiceHeaderVO.PK_SUPPLIER);
    columnMapping.put(InvoiceQryFieldCode.supplier_name.code(),
        InvoiceHeaderVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return InvoiceHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return InvoiceQryFieldCode.pk_org.code();
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new InvoiceDetailScaleStrategy();
  }

  @Override
  protected void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 供应商编码，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator,
            InvoiceQryFieldCode.supplier_code.code());
    qSupplierFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierFilter.addEditorListener();

    // 供应商名称，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator,
            InvoiceQryFieldCode.supplier_name.code());
    qSupplierNameFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierNameFilter.addEditorListener();
    // 部门，如果查询一个采购组织的数据，则可参照该组织所属的公司下的部门档案；否则可参照集团范围的部门档案。
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            InvoiceQryFieldCode.pk_dept.code());
    deptFilter.setPk_orgCode(InvoiceQryFieldCode.pk_purchaseorg.code());
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员，如果查询一个采购组织的数据，则可参照该组织所属的公司下的人员档案；否则可参照集团范围的人员档案。
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            InvoiceQryFieldCode.pk_bizpsn.code());
    psndoc.setPk_orgCode(InvoiceQryFieldCode.pk_purchaseorg.code());
    psndoc.addEditorListener();
    // 发票类型
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator,
            InvoiceQryFieldCode.vtrantypecode.code(),
            POBillType.Invoice.getCode());
    trantype.filter();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(dlgDelegator,
            InvoiceQryFieldCode.marbasclass_code.code());
    marbasFilter.setPk_orgCode(InvoiceQryFieldCode.pk_org.code());
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(dlgDelegator, InvoiceQryFieldCode.pk_org.code(),
        InvoiceQryFieldCode.material_code.code()).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(dlgDelegator, InvoiceQryFieldCode.pk_org.code(),
        InvoiceQryFieldCode.material_name.code()).addEditorListener();
    // 付款单位
    qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator,
            InvoiceQryFieldCode.pk_paytosupplier.code());
    qSupplierNameFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierNameFilter.addEditorListener();
    // 付款协议编码
    QPaytermFilter payFilter =
        new QPaytermFilter(dlgDelegator, InvoiceQryFieldCode.pk_payterm.code());
    payFilter.setPk_orgCode(this.getMainOrgKey());
    payFilter.addEditorListener();
    // 仓库编码
    new QWareHouseFilter(dlgDelegator, InvoiceQryFieldCode.pk_stockorg.code(),
        InvoiceQryFieldCode.pk_stordoc.code()).addEditorListener();
    // 按主组织过滤客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(dlgDelegator, PUEntity.M25_B_TAB + PUQueryConst.DOT
            + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(InvoiceQryFieldCode.pk_stockorg.code());
    customerFilter.addEditorListener();

    // 按物料过滤特征码
    new QFfileFilter(dlgDelegator, "invoicebody.pk_srcmaterial",
        "invoicebody.cffileid").addEditorListener();
    new QFfileFilter(dlgDelegator, "invoicebody.pk_srcmaterial",
        "invoicebody.cffileid.vskucode").addEditorListener();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    SqlBuilder whereSql = new SqlBuilder();
    // 获取查询模板条件
    String dlgWhereSql = queryDlg.getWhereSQL();
    whereSql.append(dlgWhereSql);
    // 根据查询模板非查询条件，获取相应的wheresql
    String conditionSql = this.getConditionSql(queryDlg);
    if (!StringUtils.isBlank(conditionSql)) {
      whereSql.append(conditionSql);
    }
    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setExpression(whereSql.toString());
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    BaseQueryCondition result = (BaseQueryCondition) condition;
    result.setDescriptors(new Descriptor[] {
      filter
    });
    this.setMainOrgFilter(filter, context);
    return result;
  }
}
