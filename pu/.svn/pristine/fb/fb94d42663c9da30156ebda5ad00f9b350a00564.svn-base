package nc.bs.pu.m24.maintain;

import nc.bs.pu.m24.maintain.rewrite.Write45WhenDeleteRule;
import nc.bs.pu.m24.maintain.rule.BillStatusRule;
import nc.bs.pu.m24.maintain.rule.ReturnBillCodeRule;
import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单删除BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:34:51
 */
public class PricestlDeleteBP {

  public void delete(PricestlVO[] vos) {

    CompareAroundProcesser<PricestlVO> processer =
        new CompareAroundProcesser<PricestlVO>(PricestlPluginPoint.DELETE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(vos, vos);
    BillDelete<PricestlVO> bo = new BillDelete<PricestlVO>();
    bo.delete(vos);

    processer.after(vos, vos);

  }

  private void addAfterRule(CompareAroundProcesser<PricestlVO> processer) {

    // 回写上游
    processer.addAfterRule(new Write45WhenDeleteRule());

    // 退回单据号
    processer.addAfterRule(new ReturnBillCodeRule());

  }

  private void addBeforeRule(CompareAroundProcesser<PricestlVO> processer) {
    // 单据状态检查
    processer.addBeforeFinalRule(new BillStatusRule());

  }
}
