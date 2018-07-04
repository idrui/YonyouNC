/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-8-5 ����04:12:43
 */
package nc.ui.pu.m25.action;

import java.awt.Container;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m25.action.processor.InvoiceMultiScriptRunner;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.MultiBillScriptRunner;
import nc.ui.uif2.UIState;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.exception.InvoiceDelResumeException;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pflow.PfUserObject;

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
 * @time 2009-8-5 ����04:12:43
 */
public class InvoiceDeleteAction extends DeleteScriptAction {

  /**
  * 
  */
  private static final long serialVersionUID = -963855408226033971L;

  public InvoiceDeleteAction() {
    super();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#getMultibillScriptRunner()
   */
  @Override
  public MultiBillScriptRunner getMultibillScriptRunner() {
    if (this.multibillScriptRunner == null) {
      this.multibillScriptRunner =
          new InvoiceMultiScriptRunner(this.flowContext, this);
    }
    return this.multibillScriptRunner;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#beforeCheck(nc.vo.pub.AggregatedValueObject)
   */
  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction#getFlowData()
   */
  @Override
  protected AbstractBill[] getFlowData() {
    AbstractBill[] superVos = super.getFlowData();
    if (ArrayUtils.isEmpty(superVos)) {
      return superVos;
    }
    if (1 == superVos.length) {
      return superVos;
    }
    InvoiceVO[] vos = new InvoiceVO[superVos.length];
    System.arraycopy(superVos, 0, vos, 0, superVos.length);
    vos = (InvoiceVO[]) this.getFullBills(vos);
    // ѡ����ŷ�Ʊʱ���÷�Ʊ����ɾ��,���Ⲣ������
    vos = InvoiceVOUtil.priOrderFeeVO(vos);
    AggVOUtil.reSortVO(superVos, vos);
    return superVos;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.DeleteAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        this.getModel().getUiState() == UIState.NOT_EDIT
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();
      isEnable &= ApproveFlowUtil.isCanDel(vo) || null != vos && 1 < vos.length;
    }
    return isEnable;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    if (!(resumeInfo instanceof InvoiceDelResumeException)) {
      return super.isResume(resumeInfo);
    }
    InvoiceDelResumeException exp = (InvoiceDelResumeException) resumeInfo;
    Container parent = this.getFlowContext().getParent();
    int answer =
        MessageDialog.showYesNoCancelDlg(parent, null, exp.getMessage());
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    if (UIDialog.ID_YES == answer) {
      env.setDelAnswer(InvoiceUserAnswerType.YES);
    }
    else if (UIDialog.ID_NO == answer) {
      env.setDelAnswer(InvoiceUserAnswerType.NO);
    }
    else {
      return false;
    }
    PfUserObject pfuo = new PfUserObject();
    pfuo.setUserObject(env);
    this.getFlowContext().setUserObj(pfuo);
    return true;
  }
}
