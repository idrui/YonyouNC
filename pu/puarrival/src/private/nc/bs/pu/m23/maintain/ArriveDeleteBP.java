package nc.bs.pu.m23.maintain;

import nc.bs.pu.m23.maintain.rule.InsertAndDelBatchCodeBeforeRule;
import nc.bs.pu.m23.maintain.rule.WriteBackFor23Rule;
import nc.bs.pu.m23.maintain.rule.delete.ChkCanDeleteRule;
import nc.bs.pu.m23.maintain.rule.delete.DeleteAfterEventRule;
import nc.bs.pu.m23.maintain.rule.delete.DeleteBatchCodeAfterRule;
import nc.bs.pu.m23.maintain.rule.delete.DeleteBeforeEventRule;
import nc.bs.pu.m23.maintain.rule.delete.DeleteBillCodeRule;
import nc.bs.pu.m23.maintain.rule.delete.Write21WhenDeleteRule;
import nc.bs.pu.m23.maintain.rule.delete.Write61WhenDeleteRule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;
import nc.vo.pu.pub.enumeration.EnumOperate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>完成删除到货单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午10:41:56
 */
public class ArriveDeleteBP {
  private ArrivalUIToBSEnv env;

  public ArriveDeleteBP(ArrivalUIToBSEnv env) {
    this.env = env;
  }

  /**
   * 方法功能描述：完成删除到货单
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO数组
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午10:18:08
   */
  public void deleteArrive(ArriveVO[] aggVOArray) {
    CompareAroundProcesser<ArriveVO> processer =
        new CompareAroundProcesser<ArriveVO>(ArriveBPPlugInPoint.ArriveDeleteBP);

    // 增加执行规则
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(aggVOArray, null);
    BillDelete<ArriveVO> bo = new BillDelete<ArriveVO>();
    bo.delete(aggVOArray);
    processer.after(aggVOArray, null);
  }

  private void addAfterRule(CompareAroundProcesser<ArriveVO> processer) {
    // 删除后批次号处理
    processer.addAfterRule(new DeleteBatchCodeAfterRule());
    // 回写来源到货单的累计退货数量。
    processer.addAfterRule(new WriteBackFor23Rule(EnumOperate.DELETE));
    // 回写采购订单的累计到货数量、途耗数量
    processer.addAfterRule(new Write21WhenDeleteRule(this.env));
    // 回写委外订单的累计到货数量、途耗数量
    processer.addAfterRule(new Write61WhenDeleteRule(this.env));
    // 单据号退回处理
    processer.addAfterRule(new DeleteBillCodeRule());
    // 可用量变更后操作
    processer.addAfterRule(new ArriveATPUpdateRule(false));
    // 删除后事件
    processer.addAfterRule(new DeleteAfterEventRule());
  }

  private void addBeforeRule(CompareAroundProcesser<ArriveVO> processer) {
    // 检查到货单是否可被删除
    processer.addBeforeRule(new ChkCanDeleteRule());
    // 删除保存前批次号处理
    processer.addBeforeRule(new InsertAndDelBatchCodeBeforeRule());
    // 可用量变更前操作
    processer.addBeforeRule(new ArriveATPUpdateRule(true));
    // 删除前事件
    processer.addBeforeRule(new DeleteBeforeEventRule());
  }
}
