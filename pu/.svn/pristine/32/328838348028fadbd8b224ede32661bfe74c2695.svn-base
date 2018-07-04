/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 上午07:17:36
 */
package nc.impl.pu.m422x.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            物资需求申请单取消申请时，单据状态检查
 * @scene
 *      物资需求申请单取消申请
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 上午07:17:36
 * @author wuxla
 */
public class UnApproveValidateRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    this.statusCheck(vos);
  }

  private void statusCheck(StoreReqAppVO[] vos) {
    for (StoreReqAppVO vo : vos) {
      // 状态是自由态的单据不能取消审批也不能收回
      if (vo.getHVO().getFbillstatus().equals(POEnumBillStatus.FREE.value())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0010")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能操作！"
                                                                     */);
      }

      // 整单关闭则不能弃审
      if (vo.getHVO().getFbillstatus().equals(EnumBillStatus.CLOSE.toInteger())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0158")/*
                                                                     * @res
                                                                     * "单据已最终关闭，不能操作！\n"
                                                                     */);
      }

      // 手工取消时来源于维修计划的单据不能取消审批
      // 维修计划点击弃审时不控制此校验
//      if (!ForeignForPUConstant.M4B32.equals(StorereqContext.getInstance()
//          .getSession("SrcAction"))) {
//        StoreReqAppItemVO[] items = vo.getBVO();
//        if (items.length > 0
//            && items[0].getCsourcetypecode() != null
//            && items[0].getCsourcetypecode().equalsIgnoreCase(
//                PuRefBillTypeIdEnum.M4B32.getBillTypeId())) {
//          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
//              .getNCLangRes().getStrByID("4004010_0", "04004010-0030")/*
//                                                                       * @res
//                                                                       * "来源维修计划的物资需求申请单不允许弃审！\n"
//                                                                       */);
//        }
//      }
//      else {
//        StorereqContext.getInstance().setSession("SrcAction", null);
//      }

      for (StoreReqAppItemVO itemVo : vo.getBVO()) {
        // 已平衡的单据不能取消审批
        if(itemVo.getBendgather().booleanValue()){
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004010_0", "04004010-0036")/*
                                                                       * @res
                                                                       * "已平衡的单据不能取消审批！"
                                                                       */);
        }
        if (MathTool.greaterThan(itemVo.getNaccumbuyreqnum(), UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNaccuoutnum(), UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNaccuoutreqnum(),
                UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNaccumbuyreqnum(),
                UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNnetnum(), UFDouble.ZERO_DBL)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004010_0", "04004010-0025")/*
                                                                       * @res
                                                                       * "已生成下游单据，不能操作！"
                                                                       */);
          }
        // 有一行关闭则不能弃审
        if (itemVo.getBclose().booleanValue()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0157")/*
                                                                       * @res
                                                                       * "单据存在关闭的行，不能操作！\n"
                                                                       */);
        }

      }
    }
  }
}
