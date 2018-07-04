package nc.ui.pu.m21.action;

import nc.ui.pubapp.uif2app.actions.OutputAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

public class OutputSpecialProcessAction extends OutputAction {

  private static final long serialVersionUID = 4762148769354319744L;

  @Override
  public BillManageModel getModel() {
    return (BillManageModel) super.getModel();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (null != objs && 1 < objs.length) {
      return true;
    }
    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.FALSE.equals(vo.getHVO().getBfrozen());
  }
}
