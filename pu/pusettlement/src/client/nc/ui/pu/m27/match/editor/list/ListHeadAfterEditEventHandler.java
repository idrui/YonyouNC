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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>列表表头编辑后事件的处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-29 下午03:08:50
 */
public class ListHeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {
  private MatchManageModel model;

  public MatchManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(ListHeadAfterEditEvent e) {
    // 先将model放到contextObject中以便后面使用
    e.setContextObject(this.model);
    super.handleAppEvent(e);
  }

  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    // 本次发票结算数量的编辑后事件
    listenerMap.put(MatchMaterialVO.NCURINVOICESETTLENUM,
        new CurrentInvoiceSettleNum());
    // 本次入库结算数量的编辑后事件
    listenerMap.put(MatchMaterialVO.NCURSTOCKSETTLENUM,
        new CurrentStockSettleNum());
    // 合理损耗的编辑后事件
    listenerMap.put(MatchMaterialVO.NREASONWASTENUM, new ReasonWasteNum());
    // 编辑入库单结算单价
    listenerMap.put(MatchMaterialVO.NPRICE, new StockSettlePrice());
    // 编辑入库单本次结算金额
    listenerMap.put(MatchMaterialVO.NCURSEETLEMNY, new StockSettleMny());
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

}
