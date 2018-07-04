package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>���Ƿ���Ʒ���ֶα༭�¼������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
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
public class PresentFlag implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel card = e.getBillCardPanel();

    boolean bpresent =
        ArriveClientUtil.getBooleanCellValue(card, e.getRow(),
            ArriveItemVO.BPRESENT);
    if (bpresent) {
      // ��Ʒ���� = ��������
      Object obj = card.getBodyValueAt(e.getRow(), ArriveItemVO.NASTNUM);
      card.setBodyValueAt(obj, e.getRow(), ArriveItemVO.NPRESENTASTNUM);

      // ��Ʒ������ = ����������
      obj = card.getBodyValueAt(e.getRow(), ArriveItemVO.NNUM);
      card.setBodyValueAt(obj, e.getRow(), ArriveItemVO.NPRESENTNUM);
    }
    else {
      // �����Ʒ��������Ʒ������
      ArriveClientUtil.clearBodyCellValues(card, e.getRow(), new String[] {
        ArriveItemVO.NPRESENTASTNUM, ArriveItemVO.NPRESENTNUM
      });
    }
  }
}
