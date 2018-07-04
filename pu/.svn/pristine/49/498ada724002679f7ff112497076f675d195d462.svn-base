/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 下午03:13:01
 */
package nc.bs.pu.m21.writeback.so.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单生成协同销售订单状态检查
 * @scene
 *        协同回写
 * @param boolean flag 如果为false，直接返回不进行回写
 * @since 6.3
 * @version 2014-10-22 下午4:01:33
 * @author luojw
 */
public class CoopStatusCheck implements IRule<OrderHeaderVO> {
  private boolean flag;

  public CoopStatusCheck(boolean flag) {
    this.flag = flag;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderHeaderVO[] vos) {
    if (!this.flag) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderHeaderVO headerVO : vos) {
      if (!POEnumBillStatus.APPROVE.value().equals(headerVO.getForderstatus())) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0262", null, new String[] {
              headerVO.getVbillcode()
            })/* 订单{0}必须为审核状态\n */);
      }

      if (UFBoolean.TRUE.equals(headerVO.getBcooptoso())) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0263", null, new String[] {
              headerVO.getVbillcode()
            })/* 订单{0}已经协同生成销售订单，不能再生成销售订单\n */);
      }

      if (UFBoolean.TRUE.equals(headerVO.getBsocooptome())) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0264", null, new String[] {
              headerVO.getVbillcode()
            })/* 订单{0}由销售订单协同生成，不能再生成销售订单\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }
}
