/**
 * 
 */
package nc.ui.pu.m25.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.beforeedit.header.AccountBank;
import nc.ui.pu.m25.editor.card.beforeedit.header.BizPerson;
import nc.ui.pu.m25.editor.card.beforeedit.header.Dept;
import nc.ui.pu.m25.editor.card.beforeedit.header.EditableByVAT;
import nc.ui.pu.m25.editor.card.beforeedit.header.ExchangeRate;
import nc.ui.pu.m25.editor.card.beforeedit.header.FreeCust;
import nc.ui.pu.m25.editor.card.beforeedit.header.InvoiceTranstype;
import nc.ui.pu.pub.editor.card.beforeedit.GlobalExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.GroupExchangeRate;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头编辑前事件注册
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 上午09:50:29
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler
   * #registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    listenerMap.put(InvoiceHeaderVO.CTRANTYPEID, new InvoiceTranstype());// 发票类型
    listenerMap.put(InvoiceHeaderVO.PK_BANKACCBAS, new AccountBank());// 银行账户
    listenerMap.put(InvoiceHeaderVO.PK_BIZPSN, new BizPerson());// 采购员
    listenerMap.put(InvoiceHeaderVO.PK_DEPT_V, new Dept());// 部门
    listenerMap.put(InvoiceHeaderVO.NEXCHANGERATE, new ExchangeRate());// 折本汇率
    listenerMap.put(InvoiceHeaderVO.NGLOBALEXCHGRATE, new GlobalExchangeRate());// 全局本位币汇率
    listenerMap.put(InvoiceHeaderVO.NGROUPEXCHGRATE, new GroupExchangeRate());// 集团本位币汇率
    listenerMap.put(InvoiceHeaderVO.PK_FREECUST, new FreeCust());// 散户
    // 发货国/地区
    listenerMap.put(InvoiceHeaderVO.CSENDCOUNTRYID, new EditableByVAT());
    // 收货国/地区
    listenerMap.put(InvoiceHeaderVO.CRECECOUNTRYID, new EditableByVAT());
    // 报税国/地区
    listenerMap.put(InvoiceHeaderVO.CTAXCOUNTRYID, new EditableByVAT());

    // add by liangchen1 NC631进出口采购需求，为继承该类的子类提供规则扩展
    this.registerExpandEventListener(listenerMap);

  }

  /**
   * 规则扩展
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    // do nothing
  }

}
