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
 * ��ⵥ��VMI���Ļ��ܲ�ѯ�Ķ���������
 * 
 * @since 6.0
 * @version 2010-11-17 ����09:25:37
 * @author duy
 */
public abstract class AbstractStockQueryAction2 extends DefaultQueryAction {
  private static final long serialVersionUID = 4047932501432594775L;

  private IQueryScheme curQueryScheme = null;

  // �ϴ��������֯
  private String pk_org_last = null;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!this.getModel().getContext().getPk_org().equals(this.pk_org_last)) {
      this.pk_org_last = this.getModel().getContext().getPk_org();
      this.setQryCondDLGDelegator(null);
      // new QueryDlgValueSetter(this.getQryDLGDelegator()).resetFiOrg(
      // this.getFiOrgFieldName(), this.pk_org_last);
      // ���θ߼�������ѯ
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
   * ��ѯ���ķ�Ʊ������vid
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
   * ��������ѯǰ��һЩ����,������ݲ�ͬ������Ը�д
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
   * ������֯�ֶ�key
   * 
   * @return
   */
  protected String getFiOrgFieldName() {
    return StockQryFieldCode.financeorgbody.code();
  }

  /**
   * �Ƿ����ظ߼����
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
   * ���ݷ�Ʊ�����Ϲ������
   * 
   * @param queryScheme
   */
  protected void setInvoiceMaterialForScheme(IQueryScheme queryScheme) {
    // �û���������в�ѯ����
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) queryScheme
            .get(QueryConstants.QUERY_CONDITION);
    // �Ƿ��Ѳ���ķ�Ʊ����
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
    // �����Ƿ���Ҫ���ݷ�Ʊ���Ϲ��ˣ����ﶼ���÷ǿ�set����ֱ̨�ӻ�ȡ�����ж�
    queryScheme.put(StockQryFieldCode.invoice_matrial_id.code(), materalVids);
  }

  @Override
  protected void showQueryInfo() {
    // ����Model�е�����ģ�Ͳ���Ĭ�ϵ�ģ�ͣ���˸÷�����������
    StockSettleVO[] stockSettleVOs =
        ((MatchManageModel) this.getModel()).getQueryStockVOs();
    int size = stockSettleVOs == null ? 0 : stockSettleVOs.length;
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004060_0", "04004060-0199",
              null, new String[] {
                String.valueOf(size)
              })/* ��ѯ�ɹ����Ѳ鵽{0}�����ݡ� */, this.getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          IShowMsgConstant.getQueryNullInfo(), this.getModel().getContext());
    }
  }
}
