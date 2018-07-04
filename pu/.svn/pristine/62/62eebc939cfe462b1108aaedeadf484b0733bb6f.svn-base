package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * 表体项目编辑前处理类
 * 
 * @since 6.0
 * @version 2010-11-10 下午02:34:10
 * @author hanbin
 */
public class Project implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // 库存组织
    String stockOrg =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_ORG);

    // 项目(按库存组织过滤)
    BillItem cproject = card.getBodyItem(ArriveItemVO.CPROJECTID);
    new FilterProjectRefUtils(cproject).filterItemRefByOrg(stockOrg);

    e.setReturnValue(Boolean.TRUE);
  }
}
