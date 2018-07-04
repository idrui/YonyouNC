/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:46:28
 */
package nc.bs.pu.m4t.maintain;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m4t.maintain.rule.delete.InitialEstCodeReturnRule;
import nc.bs.pu.m4t.maintain.rule.delete.InitialEstDelStatusChkRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单删除BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:46:28
 */
public class InitialEstDeleteBP {
  private InitialEstContext context;

  public InitialEstDeleteBP(InitialEstContext context) {
    this.context = context;
  }

  public void delete(InitialEstVO[] vos) {
    CompareAroundProcesser<InitialEstVO> processer =
        new CompareAroundProcesser<InitialEstVO>(InitialEstPluginPoint.DELETE);
    this.addRule(processer);
    processer.before(vos, vos);
    new BillDelete<InitialEstVO>().delete(vos);
    processer.after(null, vos);
  }

  /**
   * @param processer
   */
  private void addRule(CompareAroundProcesser<InitialEstVO> processer) {
    // 期初暂估单删除状态检查
    processer.addBeforeRule(new InitialEstDelStatusChkRule());
    // 单据号回退处理
    processer.addBeforeRule(new InitialEstCodeReturnRule());
    // 回写上游单据
    processer.addAfterRule(new WriteBackSourceRule(this.context));
  }

}
