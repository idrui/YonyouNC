package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.exception.AskNotMeetCntCenPurRuleException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmf.pu.cenpurule.entity.ControlMode;
import nc.vo.scmf.pu.cenpurule.entity.ControlType;

/**
 * 动作约束-是否满足集采合同
 * 
 * @since 6.0
 * @version 2012-7-23 下午04:26:23
 * @author liuyand
 */
public class MeetCenPurCnt extends CenPurRuleFunction {
  public UFBoolean meetCenPurCnt(AggregatedValueObject vo, Object obj)
      throws BusinessException {
    if (null != obj) {
      OrderContext context =
          (OrderContext) ((PfUserObject) obj).getUserObject();
      if (null != context
          && context.getMeetcntcenpurruleconfirm().booleanValue()) {
        return UFBoolean.TRUE;
      }
    }
    OrderVO ordervo = (OrderVO) vo;
    this.setCtrltype(ControlType.CONTRACT.toInt());
    return this.meetCenPuRule(ordervo);
  }

  @Override
  protected UFBoolean handWhenNeedNotEequalsAct(Integer fctrlmode, String err)
      throws BusinessException {
    if (fctrlmode != null && ControlMode.HINT.toInt() == fctrlmode.intValue()) {
      throw new AskNotMeetCntCenPurRuleException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0357", null,
              new String[] {
                err
              })/*
                 * @res
                 * "单据未满足集采合同控制，是否继续？"
                 */);
    }
    throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004030_0", "04004030-0359", null, new String[] {
          err
        })/*
           * @res
           * "单据未满足集采合同控制，是否继续？"
           */);
  }

}
