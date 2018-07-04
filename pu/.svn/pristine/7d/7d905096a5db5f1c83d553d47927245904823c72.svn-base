/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"无发票结算"按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class WithoutInvoiceMatchAction extends MatchDefaultAction {

  private static final long serialVersionUID = -5667491871111125098L;

  public WithoutInvoiceMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_WITHOUTINVOICEMATCH);
    // this.setBtnName("无发票结算");
    // this.setCode("btnWithoutInvoiceMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 结算类型
    this.getModel().getSettleEnvironment()
        .setSettleType(EnumSettleType.WITHOUT_INVOICE);

    // 初始化参与结算的VO信息
    this.getModel().initMatchVos();
  }

}
