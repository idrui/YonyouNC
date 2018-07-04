package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * ����ֿ�
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-21 ����01:18:24
 */
public class ReqStock implements ICardBodyAfterEditEventListener {
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // ���û����ʺ�����
    BillCardPanel card = event.getBillCardPanel();
    int row = event.getRow();
    String reqstor =
        (String) card.getBodyValueAt(row, PraybillItemVO.PK_REQSTOR);
    if (StringUtil.isEmptyWithTrim(reqstor)) {
      return;
    }
    card.getBillModel().execLoadFormulaByKey("pk_reqstor.storaddr");
  }
}
