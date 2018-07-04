package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.PraybillUpdateBP;
import nc.impl.pu.m20.rule.ChkPrayVONotNullRule;
import nc.impl.pu.m20.rule.ReviseNumCheckRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.ApprovingEditCheckRule;
import nc.vo.pu.pub.rule.ApprovingEditSendMsgRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修订
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-22 下午04:13:56
 */
public class PraybillRAction {

  /**
   * 方法功能描述：请购单修订操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午04:14:12
   */
  public PraybillVO[] revise(PraybillVO[] orderVos, PraybillContext ptx) {
    if (ArrayUtils.isEmpty(orderVos)) {
      return null;
    }

    BillTransferTool<PraybillVO> tool =
        new BillTransferTool<PraybillVO>(orderVos);
    PraybillVO[] voOrginal = tool.getOriginBills();

    // 执行最新版的采购订单保存
    PraybillVO[] voRevised = this.saveNewVO(orderVos, voOrginal, ptx);

    // 设置历史记录
    this.setOrignialVO(voOrginal);

    // 执行历史订单保存
    this.saveHistoryVO(voOrginal);

    return voRevised;

  }

  /**
   * 方法功能描述：设置旧VO的值，使其变成历史记录。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voOrginal
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午04:14:32
   */
  public void setOrignialVO(PraybillVO[] voOrginal) {
    if (ArrayUtils.isEmpty(voOrginal)) {
      return;
    }

    for (int i = 0; i < voOrginal.length; ++i) {
      // 头
      PraybillHeaderVO voHead = voOrginal[i].getHVO();
      String pk_praybill = voHead.getPk_praybill();
      voHead.setStatus(VOStatus.NEW);
      voHead.setPk_praybill(null);
      voHead.setBislatest(UFBoolean.FALSE);
      voHead.setTs(null);
      voHead.setPk_srcpraybill(pk_praybill);

      // 体
      PraybillItemVO[] voItems = voOrginal[i].getBVO();
      for (int j = 0; j < voItems.length; ++j) {
        voItems[j].setStatus(VOStatus.NEW);
        voItems[j].setPk_praybill_b(null);
        voItems[j].setPk_praybill(null);
        voItems[j].setTs(null);
      }
    }
  }

  /**
   * 方法功能描述：保存后规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午04:15:52
   */
  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer,
      PraybillContext ptx) {
    // 修订数量合法性检查，修订后的数量与原数量同正负，且不能小于后续数量
    processer.addAfterRule(new ReviseNumCheckRule(ptx
        .getPrayNumToleranceConfirm()));
  }

  /**
   * 方法功能描述： 保存前规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午04:16:02
   */
  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {

    // 参数合法性检查
    processer.addBeforeRule(new ChkPrayVONotNullRule());

    // 支持审批中修改（修订）的单据，检查是否应该由当前审批人修改（修订）
    processer.addBeforeFinalRule(new ApprovingEditCheckRule<PraybillVO>(
        POBillType.PrayBill));

    // 支持审批中修改（修订）的单据，后台向所有已经处理过审批任务的人发送消息
    processer.addBeforeFinalRule(new ApprovingEditSendMsgRule<PraybillVO>());

  }

  /**
   * 方法功能描述：历史请购单保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voOrginal
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午04:16:38
   */
  private void saveHistoryVO(PraybillVO[] voOrginal) {
    if (!ArrayUtils.isEmpty(voOrginal)) {
      new BillInsert<PraybillVO>().insert(voOrginal);
    }
  }

  /**
   * 方法功能描述：最新版的请购单保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voSaved
   * @param voOrginal
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午04:16:14
   */
  private PraybillVO[] saveNewVO(PraybillVO[] voSaved, PraybillVO[] voOrginal,
      PraybillContext ptx) {
    if (ArrayUtils.isEmpty(voSaved)) {
      return null;
    }

    // 规则
    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(null);
    this.addBeforeRule(processer);
    this.addAfterRule(processer, ptx);

    PraybillVO[] vos = voSaved;
    // 执行最新版的采购订单保存
    processer.before(vos, voOrginal);
    vos = new PraybillUpdateBP().update(vos, voOrginal, true);

    processer.after(vos, voOrginal);

    return vos;
  }

}
