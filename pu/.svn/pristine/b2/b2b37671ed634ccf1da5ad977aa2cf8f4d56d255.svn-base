/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-9 下午01:32:14
 */
package nc.bs.pu.position.maintain;

import nc.bs.pu.position.maintain.rule.BodyEmptyRule;
import nc.bs.pu.position.maintain.rule.CheckCodeRule;
import nc.bs.pu.position.maintain.rule.CheckItemRule;
import nc.bs.pu.position.maintain.rule.CheckUniqueRule;
import nc.bs.pu.position.maintain.rule.FillDataRule;
import nc.bs.pu.position.maintain.rule.InsertTRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 计划岗(采购岗)物料设置新增保存BP
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划岗(采购岗)物料设置新增保存
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
   * CostFactorInsertBP 的构造子
   */
  public InsertBP() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：计划岗(采购岗)物料设置新增保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          计划岗(采购岗)物料设置
   * @return 保存后的计划岗(采购岗)物料设置
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 下午01:35:22
   */
  public PositionVO[] insert(PositionVO[] bills) {
    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("保存前执行业务规则");/* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<PositionVO> bo = new BillInsert<PositionVO>();
    PositionVO[] vos = bo.insert(bills);
    TimeLog.info("保存单据到数据库");/* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("保存后执行业务规则");/* -=notranslate=- */

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
    processer.addBeforeRule(new CheckCodeRule());
    // 表体行唯一性检查
    processer.addBeforeRule(new CheckItemRule());

  }
}
