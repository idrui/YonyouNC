package nc.ui.pu.m21.billref.ic.m45;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.action.RefreshAction;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * @since 6.0
 * @version 2011-7-21 ÉÏÎç11:51:54
 * @author wuxla
 */

public class OrderRefreshActionFor45 extends RefreshAction {

  private static final long serialVersionUID = 2599058543189178170L;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    BillSourceVar sourceVar = this.getRefContext().getRefInfo().getBillSrcVar();
    if (sourceVar.getUserObj() == null) {
      BusitypeFilerUtil.appendWhr(queryScheme, this.getRefContext(), true);
    }
    super.executeQuery(queryScheme);
  }
}
