package nc.ui.pu.est.editor.body.before;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.est.entity.FeeEstVO;

import org.apache.commons.lang.StringUtils;

/**
 * ���õ��۱����ʱ༭ǰ����
 * 
 * @since 6.0
 * @version 2011-1-25 ����02:10:19
 * @author zhaoyha
 */
public class FeeExchgRate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel bcp = e.getBillCardPanel();
    String origCurr =
        (String) bcp.getBodyValueAt(e.getRow(), FeeEstVO.PK_ESTCURRENCY);
    String localCurr =
        (String) bcp.getBodyValueAt(e.getRow(), FeeEstVO.PK_LOCALCURRENCY);
    // �������ͬ���۱����ɱ༭
    if (StringUtils.isNotBlank(origCurr) && origCurr.equals(localCurr)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else {
      e.setReturnValue(Boolean.TRUE);
    }
  }

}
