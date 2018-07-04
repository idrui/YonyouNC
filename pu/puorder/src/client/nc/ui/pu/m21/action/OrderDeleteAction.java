package nc.ui.pu.m21.action;

import nc.ui.pu.pub.action.PUDeleteAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����ɾ���İ�ť�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-25 ����07:04:08
 */
public class OrderDeleteAction extends PUDeleteAction {
  private static final long serialVersionUID = 5219936951217999815L;

  @Override
  protected boolean isActionEnable() {
    // һ��Ҫ�ӣ�Ч���������
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
