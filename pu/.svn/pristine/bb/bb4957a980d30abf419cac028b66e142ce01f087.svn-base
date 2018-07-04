package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pub.power.BillPowerChecker;

/**
 * @description
 *              单据审批者权限
 * @scene
 *        物资需求申请单、到货单、请购单和采购订单弃审
 * @param String billtypecode 单据类型编码
 * @since 6.3
 * @version 2014-10-21 上午10:28:35
 * @author yanxm5
 */

public class ApproverPermissionRule<E extends AbstractBill> implements IRule<E> {
  private String billtypecode;

  private String vbillcode = "vbillcode";

  public ApproverPermissionRule(String billtypecode) {
    this.billtypecode = billtypecode;
  }

  public ApproverPermissionRule(String billtypecode, String vbillcode) {
    this.billtypecode = billtypecode;
    this.vbillcode = vbillcode;
  }

  @Override
  public void process(E[] vos) {
    for (E vo : vos) {
      if (!BillPowerChecker.hasApproverPermission(vo, this.billtypecode)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0124", null, new String[] {
              (String) vo.getParent().getAttributeValue(this.vbillcode)
            })/* 当前用户对单据不具有审批者权限。单据号：{0} */);
      }
    }
  }
}
