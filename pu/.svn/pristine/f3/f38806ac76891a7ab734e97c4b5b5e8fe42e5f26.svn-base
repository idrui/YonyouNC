/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-18 上午10:51:15
 */
package nc.ui.pu.m20.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>是否直运编辑后事件
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-18 上午10:51:15
 */
public class Bdirecttransit implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {

    if (!this.canSelectBdirecttransit(event)) {
      event.getBillCardPanel().getHeadItem(PraybillHeaderVO.BDIRECTTRANSIT)
          .setValue(UFBoolean.FALSE);
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0","04004020-0020")/*@res "直运和委外互斥，不能同时选择。"*/,
          event.getContext());
      return;
    }

  }

  private boolean canSelectBdirecttransit(CardHeadTailAfterEditEvent event) {
    Boolean bsctype =
        (Boolean) event.getBillCardPanel()
            .getHeadItem(PraybillHeaderVO.BSCTYPE).getValueObject();
    if ((null != bsctype) && bsctype.booleanValue()) {
      return false;
    }
    return true;
  }
}