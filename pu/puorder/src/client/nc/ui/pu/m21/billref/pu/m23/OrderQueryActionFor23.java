/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 下午03:51:22
 */
package nc.ui.pu.m21.billref.pu.m23;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>入库查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 下午03:51:22
 */
public class OrderQueryActionFor23 extends QueryAction {

  private static final long serialVersionUID = -3048862361116067166L;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    BillSourceVar sourceVar = this.getRefContext().getRefInfo().getBillSrcVar();
    if (sourceVar.getUserObj() == null) {
      BusitypeFilerUtil.appendWhr(queryScheme, this.getRefContext(), true);
    }
    super.executeQuery(queryScheme);
  }

}
