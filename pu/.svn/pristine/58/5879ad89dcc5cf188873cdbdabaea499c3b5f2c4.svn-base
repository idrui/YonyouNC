package nc.bs.pu.costfactor.maintain;

import nc.bs.pu.costfactor.maintain.rule.CheckEnableDeleteRule;
import nc.bs.pu.costfactor.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 成本要素定义删除BP
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素定义删除
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午02:57:58
 */
public class DeleteBP {

  /**
   * 方法功能描述：成本要素定义删除。
   * <p>
   * <b>参数说明</b>
   *
   * @param bills
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 下午02:52:23
   */
  public void delete(CostfactorVO[] bills) {
    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(BPPlugInPoint.DeleteBP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0053")/*@res "删除前执行业务规则"*/);

    TimeLog.logStart();
    BillDelete<CostfactorVO> bo = new BillDelete<CostfactorVO>();
    bo.delete(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0054")/*@res "写数据库，删除单据"*/);

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0055")/*@res "删除后执行业务规则"*/);
  }

  private void addBeforeRule(AroundProcesser<CostfactorVO> processer) {
    // 检查成本要素定义当前状态是否可被删除
    IRule<CostfactorVO> rule = new CheckEnableDeleteRule();
    processer.addBeforeRule(rule);
  }
}