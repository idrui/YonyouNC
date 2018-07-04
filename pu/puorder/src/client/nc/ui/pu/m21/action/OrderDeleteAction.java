package nc.ui.pu.m21.action;

import nc.ui.pu.pub.action.PUDeleteAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单删除的按钮事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-25 下午07:04:08
 */
public class OrderDeleteAction extends PUDeleteAction {
  private static final long serialVersionUID = 5219936951217999815L;

  @Override
  protected boolean isActionEnable() {
    // 一定要加，效率问题否则
    if (UIState.ADD == this.getModel().getUiState()
        || UIState.EDIT == this.getModel().getUiState()) {
      return false;
    }
    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    OrderVO order = (OrderVO) this.getModel().getSelectedData();
    return order != null && ApproveFlowUtil.isCanDel(order);
  }
}
