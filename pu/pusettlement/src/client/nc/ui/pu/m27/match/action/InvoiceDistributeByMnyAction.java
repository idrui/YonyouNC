package nc.ui.pu.m27.match.action;

import nc.ui.pub.bill.BillModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>发票分摊--按金额分摊，本类主要完成以下功能：</b>
 * <ul>
 * <li>只有点击异物料结算后该按钮可用，针对发票按照金额进行分摊。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-2 下午03:20:25
 */
public class InvoiceDistributeByMnyAction extends InvoiceDistributeAction {

  private static final long serialVersionUID = -3237862893852368503L;

  public InvoiceDistributeByMnyAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_INVOICEDISTRIBUTEBYMNY);
    // this.setBtnName("按金额分摊");
    // this.setCode("btnInvoiceDistributeByNum");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  protected UFDouble getDistributedStockBy(BillModel bm, int i) {
    UFDouble nprice = MathTool.nvl(ValueUtils.getUFDouble(bm.getValueAt(i, MatchMaterialVO.NPRICE)));
    UFDouble num = ValueUtils.getUFDouble(bm.getValueAt(i, MatchMaterialVO.NCURSTOCKSETTLENUM));
    return num.multiply(nprice);
  }
}
