package nc.ui.pu.m27.match.editor.list.afteredit;

import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合理损耗的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-29 下午03:15:41
 */
public class ReasonWasteNum implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    try {
      this.check(event.getBillListPanel().getHeadBillModel(), event.getRow());
    }
    catch (Exception e) {
      event.getBillListPanel().getHeadBillModel().setValueAt(
          event.getOldValue(), event.getRow(), MatchMaterialVO.NREASONWASTENUM);
      ExceptionUtils.wrappException(e);
    }
  }

  private void check(BillModel bm, int row) {
    UFDouble reasonWasteNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NREASONWASTENUM);
    if (MathTool.lessThan(reasonWasteNum, UFDouble.ZERO_DBL)) {
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0026")/*@res "发票合理损耗数量必须为正数！"*/);
    }
    UFDouble currInvcNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURINVOICESETTLENUM);
    if (MathTool.absCompareTo(reasonWasteNum, currInvcNum) > 0) {
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0022")/*@res "发票合理损耗数量不能超出本次结算数量！"*/);
    }
  }

}