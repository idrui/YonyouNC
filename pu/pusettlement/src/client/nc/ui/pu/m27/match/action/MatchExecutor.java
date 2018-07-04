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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ִ��ģ��������������
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 ����02:05:38
 */
public class MatchExecutor {

  private MatchProcessListPanel listView;

  private MatchExecType mexecType;

  private MatchManageModel model;

  /**
   * @param model ƥ��ģ��
   * @param listView ƥ�������б�
   * @param mexecType ��ģ�⻹������ִ�н���
   */
  public MatchExecutor(MatchManageModel model, MatchProcessListPanel listView,
      MatchExecType mexecType) {
    this.model = model;
    this.listView = listView;
    this.mexecType = mexecType;
  }

  public SettleBillVO[] executeMatch() {
    // ��ƥ������ϵ�����ͬ����Model��
    this.synchInvoicesAndStocks();
    // ���н���
    return this.doMatch();
  }

  private SettleBillVO[] doMatch() {
    SettleBillVO[] settleBillVOs = null;
    // ��Ʊ����ⵥ
    InvoiceSettleVO[] invoices = this.getModel().getMatchInvoiceVOs();
    StockSettleVO[] stocks = this.getModel().getMatchStockVOs();

    // �ۿۡ�����
    FeeDiscountSettleVO[] fees = null;
    FeeDiscountSettleVO[] discounts = null;
    // ֻ�з��÷�̯��ĲŴ��߷��÷�Ʊ���н���
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
        // "����###����ⵥ���ν����������ڷ�Ʊ�ı��ν������������������ϵ��ֹ�������");
        int returnCode =
            MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0016")/*@res "��ʾ��Ϣ"*/, e.getMessage());
        if (UIDialog.ID_YES == returnCode) {
          this.getModel().getSettleEnvironment()
              .setAllowStockBeyondInvoice(true);
          isContinue = true;
        }
      }
      // ���ڷ�Ʊ������˰���۳�������������˰�����ݲ���Ƶ��У��Ƿ����?
      catch (AskPriceException priceEx) {
        String msg = priceEx.getMessage();
        int ret = MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0016")/*@res "��ʾ��Ϣ"*/, msg);
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
    // ����ģ����㻹����������
    this.getModel().getSettleEnvironment().setExecType(this.mexecType);
    // ͬ���Ͻ���
    if (EnumSettleType.SAME_MATERIAL == settleType) {
      return service.sameMaterialMatch(voaInvoice, voaStock, voaFee,
          voaDiscount, this.getModel().getSettleEnvironment());
    }
    // �����Ͻ���
    else if (EnumSettleType.DIFFERENT_MATERIAL == settleType) {
      return service.differentMaterialMatch(voaInvoice, voaStock, voaFee,
          voaDiscount, this.getModel().getSettleEnvironment());
    }
    // ���ý���
    else if (EnumSettleType.FEE == settleType) {
      return service.feeMatch(voaInvoice, voaStock, voaFee, voaDiscount, this
          .getModel().getSettleEnvironment());
    }
    // �޷�Ʊ����
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
    // ���浱ǰ�Ļ��﷢Ʊ����ⵥ����ͬ����Model��
    InvoiceSettleVOUtil.synchInvoiceOfMatchMaterial(uiMatchVos, modelMatchVos);
    StockSettleVOUtil.synchStockOfMatchMaterial(uiMatchVos, modelMatchVos, this
        .getModel().getSettleEnvironment().getSettleType());

    // ͬ����ǰModel�������ࡢ�ۿ��෢Ʊ�ĵ�ǰ������
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