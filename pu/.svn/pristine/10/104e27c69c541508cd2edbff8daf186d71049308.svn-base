package nc.ui.pu.m27.match.editor.list.afteredit;

import nc.ui.pu.m27.match.util.MatchRelaCalc;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 入库结算单价的编辑后事件(用于无发票结算) <br>
 * 支持单价和结算金额为零的结算，如有暂估应付，只冲暂估，不传应付
 *
 * @since 6.0
 * @version 2011-2-6 下午10:30:15
 * @author zhaoyha
 */
public class StockSettlePrice implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    try {
      this.check(event);
    }
    finally {
      // 联动计算
      new MatchRelaCalc(event).calc(event.getKey());
    }
  }

  private void check(ListHeadAfterEditEvent event) {
    // 支持单价和结算金额为零的结算，如有暂估应付，只冲暂估，不传应付
    UFDouble newprice = (UFDouble) event.getValue();
    // if (MathTool.compareTo(newprice, UFDouble.ZERO_DBL) <= 0) {
    // event.getBillListPanel().getHeadBillModel().setValueAt(
    // event.getOldValue(), event.getRow(), MatchMaterialVO.NPRICE);
    // throw new BusinessRuntimeException("入库单结算单价不能小于等于零");
    // }
    if (MathTool.compareTo(newprice, UFDouble.ZERO_DBL) < 0) {
      event.getBillListPanel().getHeadBillModel().setValueAt(
          event.getOldValue(), event.getRow(), MatchMaterialVO.NPRICE);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0028")/*@res "入库单结算单价不能小于零"*/);
    }
  }

}