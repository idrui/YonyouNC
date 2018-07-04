/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午02:38:25
 */
package nc.impl.pu.m25.action;

import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.rule.approve.UnSendApproveStatusChkRule;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票收回动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-26 下午02:38:25
 */
public class InvoiceUnSendApproveAction {
  public InvoiceVO[] unSendApprove(InvoiceVO[] invoiceVOs,
      AbstractCompiler2 script) {
    InvoiceVO[] vos = invoiceVOs;
    BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
    vos = tool.getClientFullInfoBill();
    InvoiceVO[] orgVos = tool.getOriginBills();
    CompareAroundProcesser<InvoiceVO> prcr =
        new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.UNSENDAPPROVE);
    this.addRule(prcr);
    prcr.before(vos, orgVos);
    if (null != script) {
      try {
        script.procRecallFlow(script.getPfParameterVO());
        InvoiceVO[] unappovedVos =
            (InvoiceVO[]) script.getPfParameterVO().m_preValueVos;
        for (InvoiceVO vo : unappovedVos) {
          vo.getParentVO().setStatus(VOStatus.UPDATED);
        }
        vos = new BillUpdate<InvoiceVO>().update(unappovedVos, orgVos);
      }
      catch (Exception e) {
        // 日志异常
        ExceptionUtils.wrappException(e);

      }
    }
    prcr.after(vos, orgVos);
    // 重新查一下VO
    String[] ids = AggVOUtil.getPrimaryKeys(vos);
    InvoiceVO[] approvedVos =
        new BillQuery<InvoiceVO>(InvoiceVO.class).query(ids);
    // 返回差异VO
    return tool.getBillForToClient(approvedVos);
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> prcr) {
    // 发票可取消审批状态检查
    prcr.addBeforeFinalRule(new UnSendApproveStatusChkRule());
  }
}
