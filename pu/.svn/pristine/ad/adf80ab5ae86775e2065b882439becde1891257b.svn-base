package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>同物料结算的按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 下午02:07:28
 */
public class SameMaterialMatchAction extends MatchDefaultAction {
  private static final long serialVersionUID = -1081048528627323723L;

  public SameMaterialMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_SAMEMATERIELMATCH);
    // this.setBtnName("同物料结算");
    // this.setCode("btnSameMaterielMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 结算类型
    this.getModel().getSettleEnvironment()
        .setSettleType(EnumSettleType.SAME_MATERIAL);

    // 初始化参与结算的VO信息
    this.getModel().initMatchVos();

  }

}
