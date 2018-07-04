package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"发票"查询按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class InvoiceQueryAction extends DefaultQueryAction {

  private static final long serialVersionUID = -5667491871111125098L;

  private IQueryScheme curQueryScheme = null;

  private String pk_org_last = null;

  public InvoiceQueryAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_INVOICEQUERY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!this.getModel().getContext().getPk_org().equals(this.pk_org_last)) {
      this.pk_org_last = this.getModel().getContext().getPk_org();
      this.setQryCondDLGDelegator(null);
      // new QueryDlgValueSetter(this.getQryDLGDelegator()).resetFiOrg(
      // InvoiceHeaderVO.PK_ORG, this.pk_org_last);
    }
    super.doAction(e);
  }

  private void beforeExecQuery(IQueryScheme qs) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(qs);
    QueryCondition qc =
        qsp.getQueryCondition(InvoiceQryFieldCode.bmarinclude.code());
    QueryCondition feeqc =
        qsp.getQueryCondition(InvoiceQryFieldCode.bfeeinvoice.code());
    // 如果不按已查出入库单过滤或只查费用发票（入库单无费用物料，相当于不过滤），则不处理
    if (null == qc || !ValueUtils.getBoolean(qc.getValues()[0])
        || null != feeqc && ValueUtils.getBoolean(feeqc.getValues()[0])) {
      return;
    }
    // 将查询出的入库单物料ID放到scheme中，以便后台使用
    qs.put(IInvoiceSettleQuery.INCLUDE_MAR__KEY, this.getQueryStockMaterialID());
  }

  private Set<String> getQueryStockMaterialID() {
    StockSettleVO[] sstocks =
        ((MatchManageModel) this.getModel()).getQueryStockVOs();
    if (ArrayUtils.isEmpty(sstocks)) {
      return null;
    }
    Set<String> smidSet = new HashSet<String>();
    for (StockSettleVO ssVo : sstocks) {
      smidSet.add(ssVo.getPk_material());
    }
    return smidSet;
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    if (null == queryScheme) {
      return;
    }
    this.beforeExecQuery(queryScheme);
    IInvoiceSettleQuery service =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    try {
      // 为采购结算的发票查询
      this.setQueryType(queryScheme);
      // 查询发票
      InvoiceQueryResultFor27 result = service.queryByWhereSql(queryScheme);
      // 把发票数据放到Model中
      ((MatchManageModel) this.getModel()).initInvoice(result);
      // 显示查询信息
      this.showQueryInfo();
      // 更新querySchme
      this.curQueryScheme = queryScheme;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  protected IQueryScheme getCurQueryScheme() {
    return this.curQueryScheme;
  }

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getContext().getPk_org() != null;
  }

  /**
   * 为结算的采购发票查询类型
   * 
   * @param queryScheme
   */
  protected void setQueryType(IQueryScheme queryScheme) {
    queryScheme.put(IInvoiceSettleQuery.QRY_TYPE_KEY,
        IInvoiceSettleQuery.QRY_TYPE_PO);
  }

  @Override
  protected void showQueryInfo() {
    // 由于Model中的数据模型不是默认的模型，因此该方法必须重载
    InvoiceSettleVO[] goodInvoices =
        ((MatchManageModel) this.getModel()).getQueryInvoiceVOs();
    FeeDiscountSettleVO[] feeInvoices =
        ((MatchManageModel) this.getModel()).getQueryFeeInvoices();
    FeeDiscountSettleVO[] discountInvoices =
        ((MatchManageModel) this.getModel()).getQueryDiscountInvoices();
    int size = goodInvoices == null ? 0 : goodInvoices.length;
    size += feeInvoices == null ? 0 : feeInvoices.length;
    size += discountInvoices == null ? 0 : discountInvoices.length;
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004060_0", "04004060-0199",
              null, new String[] {
                String.valueOf(size)
              })/* 查询成功，已查到{0}条数据。 */, this.getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          IShowMsgConstant.getQueryNullInfo(), this.getModel().getContext());
    }
  }
}
