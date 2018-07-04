package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterRackRefUtils;
import nc.vo.pu.m23.entity.ArriveItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * 货位按仓库过滤
 * 
 * @since 6.0
 * @version 2011-3-4 下午06:09:41
 * @author yinfy
 */

public class Rack implements ICardBodyBeforeEditEventListener {
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // 对应采购订单表体行收货仓库
    String pk_receivestore =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.PK_RECEIVESTORE);
    if (!StringUtils.isEmpty(pk_receivestore)) {
      // 货位(按收货仓库过滤)
      UIRefPane rackRefPane =
          (UIRefPane) card.getBodyItem(ArriveItemVO.PK_RACK).getComponent();
      new FilterRackRefUtils(rackRefPane).filterByWarehouse(pk_receivestore);
    }
  }
}
