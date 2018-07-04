package nc.impl.pu.position.action;

import nc.bs.pu.position.maintain.DeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 计划岗(采购岗)物料设置删除动作
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
 * @time 2010-1-9 下午03:15:40
 */
public class DeleteAction {
  /**
   * 方法功能描述：计划岗(采购岗)物料设置删除动作入口方法。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   */
  public void delete(PositionVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PositionVO> transferTool =
        new BillTransferTool<PositionVO>(bills);
    PositionVO[] delbills = transferTool.getClientFullInfoBill();
    TimeLog.info("补全前台VO、加锁、检查时间戳"); /* -=notranslate=- */

    AroundProcesser<PositionVO> processer =
        new AroundProcesser<PositionVO>(null);

    TimeLog.logStart();
    processer.before(delbills);
    TimeLog.info("调用删除BP前执行业务规则"); /* -=notranslate=- */

    TimeLog.logStart();
    DeleteBP action = new DeleteBP();
    action.delete(delbills);
    TimeLog.info("调用删除BP，进行删除"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(delbills);
    TimeLog.info("调用删除BP后执行业务规则"); /* -=notranslate=- */

    TimeLog.logStart();

    TimeLog.info("业务日志"); /* -=notranslate=- */
  }
}
