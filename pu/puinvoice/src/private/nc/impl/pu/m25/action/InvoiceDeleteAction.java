/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午02:29:00
 */
package nc.impl.pu.m25.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m25.maintain.InvoiceDeleteBP;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.m25.IInvoiceQuery;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.exception.InvoiceDelResumeException;
import nc.vo.pu.m25.rule.maintain.InvoiceDelStatusChkRule;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票删除操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-26 下午02:29:00
 */
public class InvoiceDeleteAction {
  public void delete(InvoiceVO[] invoiceVOs, InvoiceUIToBSEnv[] envs) {
    InvoiceVO[] vos = invoiceVOs;
    // 先查询是否有附带的费用发票，有则提示用户
    InvoiceVO[] fees = this.queryFeeVO(vos);
    InvoiceVO[] unApproveFees = this.pickupUnapproveFeeVO(fees);
    InvoiceVO[] deleteFees = null;
    InvoiceVO[] cutRelationFees = fees;
    InvoiceUIToBSEnv env =
        ArrayUtils.isEmpty(envs) ? new InvoiceUIToBSEnv() : envs[0];
    if (!ArrayUtils.isEmpty(unApproveFees)) {
      InvoiceUserAnswerType atype = this.getAnswerType(env);
      if (InvoiceUserAnswerType.NO_ANSWER == atype) {
        ExceptionUtils.wrappException(new InvoiceDelResumeException(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0034")/* @res "货物发票关联有费用发票，是否一并删除？" */));
        // 用户回答删除
      }
      else if (InvoiceUserAnswerType.YES == atype) {
        deleteFees = unApproveFees;
        cutRelationFees = this.pickupApproveFeeVO(fees);
      }
    }
    // 并发控制
    BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
    // 费用发票并发控制
    if (!ArrayUtils.isEmpty(fees)) {
      new BillTransferTool<InvoiceVO>(fees);
    }
    vos = tool.getClientFullInfoBill();
    AroundProcesser<InvoiceVO> processer =
        new AroundProcesser<InvoiceVO>(InvoicePluginPoint.DELETE_IMPL);
    this.addRule(processer);
    processer.before(vos);
    new InvoiceDeleteBP(env).delete(vos, deleteFees, cutRelationFees);
    processer.after(vos);
  }

  private void addRule(AroundProcesser<InvoiceVO> processer) {
    // 可删除的发票状态检查
    processer.addBeforeFinalRule(new InvoiceDelStatusChkRule());
  }

  private InvoiceUserAnswerType getAnswerType(InvoiceUIToBSEnv env) {
    if (null == env) {
      return InvoiceUserAnswerType.NO_ANSWER;
    }
    if (null == env.getDelAnswer()) {
      return InvoiceUserAnswerType.NO_ANSWER;
    }
    return env.getDelAnswer();
  }

  private InvoiceVO[] pickupApproveFeeVO(InvoiceVO[] fees) {
    InvoiceVO[] unfees = this.pickupUnapproveFeeVO(fees);
    if (ArrayUtils.isEmpty(unfees)) {
      return fees;
    }
    Set<InvoiceVO> feeset = new HashSet<InvoiceVO>(Arrays.asList(fees));
    Set<InvoiceVO> unfeeset = new HashSet<InvoiceVO>(Arrays.asList(unfees));
    feeset.removeAll(unfeeset);
    return feeset.toArray(new InvoiceVO[feeset.size()]);
  }

  private InvoiceVO[] pickupUnapproveFeeVO(InvoiceVO[] fees) {
    if (ArrayUtils.isEmpty(fees)) {
      return null;
    }
    List<InvoiceVO> unfees = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : fees) {
      if (ApproveFlowUtil.isCanDel(vo)) {
        unfees.add(vo);
      }
    }
    return unfees.toArray(new InvoiceVO[unfees.size()]);
  }

  private InvoiceVO[] queryFeeVO(InvoiceVO[] vos) {
    Set<String> ids = new HashSet<String>();
    for (InvoiceVO vo : vos) {
      if (!ValueUtils.getBoolean(vo.getParentVO().getBfee())) {
        ids.add(vo.getParentVO().getPk_invoice());
      }
    }
    if (0 == ids.size()) {
      return null;
    }
    IInvoiceQuery querysrv =
        NCLocator.getInstance().lookup(IInvoiceQuery.class);
    InvoiceVO[] feeVos = null;
    try {
      feeVos = querysrv.queryFee(ids.toArray(new String[ids.size()]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return feeVos;
  }

}
