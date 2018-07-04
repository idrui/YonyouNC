package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.ui.ml.NCLangRes;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4201.enumeration.StockQryFieldCode;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;

import org.apache.commons.lang.ArrayUtils;

/**
 * 入库单或VMI消耗汇总查询的动作抽象类
 * 
 * @since 6.0
 * @version 2010-11-17 上午09:25:37
 * @author duy
 */
public abstract class AbstractStockQueryAction2 extends DefaultQueryAction {
  private static final long serialVersionUID = 4047932501432594775L;

  private IQueryScheme curQueryScheme = null;

  // 上次输入的组织
  private String pk_org_last = null;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!this.getModel().getContext().getPk_org().equals(this.pk_org_last)) {
      this.pk_org_last = this.getModel().getContext().getPk_org();
      this.setQryCondDLGDelegator(null);
      // new QueryDlgValueSetter(this.getQryDLGDelegator()).resetFiOrg(
      // this.getFiOrgFieldName(), this.pk_org_last);
      // 屏蔽高级条件查询
      if (this.hideAdvancePanel()) {
        this.getQryDLGDelegator().getQueryConditionDLG()
            .setVisibleAdvancePanel(false);
      }
    }
    super.doAction(e);
  }

  public IQueryScheme getCurQueryScheme() {
    return this.curQueryScheme;
  }

  public void setCurQueryScheme(IQueryScheme curQueryScheme) {
    this.curQueryScheme = curQueryScheme;
  }

  /**
   * 查询出的发票的物料vid
   * 
   * @return
   */
  private Set<String> getQueryInvoiceMaterialID() {
    InvoiceSettleVO[] invoices =
        ((MatchManageModel) this.getModel()).getQueryInvoiceVOs();
    if (ArrayUtils.isEmpty(invoices)) {
      return null;
    }
    Set<String> smidSet = new HashSet<String>();
    for (InvoiceSettleVO invoice : invoices) {
      smidSet.add(invoice.getPk_material());
    }
    return smidSet;
  }

  /**
   * 这里做查询前的一些处理,子类根据不同情况可以覆写
   * 
   * @param queryScheme
   */
  protected void beforeExecuteQuery(IQueryScheme queryScheme) {
    return;
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.beforeExecuteQuery(queryScheme);
    super.executeQuery(queryScheme);
  }

  /**
   * 财务组织字段key
   * 
   * @return
   */
  protected String getFiOrgFieldName() {
    return StockQryFieldCode.financeorgbody.code();
  }

  /**
   * 是否隐藏高级面板
   * 
   * @return
   */
  protected boolean hideAdvancePanel() {
    return true;
  }

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getContext().getPk_org() != null;
  }

  /**
   * 根据发票的物料过滤入库
   * 
   * @param queryScheme
   */
  protected void setInvoiceMaterialForScheme(IQueryScheme queryScheme) {
    // 用户输入的所有查询条件
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) queryScheme
            .get(QueryConstants.QUERY_CONDITION);
    // 是否按已查出的发票过滤
    QueryCondition filterByInoice =
        logicalConditionMap.get(StockQryFieldCode.bmarinclude.code());
    Set<String> materalVids = null;
    if (filterByInoice != null) {
      String[] values = filterByInoice.getValues();
      if (UFBoolean.valueOf(values[0]).booleanValue()) {
        materalVids = this.getQueryInvoiceMaterialID();
      }
    }
    if (materalVids == null) {
      materalVids = new HashSet<String>();
    }
    // 无论是否需要根据发票物料过滤，这里都放置非空set，后台直接获取不需判断
    queryScheme.put(StockQryFieldCode.invoice_matrial_id.code(), materalVids);
  }

  @Override
  protected void showQueryInfo() {
    // 由于Model中的数据模型不是默认的模型，因此该方法必须重载
    StockSettleVO[] stockSettleVOs =
        ((MatchManageModel) this.getModel()).getQueryStockVOs();
    int size = stockSettleVOs == null ? 0 : stockSettleVOs.length;
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
