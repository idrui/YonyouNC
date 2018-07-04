package nc.ui.pu.m21.view.sideform;

import java.awt.event.ActionEvent;

import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理"查看全部"按钮
 * </ul>
 * <p>
 * 
 * @since 6.0
 * @version 2011-4-20 上午08:33:38
 * @author xihy1
 */

public class ShowAllAction extends NCAction {

  private static final long serialVersionUID = -2918170373641812683L;

  private OrderSideFormMediator mediator;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (null == this.mediator || this.mediator.getBillCardPanel() == null
        || this.mediator.getCurrentRow() < 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0083")/*
                                                                   * @res
                                                                   * "请选一个订单行！"
                                                                   */);
    }
    this.mediator.displayAll();
  }

  public OrderSideFormMediator getMediator() {
    return this.mediator;
  }

  public void setMediator(OrderSideFormMediator mediator) {
    this.mediator = mediator;
  }

}
