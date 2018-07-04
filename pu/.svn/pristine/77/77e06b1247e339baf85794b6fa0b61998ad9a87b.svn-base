package nc.ui.pu.report.supplierest;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pu.report.pub.validator.PuReportDateValidator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.report.enumeration.SupplierEstQryFieldCode;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pu.report.scale.supplierest.SupplierEstBalScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 供应商暂估余额表查询按钮
 * 
 * @since 6.0
 * @version 2011-3-22 上午09:45:58
 * @author yinfy
 */
public class SupplierEstQueryAction extends PURptDefaultQueryAction {

  public static final String BD_MATERIAL_NAME = "bd_material.name";

  public static final String BD_MATERIAL_PK_MARBASCLASS =
      "bd_material.pk_marbasclass";

  public static final String BD_SUPPLIER_NAME = "bd_supplier.name";

  public static final String EB_PK_PURCHASEORG = "eb.pk_purchaseorg";

  public static final String EB_PK_SRCMATERIAL = "eb.pk_srcmaterial";

  public static final String EB_PK_SUPPLIER = "eb.pk_supplier";

  public static final String EH_PK_DEPT = "eh.pk_dept";

  public static final String EH_PK_PSNDOC = "eh.pk_psndoc";

  public static final String mainOrg = "eb.pk_financeorg";

  public static final String PK_SUPPLIER = "pk_supplier";

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    IQueryCondition condition = this.createQueryCondition(context);
    ConditionVO[] condVOs =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    String whereSql = queryScheme.getWhereSQLOnly();
    this.processQueryPara(condVOs, context, whereSql);
    this.setCondOrgAttribute(context, queryScheme);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new SupplierEstQryDlgInitializer();
  }

  private String[] getBillTypes(ConditionVO vo) {
    String value = vo.getValue();
    value = StringUtils.replace(value, "(", "");
    value = StringUtils.replace(value, ")", "");
    value = StringUtils.replace(value, "'", "");
    String[] bts = value.split(",");
    return bts;
  }

  /**
   * @param queryDlg
   * @param context
   */
  private void processQueryPara(ConditionVO[] conds, IContext context,
      String wherePart) {
    PuSupplierEstQryInfoPara para = new PuSupplierEstQryInfoPara();

    for (ConditionVO ite : conds) {
      // 已全部结算入库单是否统计
      if (ite.getFieldCode()
          .equals(SupplierEstQryFieldCode.bincldfinish.code())) {
        para.setBincldfinish(UFBoolean.valueOf(ite.getValue()).booleanValue());
      }
      // 统计内容，可选值为：采购入库暂估、委托加工入库暂估、消耗汇总暂估
      else if (ite.getFieldCode().equals(
          SupplierEstQryFieldCode.qrycontent.code())) {
        para.setBilltypes(this.getBillTypes(ite));
      }
      // 日期区间
      else if (ite.getFieldCode()
          .equals(SupplierEstQryFieldCode.fperiod.code())) {
        this.setPeriodOfPara(ite, para);
      }
    }
    para.setWheresql(wherePart);
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
  }

  private void setPeriodOfPara(ConditionVO vo, PuSupplierEstQryInfoPara para) {
    String[] dates = vo.getValue().split(",");
    if (!"ISNULL".equals(dates[0]) && StringUtils.isNotBlank(dates[0])) {
      para.setBegindate(new UFDate(dates[0]).asBegin());
    }
    if (!"ISNULL".equals(dates[1]) && StringUtils.isNotBlank(dates[1])) {
      para.setEnddate(new UFDate(dates[1]).asEnd());
    }
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(SupplierEstQueryAction.EB_PK_SRCMATERIAL,
        PurchaseinFIItemVO.PK_STOCKPS_B + "."
            + PurchaseinFIItemVO.PK_SRCMATERIAL);
    map.put(SupplierEstQueryAction.BD_MATERIAL_NAME,
        PurchaseinFIItemVO.PK_STOCKPS_B + "."
            + PurchaseinFIItemVO.PK_SRCMATERIAL);
    map.put(SupplierEstQueryAction.EB_PK_SUPPLIER,
        PurchaseinFIItemVO.PK_STOCKPS_B + "." + PurchaseinFIItemVO.PK_SUPPLIER);
    map.put(SupplierEstQueryAction.BD_SUPPLIER_NAME,
        PurchaseinFIItemVO.PK_STOCKPS_B + "." + PurchaseinFIItemVO.PK_SUPPLIER);
    return map;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return PurchaseinFIHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return SupplierEstQueryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new SupplierEstBalScaleStrategy();
  }

  @Override
  protected void setFilter(QueryConditionDLGDelegator delegator) {
    super.setFilter(delegator);
    delegator.registerICriteriaEditorValidator(new PuReportDateValidator(
        SupplierEstQryFieldCode.fperiod.code()));
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    ICReportConditionUtils.setDescription(context, queryDlg.getQueryScheme());
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    ConditionVO[] conds = this.getDelegator().getGeneralCondtionVOs();
    context.setAttribute("query_conds", conds);
    ConditionVO[] logicconds = queryDlg.getLogicalConditionVOs();
    this.processQueryPara(logicconds, context, queryDlg.getWhereSQL());
    this.setCondOrgAttribute(context);
    return condition;

  }
}
