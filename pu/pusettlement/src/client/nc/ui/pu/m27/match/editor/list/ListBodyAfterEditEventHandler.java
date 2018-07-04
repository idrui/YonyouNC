package nc.ui.pu.m27.match.editor.list;

import java.util.Map;

import nc.ui.pu.m27.match.editor.list.afteredit.FeeInvoiceSettleMny;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.pub.editor.list.handler.AbstractListBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent;
import nc.vo.pu.m25.settle.InvoiceSettleVO;

/**
 * 列表表体编辑后事件的处理类(费用及折扣发票的编辑后处理)
 * 
 * @since 6.0
 * @version 2011-2-8 下午10:54:38
 * @author zhaoyha
 */
public class ListBodyAfterEditEventHandler extends
    AbstractListBodyAfterEditEventHandler {
  private MatchManageModel model;

  public MatchManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(ListBodyAfterEditEvent e) {
    // 先将model放到contextObject中以便后面使用
    e.setContextObject(this.model);
    super.handleAppEvent(e);
  }

  @Override
  public void registerEventListener(
      Map<String, IListBodyAfterEditEventListener> listenerMap) {
    // 费用发票本次结算金额
    listenerMap.put(InvoiceSettleVO.NCURRENTINVOICESETTLEMNY,
        new FeeInvoiceSettleMny());
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

}
