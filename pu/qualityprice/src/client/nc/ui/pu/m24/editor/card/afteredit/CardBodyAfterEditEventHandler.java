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

    // �۸���㵥����������Ƚ����⣬ֻ�б��Һ�˰���ۺͱ�����˰���ۿ��Ա༭���������������Լ�ʵ�֣�û����ƽ̨���㷨��
    RelationCalculate relationCalculate = new RelationCalculate();
    listenerMap.put(PricestlItemVO.NTAXPRICE, relationCalculate);
    listenerMap.put(PricestlItemVO.NPRICE, relationCalculate);
  }
}
