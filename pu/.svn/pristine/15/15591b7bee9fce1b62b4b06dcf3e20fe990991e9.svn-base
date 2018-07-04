package nc.impl.pu.costfactor.action;

import nc.bs.pu.costfactor.maintain.DeleteBP;
import nc.impl.pu.costfactor.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 成本要素定义删除动作
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
 * @time 2010-1-9 下午03:07:40
 */

public class DeleteAction {
  /**
   * 方法功能描述：成本要素定义删除动作入口方法。
   * <p>
   * <b>参数说明</b>
   *
   * @param bills
   */
  public void delete(CostfactorVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<CostfactorVO> transferTool =
        new BillTransferTool<CostfactorVO>(bills);
    CostfactorVO[] clientBills = transferTool.getClientFullInfoBill();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0093")/*@res "补全前台VO、加锁、检查时间戳"*/);

    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(ActionPlugInPoint.DeleteAction);

    TimeLog.logStart();
    processer.before(clientBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0094")/*@res "调用删除BP前执行业务规则"*/);

    TimeLog.logStart();
    DeleteBP action = new DeleteBP();
    action.delete(clientBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0095")/*@res "调用删除BP，进行删除"*/);

    TimeLog.logStart();
    processer.after(clientBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0096")/*@res "调用删除BP后执行业务规则"*/);

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0097")/*@res "业务日志"*/);
  }
}