/**
 * 
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author xiebo
 * @time 2010-1-25 ����09:55:28
 */
public class InvoiceUnApproveAction extends UNApproveScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2662213321607955397L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.isUnapprove();
    super.doAction(e);
  }

  private boolean isUnapprove() {
    String str = "";
    Object[] obs = this.getModel().getSelectedOperaDatas();
    for (Object ob : obs) {
      InvoiceVO vo = (InvoiceVO) ob;
      InvoiceItemVO[] itemVOs = vo.getChildrenVO();
      if (itemVOs == null) {
        continue;
      }
      for (InvoiceItemVO itemVo : itemVOs) {
        if ("4812".equals(itemVo.getCsourcetypecode())) {
          str = str + vo.getParentVO().getVbillcode() + " ";
        }
      }
    }
    if (!str.equals("")) {
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0137")/*
                                                                   * ���·�Ʊ��ԴΪ�˷ѷ�Ʊ��
                                                                   * �޷�ȡ��������
                                                                   */+ str,
          this.getModel().getContext());
      return false;
    }
    return true;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#beforeCheck(java.lang.Object)
   */
  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    // 2011-09-13 tianft ��Ϊkongxd�Ľű�ǰ̨��Ϊ�����������У�鲻����ǰ̨У���ˣ������ȫ����ͨ��
    // InvoiceVO[] vos = new InvoiceVO[] {
    // (InvoiceVO) vo
    // };
    // // ��Ʊ��ȡ������״̬���
    // new UnApproveStatusChkRule().process(vos);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = true;
    isEnable &=
        UIState.NOT_EDIT == this.getModel().getUiState()
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();

      // ״̬����
      isEnable &=
          null != vos && 1 < vos.length || ApproveFlowUtil.isCanUnApprove(vo);
      InvoiceItemVO[] itemVOs = vo.getChildrenVO();
      if (itemVOs == null) {
        return isEnable;
      }
      for (InvoiceItemVO itemVo : itemVOs) {
        if ("4812".equals(itemVo.getCsourcetypecode())) {
          return false;
        }
      }
    }
    return isEnable;
  }

}
