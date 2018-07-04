/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午04:33:35
 */
package nc.ui.pu.m21.action.status.sendout;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.ui.pu.m21.action.AbstractUnOnwayStatusAction;
import nc.ui.pu.m21.editor.list.SelectBillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>反发货
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-20 下午04:33:35
 */
public class UnSendoutAction extends AbstractUnOnwayStatusAction {

  private static final long serialVersionUID = 5335426118269137573L;

  // private SendoutQueryActionMy queryAction = null;

  public UnSendoutAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELSENDOUT);
    // this.setBtnName("反发货");
    // this.setCode("unSendoutAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
    // this.setEnabled(false);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    // validateService.validate(null);
    OrderOnwayVO vo = (OrderOnwayVO) ((BillForm) this.editor).getValue();

    // 检测非空、确认数量的有效性
    List<ISuperVO> rows =
        ((SelectBillManageModel) this.model).getSelectedBodyRows();
    OrderOnwayItemVO[] voitems = rows.toArray(new OrderOnwayItemVO[0]);
    // 跟界面数据一致
    AggVOUtil.updateItemVOs(vo, voitems);
    if (ArrayUtils.isEmpty(voitems)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0362")/*
                                                                   * @res
                                                                   * "请勾选记录进行操作"
                                                                   */);
    }

    // 确认
    for (int i = 0; i < voitems.length; i++) {
      // voitems[i].setFonwaystatus(ValueUtils.getInteger(
      // OnwayStatus.STATUS_SENDOUT.value()));
      voitems[i].setStatus(VOStatus.DELETED);
    }
    vo.setBVO(voitems);
    this.service.delete(new OrderOnwayVO[] {
      vo
    });
    // 刷新界面
    // int nRowNum = this.getModel().getSelectedRow();
    if (this.dataManager != null) {
      this.dataManager.refresh();
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getStatus()
   */
  @Override
  public Integer getStatus() {
    return (Integer) OnwayStatus.STATUS_SENDOUT.value();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.action.AbstractUnOnwayStatusAction#getIsDone()
   */
  // @Override
  // public boolean getIsDone() {
  // return this.queryAction.getOperate().booleanValue();
  // }

  /**
   * @return queryAction
   */
  // public SendoutQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  @Override
  public String getStatusStr() {
    return OnwayStatusQryEnum.bissendout.code();
  }

  /**
   * @param queryAction
   *          要设置的 queryAction
   */
  // public void setQueryAction(SendoutQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }
}
