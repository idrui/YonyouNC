package nc.bs.pu.position.maintain;

import nc.bs.pu.position.maintain.rule.DelTRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 计划岗(采购岗)物料设置删除BP
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划岗(采购岗)物料设置删除
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
   * DeleteBP 的构造子
   */
  public DeleteBP() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：计划岗(采购岗)物料设置删除。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 下午02:52:23
   */
  public void delete(PositionVO[] bills) {
    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    // 增加执行前业务规则
    this.addBeforeRule();

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("删除前执行业务规则");/* -=notranslate=- */

    TimeLog.logStart();
    BillDelete<PositionVO> bo = new BillDelete<PositionVO>();
    bo.delete(bills);
    TimeLog.info("写数据库，删除单据");/* -=notranslate=- */

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("删除后执行业务规则");/* -=notranslate=- */
  }

  /**
   * 方法功能描述：更新后规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午01:35:56
   */
  private void addAfterRule(AroundProcesser<PositionVO> processer) {

    processer.addBeforeRule(new DelTRule());
  }

  /**
   * 方法功能描述：更新前规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午01:35:56
   */
  private void addBeforeRule() {
    // 没有规则
  }
}
