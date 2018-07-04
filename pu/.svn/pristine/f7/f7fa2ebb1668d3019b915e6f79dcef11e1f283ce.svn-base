package nc.ui.pu.m21.action.status.sendout;

import nc.ui.pu.m21.action.status.AbstractStatusQueryAction;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * @since 6.0
 * @version 2011-1-26 下午01:02:35
 * @author wuxla
 */

public class SendoutQueryAction extends AbstractStatusQueryAction {

  private static final long serialVersionUID = -1769382731706782246L;

  @Override
  protected int getOnwayStatusInt() {
    return ((Integer) OnwayStatus.STATUS_SENDOUT.value()).intValue();
  }

  @Override
  protected String getOnwayStatusStr() {
    return OnwayStatusQryEnum.bissendout.code();
  }

  @Override
  protected void setHiddenItem(BillCardPanel cardpanel, boolean isDone) {
    // 已发货数量
    BillItem ownumItem = cardpanel.getBodyItem("nonwaynum");
    // ownumItem.setShareTableCode("pk_order_b");
    ownumItem.setShow(!isDone);
    // 已发货金额
    BillItem owmnyItem = cardpanel.getBodyItem("nonwaymny");
    // owmnyItem.setShareTableCode("pk_order_b");
    owmnyItem.setShow(!isDone);
    // 本次发货数量
    BillItem nsendoutnum = cardpanel.getBodyItem("nsendoutnum");
    // nsendoutnum.setShareTableCode("pk_order_b");
    nsendoutnum.setShow(isDone);
    // 本次发货金额
    BillItem nstatusmny = cardpanel.getBodyItem("nstatusmny");
    // nstatusmny.setShareTableCode("pk_order_b");
    nstatusmny.setShow(isDone);
  }
}
