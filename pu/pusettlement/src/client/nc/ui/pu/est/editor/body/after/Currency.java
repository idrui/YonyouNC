/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-18 下午03:59:33
 */
package nc.ui.pu.est.editor.body.after;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.util.EstCurrencyUtil;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-18 下午03:59:33
 */
public class Currency implements ICardBodyAfterEditEventListener {
  private IRelationForItems relaitems;

  /**
   * Currency 的构造子
   * 
   * @param relaitems
   */
  public Currency(IRelationForItems relaitems) {
    this.relaitems = relaitems;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    String pk_currency = (String) event.getValue();
    if (StringUtil.isEmptyWithTrim(pk_currency)) {
      return;
    }
    int row = event.getRow();
    BillCardPanel bcp = event.getBillCardPanel();
    IDataSetForCal dataset =
        new BillModelDataSet(bcp.getBillModel(), row, this.relaitems);
    String pk_group = dataset.getPk_group();
    EstRelationCalcUtil calcUtil = new EstRelationCalcUtil(pk_group);
    EstCurrencyUtil.feeCurrencyChange(dataset, calcUtil,
        PricePriority.TAXPRICE_PRIOR_TO_PRICE);// 需求确认， 默认使用含税优先
    this.resetOrigValue(bcp, row);// 重新设置原币金额，以生效精度
  }

  private void resetOrigValue(BillCardPanel bcp, int row) {
    String[] keys = EstVOUtil.getFeeEstScaleKeyInfo().getOmnyKeys();
    for (String key : keys) {
      Object oldValue = bcp.getBodyValueAt(row, key);
      bcp.setBodyValueAt(oldValue, row, key);
    }
  }

}
