package nc.ui.pu.report.purchaseIn.action;

import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.report.scale.m25.M45NoInvoiceScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * �ɹ�����Ʊ������ѯaction
 * 
 * @since 6.0
 * @version 2011-3-24 ����10:29:24
 * @author lizhengb
 */
public class PurchaseInReportQueryAction extends PURptDefaultQueryAction {
  public static final String mainOrg = "po_purchaseinfi_b.pk_financeorg";

  public static final String PO_PURCHASEINFI_B_PK_MATERIAL_PK_MARBASCLASS_CODE =
      "po_purchaseinfi_b.pk_material.pk_marbasclass.code";

  /** �ɹ���֯ **/
  public static final String PO_PURCHASEINFI_B_PK_PURCHASEORG =
      "po_purchaseinfi_b.pk_purchaseorg";

  public static final String PO_PURCHASEINFI_B_PK_SUPPLIER_CODE =
      "po_purchaseinfi_b.pk_supplier.code";

  public static final String PO_PURCHASEINFI_B_PK_SUPPLIER_NAME =
      "po_purchaseinfi_b.pk_supplier.name";

  public static final String PO_PURCHASEINFI_CTRANTYPEID =
      "po_purchaseinfi.ctrantypeid";

  public static final String PO_PURCHASEINFI_PK_DEPT =
      "po_purchaseinfi.pk_dept";

  public static final String PO_PURCHASEINFI_PK_PSNDOC =
      "po_purchaseinfi.pk_psndoc";

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(
        PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_SUPPLIER_CODE,
        PurchaseinFIItemVO.PK_STOCKPS_B + "." + PurchaseinFIItemVO.PK_SUPPLIER);
    columnMapping.put(
        PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_SUPPLIER_NAME,
        PurchaseinFIItemVO.PK_STOCKPS_B + "." + PurchaseinFIItemVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return PurchaseinFIHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return PurchaseInReportQueryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new M45NoInvoiceScaleStrategy();
  }

  @Override
  protected void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // ���������
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator,
            PurchaseInReportQueryAction.PO_PURCHASEINFI_CTRANTYPEID,
            ICBillType.PurchaseIn.getCode());
    trantype.filter();
    // ��Ӧ�̱��룬���ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator,
            PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_SUPPLIER_CODE);
    qSupplierFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierFilter.addEditorListener();

    // ��Ӧ�����ƣ����ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator,
            PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_SUPPLIER_NAME);
    qSupplierNameFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierNameFilter.addEditorListener();

    // ���ţ������ѯһ��������֯�����ݣ���ɲ��ո���֯�����Ĺ�˾�µĲ��ŵ���������ɲ��ռ��ŷ�Χ�Ĳ��ŵ�����
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            PurchaseInReportQueryAction.PO_PURCHASEINFI_PK_DEPT);
    deptFilter
        .setPk_orgCode(PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_PURCHASEORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // �ɹ�Ա�������ѯһ��������֯�����ݣ���ɲ��ո���֯�����Ĺ�˾�µ���Ա����������ɲ��ռ��ŷ�Χ����Ա������
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            PurchaseInReportQueryAction.PO_PURCHASEINFI_PK_PSNDOC);
    psndoc
        .setPk_orgCode(PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_PURCHASEORG);
    psndoc.addEditorListener();

    // // ���ϻ����������
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(
            dlgDelegator,
            PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_MATERIAL_PK_MARBASCLASS_CODE);
    qMarbasclassFilter
        .setPk_orgCode(PurchaseInReportQueryAction.PO_PURCHASEINFI_B_PK_PURCHASEORG);
    qMarbasclassFilter.addEditorListener();

  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    SqlBuilder whereSql = new SqlBuilder();
    String dlgWhereSql = queryDlg.getWhereSQL();
    if (StringUtils.isNotBlank(dlgWhereSql)) {
      whereSql.append(dlgWhereSql);
    }
    // where����ֱ�Ӵ����ɸѡ������
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
