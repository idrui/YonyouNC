/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-3 ����01:21:34
 */
package nc.ui.pu.m25.action;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m21.exception.AskPriceException;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.InvoiceNotNullChkRule;
import nc.vo.pu.m25.rule.InvoiceNumValueChkRule;
import nc.vo.pu.m25.rule.InvoiceSourceTypeChkRule;
import nc.vo.pu.m25.rule.InvoiceSourceInfoChkRule;
import nc.vo.pu.m25.rule.ParaValidityChkRule;
import nc.vo.pu.m25.rule.maintain.InvoiceLimitCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceRowNoCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceTotalValueCalcRule;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.sc.m61.exception.SCOrderAskPriceException;

import org.apache.commons.lang.ArrayUtils;

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
 * @author zhaoyha
 * @time 2009-7-3 ����01:21:34
 */
public class InvoiceSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = -2457586654109652720L;

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    InvoiceVO[] vos = new InvoiceVO[] {
      (InvoiceVO) vo
    };
    // ������ȷ�Լ��(ǰ��̨���)
    new ParaValidityChkRule().process(vos);
    // �ǿռ��(ǰ��̨���)
    new InvoiceNotNullChkRule().process(vos);
    // ��ֵ�ͼ��(ǰ��̨���)
    new InvoiceNumValueChkRule().process(vos);
    // ��Դ��Ϣ���(ǰ��̨���)
    new InvoiceSourceInfoChkRule().process(vos);
    // ��Դ�������ͼ��
    new InvoiceSourceTypeChkRule().process(vos);
    // ��Ʊ�кźϷ��Լ��(ǰ��̨���)
    new InvoiceRowNoCheckRule().process(vos);
    // ����ֵ���(ǰ��̨���)
    new InvoiceLimitCheckRule().process(vos);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    int answer =
        MessageDialog.showYesNoDlg(this.getFlowContext().getParent(), null,
            ((Exception) resumeInfo).getMessage());

    if (UIDialog.ID_YES != answer) {
      return false;
    }

    InvoiceUIToBSEnv env = null;
    PfUserObject pfuo = this.getFlowContext().getUserObj();
    if (pfuo == null) {
      env = new InvoiceUIToBSEnv();
      pfuo = new PfUserObject();
    }
    else {
      env = (InvoiceUIToBSEnv) pfuo.getUserObject();
    }

    if (resumeInfo instanceof AskNumException) {
      env.setOverPONum(InvoiceUserAnswerType.YES);
    }
    else if (resumeInfo instanceof AskPriceException) {
      env.setOverPOPrice(InvoiceUserAnswerType.YES);
    }
    else if (resumeInfo instanceof SCOrderAskPriceException) {
      // ί�ⶩ���۸��ݲ�
      env.setOverPOPrice(InvoiceUserAnswerType.YES);
    }
    else {
      return super.isResume(resumeInfo);
    }

    pfuo.setUserObject(env);
    this.getFlowContext().setUserObj(pfuo);
    return true;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction#processBefore(java.lang.Object[])
   */
  @Override
  protected Object[] processBefore(Object[] vos) {
    Object[] returnVOs = super.processBefore(vos);
    if (!ArrayUtils.isEmpty(returnVOs)) {
      // �����ϼƼ���
      new InvoiceTotalValueCalcRule().process((InvoiceVO[]) returnVOs);
    }
    return returnVOs;
  }

}
