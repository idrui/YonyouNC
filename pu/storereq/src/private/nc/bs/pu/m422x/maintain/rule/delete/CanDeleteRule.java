package nc.bs.pu.m422x.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            物资需求申请单删除时，判断是否可以删除
 * @scene
 *      物资需求申请单删除
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 上午07:27:24
 * @author wuxla
 */
public class CanDeleteRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    this.canDelete(vos);
  }

  private void canDelete(StoreReqAppVO[] vos) {
    for (StoreReqAppVO vo : vos) {
      this.checkCanDelete(vo);
    }
  }

  private void checkCanDelete(StoreReqAppVO vo) {
    String code = "[" + vo.getHVO().getVbillcode() + "]";
    if (vo.getHVO().getFbillstatus().equals(POEnumBillStatus.APPROVING.value())) {
      ExceptionUtils.wrappBusinessException(code + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0","04004010-0007")/*@res "单据审批中,不允许删除"*/);
    }

    if (vo.getHVO().getFbillstatus().equals(POEnumBillStatus.APPROVE.value())) {
      ExceptionUtils.wrappBusinessException(code + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0","04004010-0008")/*@res "单据已审批,不允许删除"*/);
    }
  }

}