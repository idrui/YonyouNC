/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午11:02:22
 */
package nc.pubimpl.pu.m20.aim.action;

import nc.bs.pu.m20.maintain.PraybillWriteBackSourceRule;
import nc.bs.pu.m20.maintain.rule.ATPAfterUpdateRule;
import nc.bs.pu.m20.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m20.maintain.rule.ReturnBillCodeRule;
import nc.bs.pu.m20.maintain.rule.delete.BillStatusRule;
import nc.bs.pu.m20.maintain.rule.delete.DelOldVersionRule;
import nc.bs.pu.m20.maintain.rule.delete.DeleteAfterEventRule;
import nc.bs.pu.m20.maintain.rule.delete.DeleteBeforeEventRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.pub.rule.WriteBack422xRule;
import nc.vo.pu.pub.rule.WriteBackM4A08Rule;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>资产配置申请弃审删除请购单BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午11:02:22
 */
public class PraybillDeleteFor4A08BP {

  public void delete(PraybillVO[] vos) {

    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(PraybillPluginPoint.DELETE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(vos, vos);
    BillDelete<PraybillVO> bo = new BillDelete<PraybillVO>();
    bo.delete(vos);

    processer.after(vos, vos);

  }

  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer) {
    // 删除老版本
    processer.addAfterRule(new DelOldVersionRule());

    // 回写上游
    processer.addAfterRule(new PraybillWriteBackSourceRule(EnumOperate.DELETE));
    // 删除后可用量处理
    processer.addAfterRule(new ATPAfterUpdateRule());
    // 退回单据号
    processer.addAfterRule(new ReturnBillCodeRule());
    // 写业务日志
    // processer.addAfterRule(new WriteBusiLogRule<PraybillVO>(
    // PuBusiLogActionCode.delete));

    // 请购订单删除后调用，为了调出请购订单的ATP序列
    // processer.addAfterRule(new Update55A3StatusRule());
    processer.addAfterRule(new WriteBackPIMRule<PraybillVO>(POBillType.PrayBill
        .getCode()));

    // 回写资产配置申请标志位
    processer.addAfterRule(new WriteBackM4A08Rule());

    processer.addAfterRule(new WriteBack422xRule<PraybillVO>(
        POBillType.PrayBill.getCode()));

    // 触发删除后事件
    processer.addAfterRule(new DeleteAfterEventRule());
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // 单据状态检查
    processer.addBeforeFinalRule(new BillStatusRule());

    // 删除前可用量处理
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    // 触发删除前事件
    processer.addBeforeRule(new DeleteBeforeEventRule());
    // 制造计划定单取消下达
    // processer.addBeforeRule(new RearOrderDelete());
  }
}
