/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.afteredit.header.ArriveDate;
import nc.ui.pu.m25.editor.card.afteredit.header.BillDate;
import nc.ui.pu.m25.editor.card.afteredit.header.CurrencyType;
import nc.ui.pu.m25.editor.card.afteredit.header.ExchangeRate;
import nc.ui.pu.m25.editor.card.afteredit.header.FreeCust;
import nc.ui.pu.m25.editor.card.afteredit.header.GroupAndGlobalExchangeRate;
import nc.ui.pu.m25.editor.card.afteredit.header.InvoiceTranstype;
import nc.ui.pu.m25.editor.card.afteredit.header.PurDept;
import nc.ui.pu.m25.editor.card.afteredit.header.PurchaseOrg;
import nc.ui.pu.m25.editor.card.afteredit.header.ReceCountry;
import nc.ui.pu.m25.editor.card.afteredit.header.SendCountry;
import nc.ui.pu.m25.editor.card.afteredit.header.StockOrg;
import nc.ui.pu.m25.editor.card.afteredit.header.Supplier;
import nc.ui.pu.m25.editor.card.afteredit.header.TaxCountry;
import nc.ui.pu.m25.editor.card.afteredit.header.TaxRate;
import nc.ui.pu.m25.editor.card.afteredit.header.TaxType;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头表体编辑后事件分发器</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 上午09:48:42
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler
   * #registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    listenerMap.put(InvoiceHeaderVO.DBILLDATE, new BillDate());// 发票日期
    listenerMap.put(InvoiceHeaderVO.DARRIVEDATE, new ArriveDate());// 票到日期
    listenerMap.put(InvoiceHeaderVO.CORIGCURRENCYID, new CurrencyType());// 币种(原币)
    listenerMap.put(InvoiceHeaderVO.NEXCHANGERATE, new ExchangeRate());// 折本汇率
    listenerMap.put(InvoiceHeaderVO.PK_FREECUST, new FreeCust());// 散户
    listenerMap.put(InvoiceHeaderVO.PK_DEPT_V, new PurDept());// 采购部门
    listenerMap.put(InvoiceHeaderVO.PK_SUPPLIER, new Supplier());// 供应商
    listenerMap.put(InvoiceHeaderVO.NTAXRATEH, new TaxRate());// 税率
    listenerMap.put(InvoiceHeaderVO.FTAXTYPEFLAGH, new TaxType());// 扣税类别
    listenerMap.put(InvoiceHeaderVO.PK_STOCKORG_V, new StockOrg());// 库存组织
    listenerMap.put(InvoiceHeaderVO.PK_PURCHASEORG_V, new PurchaseOrg());// 采购组织
    listenerMap.put(InvoiceHeaderVO.NGROUPEXCHGRATE,
        new GroupAndGlobalExchangeRate());// 集团本位币汇率
    listenerMap.put(InvoiceHeaderVO.NGLOBALEXCHGRATE,
        new GroupAndGlobalExchangeRate());// 全局本位币汇率
    listenerMap.put(InvoiceHeaderVO.CTRANTYPEID, new InvoiceTranstype());// 交易类型
    listenerMap.put(InvoiceHeaderVO.CRECECOUNTRYID, new ReceCountry());// 收货国
    listenerMap.put(InvoiceHeaderVO.CSENDCOUNTRYID, new SendCountry());// 发货国
    listenerMap.put(InvoiceHeaderVO.CTAXCOUNTRYID, new TaxCountry());// 报税国

    // add by liangchen1 NC631进出口采购需求，为继承该类的子类提供规则扩展
    this.registerExpandEventListener(listenerMap);
  }

  /**
   * 规则扩展
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // do nothing
  }

}
