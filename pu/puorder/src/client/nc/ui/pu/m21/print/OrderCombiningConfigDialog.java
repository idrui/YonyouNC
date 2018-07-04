package nc.ui.pu.m21.print;

import java.awt.Container;

import nc.ui.pu.pub.print.PUCombiningConfigDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.print.combination.CombinedResultDialog;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * �ϲ���ʾȷ�϶Ի���
 * ʹ�ö����ĺϲ�����Ի���Ϊ�˽�����Э������ݼ��ؽ�ȥ
 * 
 * @since 6.5
 * @version 2015-7-14 ����4:25:34
 * @author luojw
 */
public class OrderCombiningConfigDialog extends PUCombiningConfigDialog {

  private static final long serialVersionUID = -7900587880976937717L;
  
  /** ����Э��������� */
  private OrderPaymentVO[] payments;
  
  private OrderCombinedResultDialog resultDialog;

  public OrderCombiningConfigDialog(Container container, BillCardPanel cardPanel,
      OrderVO bill) {
    super(container, cardPanel, bill);
    this.payments = bill.getPaymentVO();
  }
  
  @Override
  public CombinedResultDialog getResultDialog() {
    if (this.resultDialog == null) {
      this.resultDialog = new OrderCombinedResultDialog(this.getParent());
      this.resultDialog.setVoProcessor(this.getVoProcessor());
      this.resultDialog.setNodeCode(this.getNodeCode());
      this.resultDialog.setLoginContext(this.getLoginContext());
      this.resultDialog.setPtMaterialNameItemKey(this.getPtMaterialNameItemKey());
      this.resultDialog.setNodeKey(this.getNodeKey());
      this.resultDialog.setPayments(this.payments);
    }
    return this.resultDialog;
  }
  
  
  
}
