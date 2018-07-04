package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"费用结算"按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class FeeMatchAction extends MatchDefaultAction {

  private static final long serialVersionUID = -5667491871111125098L;

  public FeeMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEEMATCH);
    // this.setBtnName("费用结算");
    // this.setCode("btnFeeMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    // 结算用的环境变量，包含结算规则等
    this.getModel().getSettleEnvironment().setSettleType(EnumSettleType.FEE);

    // 初始化参与结算的VO信息
    this.getModel().initMatchVos();

  }

}
