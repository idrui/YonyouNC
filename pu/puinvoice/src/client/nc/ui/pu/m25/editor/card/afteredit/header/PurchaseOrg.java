/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午04:56:50
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.rule.InvoiceQueryPriceHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购组织编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-31 下午04:56:50
 */
public class PurchaseOrg implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 1.过滤采购部门和人员参照
    String newValue = ValueUtils.getString(event.getValue());
    // 清空采购组织
    if (StringUtil.isEmptyWithTrim(newValue)) {
      return;
    }
    // 2.触发询价,针对表体所有行
    InvoiceQueryPriceHandler price =
        new InvoiceQueryPriceHandler(event.getBillCardPanel());
    // 远程调用合并
    price.prepareQueryPrice();
    price.handleQueryPrice();

  }

}
