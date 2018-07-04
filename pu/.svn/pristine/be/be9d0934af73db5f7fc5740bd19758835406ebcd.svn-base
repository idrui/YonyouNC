package nc.ui.pu.m23.check.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PULinkQueryAction;
import nc.ui.pubapp.billgraph.SourceBillFlowDlg;
import nc.ui.pubapp.uif2app.actions.LinkQueryAction;
import nc.ui.trade.billgraph.billflow.control.DefaultBillGraphListener;
import nc.ui.trade.billgraph.billflow.control.IBillGraphListener;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查单据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-4 下午02:27:22
 */
public class LinkBillUIAction extends PULinkQueryAction {
  private static final String BILL_FINDER_CLASSNAME =
      "nc.impl.pubapp.linkquery.BillTypeSetBillFinder";

  private static final long serialVersionUID = -8876691494402335939L;

  private IBillGraphListener billGraphListener = null;

  public LinkBillUIAction() {
    super();
    this.billGraphListener = new DefaultBillGraphListener();
    ((DefaultBillGraphListener) this.billGraphListener).setOpenMode(1);
    this.setBillGraphListeners(this.billGraphListener);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
  	if (Thread.currentThread().getContextClassLoader() == null){
			Thread.currentThread().setContextClassLoader(
					LinkQueryAction.class.getClassLoader());
		}
    ArriveViewVO selectedData =
        (ArriveViewVO) this.getModel().getSelectedData();
    if (selectedData == null) {
      return;
    }
    // 需要将试图vo转换一下
    ArriveVO vo = selectedData.changeToBill();
    IBillInfo info = this.getBillInfoFactory().getBillInfo(vo);
    SourceBillFlowDlg dlg =
        new SourceBillFlowDlg(this.getModel().getContext().getEntranceUI(),
            LinkBillUIAction.BILL_FINDER_CLASSNAME, info.getBillType(),
            info.getBillId(), info.getBillCode());
    // 增加默认监听
    dlg.setBillGraphListener(this.billGraphListener);
    dlg.showModal();

  }

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getSelectedData() != null;
  }

}
