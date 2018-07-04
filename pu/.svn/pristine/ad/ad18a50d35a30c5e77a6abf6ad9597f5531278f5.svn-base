/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 上午11:16:32
 */
package nc.bs.pu.m422x.maintain;

import nc.bs.pu.m422x.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m422x.maintain.rule.delete.CanDeleteRule;
import nc.bs.pu.m422x.maintain.rule.delete.CodeRuturnRule;
import nc.bs.pu.m422x.maintain.rule.delete.DeleteAfterEventRule;
import nc.bs.pu.m422x.maintain.rule.delete.DeleteBeforeEventRule;
import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.rule.WriteBack422xRule;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.scmpub.res.billtype.POBillType;

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
 * @author wuxla
 * @time 2010-7-20 上午11:16:32
 */
public class StoreReqAppDeleteBP {

  public void delete(StoreReqAppVO[] vos) {
    CompareAroundProcesser<StoreReqAppVO> processer =
        new CompareAroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.DELETE);
    this.addRule(processer);

    processer.before(vos, vos);

    new BillDelete<StoreReqAppVO>().delete(vos);

    processer.after(null, vos);
  }

  private void addRule(CompareAroundProcesser<StoreReqAppVO> processer) {
    processer.addBeforeRule(new CanDeleteRule());
    // 触发删除前事件
    processer.addBeforeRule(new DeleteBeforeEventRule());
    processer.addAfterRule(new CodeRuturnRule());
    processer.addAfterRule(new WriteBackPIMRule<StoreReqAppVO>(
        POBillType.MRBill.getCode()));
    processer.addAfterRule(new WriteBack422xRule<StoreReqAppVO>(
        POBillType.MRBill.getCode()));
    // 回写上游
    processer.addAfterRule(new WriteBackSourceRule());
    // 触发删除后事件
    processer.addAfterRule(new DeleteAfterEventRule());
  }
}
