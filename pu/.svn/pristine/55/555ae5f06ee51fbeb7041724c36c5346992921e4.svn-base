package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleMatch;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.exception.AskPriceException;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动结算的按钮事件
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 下午02:07:28
 */
public class AutoMatchAction extends MatchDefaultAction {
  private static final long serialVersionUID = -1081048528627323723L;

  public AutoMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_AUTOMATCH);
    // this.setBtnName("自动结算");
    // this.setCode("btnAutoMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 结算类型
    this.getModel().getSettleEnvironment()
        .setSettleType(EnumSettleType.UI_AUTO);

    // 准备进行结算的数据
    InvoiceSettleVO[] invoices = this.getModel().getQueryInvoiceVOs();
    StockSettleVO[] stocks = this.getModel().getQueryStockVOs();
    FeeDiscountSettleVO[] fees = this.getModel().getQueryFeeInvoices();
    FeeDiscountSettleVO[] discounts =
        this.getModel().getQueryDiscountInvoices();
    if (ArrayUtils.isEmpty(invoices) && ArrayUtils.isEmpty(stocks)
        && ArrayUtils.isEmpty(fees) && ArrayUtils.isEmpty(discounts)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0015")/*@res "无任何可结算入库单和采购发票！"*/);
    }
    SettleBillVO[] settleBills = null;
    boolean isContinue = true;
    this.getModel().getSettleEnvironment().setInvoicePriceOverOder(false);
    while (isContinue) {
      isContinue = false;
      try {
        // 进行结算
        settleBills = this.doMatch(invoices, stocks, fees, discounts);
      }
      catch (AskPriceException priceEx) {
        String msg = priceEx.getMessage();
        int ret = MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0016")/*@res "提示信息"*/, msg);
        if (ret == UIDialog.ID_YES) {
          this.getModel().getSettleEnvironment().setInvoicePriceOverOder(true);
          isContinue = true;
        }
      }
    }
    // 把结算后的单据设置到模型中
    this.getModel().initSettleBillVOs(settleBills);
  }

  private SettleBillVO[] doMatch(InvoiceSettleVO[] invoices,
      StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts) throws BusinessException {
    ISettleMatch service = NCLocator.getInstance().lookup(ISettleMatch.class);
    return service.autoMatch(invoices, stocks, fees, discounts, this.getModel()
        .getSettleEnvironment());
  }

}