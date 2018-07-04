/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 下午05:06:01
 */
package nc.ui.pu.m4t.editor.head.after;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m4t.rule.CostregionSetter;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>仓库
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 下午05:06:01
 */
public class Stordoc implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    new CostregionSetter(editor).setCostregion();
    // 结算利润中心设值逻辑是根据业务委托关系设置同采购订单表体结算利润中心，不需要编辑后事件
    // new PCCostregionSetter(editor).setPK_apliabcenter();
  }

}
