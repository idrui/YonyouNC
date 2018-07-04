package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

public class Reporter implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel card = event.getBillCardPanel();
    // 报告人
    String pk_group =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_GROUP);
    CardPanelValueUtils util = new CardPanelValueUtils(card);
    UIRefPane refPane =
        (UIRefPane) util.getBodyItem(ArriveItemVO.CREPORTERID).getComponent();
    // 按集团过滤
    if (pk_group != null) {
      FilterPsndocRefUtils filter =
          FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(refPane);
      filter.filterItemRefByOrg(pk_group);
    }
    event.setReturnValue(Boolean.TRUE);
  }

}
