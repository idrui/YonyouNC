/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 下午02:54:14
 */
package nc.ui.pu.pub.action;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购业务流程过滤
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 下午02:54:14
 */
public class PUQueryAction extends QueryAction {

  private static final long serialVersionUID = 2903840654809627830L;

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
