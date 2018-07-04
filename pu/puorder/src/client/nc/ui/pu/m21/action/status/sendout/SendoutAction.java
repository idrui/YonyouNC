/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 ����04:31:13
 */
package nc.ui.pu.m21.action.status.sendout;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.ui.pu.m21.action.AbstractOnwayStatusAction;
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
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-20 ����04:31:13
 */
public class SendoutAction extends AbstractOnwayStatusAction {

  private static final long serialVersionUID = -5337661688398689943L;

  public SendoutAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SENDOUT);
    // this.setBtnName("����");
    // this.setCode("sendoutAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Alt+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
    // this.setEnabled(false);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderOnwayVO vo = (OrderOnwayVO) ((BillForm) this.editor).getValue();
    // ���ǿա�ȷ����������Ч��
    List<ISuperVO> rows =
        ((SelectBillManageModel) this.model).getSelectedBodyRows();
    OrderOnwayItemVO[] voitems = rows.toArray(new OrderOnwayItemVO[0]);
    // ����������һ��
    AggVOUtil.updateItemVOs(vo, voitems);

    if (ArrayUtils.isEmpty(voitems)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0362")/*
                                                                   * @res
                                                                   * "�빴ѡ��¼���в���"
                                                                   */);
    }

    // ���ǿա�ȷ����������Ч��
    this.validateService.validate(voitems);

    // ������;״̬
    for (int i = 0; i < voitems.length; i++) {
      voitems[i].setFonwaystatus(ValueUtils.getInteger(this.getStatus()));
      voitems[i].setStatus(VOStatus.UPDATED);
    }
    vo.setBVO(voitems);
    this.service.update(new OrderOnwayVO[] {
      vo
    });

    // ˢ�½���
    // int nRowNum = this.getModel().getSelectedRow();
    if (this.dataManager != null) {
      this.dataManager.refresh();
    }
  }

  // private SendoutQueryActionMy queryAction = null;

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getStatus()
   */
  @Override
  public Integer getStatus() {
    return (Integer) OnwayStatus.STATUS_SENDOUT.value();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.action.AbstractOnwayStatusAction#getIsDone()
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
   *          Ҫ���õ� queryAction
   */
  // public void setQueryAction(SendoutQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }

}
