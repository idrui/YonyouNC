package nc.ui.pu.m24.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m24.editor.card.afteredit.body.RelationCalculate;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m24.entity.PricestlItemVO;

public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {

    // 价格结算单的联动计算比较特殊，只有本币含税单价和本币无税单价可以编辑，所以联动计算自己实现，没有走平台的算法。
    RelationCalculate relationCalculate = new RelationCalculate();
    listenerMap.put(PricestlItemVO.NTAXPRICE, relationCalculate);
    listenerMap.put(PricestlItemVO.NPRICE, relationCalculate);
  }
}
