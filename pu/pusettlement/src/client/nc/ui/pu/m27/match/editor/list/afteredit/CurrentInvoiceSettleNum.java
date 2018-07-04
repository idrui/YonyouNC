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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>本次发票结算数量的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-29 下午03:14:43
 */
public class CurrentInvoiceSettleNum implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    BillModel bm = event.getBillListPanel().getHeadBillModel();
    int row = event.getRow();
    try {
      this.check(event, bm, row);
    }
    finally {
      new MatchRelaCalc(event).calc(event.getKey());
    }
  }

  private void check(ListHeadAfterEditEvent event, BillModel bm, int row) {
    UFDouble settleNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURINVOICESETTLENUM);
    UFDouble canSettleNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLENUM);
    UFDouble reasonWasteNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NREASONWASTENUM);

    if (MathTool.isZero(settleNum)) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURINVOICESETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0019")/*@res "发票本次结算数量不能为零！"*/);
    }
    if (MathTool.isDiffSign(settleNum, canSettleNum)) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURINVOICESETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0020")/*@res "发票本次结算数量正负与发票数量不一致！"*/);
    }
    if (MathTool.absCompareTo(settleNum, canSettleNum) > 0) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURINVOICESETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0021")/*@res "发票本次结算数量不能超出未结算数量！"*/);
    }
    if (MathTool.absCompareTo(reasonWasteNum, settleNum) > 0) {
      bm.setValueAt(event.getOldValue(), row,
          MatchMaterialVO.NCURINVOICESETTLENUM);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0022")/*@res "发票合理损耗数量不能超出本次结算数量！"*/);
    }
  }

}