package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>���Ƿ���Ʒ���ֶα༭�¼������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�༭ǰ�������Դ��������Ʒ�����Ƿ���Ʒ���ֶβ������޸�
 * <li>
 * <li>�༭�󣺸��ݡ��Ƿ���Ʒ���ֶε�ֵ��������Ʒ��������Ʒ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 ����01:25:25
 */
public class PresentFlag implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    BillCardPanel card = event.getBillCardPanel();

    // ������ε�������Ʒ���������޸ġ��Ƿ���Ʒ��
    boolean bpresentsource =
        ArriveClientUtil.getBooleanCellValue(card, event.getRow(),
            ArriveItemVO.BPRESENTSOURCE);
    if (bpresentsource) {
      event.setReturnValue(Boolean.FALSE);
    }
  }
}
