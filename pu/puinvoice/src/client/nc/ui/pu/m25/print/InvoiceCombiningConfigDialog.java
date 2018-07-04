package nc.ui.pu.m25.print;

import java.awt.Container;

import nc.ui.pu.pub.print.PUCombiningConfigDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.lang.UFDouble;

/**
 * ��Ʊ�ϲ���ʾȷ�϶Ի���
 * ���÷�Ʊ�����������У���������ֵΪ�㣬�������ȡ����ֵʱ������
 * 
 * @since 6.5
 * @version 2015-7-14 ����4:25:34
 * @author luojw
 */
public class InvoiceCombiningConfigDialog extends PUCombiningConfigDialog {

  private static final long serialVersionUID = -7900587880976937717L;

  public InvoiceCombiningConfigDialog(Container container, BillCardPanel cardPanel,
      InvoiceVO bill) {
    super(container, cardPanel, bill);
    this.initNoNumItems(bill);
  }
  
  private void initNoNumItems(InvoiceVO bill){
    for(InvoiceItemVO item : bill.getChildrenVO()){
      if(item.getNnum() == null){
        item.setNnum(UFDouble.ZERO_DBL);
      }
    }
  }
  
}
