package nc.ui.pu.m23.action.maintain;

import java.awt.event.ActionEvent;

import nc.ui.pu.m23.editor.card.utils.BackReasonEditUtil;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pu.pub.action.PUEditAction;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 修改 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class UpdateUIAction extends PUEditAction {

  private static final long serialVersionUID = -7126460625856669611L;

  private ArriveCardForm form;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 触发主组织编辑事件
    ArriveVO aggVO = (ArriveVO) this.getModel().getSelectedData();
    this.getModel().fireEvent(
        new OrgChangedEvent(null, aggVO.getHVO().getPk_org()));

    BillCardPanel card = this.form.getBillCardPanel();

    new PuVDefFilterUtil().setOrgForDefRef(card, this.getModel().getContext());
    super.doAction(e);

    // 设置退货理由
    new BackReasonEditUtil(card).setEnabled();
  }

  public ArriveCardForm getBillForm() {
    return this.form;
  }

  public void setBillForm(ArriveCardForm billForm) {
    this.form = billForm;
  }

  private boolean isOneVOEnable(ArriveVO vo) {
    if (ApproveFlowUtil.isCanEdit(vo)) {
      return true; // 只有自由态，才允许修改
    }
    return false;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getSelectedData() == null) {
      return false;
    }

    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(objs)) {
      ArriveVO aggVO = (ArriveVO) this.getModel().getSelectedData();
      return this.isOneVOEnable(aggVO);
    }
    if (objs.length > 1) {
      return true;
    }
    return this.isOneVOEnable((ArriveVO) objs[0]);
  }
}
