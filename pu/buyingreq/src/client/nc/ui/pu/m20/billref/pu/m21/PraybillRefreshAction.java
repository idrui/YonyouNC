package nc.ui.pu.m20.billref.pu.m21;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pubapp.billref.src.action.RefreshAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;

/**
 * 请购单转单刷新Action，需要和保存Action逻辑一致。
 * 
 * @since 6.3
 * @version 2013-6-7 上午10:31:44
 * @author lixyp
 */
public class PraybillRefreshAction extends RefreshAction {

  private static final long serialVersionUID = 570869051352451331L;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    BusitypeFilerUtil.appendWhr(queryScheme, this.getRefContext(), false);
    // scheme里添加业务流程，供后续查询用
    queryScheme.put(PUQueryConst.BUSITYPES_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBusitypes());
    // scheme里添加当前单据类型或交易类型，供后续查询用
    queryScheme.put(PUQueryConst.BILLTYPE_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBillSrcVar().getCurrBillOrTranstype());
    super.executeQuery(queryScheme);
  }
}
