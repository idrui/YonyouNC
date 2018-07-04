package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.m23.editor.card.utils.CalculateAstNum;
import nc.ui.pu.m23.editor.card.utils.ChangeRateUtil;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>�ջ��ֿ�༭�¼������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�༭��
 * <li>���á��Ƿ�̶������ʡ����������ʡ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 ����01:25:25
 */
public class AstUnit implements ICardBodyAfterEditEventListener {

  private AbstractRelationCalculateListener calculate;

  public AstUnit(AbstractRelationCalculateListener calculateListener) {
    this.calculate = calculateListener;
  }

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel card = event.getBillCardPanel();
    // ���á��Ƿ�̶������ʡ����������ʡ�
    ChangeRateUtil.setChgRateAndFixedFlag(card, event.getRow());

    // ҵ��λ�޸ĺ󣬻����ʻ�䣬��Ҫ�޸�
    this.calculate.calculate(card, event.getRow(), ArriveItemVO.VCHANGERATE);
    // ���¸���������������
    new CalculateAstNum(card, event.getRow()).calculateAstNum();
  }
}
