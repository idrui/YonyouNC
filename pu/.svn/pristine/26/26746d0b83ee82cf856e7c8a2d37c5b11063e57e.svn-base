/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午04:52:35
 */
package nc.ui.pu.m4t.editor.head;

import java.util.Map;

import nc.ui.pu.m4t.editor.head.before.Bizpsn;
import nc.ui.pu.m4t.editor.head.before.Dept;
import nc.ui.pu.m4t.editor.head.before.ExchangeRate;
import nc.ui.pu.m4t.editor.head.before.InitialEstTranstype;
import nc.ui.pu.m4t.editor.head.before.Managepsn;
import nc.ui.pu.m4t.editor.head.before.Purchaseorg;
import nc.ui.pu.m4t.editor.head.before.Stordoc;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单卡片界面表头编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午04:52:35
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    // 仓库
    listenerMap.put(InitialEstHeaderVO.PK_STORDOC, new Stordoc());
    // 采购员
    listenerMap.put(InitialEstHeaderVO.PK_BIZPSN, new Bizpsn());
    // 采购部门
    listenerMap.put(InitialEstHeaderVO.PK_DEPT_V, new Dept());
    // 保管员
    listenerMap.put(InitialEstHeaderVO.PK_MANAGEPSN, new Managepsn());
    // 折本汇率
    listenerMap.put(InitialEstHeaderVO.NEXCHANGERATE, new ExchangeRate());
    // 交易类型
    listenerMap.put(InitialEstHeaderVO.CTRANTYPEID, new InitialEstTranstype());
    // 采购组织
    listenerMap.put(InitialEstHeaderVO.PK_PURCHASEORG_V, new Purchaseorg());

  }

}
