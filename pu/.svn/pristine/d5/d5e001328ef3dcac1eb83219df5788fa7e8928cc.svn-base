package nc.ui.pu.m23.editor.card.utils;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头“退货理由”编辑前事件处理，只有退货时才可以进行编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-5 下午03:43:07
 */
public class BackReasonEditUtil {

  private BillCardPanel card;

  public BackReasonEditUtil(BillCardPanel card) {
    this.card = card;
  }

  public boolean getEnabled() {
    return ArriveClientUtil.getBooleanHeaderValue(this.card,
        ArriveHeaderVO.BISBACK);
  }

  public void setEnabled() {
    // 是否退货
    String key = ArriveHeaderVO.BISBACK;
    boolean isback = ArriveClientUtil.getBooleanHeaderValue(this.card, key);

    this.card.getHeadItem(ArriveHeaderVO.VBACKREASON).setEnabled(isback);
    this.card.getBodyItem(ArriveItemVO.VBACKREASONB).setEnabled(isback);
  }
}
