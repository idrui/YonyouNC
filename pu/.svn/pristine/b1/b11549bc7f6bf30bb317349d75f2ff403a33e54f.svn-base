package nc.bs.pu.position.maintain;

import nc.bs.pu.position.maintain.rule.BodyEmptyRule;
import nc.bs.pu.position.maintain.rule.CheckCodeRule;
import nc.bs.pu.position.maintain.rule.CheckItemRule;
import nc.bs.pu.position.maintain.rule.CheckUniqueRule;
import nc.bs.pu.position.maintain.rule.FillDataRule;
import nc.bs.pu.position.maintain.rule.InsertTRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 计划岗(采购岗)物料设置保存BP
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划岗(采购岗)物料设置保存
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
   * UpdateBP 的构造子
   */
  public UpdateBP() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：计划岗(采购岗)物料设置保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          需要保存的计划岗(采购岗)物料设置
   * @param originBills
   *          修改前的计划岗(采购岗)物料设置
   * @return 保存后的计划岗(采购岗)物料设置
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 下午01:35:22
   */
  public PositionVO[] update(PositionVO[] bills, PositionVO[] originBills) {
    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("修改保存前执行业务规则");/* -=notranslate=- */

    TimeLog.logStart();
    BillUpdate<PositionVO> bo = new BillUpdate<PositionVO>();
    PositionVO[] vos = bo.update(bills, originBills);
    TimeLog.info("保存修改单据到数据库");/* -=notranslate=- */

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("修改保存后执行业务规则");/* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(AroundProcesser<PositionVO> processer) {
    // 计划岗(采购岗)物料设置变更后操作
    // 插入附表
    processer.addAfterRule(new InsertTRule());

    // 业务唯一性检查
    processer.addAfterRule(new CheckUniqueRule());
  }

  private void addBeforeRule(AroundProcesser<PositionVO> processer) {
    // 计划岗(采购岗)物料设置变更前操作
    // 表头表体不能为空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // 补充数据
    processer.addBeforeRule(new FillDataRule());
    // 编码检查规则
    IRule<PositionVO> rule = new CheckCodeRule();
    processer.addBeforeRule(rule);
    // 表体行唯一性检查
    processer.addBeforeRule(new CheckItemRule());
  }
}
