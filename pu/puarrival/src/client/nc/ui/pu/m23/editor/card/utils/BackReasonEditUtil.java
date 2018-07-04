package nc.ui.pu.m23.editor.card.utils;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ���˻����ɡ��༭ǰ�¼�����ֻ���˻�ʱ�ſ��Խ��б༭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-5 ����03:43:07
 */
public class BackReasonEditUtil {

  private BillCardPanel card;

  public BackReasonEditUtil(BillCardPanel card) {
    this.card = card;
  }

  public boolean getEnabled() {
    return ArriveClientUtil.getBooleanHeaderValue(this.card,
        ArriveHeaderVO.BISBACK);
  }

  public void setEnabled() {
    // �Ƿ��˻�
    String key = ArriveHeaderVO.BISBACK;
    boolean isback = ArriveClientUtil.getBooleanHeaderValue(this.card, key);

    this.card.getHeadItem(ArriveHeaderVO.VBACKREASON).setEnabled(isback);
    this.card.getBodyItem(ArriveItemVO.VBACKREASONB).setEnabled(isback);
  }
}
