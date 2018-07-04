/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午04:56:37
 */
package nc.ui.pu.m4t.editor.head;

import java.util.Map;

import nc.ui.pu.m4t.editor.head.after.BillDate;
import nc.ui.pu.m4t.editor.head.after.Corigcurrencyid;
import nc.ui.pu.m4t.editor.head.after.Dept;
import nc.ui.pu.m4t.editor.head.after.ExchangeRate;
import nc.ui.pu.m4t.editor.head.after.InitialEstTranstype;
import nc.ui.pu.m4t.editor.head.after.Stockorg;
import nc.ui.pu.m4t.editor.head.after.Stordoc;
import nc.ui.pu.m4t.editor.head.after.Supplier;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片表头编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午04:56:37
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // 币种
    listenerMap.put(InitialEstHeaderVO.CORIGCURRENCYID, new Corigcurrencyid());
    // 折本汇率
    listenerMap.put(InitialEstHeaderVO.NEXCHANGERATE, new ExchangeRate());
    // 采购部门
    listenerMap.put(InitialEstHeaderVO.PK_DEPT, new Dept());
    // 仓库
    listenerMap.put(InitialEstHeaderVO.PK_STORDOC, new Stordoc());
    // 单据日期
    listenerMap.put(InitialEstHeaderVO.DBILLDATE, new BillDate());
    // 库存组织
    listenerMap.put(InitialEstHeaderVO.PK_STOCKORG_V, new Stockorg());
    // 供应商
    listenerMap.put(InitialEstHeaderVO.PK_SUPPLIER, new Supplier());
    // 交易类型
    listenerMap.put(InitialEstHeaderVO.CTRANTYPEID, new InitialEstTranstype());
  }

}
