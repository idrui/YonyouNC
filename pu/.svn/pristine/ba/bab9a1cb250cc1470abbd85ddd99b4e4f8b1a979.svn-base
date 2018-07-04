package nc.ui.pu.pub.action;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pubapp.billref.src.action.RefreshAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;

/**
 * @since 6.0
 * @version 2011-7-21 上午11:08:22
 * @author wuxla
 */

public class PURefreshAction extends RefreshAction {
  private static final long serialVersionUID = 6469997350532142726L;

  private boolean bizflow = false;

  public boolean isBizflow() {
    return this.bizflow;
  }

  public void setBizflow(boolean bizflow) {
    this.bizflow = bizflow;
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    BusitypeFilerUtil.appendWhr(queryScheme, this.getRefContext(),
        this.isBizflow());
    // scheme里添加业务流程，供后续查询用
    queryScheme.put(PUQueryConst.BUSITYPES_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBusitypes());
    // scheme里添加当前单据类型或交易类型，供后续查询用
    queryScheme.put(PUQueryConst.BILLTYPE_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBillSrcVar().getCurrBillOrTranstype());
    super.executeQuery(queryScheme);
  }
}
