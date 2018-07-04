package nc.ui.pu.m27.match.action;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>VMI结算的第一个界面中的结算按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 下午02:07:28
 */
public class VmiMatchAction extends SameMaterialMatchAction {
  private static final long serialVersionUID = -1081048528627323723L;

  public VmiMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_MATCH);
    // this.setBtnName("结算");
    // this.setCode("btnVmiMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

}
