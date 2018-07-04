/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-3 下午03:34:15
 */
package nc.ui.pu.m25.action;

import java.awt.Container;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m25.action.processor.InvoiceMultiScriptRunner;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.MultiBillScriptRunner;
import nc.ui.uif2.UIState;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.exception.InvoiceApproveWithFeeException;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pflow.PfUserObject;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-7-3 下午03:34:15
 */
public class InvoiceApproveAction extends ApproveScriptAction {

  /**
  * 
  */
  private static final long serialVersionUID = -2841188491995025799L;

  /**
   * 父类方法重写
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

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    // 2011-09-13 tianft 因为kongxd的脚本前台改为了批处理，这个校验不能在前台校验了，否则会全部不通过
    // InvoiceVO[] vos = new InvoiceVO[] {
    // (InvoiceVO) vo
    // };
    // // 审批时发票状态检查
    // new ApproveStatusChkRule().process(vos);
  }

  /**
   * 父类方法重写
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
    // 选择多张发票时费用发票优先删除,避免并发问题
    vos = InvoiceVOUtil.priOrderFeeVO(vos);
    this.setFullOldVOs(vos);
    AggVOUtil.reSortVO(superVos, vos);
    return superVos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.SaveAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    boolean isEnable = true;
    isEnable &=
        UIState.NOT_EDIT == this.getModel().getUiState()
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();
      // 状态不是"审批"情况下允许审批
      isEnable &=
          POEnumBillStatus.APPROVE.toInt() != vo.getParentVO().getFbillstatus()
              .intValue()
              || null != vos && 1 < vos.length;
      isEnable =
          isEnable
              && POEnumBillStatus.NOPASS.toInt() != vo.getParentVO()
                  .getFbillstatus().intValue();
    }
    return isEnable;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    if (!(resumeInfo instanceof InvoiceApproveWithFeeException)) {
      return super.isResume(resumeInfo);
    }
    InvoiceApproveWithFeeException exp =
        (InvoiceApproveWithFeeException) resumeInfo;
    Container parent = this.getFlowContext().getParent();
    int answer = MessageDialog.showYesNoDlg(parent, null, exp.getMessage());
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    if (UIDialog.ID_YES == answer) {
      env.setApproveFee(InvoiceUserAnswerType.YES);
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
