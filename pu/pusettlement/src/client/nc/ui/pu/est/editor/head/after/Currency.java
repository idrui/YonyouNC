/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-13 下午01:18:52
 */
package nc.ui.pu.est.editor.head.after;

import nc.ui.pu.est.util.EstScaleUtil;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.est.util.EstCurrencyUtil;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.data.IDataSetForCal;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>货物暂估，币种修改后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-13 下午01:18:52
 */
public class Currency extends EstHeadAfterEditHandler {

  private void resetOrigValue(BillListPanel blp, int row) {
    String[] keys = EstVOUtil.getGoodsEstScaleKeyInfo().getOmnyKeys();
    for (String key : keys) {
      Object oldValue = blp.getHeadBillModel().getValueAt(row, key);
      blp.getHeadBillModel().setValueAt(oldValue, row, key);
    }
  }

  @Override
  protected void realAfterEdit(ListHeadAfterEditEvent event, PricePriority pp,
      EstRelationCalcUtil calUtil, IDataSetForCal dataset) {
    // mengjian by 2014-05-28
    EstScaleUtil util =
        new EstScaleUtil(event.getBillListPanel(),
            EstVOUtil.getGoodsEstScaleKeyInfo(), AppContext.getInstance()
                .getPkGroup());
    util.initGoodsScale();

    EstCurrencyUtil.goodsCurrencyChange(event.getKey(), dataset, calUtil, pp);
    this.resetOrigValue(event.getBillListPanel(), event.getRow());// 重新设置原币金额，
                                                                  // 以生效精度
  }

}
