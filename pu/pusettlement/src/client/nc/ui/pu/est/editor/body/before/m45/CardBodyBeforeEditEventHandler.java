package nc.ui.pu.est.editor.body.before.m45;

import java.util.Map;

import nc.ui.pu.est.editor.body.before.Ctaxcodeid;
import nc.ui.pu.est.editor.body.before.FeeExchgRate;
import nc.ui.pu.est.editor.body.before.Material;
import nc.ui.pu.est.editor.body.before.Ncaltaxmny;
import nc.ui.pu.est.editor.body.before.Supplier;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.est.entity.FeeEstVO;

/**
 * <p>
 * <b>表体编辑事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-06-17 下午05:00:09
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // 物料
    listenerMap.put(FeeEstVO.PK_FEEMATERIAL, new Material());
    // 折本汇率
    listenerMap.put(FeeEstVO.NESTEXCHGRATE, new FeeExchgRate());
    listenerMap.put(FeeEstVO.NCALTAXMNY, new Ncaltaxmny());
    // 税码
    listenerMap.put(FeeEstVO.CTAXCODEID, new Ctaxcodeid());
    // 供应商
    listenerMap.put(FeeEstVO.PK_SUPPLIER, new Supplier());
  }
}
