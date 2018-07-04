/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午10:04:04
 */
package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.PraybillDeleteBP;
import nc.impl.pu.m20.rule.PrayBillVOChkRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午10:04:04
 */
public class PraybillDeleteAction {

  public void delete(PraybillVO[] vos) {
    BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(vos);
    PraybillVO[] delvos = tool.getOriginBills();
    // 添加Action部分的rule
    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(null);
    this.addBeforeRule(processer);
    processer.before(delvos, delvos);
    new PraybillDeleteBP().delete(delvos);
    processer.after(delvos, null);
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // 数据合法性检查
    processer.addBeforeRule(new PrayBillVOChkRule());

  }

}
