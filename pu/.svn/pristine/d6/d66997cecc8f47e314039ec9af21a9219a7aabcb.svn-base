package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @description
 *              借入转采购设置规则
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:50:43
 * @author luojw
 */

public class BorrowpurSetterRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      for (OrderItemVO itemVO : itemVOs) {
        String csourcetypecode = itemVO.getCsourcetypecode();
        if (null == csourcetypecode) {
          continue;
        }
        if (ICBillType.BorrowIn.getCode().equals(csourcetypecode)) {
          itemVO.setBborrowpur(UFBoolean.TRUE);
        }
      }
    }
  }

}
