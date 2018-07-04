package nc.ui.pu.m27.match.editor.list.afteredit;

import nc.ui.pu.m27.match.util.MatchRelaCalc;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������������ı༭���¼�
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-29 ����03:13:32
 */
public class CurrentStockSettleNum implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    BillModel bm = event.getBillListPanel().getHeadBillModel();
    int row = event.getRow();
    try {
      this.check(event, bm, row);
    }
    finally {
      // ��������
      new MatchRelaCalc(event).calc(event.getKey());
    }
  }

  private void check(ListHeadAfterEditEvent event, BillModel bm, int row) {
    UFDouble settleNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURSTOCKSETTLENUM);
    UFDouble canSettleNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLENUM);
    if (MathTool.isZero(settleNum)) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURSTOCKSETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0023")/*@res "��ⵥ���ν�����������Ϊ�㣡"*/);
    }
    if (MathTool.isDiffSign(settleNum, canSettleNum)) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURSTOCKSETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0024")/*@res "��ⵥ���ν����������������������һ�£�"*/);
    }
    if (MathTool.absCompareTo(settleNum, canSettleNum) > 0) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURSTOCKSETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0025")/*@res "��ⵥ���ν����������ܳ���δ����������"*/);
    }
  }

}