package nc.ui.pu.m21.print;

import java.awt.Container;

import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.print.combination.CombinedResultDialog;
import nc.ui.pubapp.uif2app.view.value.BillCardPanelMetaDataFullValueAdapter;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;

/**
 * 采购订单合并结果对话框
 * 采购订单合并显示，显示付款协议页签
 * 
 * @since 6.5
 * @version 2015-7-14 下午4:18:30
 * @author luojw
 */
public class OrderCombinedResultDialog extends CombinedResultDialog{

  private static final long serialVersionUID = 7068344108115090919L;
  
  /** 付款协议表体数据 */
  private OrderPaymentVO[] payments ;
  
  public OrderCombinedResultDialog(Container parent) {
    super(parent);
  }
  
  public void setPayments(OrderPaymentVO[] payments) {
    this.payments = payments;
  }
  
  @Override
  public void setData(AggregatedValueObject bill) {
    if (getCardPanelInit() != null) {
      getCardPanelInit().intCardPanel(getBillCardPanel());
    }
    OrderVO billVO = (OrderVO)bill; 
    billVO.setChildren(OrderPaymentVO.class, this.payments);
    this.setBillVO(billVO);
    
    if (this.isMarClassShow()) {
      this.getBillCardPanel().getBillData()
          .setHeaderValueVO(billVO.getParentVO());
      this.getBillCardPanel().getBillData()
          .setBodyValueVO(billVO.getChildrenVO());
      // 给付款协议页签加入数据
      this.getBillCardPanel().getBillModel(OrderPaymentVO.TABSNAME).setBodyDataVO(this.payments);
      this.getBillCardPanel().getBillData().loadLoadHeadRelation();
      this.getBillCardPanel().getBillData().getBillModel().loadLoadRelationItemValue();
      this.getBillCardPanel().getBillModel(OrderPaymentVO.TABSNAME).loadLoadRelationItemValue();
      this.execLoadFormula();
    } else {
      BillCardPanelMetaDataFullValueAdapter valueSetter = new BillCardPanelMetaDataFullValueAdapter();
      valueSetter.setComponent(this.getBillCardPanel());
      valueSetter.setValue(billVO);
    }
  }
  
  /**
   * 执行显示公式 创建日期：(2003-9-12 16:10:25)
   */
  private void execLoadFormula() {
    this.getBillCardPanel().execHeadTailLoadFormulas();

    // 执行表体公式
    String[] tabcodes = this.getBillCardPanel().getBillData()
        .getBodyBaseTableCodes();
    if (tabcodes != null && tabcodes.length > 0) {
      for (String tabcode : tabcodes) {
        BillModel billModel = this.getBillCardPanel().getBillModel(
            tabcode);
        if (billModel != null)
          billModel.execLoadFormula();
      }
    }
  }

}
