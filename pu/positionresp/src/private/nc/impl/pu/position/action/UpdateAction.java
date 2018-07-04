package nc.impl.pu.position.action;

import nc.bs.pu.position.maintain.UpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 计划岗(采购岗)物料设置修改保存动作。
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划岗(采购岗)物料设置修改保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午03:16:25
 */
public class UpdateAction {
  public UpdateAction() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：计划岗(采购岗)物料设置修改保存动作入口方法。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          需要保存的计划岗(采购岗)物料设置数组
   * @return 保存后的计划岗(采购岗)物料设置数组，轻量级VO
   */
  public PositionVO[] update(PositionVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PositionVO> transferTool =
        new BillTransferTool<PositionVO>(bills);
    PositionVO[] upbills = transferTool.getClientFullInfoBill();
    PositionVO[] originBills = transferTool.getOriginBills();
    TimeLog.info("补全前台VO、获得修改前VO、加锁、检查时间戳");/* -=notranslate=- */

    TimeLog.logStart();

    TimeLog.info("检查时间戳、锁来源");/* -=notranslate=- */

    CompareAroundProcesser<PositionVO> compareProcesser =
        new CompareAroundProcesser<PositionVO>(null);

    TimeLog.logStart();
    compareProcesser.before(upbills, originBills);
    TimeLog.info("调用更新保存BP前执行业务规则");/* -=notranslate=- */

    UpdateBP action = new UpdateBP();
    PositionVO[] ret = action.update(upbills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info("调用更新保存BP后执行业务规则");/* -=notranslate=- */

    TimeLog.logStart();
    ret = transferTool.getBillForToClient(ret);
    TimeLog.info("组织返回值,返回轻量级VO");/* -=notranslate=- */

    TimeLog.logStart();
    TimeLog.info("业务日志");/* -=notranslate=- */

    return ret;
  }

}
