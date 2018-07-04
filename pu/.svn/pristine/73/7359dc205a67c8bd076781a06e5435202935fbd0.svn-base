/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午11:00:36
 */
package nc.bs.pu.m20.maintain;

import nc.bs.pu.m20.maintain.rule.ATPAfterUpdateRule;
import nc.bs.pu.m20.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m20.maintain.rule.close.CloseBudgetCtrlRule;
import nc.bs.pu.m20.maintain.rule.close.SetCloseStatus;
import nc.bs.pu.m20.maintain.rule.pub.BodyEmptyRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单关闭BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-15 上午10:43:03
 */
public class PraybillCloseBP {

  public PraybillVO[] closeBill(PraybillVO[] updateVos, PraybillVO[] originalVos) {
    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(PraybillPluginPoint.CLOSE_BILL);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    PraybillVO[] upVos = updateVos;
    processer.before(upVos, originalVos);
    BillUpdate<PraybillVO> bo = new BillUpdate<PraybillVO>();
    upVos = bo.update(upVos, originalVos);
    processer.after(upVos, originalVos);

    return upVos;
  }

  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer) {
    // 回写采购计划
    processer.addAfterRule(new CloseBudgetCtrlRule());
    // 保存后可用量处理
    processer.addAfterRule(new ATPAfterUpdateRule());
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // 表体非空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // 状态设置
    processer.addBeforeRule(new SetCloseStatus());
    // 保存前可用量处理
    processer.addBeforeRule(new ATPBeforeUpdateRule());

  }
}
