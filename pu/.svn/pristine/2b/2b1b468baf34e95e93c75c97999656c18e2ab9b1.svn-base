/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午08:36:20
 */
package nc.impl.pu.m25.action;

import nc.impl.pu.m25.action.rule.sendapprove.ApproveFlowCheckRule;
import nc.impl.pu.m25.action.rule.sendapprove.InvoiceStateChgRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.rule.pf.SendApproveStatusChkRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票送审操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-28 上午08:36:20
 */
public class InvoiceSendApproveAction {
  public InvoiceVO[] sendapprove(InvoiceVO[] invoiceVOs) {
    InvoiceVO[] vos = invoiceVOs;
    BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos); // 加锁
    // tool.getClientFullInfoBill()是重新克隆的vo，和平台paramvo持有的vo不同，平台更新的是paramvo的vo
    // 应该使用平台的vo
    // 可能出现的情况：提交并审批，提交已经更新了ts，审批时重新查询的数据ts已经改变。
    // 但是平台持有的vo的ts仍是旧值，就会导致审批校验并发时报错。
    // vos = tool.getClientFullInfoBill();
    InvoiceVO[] orgVos = tool.getOriginBills();
    AroundProcesser<InvoiceVO> processer = new AroundProcesser<InvoiceVO>(null);
    this.addRule(processer);
    processer.before(vos);
    InvoiceVO[] updatedVos = new BillUpdate<InvoiceVO>().update(vos, orgVos);
    return tool.getBillForToClient(updatedVos);
  }

  private void addRule(AroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new ApproveFlowCheckRule());// 是否可以送审
    processer.addBeforeFinalRule(new SendApproveStatusChkRule<InvoiceVO>());
    processer.addBeforeFinalRule(new InvoiceStateChgRule()); // 单据自己维护送审状态
  }

}
