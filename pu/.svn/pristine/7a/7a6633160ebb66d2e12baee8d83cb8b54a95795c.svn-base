/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-3 下午01:21:34
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
 * @time 2009-7-3 下午01:21:34
 */
public class InvoiceSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = -2457586654109652720L;

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    InvoiceVO[] vos = new InvoiceVO[] {
      (InvoiceVO) vo
    };
    // 参数正确性检查(前后台检查)
    new ParaValidityChkRule().process(vos);
    // 非空检查(前后台检查)
    new InvoiceNotNullChkRule().process(vos);
    // 数值型检查(前后台检查)
    new InvoiceNumValueChkRule().process(vos);
    // 来源信息检查(前后台检查)
    new InvoiceSourceInfoChkRule().process(vos);
    // 来源单据类型检查
    new InvoiceSourceTypeChkRule().process(vos);
    // 发票行号合法性检查(前后台检查)
    new InvoiceRowNoCheckRule().process(vos);
    // 极限值检查(前后台检查)
    new InvoiceLimitCheckRule().process(vos);
  }

  /**
   * 父类方法重写
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
      // 委外订单价格容差
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
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction#processBefore(java.lang.Object[])
   */
  @Override
  protected Object[] processBefore(Object[] vos) {
    Object[] returnVOs = super.processBefore(vos);
    if (!ArrayUtils.isEmpty(returnVOs)) {
      // 整单合计计算
      new InvoiceTotalValueCalcRule().process((InvoiceVO[]) returnVOs);
    }
    return returnVOs;
  }

}
