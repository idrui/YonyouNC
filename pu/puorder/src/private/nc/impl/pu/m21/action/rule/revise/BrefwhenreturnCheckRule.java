package nc.impl.pu.m21.action.rule.revise;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.DownFlowCheck;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              判断采购订单是否有后续单据，如有后续单据不允许修改退货/库基于原订单补货字段
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:19:20
 * @author luojw
 */
public class BrefwhenreturnCheckRule implements ICompareRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    DownFlowCheck downFlow = new DownFlowCheck();

    for (int i = 0; i < vos.length; ++i) {
      if (null == vos[i] || null == originVOs[i]) {
        continue;
      }

      if (!ObjectUtils.equals(vos[i].getHVO().getBrefwhenreturn(), originVOs[i]
          .getHVO().getBrefwhenreturn())) {
        // 在途，到货，入库，发票
        boolean hasDownFlow = downFlow.hasDownFlow(vos[i]);
        if (hasDownFlow) {
          sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
              "04004030-0283", null, new String[] {
                vos[i].getHVO().getVbillcode()
              })/* 订单{0}\n */);
        }
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0284", null, new String[] {
            sb.toString()
          })/* {0}存在后续单据，不能修改退货/库基于原订单补货字段 */);
    }
  }
}
