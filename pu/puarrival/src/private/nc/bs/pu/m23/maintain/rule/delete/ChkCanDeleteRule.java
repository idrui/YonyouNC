package nc.bs.pu.m23.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 检查到货单是否可被删除，已审核、送审的不允许删除
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class ChkCanDeleteRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {

    for (ArriveVO aggVO : voArray) {
      // 检查到货单是否可被删除
      this.chkCanDelete(aggVO);
    }
  }

  private void chkCanDelete(ArriveVO aggVO) {
    // 已送审，不可以删除
    Integer state = aggVO.getHVO().getFbillstatus();
    if (POEnumBillStatus.APPROVING.value().equals(state)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0081")/*@res "到货单审批中，不可以删除！"*/);
    }

    // 已审核，不可以删除
    if (POEnumBillStatus.APPROVE.value().equals(state)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0082")/*@res "到货单已审核，不可以删除！"*/);
    }
  }
}