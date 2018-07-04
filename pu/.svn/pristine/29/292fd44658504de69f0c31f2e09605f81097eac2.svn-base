/**
 * 
 */
package nc.pubimpl.pu.m25.pf;

import nc.bs.pu.m25.ap.CancelSendApBP;
import nc.bs.pu.m25.ap.SendApBP;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.pu.m25.pf.rule.FilterSendAPInvoiceRule;
import nc.pubitf.pu.m25.pf.IInvoiceSendAP;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.pf.UpdatePflowVORule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>传应付实现组件</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-2 下午04:37:35
 */
public class InvoiceSendAPImpl implements IInvoiceSendAP {

  /**
   * 取消传应付
   */
  @Override
  public InvoiceVO[] cancelSendAP(InvoiceVO[] invoiceVOs, InvoiceUIToBSEnv env)
      throws BusinessException {
    InvoiceVO[] vos = invoiceVOs;
    if (!SysInitGroupQuery.isAPEnabled()) {
      return invoiceVOs;
    }
    try {
      BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
      InvoiceVO[] orgVos = tool.getOriginBills();
      vos = tool.getClientFullInfoBill();
      CancelSendApBP bp = new CancelSendApBP();
      bp.setIsmanual(InvoiceVOUtil.isManualCancelSendAP(env));
      bp.cancelSendAp(vos, orgVos);
      vos = tool.getBillForToClient(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return vos;
  }

  /**
   * 传应付
   */
  @Override
  public InvoiceVO[] sendAP(InvoiceVO[] invoiceVOs, InvoiceUIToBSEnv env,
      PfParameterVO pfVo) throws BusinessException {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return invoiceVOs;
    }
    if (ArrayUtils.isEmpty(invoiceVOs)) {
      return null;// 可能是推式动作过来，流程中的数据已经为空
    }
    InvoiceVO[] vos = invoiceVOs;
    try {
      InvoiceVO[] orgVos = null;
      BillTransferTool<InvoiceVO> tool = null;
      // 如果发票审批后传应付，这里的vos应该已经是全的了，因为前台审批动作配置了自动补全
      // 可以直接用vos的克隆作为orgVos
      // 如果用杜勇的单据类型注册类，则这里都不用处理差异和考虑是否手工的迸发控制问题
      if (InvoiceVOUtil.isManualSendAP(env)) {
        tool = new BillTransferTool<InvoiceVO>(vos);
        orgVos = tool.getOriginBills();
        vos = tool.getClientFullInfoBill();
      }
      else {
        orgVos = InvoiceVOUtil.getCloneVOs(vos);
      }
      CompareAroundProcesser<InvoiceVO> processer =
          new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.SEND_AP_IMPL);
      this.addSendAPRule(processer, pfVo, env);
      vos = processer.before(vos, orgVos);
      if (ArrayUtils.isEmpty(vos)) {
        return vos;
      }
      InvoiceVO[] updateVos = new SendApBP(env).sendAP(vos, orgVos);
      processer.after(updateVos, orgVos);
      // 这里一定要使用vos是行补全，因为bp返回的updateVos中的VO行可能已经被修改过（分行，重算）
      if (InvoiceVOUtil.isManualSendAP(env) && tool != null) {
        return tool.getBillForToClient(vos);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return vos;
  }

  private void addSendAPRule(CompareAroundProcesser<InvoiceVO> processer,
      PfParameterVO pfVo, InvoiceUIToBSEnv env) {
    // 过滤掉不能传应付的VO
    processer.addBeforeFinalRule(new FilterSendAPInvoiceRule(InvoiceVOUtil
        .isManualSendAP(env)));
    // 更新流程平台的VO--前规则必须处理，如果前面规则已经过滤为空，则后规则就不执行了
    // 平台脚本中还是原来的VO，则还会推出应付单
    processer.addBeforeFinalRule(new UpdatePflowVORule<InvoiceVO>(pfVo));
    // 过滤掉不能传应付的VO--后规则中要再处理一次
    processer.addAfterFinalRule(new FilterSendAPInvoiceRule(true));
    // 后规则中也要增加此规则，因为对应付调差之后，发票可能不用传应付了，因此也要更新流程平台脚本中的VO
    processer.addAfterFinalRule(new UpdatePflowVORule<InvoiceVO>(pfVo));
  }

}
