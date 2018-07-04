package nc.ui.pu.m4t.editor.body.before;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ڳ��ݹ����༭ǰ�����ж��ֶ��Ƿ�ɱ༭
 * 
 * @since 6.0
 * @version 2012-2-13 ����02:51:40
 * @author wuxla
 */
public class EditableByVAT implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String csourcetypecode =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.CSOURCETYPECODE);
    if (POBillType.Order.getCode().equals(csourcetypecode)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);
  }
}
