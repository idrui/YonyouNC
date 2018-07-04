/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午04:55:41
 */
package nc.bs.pu.m20.maintain.rule.delete;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumBillStatue;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              检查请购单状态,如果是下列之一，不能作废: 关闭、审批中、审批通过
 * @scene
 *        请购单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:17:57
 * @author yanxm5
 */
public class BillStatusRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {

    // 检查请购单状态,如果是下列之一，不能作废: 关闭、审批中、审批通过
    this.check(voArray);

  }

  /**
   * 方法功能描述：检查请购单状态,如果是下列之一，不能作废: 关闭、审批中、审批通过
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-3-29 下午09:44:46
   */
  private void check(PraybillVO[] vos) {
    StringBuffer sMessage = new StringBuffer();
    int nBillStatus;
    for (PraybillVO vo : vos) {

      nBillStatus = vo.getHVO().getFbillstatus().intValue();
      String sbillCode = vo.getHVO().getVbillcode();
      if (nBillStatus == EnumBillStatue.CLOSE.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004020_0",
            "04004020-0089", null, new String[] {
              sbillCode
            })/* {0}号请购单已经关闭， 不能作废 ！\n */);
      }
      else if (nBillStatus == POEnumBillStatus.APPROVING.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004020_0",
            "04004020-0090", null, new String[] {
              sbillCode
            })/* {0}号请购单审批中， 不能作废 ！\n */);
      }
      else if (nBillStatus == POEnumBillStatus.APPROVE.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004020_0",
            "04004020-0098", null, new String[] {
              sbillCode
            })/* {0}号请购单已经审批， 不能作废 ！\n */);
      }
    }
    if (sMessage.length() > 0) {
      ExceptionUtils.wrappBusinessException(sMessage.toString());
    }
  }
}
