package nc.vo.pu.pub.rule.pf;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @description
 *              提交时控制单据状态是否正确。
 *              主要是在批量提交时，如果多选，提交按钮可用，如果有的单据已经审批，会重新置为审批中。在港华项目问题中发现的。
 *              需要加上校验。
 * @scene
 * @param 无
 * @since 6.3
 * @version 2014-2-13 上午09:21:40
 * @author wuxla
 */
public class SendApproveStatusChkRule<E extends AbstractBill> implements
    IRule<E> {

  private String fbillstatus = "fbillstatus";
  
  private String billmaker = "billmaker";

  public SendApproveStatusChkRule() {
  }

  public SendApproveStatusChkRule(String fbillstatus) {
    this.fbillstatus = fbillstatus;
  }

  @Override
  public void process(E[] vos) {
    String userid = AppContext.getInstance().getPkUser();
    for (E vo : vos) {
      if(!vo.getParent().getAttributeValue(this.billmaker).equals(userid)){
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0158")/*
             * @res
             * "当前用户不是制单人，不允许提交！"
             */);
      }
      // 状态是自由的才能提交
      if (!POEnumBillStatus.FREE.toInteger().equals(
          vo.getParent().getAttributeValue(this.fbillstatus))) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID(
                "4004000_0",
                "04004000-0153",
                null,
                new String[] {
                  (String) vo.getParent().getAttributeValue(
                      PuAttrNameEnum.vbillcode.name())
                })/* 单据{0}的状态不正确，不能提交！ */);
      }

    }
  }
}
