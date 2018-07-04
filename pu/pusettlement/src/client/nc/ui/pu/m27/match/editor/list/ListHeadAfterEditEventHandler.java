package nc.ui.pu.m27.match.editor.list;

import java.util.Map;

import nc.ui.pu.m27.match.editor.list.afteredit.CurrentInvoiceSettleNum;
import nc.ui.pu.m27.match.editor.list.afteredit.CurrentStockSettleNum;
import nc.ui.pu.m27.match.editor.list.afteredit.ReasonWasteNum;
import nc.ui.pu.m27.match.editor.list.afteredit.StockSettleMny;
import nc.ui.pu.m27.match.editor.list.afteredit.StockSettlePrice;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�б��ͷ�༭���¼��Ĵ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-29 ����03:08:50
 */
public class ListHeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {
  private MatchManageModel model;

  public MatchManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(ListHeadAfterEditEvent e) {
    // �Ƚ�model�ŵ�contextObject���Ա����ʹ��
    e.setContextObject(this.model);
    super.handleAppEvent(e);
  }

  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    // ���η�Ʊ���������ı༭���¼�
    listenerMap.put(MatchMaterialVO.NCURINVOICESETTLENUM,
        new CurrentInvoiceSettleNum());
    // ���������������ı༭���¼�
    listenerMap.put(MatchMaterialVO.NCURSTOCKSETTLENUM,
        new CurrentStockSettleNum());
    // ������ĵı༭���¼�
    listenerMap.put(MatchMaterialVO.NREASONWASTENUM, new ReasonWasteNum());
    // �༭��ⵥ���㵥��
    listenerMap.put(MatchMaterialVO.NPRICE, new StockSettlePrice());
    // �༭��ⵥ���ν�����
    listenerMap.put(MatchMaterialVO.NCURSEETLEMNY, new StockSettleMny());
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

}
