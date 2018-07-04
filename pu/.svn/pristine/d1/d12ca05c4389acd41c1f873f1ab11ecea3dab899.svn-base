package nc.bs.pu.m24.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              价格结算单删除时状态检查
 * @scene
 *        价格结算单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:17:25
 * @author luojw
 */
public class BillStatusRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] voArray) {

    // 检查请购单状态,如果是下列之一，不能作废: 审批中、审批通过
    this.check(voArray);

  }

  /**
   * 方法功能描述：检查请购单状态,如果是下列之一，不能作废: 审批中、审批通过
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-8-8 下午09:44:46
   */
  private void check(PricestlVO[] vos) {
    StringBuffer sMessage = new StringBuffer();
    int nBillStatus;
    for (PricestlVO vo : vos) {

      nBillStatus = vo.getHVO().getFbillstatus().intValue();
      if (nBillStatus == POEnumBillStatus.APPROVING.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004070_0",
            "04004070-0011", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* {0} 号价格结算单审批中， 不能作废 ！\n */);
      }
      else if (nBillStatus == POEnumBillStatus.APPROVE.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004070_0",
            "04004070-0012", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* {0} 号价格结算单已经审批， 不能作废 ！\n */);
      }
    }
    if (sMessage.length() > 0) {
      ExceptionUtils.wrappBusinessException(sMessage.toString());
    }
  }
}
