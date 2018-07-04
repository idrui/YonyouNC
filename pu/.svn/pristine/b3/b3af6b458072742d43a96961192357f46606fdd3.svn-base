/**
 * $文件说明$
 *
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-9 下午01:32:14
 */
package nc.bs.pu.costfactor.maintain;

import nc.bs.pu.costfactor.maintain.rule.CheckEmpty;
import nc.bs.pu.costfactor.maintain.rule.CheckMaterialRule;
import nc.bs.pu.costfactor.maintain.rule.ConcurrCtrlRule;
import nc.bs.pu.costfactor.maintain.rule.FeeShowOrderSetRule;
import nc.bs.pu.costfactor.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 成本要素定义新增保存BP
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素定义新增保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午01:32:14
 */
public class InsertBP {

  /**
   * 方法功能描述：成本要素定义新增保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills 成本要素定义
   * @return 保存后的成本要素定义
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 下午01:35:22
   */
  public CostfactorVO[] insert(CostfactorVO[] bills) {
    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(BPPlugInPoint.InsertBP);

    // 增加执行前业务规则
    this.addRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0056")/* @res "保存前执行业务规则" */);

    TimeLog.logStart();
    BillInsert<CostfactorVO> bo = new BillInsert<CostfactorVO>();
    CostfactorVO[] vos = bo.insert(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0057")/* @res "保存单据到数据库" */);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0058")/* @res "保存后执行业务规则" */);

    return vos;
  }

  private void addRule(AroundProcesser<CostfactorVO> processer) {
    // 成本要素定义变更前操作
    // processer.addBeforeRule(new BodyEmptyRule());
    // 成本要素后台非空校验
    processer.addBeforeRule(new CheckEmpty());
    // 并发控制
    processer.addBeforeFinalRule(new ConcurrCtrlRule());
    // 检查费用项物料的重复
    processer.addBeforeRule(new CheckMaterialRule());
    // 设置费用项的默认显示值（暂估是带入界面的值）
    processer.addAfterFinalRule(new FeeShowOrderSetRule());

  }
}
