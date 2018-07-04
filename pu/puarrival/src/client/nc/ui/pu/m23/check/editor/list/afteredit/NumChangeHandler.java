package nc.ui.pu.m23.check.editor.list.afteredit;

import nc.ui.ml.NCLangRes;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>数量相关的编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑后：修改“本次报检数量”“合格主数量”联动“不合格主数量”，及数量关系检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author guizhw
 * @time 2011-10-13 下午12:10:25
 */

public class NumChangeHandler implements IListHeadAfterEditEventListener {
  private BillManageModel model;

  public NumChangeHandler(BillManageModel model) {
    this.model = model;
  }

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    BillListPanel panel = event.getBillListPanel();
    BillModel bm = panel.getHeadBillModel();
    ArriveViewVO viewvo = (ArriveViewVO) this.model.getSelectedData();
    ArriveItemVO bvo = viewvo.getBVO();
    int row = event.getRow();

    // 合格主数量
    UFDouble nelignum =
        (UFDouble) bm.getValueAt(row, ArriveItemVO.NWILLELIGNUM);
    // 本次报检数量
    UFDouble nchecknum = (UFDouble) bm.getValueAt(row, ArriveItemVO.NCHECKNUM);
    // 累计报检主数量
    UFDouble naccumchecknum =
        (UFDouble) bm.getValueAt(row, ArriveItemVO.NACCUMCHECKNUM);
    // 到货数量
    UFDouble nnum = (UFDouble) bm.getValueAt(row, ArriveItemVO.NNUM);

    if (nchecknum.compareTo(UFDouble.ZERO_DBL) < 0
        || nelignum.compareTo(UFDouble.ZERO_DBL) < 0) {
      MessageDialog
          .showWarningDlg(
              panel,
              NCLangRes.getInstance().getStrByID("4004040_0", "04004040-0020")/* 提示 */,
              NCLangRes.getInstance().getStrByID("4004040_0", "04004040-0213")/* 报检数量和合格主数量不能为负数 */);
      bm.setValueAt(0, row, ArriveItemVO.NCHECKNUM);
      bm.setValueAt(0, row, ArriveItemVO.NWILLELIGNUM);
      return;
    }

    // 换算关系检查 本次报检 + 累计报检主数量 <= 到货数量
    if (MathTool.add(nchecknum, naccumchecknum).compareTo(nnum) > 0) {
      bm.setValueAt(nnum.sub(naccumchecknum), row, ArriveItemVO.NCHECKNUM);
      bm.setValueAt(nnum.sub(naccumchecknum), row, ArriveItemVO.NWILLELIGNUM);
      bvo.setNchecknum(nnum.sub(naccumchecknum));
      bvo.setNwillelignum(nnum.sub(naccumchecknum));
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4004040_0", "04004040-0178")/* 数量关系错：本次报检 + 累计报检主数量 > 到货数量 */);
    }
    if (!event.getKey().equals("nchecknum")) {
      // 换算关系检查 合格主数量 <= 本次报检数量
      if (MathTool.sub(nelignum, nchecknum).compareTo(UFDouble.ZERO_DBL) > 0) {
        bm.setValueAt(nchecknum, row, ArriveItemVO.NWILLELIGNUM);
        bvo.setNwillelignum(nchecknum);
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
            .getStrByID("4004040_0", "04004040-0179")/*
                                                      * 数量关系错：合格主数量 >
                                                      * 本次报检数量
                                                      */);
      }
    }
    if (event.getKey().equals("nchecknum")) {
      bm.setValueAt(nchecknum, row, ArriveItemVO.NWILLELIGNUM);
      bvo.setNchecknum(nchecknum);
      bvo.setNwillelignum(nchecknum);
      nelignum = (UFDouble) bm.getValueAt(row, ArriveItemVO.NWILLELIGNUM);
    }
    bvo.setNwillelignum(nelignum);
    // 不合格主数量= 本次报检数量-合格主数量
    UFDouble nnotelignum = MathTool.sub(nchecknum, nelignum);
    bm.setValueAt(nnotelignum, row, ArriveItemVO.NWILLNOTELIGNUM);
    bvo.setNwillnotelignum(nnotelignum);
    try {
      this.model.update(viewvo);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
