package nc.ui.pu.m27.match.util;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * UI端的结算匹配编辑后的联动计算工具
 * 
 * @since 6.0
 * @version 2011-2-6 下午03:16:36
 * @author zhaoyha
 */
public class MatchRelaCalc {

  private ListHeadAfterEditEvent event;

  public MatchRelaCalc(ListHeadAfterEditEvent event) {
    super();
    this.event = event;
  }

  /**
   * 根据变化的字段（入库单结算信息和发票结算信息）进行联动计算
   * 
   * @param chgKey
   */
  public void calc(String chgKey) {
    int row = this.event.getRow();
    BillModel bm = this.event.getBillListPanel().getHeadBillModel();
    if (MatchMaterialVO.NCURSTOCKSETTLENUM.equals(chgKey)) {
      this.stockStlNumChg(bm, row);
    }
    else if (MatchMaterialVO.NPRICE.equals(chgKey)) {
      this.calcSettleMny(bm, row);
    }
    else if (MatchMaterialVO.NCURSEETLEMNY.equals(chgKey)) {
      this.settleMnyChg(bm, row, (UFDouble) this.event.getValue());
    }
    else if (MatchMaterialVO.NCURINVOICESETTLENUM.equals(chgKey)) {
      this.invoiceStlNumChg(bm, row);
    }
  }

  private void calcEstMny(BillModel bm, int row) {
    UFDouble nestprice =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NESTPRICE);
    UFDouble num =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURSTOCKSETTLENUM);
    UFDouble canStlNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLENUM);
    UFDouble stlMny = MathTool.nvl(nestprice).multiply(MathTool.nvl(num));
    if (MathTool.equals(num, canStlNum)) {
      stlMny =
          this.getMatchManageModel().getMatchMaterialVOs()[row].getNestmny();
    }
    bm.setValueAt(stlMny, row, MatchMaterialVO.NESTMNY);
  }

  private void calcSettleMny(BillModel bm, int row) {
    if (EnumSettleType.WITHOUT_INVOICE != this.getSettleType()) {
      return;
    }
    UFDouble nprice = (UFDouble) bm.getValueAt(row, MatchMaterialVO.NPRICE);
    UFDouble origprice =
        this.getMatchManageModel().getMatchMaterialVOs()[row].getNprice();
    UFDouble num =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURSTOCKSETTLENUM);
    UFDouble orignum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLENUM);
    // 只有无发票结算时要计算本次结算金额
    UFDouble stlMny = MathTool.nvl(nprice).multiply(MathTool.nvl(num));
    if (MathTool.equals(nprice, origprice) && MathTool.equals(num, orignum)) {
      stlMny = (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLEMNY);
    }
    bm.setValueAt(stlMny, row, MatchMaterialVO.NCURSEETLEMNY);
  }

  private MatchManageModel getMatchManageModel() {
    MatchManageModel model = (MatchManageModel) this.event.getContextObject();
    return model;
  }

  private EnumSettleType getSettleType() {
    MatchManageModel model = this.getMatchManageModel();
    return model.getSettleEnvironment().getSettleType();
  }

  private void invoiceStlNumChg(BillModel bm, int row) {
    // TODO 赵玉行 考虑合理损耗数量的计算影响
    // 更新本次发票结算金额 = 单价*本次发票结算数量
    UFDouble nprice = (UFDouble) bm.getValueAt(row, MatchMaterialVO.NPRICE);
    UFDouble ncurInvoiceSettlNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURINVOICESETTLENUM);
    UFDouble canStlNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLENUM);
    UFDouble curInvoiceSettleMny = null;
    if (MathTool.equals(canStlNum, ncurInvoiceSettlNum)) {
      curInvoiceSettleMny =
          (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLEMNY);
    }
    else {
      curInvoiceSettleMny =
          MathTool.nvl(nprice).multiply(MathTool.nvl(ncurInvoiceSettlNum));
    }
    // 本次发票结算金额 = 单价*本次发票结算数量
    bm.setValueAt(curInvoiceSettleMny, row,
        MatchMaterialVO.NCURINVOICESETTLEMNY);
    // 结算总金额=结算金额+折扣+成本要素(其实此时还没有分摊，总结算金额＝本次结算金额)
    bm.setValueAt(curInvoiceSettleMny, row, MatchMaterialVO.NCURSEETLEMNY);
  }

  private void settleMnyChg(BillModel bm, int row, UFDouble stlMny) {
    if (EnumSettleType.WITHOUT_INVOICE != this.getSettleType()) {
      return;
    }
    UFDouble stlNum =
        (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCURSTOCKSETTLENUM);
    UFDouble price = MathTool.nvl(stlMny).div(stlNum, UFDouble.DEFAULT_POWER);
    bm.setValueAt(price, row, MatchMaterialVO.NPRICE);
  }

  private void stockStlNumChg(BillModel bm, int row) {
    // 计算本次结算金额，用于无发票结算
    this.calcSettleMny(bm, row);
    // 计算本次结算数量所占暂估金额
    this.calcEstMny(bm, row);
  }

}
