package nc.ui.pu.m27.match.action;

import javax.swing.table.TableCellEditor;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleMatch;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.view.MatchProcessListPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.pu.m21.exception.AskPriceException;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.MatchValidationException;
import nc.vo.pu.m27.pub.SettleEnvironment.MatchExecType;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>执行模拟结算或真正结算
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class MatchExecutor {

  private MatchProcessListPanel listView;

  private MatchExecType mexecType;

  private MatchManageModel model;

  /**
   * @param model 匹配模型
   * @param listView 匹配界面的列表
   * @param mexecType 是模拟还是真正执行结算
   */
  public MatchExecutor(MatchManageModel model, MatchProcessListPanel listView,
      MatchExecType mexecType) {
    this.model = model;
    this.listView = listView;
    this.mexecType = mexecType;
  }

  public SettleBillVO[] executeMatch() {
    // 把匹配界面上的数据同步到Model中
    this.synchInvoicesAndStocks();
    // 进行结算
    return this.doMatch();
  }

  private SettleBillVO[] doMatch() {
    SettleBillVO[] settleBillVOs = null;
    // 发票、入库单
    InvoiceSettleVO[] invoices = this.getModel().getMatchInvoiceVOs();
    StockSettleVO[] stocks = this.getModel().getMatchStockVOs();

    // 折扣、费用
    FeeDiscountSettleVO[] fees = null;
    FeeDiscountSettleVO[] discounts = null;
    // 只有费用分摊后的才带走费用发票进行结算
    if (DistState.dist == this.getModel().getAppUiState().getFdstate()) {
      fees = this.getModel().getMatchFeeInvoices();
      discounts = this.getModel().getMatchDiscountInvoices();
    }

    this.getModel().getSettleEnvironment().setAllowStockBeyondInvoice(false);
    this.getModel().getSettleEnvironment().setInvoicePriceOverOder(false);
    boolean isContinue = true;
    while (isContinue) {
      isContinue = false;
      try {
        settleBillVOs = this.doMatch(invoices, stocks, fees, discounts);
      }
      catch (MatchValidationException e) {
        // "物料###：入库单本次结算数量大于发票的本次结算数量，继续本物料的手工结算吗？");
        int returnCode =
            MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0016")/*@res "提示信息"*/, e.getMessage());
        if (UIDialog.ID_YES == returnCode) {
          this.getModel().getSettleEnvironment()
              .setAllowStockBeyondInvoice(true);
          isContinue = true;
        }
      }
      // 存在发票本币无税单价超出订单本币无税净价容差控制的行，是否继续?
      catch (AskPriceException priceEx) {
        String msg = priceEx.getMessage();
        int ret = MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0016")/*@res "提示信息"*/, msg);
        if (ret == UIDialog.ID_YES) {
          this.getModel().getSettleEnvironment().setInvoicePriceOverOder(true);
          isContinue = true;
        }
      }
      catch (BusinessException busiExe) {
        ExceptionUtils.wrappException(busiExe);
      }
    }

    return settleBillVOs;
  }

  private SettleBillVO[] doMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount) throws BusinessException {
    ISettleMatch service = NCLocator.getInstance().lookup(ISettleMatch.class);
    EnumSettleType settleType =
        this.getModel().getSettleEnvironment().getSettleType();
    // 设置模拟结算还是真正结算
    this.getModel().getSettleEnvironment().setExecType(this.mexecType);
    // 同物料结算
    if (EnumSettleType.SAME_MATERIAL == settleType) {
      return service.sameMaterialMatch(voaInvoice, voaStock, voaFee,
          voaDiscount, this.getModel().getSettleEnvironment());
    }
    // 异物料结算
    else if (EnumSettleType.DIFFERENT_MATERIAL == settleType) {
      return service.differentMaterialMatch(voaInvoice, voaStock, voaFee,
          voaDiscount, this.getModel().getSettleEnvironment());
    }
    // 费用结算
    else if (EnumSettleType.FEE == settleType) {
      return service.feeMatch(voaInvoice, voaStock, voaFee, voaDiscount, this
          .getModel().getSettleEnvironment());
    }
    // 无发票结算
    else if (EnumSettleType.WITHOUT_INVOICE == settleType) {
      return service.withoutInvoiceMatch(voaInvoice, voaStock, voaFee,
          voaDiscount, this.getModel().getSettleEnvironment());
    }
    return null;
  }

  private MatchProcessListPanel getListView() {
    return this.listView;
  }

  private MatchManageModel getModel() {
    return this.model;
  }

  private void synchInvoicesAndStocks() {
    TableCellEditor tce =
        this.getListView().getBillListPanel().getHeadTable().getCellEditor();
    if (null != tce) {
      tce.stopCellEditing();
    }
    tce = this.getListView().getBillListPanel().getBodyTable().getCellEditor();
    if (null != tce) {
      tce.stopCellEditing();
    }
    MatchMaterialVO[] uiMatchVos =
        (MatchMaterialVO[]) this.getListView().getBillListPanel()
            .getHeadBillModel()
            .getBodyValueVOs(MatchMaterialVO.class.getName());
    MatchMaterialVO[] modelMatchVos = this.getModel().getMatchMaterialVOs();
    // 界面当前的货物发票和入库单数据同步到Model中
    InvoiceSettleVOUtil.synchInvoiceOfMatchMaterial(uiMatchVos, modelMatchVos);
    StockSettleVOUtil.synchStockOfMatchMaterial(uiMatchVos, modelMatchVos, this
        .getModel().getSettleEnvironment().getSettleType());

    // 同步当前Model中劳务类、折扣类发票的当前结算金额
    FeeDiscountSettleVO[] modelFees = this.getModel().getMatchFeeInvoices();
    FeeDiscountSettleVO[] modelDiscounts =
        this.getModel().getMatchDiscountInvoices();
    FeeDiscountSettleVO[] uiInvoices =
        (FeeDiscountSettleVO[]) this.getListView().getBillListPanel()
            .getBodyBillModel()
            .getBodyValueVOs(FeeDiscountSettleVO.class.getName());
    InvoiceSettleVOUtil.synchFeeAndDiscounts(uiInvoices, modelFees,
        modelDiscounts);
  }

}