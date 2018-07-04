package nc.ui.pu.m20.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m20.editor.card.afteredit.header.Bdirecttransit;
import nc.ui.pu.m20.editor.card.afteredit.header.BillDate;
import nc.ui.pu.m20.editor.card.afteredit.header.Bsctype;
import nc.ui.pu.m20.editor.card.afteredit.header.Project;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>请购单表头编辑事件的代理处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * <li>处理编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-24 下午05:00:09
 */
public class HanderAfterEditHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // 委外
    listenerMap.put(PraybillHeaderVO.BSCTYPE, new Bsctype());
    // 直运
    listenerMap.put(PraybillHeaderVO.BDIRECTTRANSIT, new Bdirecttransit());
    // 项目
    listenerMap.put(PraybillHeaderVO.CHPROJECTID, new Project());
    // 请购日期
    listenerMap.put(PraybillHeaderVO.DBILLDATE, new BillDate());

  }

}
