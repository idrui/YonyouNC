package nc.impl.pu.position.action;

import nc.bs.pu.position.maintain.InsertBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 计划岗(采购岗)物料设置新增保存动作
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
 * @time 2010-1-9 下午03:16:08
 */
public class InsertAction {
  /**
   * 方法功能描述：计划岗(采购岗)物料设置新增动作入口方法。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          需要新增保存的计划岗(采购岗)物料设置数组
   * @return 保存后的计划岗(采购岗)物料设置数组，轻量级VO数组
   */
  public PositionVO[] insert(PositionVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PositionVO> transferTool =
        new BillTransferTool<PositionVO>(bills);
    TimeLog.info("保存前台VO，组织返回值时使用"); /* -=notranslate=- */

    TimeLog.logStart();
    TimeLog.info("检查时间戳、锁来源"); /* -=notranslate=- */

    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("调用新增保存BP前执行业务规则"); /* -=notranslate=- */

    TimeLog.logStart();
    InsertBP bp = new InsertBP();
    PositionVO[] vos = bp.insert(bills);
    TimeLog.info("调用新增保存BP，进行保存"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("调用新增保存BP后执行业务规则"); /* -=notranslate=- */

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("组织返回值,返回轻量级VO"); /* -=notranslate=- */

    TimeLog.logStart();
    TimeLog.info("业务日志"); /* -=notranslate=- */
    return vos;
  }

}
