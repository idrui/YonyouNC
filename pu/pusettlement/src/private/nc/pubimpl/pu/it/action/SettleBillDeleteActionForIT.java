package nc.pubimpl.pu.it.action;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.pu.m27.settlebill.rule.WriteM5801AndM5805Rule;
import nc.vo.pu.m27.entity.SettleBillVO;

import nc.bs.pu.est.m45.rule.settle.PurchsInSettleUpdateRule;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.DeleteIARule;
import nc.bs.pu.m27.settlebill.rule.DeleteVirtualInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.FeeSettleCheckRule;
import nc.bs.pu.m27.settlebill.rule.InvoiceToAPCheckRule;
import nc.bs.pu.m27.settlebill.rule.SettleBillCodeReturnRule;
import nc.bs.pu.m27.settlebill.rule.UnBackWashEstAPForRBStockRush;
import nc.bs.pu.m27.settlebill.rule.WritebackInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.WritebackOrderRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * @since 6.31
 * @version 2013-11-21 下午02:09:22
 * @author mengjian
 */
public class SettleBillDeleteActionForIT {

  /**
   * 删除结算单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-6-29 下午01:49:20
   */
  public void delete(SettleBillVO[] voaBill) {
    if (ArrayUtils.isEmpty(voaBill)) {
      return;
    }
    // ExceptionUtils.wrappBusinessException("stop !");
    BillTransferTool<SettleBillVO> tool =
        new BillTransferTool<SettleBillVO>(voaBill);
    SettleBillVO[] clientVos = tool.getClientFullInfoBill();
    AroundProcesser<SettleBillVO> proc =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.DELETE_IT);
    // 删除费用结算相关(必须作为操作前规则，否则会出现孙表中关联的子表数据dr=1)
    proc.addBeforeFinalRule(new FeeSettleDeleteActionForIT());
    // 检查费用分摊，确定是否可删除
    proc.addBeforeFinalRule(new FeeSettleCheckRule());
    // 增加删除前规则：删除存货核算单据
    proc.addBeforeFinalRule(new DeleteIARule());
    // 增加删除前规则：如果是红蓝入库单对冲并且回冲过暂估应付，则取消回冲
    proc.addBeforeFinalRule(new UnBackWashEstAPForRBStockRush());
    // 增加删除前规则：回写采购入
    proc.addBeforeFinalRule(new PurchsInSettleUpdateRule(WritebackPoint.DELETE));
    // 增加删除前规则：回写发票
    proc.addBeforeFinalRule(new WritebackInvoiceRule(WritebackPoint.DELETE));
    // 增加删除前规则：删除虚拟发票结算时生成的虚拟发票
    proc.addBeforeFinalRule(new DeleteVirtualInvoiceRule());
    // 增加删除前规则：回写订单
    proc.addBeforeFinalRule(new WritebackOrderRule(WritebackPoint.DELETE));
    // 增加删除前规则：回写外模块进口合同、进口明细单
    proc.addBeforeFinalRule(new WriteM5801AndM5805Rule(WritebackPoint.DELETE));
    // 增加删除前规则：发票如果已经传应付，并且采购入库单也传过应付，则不允许删除
    proc.addAfterFinalRule(new InvoiceToAPCheckRule());
    // 单据号回退规则
    proc.addAfterRule(new SettleBillCodeReturnRule());
    proc.before(clientVos);
    new BillDelete<SettleBillVO>().delete(clientVos);

    proc.after(clientVos);
  }

}
