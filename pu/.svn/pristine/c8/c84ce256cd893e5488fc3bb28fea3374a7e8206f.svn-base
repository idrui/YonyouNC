package nc.impl.pu.m24.action;

import nc.bs.pu.m24.maintain.PricestlDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单作废
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:29:06
 */
public class PricestlDeleteAction {

  public void delete(PricestlVO[] vos) {
    BillTransferTool<PricestlVO> tool = new BillTransferTool<PricestlVO>(vos);
    PricestlVO[] delvos = tool.getOriginBills();
    // 添加Action部分的rule
    CompareAroundProcesser<PricestlVO> processer =
        new CompareAroundProcesser<PricestlVO>(null);
    this.addBeforeRule();
    processer.before(delvos, delvos);
    new PricestlDeleteBP().delete(delvos);
    processer.after(delvos, null);
  }

  /**
   * 方法功能描述：更新前规则添加。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午01:24:21
   */
  private void addBeforeRule() {
    // 无规则
  }

}
