/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午02:27:07
 */
package nc.bs.pu.m25.maintain;

import nc.bs.pu.m25.maintain.rule.InvocieWriteBackOrderRule;
import nc.bs.pu.m25.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m25.maintain.rule.delete.CutFeeParentRelationRule;
import nc.bs.pu.m25.maintain.rule.delete.InvcDelAfterEventRule;
import nc.bs.pu.m25.maintain.rule.delete.InvcDelBeforeEventRule;
import nc.bs.pu.m25.maintain.rule.delete.InvoiceCodeReturnRule;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.pub.util.ArrayUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票删除BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-26 下午02:27:07
 */
public class InvoiceDeleteBP {
  private InvoiceUIToBSEnv env;

  /**
   * InvoiceDeleteBP 的构造子
   * 
   * @param env
   */
  public InvoiceDeleteBP(InvoiceUIToBSEnv env) {
    this.env = env;
  }

  public void delete(InvoiceVO[] invoiceVOs, InvoiceVO[] delFees,
      InvoiceVO[] cutrelFees) {
    InvoiceVO[] vos = invoiceVOs;
    CompareAroundProcesser<InvoiceVO> processer =
        new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.DELETE);
    this.addRule(processer);
    this.addSpecialRule(processer, cutrelFees);
    vos = ArrayUtil.combinArrays(vos, delFees);
    processer.before(vos, vos);
    new BillDelete<InvoiceVO>().delete(vos);
    processer.after(null, vos);
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeRule(new InvcDelBeforeEventRule());// 生产工序委外加工费结算单回写
    // 发票号回退规则
    processer.addAfterRule(new InvoiceCodeReturnRule());
    processer.addAfterFinalRule(new InvocieWriteBackOrderRule(this.env));// 回写来源
    // 回写上游
    processer.addAfterFinalRule(new WriteBackSourceRule(this.env));
    processer.addAfterRule(new InvcDelAfterEventRule());// 生产工序委外加工费结算单回写

  }

  private void addSpecialRule(CompareAroundProcesser<InvoiceVO> processer,
      InvoiceVO[] cutrelFees) {
    // 清除费用发票与货物发票的关系
    processer.addAfterRule(new CutFeeParentRelationRule(cutrelFees));
  }

}
