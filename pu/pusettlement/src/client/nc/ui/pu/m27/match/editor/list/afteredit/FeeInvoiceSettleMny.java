package nc.ui.pu.m27.match.editor.list.afteredit;

import nc.ui.ml.NCLangRes;
import nc.ui.pu.pub.editor.list.listener.IListBodyAfterEditEventListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * ƥ������б��壨���÷�Ʊ���༭���ν������Ĵ���
 * 
 * @since 6.0
 * @version 2011-2-8 ����10:56:29
 * @author zhaoyha
 */
public class FeeInvoiceSettleMny implements IListBodyAfterEditEventListener {

  @Override
  public void afterEdit(ListBodyAfterEditEvent event) {
    BillModel bm = event.getBillListPanel().getBodyBillModel();
    int row = event.getRow();
    this.check(event, bm, row);
  }

  private void check(ListBodyAfterEditEvent event, BillModel bm, int row) {
    UFDouble canStlMny =
        (UFDouble) bm.getValueAt(row, InvoiceSettleVO.NCANSETTLEMNY);
    UFDouble stlMny = (UFDouble) event.getValue();
    String code = (String) bm.getValueAt(row, InvoiceSettleVO.VBILLCODE);
    boolean isRestore = true;
    try {
      if (MathTool.isZero(stlMny)) {
        throw new BusinessRuntimeException(NCLangRes.getInstance().getStrByID("4004060_0", "04004060-0200", null, new String[]{code})/*��Ʊ[{0}]�ı��ν������Ϊ�㣡*/);
      }
      if (MathTool.absCompareTo(stlMny, canStlMny) > 0) {
        throw new BusinessRuntimeException(NCLangRes.getInstance().getStrByID("4004060_0", "04004060-0201", null, new String[]{code})/*��Ʊ[{0}]�ı��ν�����ܴ���δ�����*/);
      }
      if (MathTool.isDiffSign(stlMny, canStlMny)) {
        throw new BusinessRuntimeException(NCLangRes.getInstance().getStrByID("4004060_0", "04004060-0202", null, new String[]{code})/*��Ʊ[{0}]�ı��ν�����������δ�����һ�£�*/);
      }
      isRestore = false;
    }
    catch (BusinessRuntimeException e) {
      throw e;
    }
    finally {
      if (isRestore) {
        bm.setValueAt(event.getOldValue(), row, event.getKey());
      }
    }
  }

}
