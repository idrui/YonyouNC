package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>是否赠品编辑后处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-23 上午11:25:24
 */
public class Blargess implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    UFBoolean blargess =
        (UFBoolean) card.getBodyValue(row, OrderItemVO.BLARGESS);

    if (blargess != null && !blargess.booleanValue()) {
      // 主单位原币含税净价
      UFDouble norigtaxnetprice =
          (UFDouble) card.getBodyValue(row, OrderItemVO.NORIGTAXNETPRICE);
      if (norigtaxnetprice != null
          && norigtaxnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        // 如果非赠品，主单位原币含税净价为0，将主单位原币含税净价和主单位原币含税单价设为空
        card.setBodyValue(row, OrderItemVO.NORIGTAXPRICE, null);
        card.setBodyValue(row, OrderItemVO.NORIGTAXNETPRICE, null);
      }

      event.getBillCardPanel().getBillModel().reCalcurateAll();
    }
  }

}
