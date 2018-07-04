package nc.ui.pu.pub.action;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pubapp.billref.src.action.RefreshAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;

/**
 * @since 6.0
 * @version 2011-7-21 ����11:08:22
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
    // scheme�����ҵ�����̣���������ѯ��
    queryScheme.put(PUQueryConst.BUSITYPES_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBusitypes());
    // scheme����ӵ�ǰ�������ͻ������ͣ���������ѯ��
    queryScheme.put(PUQueryConst.BILLTYPE_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBillSrcVar().getCurrBillOrTranstype());
    super.executeQuery(queryScheme);
  }
}
