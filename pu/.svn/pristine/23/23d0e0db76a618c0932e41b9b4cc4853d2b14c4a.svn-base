package nc.bs.pu.costfactor.maintain;

import nc.bs.pu.costfactor.maintain.rule.BodyEmptyRule;
import nc.bs.pu.costfactor.maintain.rule.CheckMaterialRule;
import nc.bs.pu.costfactor.maintain.rule.FeeShowOrderSetRule;
import nc.bs.pu.costfactor.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 成本要素定义保存BP
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素定义保存
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午01:32:14
 */
public class UpdateBP {

  /**
   * 方法功能描述：成本要素定义保存。
   * <p>
   * <b>参数说明</b>
   *
   * @param bills 需要保存的成本要素定义
   * @param originBills 修改前的成本要素定义
   * @return 保存后的成本要素定义
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 下午01:35:22
   */
  public CostfactorVO[] update(CostfactorVO[] bills, CostfactorVO[] originBills) {
    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(BPPlugInPoint.UpdateBP);

    // 增加执行前业务规则
    this.addRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0059")/*@res "修改保存前执行业务规则"*/);

    TimeLog.logStart();
    BillUpdate<CostfactorVO> bo = new BillUpdate<CostfactorVO>();
    CostfactorVO[] vos = bo.update(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0060")/*@res "保存修改单据到数据库"*/);

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0061")/*@res "修改保存后执行业务规则"*/);

    return vos;
  }

  private void addRule(AroundProcesser<CostfactorVO> processer) {
    // 费用检查规则
    processer.addBeforeRule(new BodyEmptyRule());
    // 检查费用项物料的重复
    processer.addBeforeRule(new CheckMaterialRule());
    // 设置费用项的默认显示值（暂估是带入界面的值）
    processer.addAfterFinalRule(new FeeShowOrderSetRule());

  }
}