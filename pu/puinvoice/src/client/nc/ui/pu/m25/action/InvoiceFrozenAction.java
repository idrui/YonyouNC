/**
 * $�ļ�˵��$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 ����02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.IActionCode;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceServiceUtil;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ���ᰴť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 ����02:29:00
 */
public class InvoiceFrozenAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public InvoiceFrozenAction() {
    SCMActionInitializer.initializeAction(this, IActionCode.FREEZE);
    // this.setBtnName("����");
    // this.setCode("btnFrozen");
    // // ctrl+J
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    InvoiceVO[] selectedVOs = this.getSelectedVOs();
    int reasonLength =
        selectedVOs[0].getMetaData().getParent()
            .getAttribute(InvoiceHeaderVO.VFROZENREASON).getColumn()
            .getLength();
    Object reason =
        MessageDialog.showInputDlg(
            null,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000444")/* @res "����ԭ��" */, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004050_0", "04004050-0002")/*
                                                                         * @res
                                                                         * "�����붳��ԭ��"
                                                                         */,
            null, reasonLength);
    // �û�ѡ��ȡ��
    if (null == reason) {
      return;
    }

    // ��ť���þ�һ�����п��õ�vo
    selectedVOs = this.pickupFrozenVOs(selectedVOs);

    InvoiceVO[] oldVOs = new InvoiceVO[selectedVOs.length];// new
    // ArrayUtil.copyArray(selectedVOs);

    for (int i = 0; i < selectedVOs.length; i++) {
      oldVOs[i] = (InvoiceVO) selectedVOs[i].clone();
      if (!ObjectUtil.isEmptyWithTrim(reason)) {
        selectedVOs[i].getParentVO().setVfrozenreason(reason.toString());
      }
    }
    ClientBillToServer<InvoiceVO> tool = new ClientBillToServer<InvoiceVO>();
    InvoiceVO[] lightVOs = tool.construct(oldVOs, selectedVOs);
    // �������
    InvoiceVO[] returnVos =
        InvoiceServiceUtil.getMaintainSerivce().freezeByLightVOs(lightVOs);
    new ClientBillCombinServer<InvoiceVO>().combine(selectedVOs, returnVos);
    this.getModel().directlyUpdate(selectedVOs);
    if (StringUtil.isEmptyWithTrim(this.getMessage())) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004050_0", "04004050-0117")/*
                                                                           * ��Ʊ����ɹ���
                                                                           */,
          this.getModel().getContext());
    }
    this.showMessage();
  }

  private InvoiceVO[] pickupFrozenVOs(InvoiceVO[] selectedVOs) {
    List<InvoiceVO> canBefrozenVOs = new ArrayList<InvoiceVO>();
    StringBuffer message = new StringBuffer();// message.length()
    for (int i = 0; i < selectedVOs.length; i++) {
      if (selectedVOs[i].getParentVO().getBfrozen() != null
          && selectedVOs[i].getParentVO().getBfrozen().booleanValue()) {
        int num = i + 1;
        message.append(NCLangRes.getInstance().getStrByID("4004050_0",
            "04004050-0105", null, new String[] {
              String.valueOf(num)
            })/* ��{0}�����ݲ���ʧ�ܣ�ʧ��ԭ�򣺸ü�¼�Ѿ����ڱ�����״̬��\n */);
        continue;
      }
      canBefrozenVOs.add(selectedVOs[i]);
    }
    this.setMessage(message.toString());
    return canBefrozenVOs.toArray(new InvoiceVO[canBefrozenVOs.size()]);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    // ֻҪ��Ʊδ��Ӧ������ð�ť����
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }
    return this.canBeFrozen(this.getSelectedVOs());
  }

}
