package nc.ui.pu.est.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PULinkQueryAction;
import nc.ui.pubapp.billgraph.SourceBillFlowDlg;
import nc.ui.pubapp.uif2app.actions.LinkQueryAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.trade.billgraph.billflow.control.DefaultBillGraphListener;
import nc.ui.trade.billgraph.billflow.control.IBillGraphListener;
import nc.vo.pu.est.entity.EstVO;

/**
 * �ݹ���ȡ���ݹ��ĵ������鰴ť
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-11-3 ����02:01:08
 */
public class EstimateLinkQueryAction extends PULinkQueryAction {

  private static final String billFinderClassname =
      "nc.impl.pubapp.linkquery.BillTypeSetBillFinder";

  private static final long serialVersionUID = 4840029356741076350L;

  private IBillGraphListener billGraphListener = null;

  public EstimateLinkQueryAction() {
    super();
    this.billGraphListener = new DefaultBillGraphListener();
    ((DefaultBillGraphListener) this.billGraphListener).setOpenMode(1);
    this.setBillGraphListeners(this.billGraphListener);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
		// ��ʼ���Ի���
		if (Thread.currentThread().getContextClassLoader() == null){
			Thread.currentThread().setContextClassLoader(
					LinkQueryAction.class.getClassLoader());
		}
    Object obj = this.getModel().getSelectedData();
    EstVO estVo = (EstVO) obj;
    String billID = estVo.getParentVO().getHID();
    String billCode = estVo.getParentVO().getVbillcode();
    SourceBillFlowDlg dlg =
        new SourceBillFlowDlg(this.getModel().getContext().getEntranceUI(),
            EstimateLinkQueryAction.billFinderClassname, this.getBillType(),
            billID, billCode);
    // ����Ĭ�ϼ���
    dlg.setBillGraphListener(this.billGraphListener);
    dlg.showModal();
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel model = (BillManageModel) this.getModel();
    if (model.getData() == null || model.getData().size() == 0) {
      return false;
    }
    int selectRow = model.getSelectedRow();
    // ����������ܷ�����ѡ�񲿷����ݽ����ݹ���ˢ�½����ʱ��
    if (selectRow >= model.getData().size()) {
      return false;
    }
    return model.getSelectedData() != null;
  }

}
