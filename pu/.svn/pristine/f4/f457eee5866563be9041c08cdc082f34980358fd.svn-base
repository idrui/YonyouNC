/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午02:30:39
 */
package nc.ui.pu.est.editor.head.after.m45;

import java.util.Map;

import nc.ui.pu.est.editor.head.after.Cesttaxcodeid;
import nc.ui.pu.est.editor.head.after.Currency;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头的编辑后事件处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-28 下午02:30:39
 */
public class HeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.list.handler.AbstractListEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    listenerMap
        .put(PurchaseInEstHeaderVO.ONEBILLSELECT, new OneBillSelection());// 整单选择
    listenerMap.put(GoodsEstVO.PK_ESTCURRENCY, new Currency());// 币种改变
    listenerMap.put(GoodsEstVO.CESTTAXCODEID, new Cesttaxcodeid());
  }

}
